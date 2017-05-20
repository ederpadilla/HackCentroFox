package androiddeveloper.eder.padilla.hackcentrofox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class BuscarRutasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_rutas);
        ButterKnife.bind(this);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        this.finish();
        super.onBackPressed();
    }
}
