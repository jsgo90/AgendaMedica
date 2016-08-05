package infosw.agenda.com.agendamedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import infosw.agenda.com.agendamedica.domain.Patient;
import infosw.agenda.com.agendamedica.domain.PatientAdapter;
import infosw.agenda.com.agendamedica.domain.PatientFactory;
import infosw.agenda.com.agendamedica.domain.PatientIdAdapter;

public class DatesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int year;
    private int month;
    private int dayOfMonth;
    private boolean hideIcon = true;

    private PatientIdAdapter adapter, adapter2;
    private PatientFactory patientFactory;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //getSupportActionBar por problemas de compatibilidad
        getSupportActionBar().setTitle(R.string.new_date);

        Intent calendar = getIntent();
        Bundle extras =calendar.getExtras();
        if (extras != null) {                                               //ver si contiene datos
            year = extras.getInt("year");                                   //Obtengo año
            month = extras.getInt("month");                                 //Obtengo mes
            month = month + 1;
            dayOfMonth = extras.getInt("dayOfMonth");                 //Obtengo dia
        }

        ((TextView) findViewById(R.id.date)).setText(dayOfMonth +"-"+month + "-" + year);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        patientFactory = PatientFactory.getInstance(this);

        try {
            adapter = new PatientIdAdapter(patientFactory.getItemList());
            spinner.setAdapter(adapter);
        } catch (Exception none) {
            Log.e(PatientActivity.class.toString(), "Something bad happened");
        }

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if( position != 0){
            hideIcon = false;
            supportInvalidateOptionsMenu(); //Hace aparecer el boton next cuando hay una fecha valida
            try {
                //int legajo = (int) spinner.getSelectedItemId();
                Patient patient = patientFactory.getPatientByLegajo(5);
                ((TextView) findViewById(R.id.patient_name)).setText(patient.getNombre());
                ((TextView) findViewById(R.id.patient_lastName)).setText(patient.getApellido());

            } catch (Exception none) {
                Log.e(PatientActivity.class.toString(), "Something bad happened");
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);

        if (hideIcon){
            menu.findItem(R.id.save_selected_date).setVisible(false);
        }else{
            menu.findItem(R.id.save_selected_date).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save_selected_date:
                selectPatient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void selectPatient(){
        Intent summaryView = new Intent(this, DateSummaryActivity.class);
        summaryView.putExtra("dayOfMonth",dayOfMonth);
        summaryView.putExtra("month",month);
        summaryView.putExtra("year",year);
        startActivity(summaryView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);

    }
}
