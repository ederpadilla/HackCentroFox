package androiddeveloper.eder.padilla.hackcentrofox;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import androiddeveloper.eder.padilla.hackcentrofox.model.PublicarRuta;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class TimePickerogGoFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        String itemCalendar = "";
        itemCalendar += "" + String.format("%02d",hourOfDay);
        itemCalendar += ":" + String.format("%02d",minute);
        Main2Activity.textToSpeech.speak("Seleeciona la hora de llegada", TextToSpeech.QUEUE_FLUSH, null);
        PublicarRutaActivity.publicarRuta.setHoraSalida(itemCalendar);
        PublicarRutaActivity.hourArriveFragment.show(getActivity().getFragmentManager(),"timearrive");
        //itemCalendar;



    }
}
