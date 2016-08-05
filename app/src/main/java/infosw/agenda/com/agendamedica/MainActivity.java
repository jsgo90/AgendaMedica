package infosw.agenda.com.agendamedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView main_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.main);

        main_logo = (ImageView) findViewById(R.id.main_logo);

    }

    public void getStarted(View view) {
        Intent startView = new Intent(this,CalendarActivity.class );
        startActivity(startView);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);

    }

    public void createPatient(View view) {
        Intent patientView = new Intent(this,PatientActivity.class );
        startActivity(patientView);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);

    }
}
