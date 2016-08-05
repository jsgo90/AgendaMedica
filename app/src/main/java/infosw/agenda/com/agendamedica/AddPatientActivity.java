package infosw.agenda.com.agendamedica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import infosw.agenda.com.agendamedica.domain.Patient;
import infosw.agenda.com.agendamedica.domain.PatientFactory;

public class AddPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //getSupportActionBar por problemas de compatibilidad
        getSupportActionBar().setTitle(R.string.new_patient);
    }

    public void addPatient(View view){

        EditText name = (EditText) findViewById(R.id.patient_name);
        EditText lastName = (EditText) findViewById(R.id.patient_lastName);
        EditText legajo = (EditText) findViewById(R.id.patient_legajo);


        if(name != null && lastName != null && legajo != null ) {
            String strName = name.getText().toString();
            String strLastName = lastName.getText().toString();
            String strLegajo = legajo.getText().toString();

            if (TextUtils.isEmpty(strName)) {
                name.setError("Cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(strLastName)) {
                lastName.setError("Cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(strLegajo)) {
                legajo.setError("Cannot be empty");
                return;
            }

            try {
                PatientFactory.getInstance(this).addPatient(new Patient(
                        name.getText().toString(),
                        lastName.getText().toString(),
                        Integer.parseInt(legajo.getText().toString())));
                Toast.makeText(AddPatientActivity.this, "Paciente creado exitosamente", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException numberexc) {
                Toast.makeText(AddPatientActivity.this, "legajo inválido", Toast.LENGTH_SHORT).show();

            } catch (SQLException sql) {
                String msg = "EL legajo " + legajo.getText().toString() + " ya existe";
                Toast.makeText(AddPatientActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            onBackPressed();
        }
    }
}
