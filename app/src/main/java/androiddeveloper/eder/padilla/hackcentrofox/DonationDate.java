package androiddeveloper.eder.padilla.hackcentrofox;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static androiddeveloper.eder.padilla.hackcentrofox.Main2Activity.donarProducto;
import static androiddeveloper.eder.padilla.hackcentrofox.Main2Activity.textToSpeech;
import static androiddeveloper.eder.padilla.hackcentrofox.Main2Activity.venderProducto;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class DonationDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
        // return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String itemCalendar = "";
        itemCalendar += String.format("%02d",day);
        itemCalendar += "-" + String.format("%02d",(month+1));
        itemCalendar += "-" + year;
        Util.log(itemCalendar);
        Main2Activity.donarProducto.setFechaDeEntrega(itemCalendar);
        DatabaseReference mFirebaseDatabase = Main2Activity.database.getReference(Util.FIREBASE_DB_DONATION).child(Main2Activity.donarProducto.getId());
        Map<String, Object> map = new HashMap<>();
        Util.log("donativo "+donarProducto.toString());
        map.put("id",donarProducto.getId());
        map.put("organizacion",donarProducto.getOrganizacion());
        map.put("donacion",donarProducto.getDonacion());
        map.put("concepto",donarProducto.getConcepto());
        map.put("fechaDeEntrega",donarProducto.getFechaDeEntrega());
        map.put("tipoDePaqueteria",donarProducto.getTipoDePaqueteria());
        mFirebaseDatabase.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                textToSpeech.speak("Muchas gracias donació recibida.", TextToSpeech.QUEUE_FLUSH, null);
                Main2Activity.thanksDialog.show();
            }
        });



    }
}
