package infosw.agenda.com.agendamedica.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import infosw.agenda.com.agendamedica.R;

public class PatientIdAdapter extends BaseAdapter {


    public PatientIdAdapter(List<Patient> patientList) {
        patients = patientList;
    }

    public PatientIdAdapter(Patient patient){patient.getLegajo();}

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
            currentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_patient_id, parent, false);
        } else {
            currentView = convertView;
        }

        Patient patient = getItem(position);

        TextView legajo = (TextView) currentView.findViewById(R.id.legajo);

        legajo.setText(String.valueOf(patient.getLegajo()));
        return currentView;
    }

    public void setNewElements(List<Patient> newPatients) {
        patients = newPatients;
    }
}
