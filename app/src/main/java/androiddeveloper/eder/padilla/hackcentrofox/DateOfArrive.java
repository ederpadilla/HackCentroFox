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

import java.util.Calendar;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class DateOfArrive  extends DialogFragment implements DatePickerDialog.OnDateSetListener {



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
        PublicarRutaActivity.publicarRuta.setFechaLlegada(itemCalendar);
        Main2Activity.textToSpeech.speak("Seleeciona el espacio en metros cubicos", TextToSpeech.QUEUE_FLUSH, null);
        PublicarRutaActivity.quantityDialog.show();


        //Main2Activity.textToSpeech.speak("Selecciona una categoría", TextToSpeech.QUEUE_FLUSH, null);
        //Main2Activity.categoryDialog.show();
            /*if (temp.contains("YYYY"))


            if (temp.contains("MM")) {
                if (temp.contains("DD")) {
                    itemCalendar += "-" + (month+1);
                } else
            }
            if (temp.contains("DD")) {
                if (temp.contains("YYYY") || temp.contains("MM")) {
                    itemCalendar += "-" + year;
                } else
            }*/


    }
}