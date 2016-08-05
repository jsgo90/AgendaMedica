package infosw.agenda.com.agendamedica.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import infosw.agenda.com.agendamedica.R;

public class PatientAdapter extends BaseAdapter {


    public PatientAdapter(List<Patient> patientList) {
        patients = patientList;
    }

    private List<Patient> patients;

    @Override
    public int getCount(){return this.patients.size();}

    @Override
    public Patient getItem(int position) {return patients.get(position);}

    @Override
    public long getItemId(int position) { return patients.get(position).getLegajo();}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView;

        if (convertView == null) {
            currentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_patient, parent, false);
        } else {
            currentView = convertView;
        }

        Patient patient = getItem(position);

        TextView legajo = (TextView) currentView.findViewById(R.id.legajo);
        TextView name = (TextView) currentView.findViewById(R.id.name);
        TextView lastName = (TextView) currentView.findViewById(R.id.lastName);

        legajo.setText(String.valueOf(patient.getLegajo()));
        name.setText(patient.getNombre());
        lastName.setText(String.valueOf(patient.getApellido()));
        return currentView;
    }

    public void setNewElements(List<Patient> newPatients) {
        patients = newPatients;
    }
}
