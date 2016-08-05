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
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import infosw.agenda.com.agendamedica.domain.PatientAdapter;
import infosw.agenda.com.agendamedica.domain.PatientFactory;

public class PatientActivity extends AppCompatActivity {

    private ListView patientList;
    private PatientAdapter adapter;
    private PatientFactory patientFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        getSupportActionBar().setTitle(R.string.patient);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //getSupportActionBar por problemas de compatibilidad

        patientList = (ListView) findViewById(R.id.list);
        patientFactory = patientFactory.getInstance(this);

        try {
            adapter = new PatientAdapter(patientFactory.getItemList());
            patientList.setAdapter(adapter);
        } catch (Exception none) {
            Log.e(PatientActivity.class.toString(), "Something bad happened");
        }

    }

    @Override
    protected void onResume() {
        try {
            adapter. setNewElements(patientFactory.getItemList());
            adapter.notifyDataSetChanged();
        } catch (SQLException e) {
            Log.e(PatientActivity.class.toString(), "Something bad happened");
        }

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_abm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_patient:
                Intent add = new Intent(this, AddPatientActivity.class);
                startActivity(add);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}