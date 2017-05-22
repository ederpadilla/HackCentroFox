package androiddeveloper.eder.padilla.hackcentrofox;

import android.app.DialogFragment;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;

import java.util.HashMap;
import java.util.Map;

import androiddeveloper.eder.padilla.hackcentrofox.model.PublicarRuta;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublicarRutaActivity extends AppCompatActivity {

    @BindView(R.id.autocomplete_maps)
    PlacesAutocompleteTextView mAutocompleteOne;


    @BindView(R.id.autocomplete_maps_2)
    PlacesAutocompleteTextView mAutocompletTwo;

    @BindView(R.id.btn_fecha)
    TextView mTvFecha;

    public static DateOfArrive newFragmentArrive;

    public static MaterialDialog mCost,quantityDialog;

    public static HourArriveFragment hourArriveFragment;

    public static PublicarRuta publicarRuta;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_ruta);
        ButterKnife.bind(this);
        setAutoComplete(mAutocompleteOne);
        setAutoComplete(mAutocompletTwo);
        database = FirebaseDatabase.getInstance();
        mAutocompleteOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Main2Activity.textToSpeech.speak("Seleeciona el origen", TextToSpeech.QUEUE_FLUSH, null);
                }else {

                }
            }
        });
        mAutocompletTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Main2Activity.textToSpeech.speak("Seleeciona el destino", TextToSpeech.QUEUE_FLUSH, null);
                }else {

                }
            }
        });
        mAutocompletTwo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    publicarRuta=new PublicarRuta();
                    publicarRuta.setId(Util.generarCodigoRuta());
                    publicarRuta.setDireccionSalida(mAutocompleteOne.getText().toString());
                    publicarRuta.setDireccionLlegada(mAutocompletTwo.getText().toString());
                    dateFragment();
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void dateFragment() {
        Main2Activity.textToSpeech.speak("Seleeciona la fecha de salida", TextToSpeech.QUEUE_FLUSH, null);
        DateOfRouteFragment newFragment = new DateOfRouteFragment();
        newFragment.show(getFragmentManager(), "datepicker");
        newFragmentArrive = new DateOfArrive();
        quantityDialog = new MaterialDialog.Builder(PublicarRutaActivity.this)
                .title(R.string.quantyty)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Cantidad","",(dialog, nameInput) -> setQuantity(dialog,nameInput))
                .onPositive((dialog, which) -> quantytySelected(dialog))
                .show();
        quantityDialog.dismiss();
    }

    private void setQuantity(MaterialDialog dialog, CharSequence nameInput) {
        publicarRuta.setEspacio(nameInput.toString());
    }

    private void quantytySelected(MaterialDialog dialog) {
        dialog.dismiss();
        Main2Activity.textToSpeech.speak("Seleeciona la hora de salida", TextToSpeech.QUEUE_FLUSH, null);
        android.support.v4.app.DialogFragment newFragment = new TimePickerogGoFragment();
        newFragment.show(getSupportFragmentManager(), "time");
        hourArriveFragment = new HourArriveFragment();
        mCost= new MaterialDialog.Builder(PublicarRutaActivity.this)
                .title(R.string.price_rout)
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .cancelable(false)
                .input("Precio","",(dialogPrice, nameInput) -> setPrice(dialogPrice,nameInput))
                .onPositive((dialogPrice, which) -> positivePrice(dialogPrice))
                .show();
        mCost.dismiss();
    }

    private void positivePrice(MaterialDialog dialogPrice) {
        publicarRuta.setCosto(mCost.getInputEditText().getText().toString());
        dialogPrice.dismiss();
        writeInDb();
        Main2Activity.textToSpeech.speak("Tu ruta se ah publicado! Muchas gracias", TextToSpeech.QUEUE_FLUSH, null);
        new MaterialDialog.Builder(PublicarRutaActivity.this)
                .title(R.string.great)
                .content("Se publico tu ruta con éxito")
                .positiveText(R.string.ok)
                .positiveColorRes(R.color.colorPrimary)
                .contentColorRes(R.color.colorPrimaryDark)
                .cancelable(false)
                .onPositive((dialogExito, which) -> exito(dialogExito))
                .show();

    }

    private void writeInDb() {
        DatabaseReference mFirebaseDatabase = database.getReference(Util.FIREBASE_DB_RUTA).child(publicarRuta.getId());
        Map<String, Object> map = new HashMap<>();
        Util.log("Ruta "+publicarRuta.toString());
        map.put("id",publicarRuta.getId());
        map.put("estado","");
        map.put("direccionSalida",publicarRuta.getDireccionSalida());
        map.put("direccionLlegada",publicarRuta.getDireccionLlegada());
        map.put("fechaSalida",publicarRuta.getFechaSalida());
        map.put("fechaLlegada",publicarRuta.getFechaLlegada());
        map.put("horaSalida",publicarRuta.getHoraSalida());
        map.put("horaLlegada",publicarRuta.getHoraLlegada());
        map.put("espacio",publicarRuta.getEspacio());
        map.put("costo",publicarRuta.getCosto());
        map.put("estado",publicarRuta.getEstado());
        mFirebaseDatabase.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Main2Activity.textToSpeech.speak("Venta lanzada con éxito", TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }

    private void exito(MaterialDialog dialogExito) {
        dialogExito.dismiss();
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        this.finish();
    }

    private void setPrice(MaterialDialog dialogPrice, CharSequence nameInput) {
    }

    private void dateofArriveFragment() {
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        this.finish();
        super.onBackPressed();
    }
    private void setAutoComplete(PlacesAutocompleteTextView mAutocomplete) {
        mAutocomplete.addTextChangedListener(addressWatcher);
        mAutocomplete.setRadiusMeters(100000l);
        mAutocomplete.setOnPlaceSelectedListener(place -> getAddressValues(place));
    }

    private void getAddressValues(Place place){
        mAutocompletTwo.getDetailsFor(place, new DetailsCallback() {
            @Override
            public void onSuccess(PlaceDetails placeDetails) {
                for (AddressComponent component : placeDetails.address_components) {
                    for (AddressComponentType type : component.types) {
                        switch (type) {
                            case STREET_NUMBER:

                                break;
                            case ROUTE:
                                break;
                            case NEIGHBORHOOD:
                                break;
                            case SUBLOCALITY_LEVEL_1:
                                break;
                            case SUBLOCALITY:
                                break;
                            case LOCALITY:
                                //mCity.setText(component.long_name);
                                break;
                            case ADMINISTRATIVE_AREA_LEVEL_1:
                                //mState.setText(component.short_name);
                                break;
                            case ADMINISTRATIVE_AREA_LEVEL_2:
                                break;
                            case COUNTRY:
                                break;
                            case POSTAL_CODE:
                                break;
                            case POLITICAL:
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Util.ERROR(throwable);
            }
        });
        mAutocompleteOne.getDetailsFor(place, new DetailsCallback() {
            @Override
            public void onSuccess(PlaceDetails placeDetails) {
                for (AddressComponent component : placeDetails.address_components) {
                    for (AddressComponentType type : component.types) {
                        switch (type) {
                            case STREET_NUMBER:

                                break;
                            case ROUTE:
                                break;
                            case NEIGHBORHOOD:
                                break;
                            case SUBLOCALITY_LEVEL_1:
                                break;
                            case SUBLOCALITY:
                                break;
                            case LOCALITY:
                                //mCity.setText(component.long_name);
                                break;
                            case ADMINISTRATIVE_AREA_LEVEL_1:
                                //mState.setText(component.short_name);
                                break;
                            case ADMINISTRATIVE_AREA_LEVEL_2:
                                break;
                            case COUNTRY:
                                break;
                            case POSTAL_CODE:
                                break;
                            case POLITICAL:
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Util.ERROR(throwable);
            }
        });
    }

    private final TextWatcher addressWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    @OnClick(R.id.btn_fecha)
    public void date(){
        dateFragment();
    }

    @OnClick(R.id.btn_fecha_llegada)
    public void fechaLlegada(){
        newFragmentArrive = new DateOfArrive();
    }
}
