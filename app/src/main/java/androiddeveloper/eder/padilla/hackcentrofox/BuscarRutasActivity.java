package androiddeveloper.eder.padilla.hackcentrofox;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androiddeveloper.eder.padilla.hackcentrofox.model.BuscarRuta;
import androiddeveloper.eder.padilla.hackcentrofox.model.PublicarRuta;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BuscarRutasActivity extends AppCompatActivity implements OnRouteSelected {

    @BindView(R.id.recView)
    RecyclerView mRecView;

    private RouteAdapters routeAdapters;

    private List<PublicarRuta> publicarRutaList = new ArrayList<>();


    public FirebaseDatabase database;

    private DatabaseReference databaseReference;

    public FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_rutas);
        ButterKnife.bind(this);Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimary));
        }
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        recViewInit();
        obtenerRutas();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        this.finish();
        super.onBackPressed();
    }

    private void recViewInit() {
        mRecView.setLayoutManager(new LinearLayoutManager(BuscarRutasActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecView.setHasFixedSize(true);
        routeAdapters = new RouteAdapters(publicarRutaList,BuscarRutasActivity.this, this);
        mRecView.setAdapter(routeAdapters);
    }

    @Override
    public void onRouteSelected(PublicarRuta publicarRuta) {
        Main2Activity.textToSpeech.speak("¿Deseas solicitar este viaje para algun cargamento?", TextToSpeech.QUEUE_FLUSH, null);
          new MaterialDialog.Builder(BuscarRutasActivity.this)
                .title(R.string.wish_to_buy)
                .positiveText(R.string.accept)
                .negativeText(R.string.cancel)
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.colorPrimaryDark)
                .cancelable(false)
                .onPositive((dialogDonation, which) -> acceptBuy(dialogDonation,publicarRuta))
                .onNegative((dialog, which) -> negative(dialog))
                .show();
    }

    private void acceptBuy(MaterialDialog dialogDonation,PublicarRuta publicarRuta) {
        DatabaseReference referenciaEstadoAlumno = database.getReference(Util.FIREBASE_DB_RUTA).child(publicarRuta.getId()).child("estado");
        referenciaEstadoAlumno.setValue("ocupado").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Main2Activity.textToSpeech.speak("¡Haz reservado esta ruta con exito para el dia "+publicarRuta.getFechaSalida(), TextToSpeech.QUEUE_FLUSH, null);
                new MaterialDialog.Builder(BuscarRutasActivity.this)
                        .title(R.string.good_request)
                        .content("¡Haz reservado esta ruta con exito para el dia "+publicarRuta.getFechaSalida())
                        .positiveText(R.string.accept)
                        .positiveColorRes(R.color.colorPrimary)
                        .cancelable(false)
                        .onPositive((dialogDonation, which) -> end(dialogDonation))
                        .show();
            }
        });


    }

    private void end(MaterialDialog dialogDonation) {
        dialogDonation.dismiss();
        this.finish();
    }

    private void negative(MaterialDialog dialog) {
        dialog.dismiss();
    }

    private void obtenerRutas() {
        DatabaseReference mDatabaseReference= FirebaseDatabase.getInstance().getReference(Util.FIREBASE_DB_RUTA);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Util.log("data snap "+dataSnapshot.toString());
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    String id = child.child("id").getValue().toString();
                    String direccionSalida = child.child("direccionSalida").getValue().toString();
                    String direccionLlegada = child.child("direccionLlegada").getValue().toString();
                    String fechaSalida = child.child("fechaSalida").getValue().toString();
                    String fechaLlegada = child.child("fechaLlegada").getValue().toString();
                    String horaSalida = child.child("horaSalida").getValue().toString();
                    String horaLlegada = child.child("horaLlegada").getValue().toString();
                    String espacio = child.child("espacio").getValue().toString();
                    String costo = child.child("costo").getValue().toString();
                    String estado="";
                    if (child.child("estado").getValue().toString()==null){
                        estado = "";
                    }else{
                        estado = child.child("estado").getValue().toString();
                    }
                    PublicarRuta publicarRuta = new PublicarRuta(id,direccionSalida,direccionLlegada,fechaSalida,fechaLlegada,horaSalida,horaLlegada,espacio,costo,estado);
                    publicarRutaList.add(publicarRuta);
                }
                for (PublicarRuta publicarRuta1 :publicarRutaList){
                    if (publicarRuta1.getEstado().equals("ocupado")){
                        publicarRutaList.remove(publicarRuta1);
                    }
                }
                routeAdapters.notifyDataSetChanged();
                    routeAdapters.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
