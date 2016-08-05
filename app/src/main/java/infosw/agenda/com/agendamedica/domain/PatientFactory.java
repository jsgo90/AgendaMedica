package infosw.agenda.com.agendamedica.domain;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import infosw.agenda.com.agendamedica.database.DatabaseHelper;


public class PatientFactory {


    private static PatientFactory instance;
    private Dao<Patient, Integer> patientDao;

    private PatientFactory(Context context) {
        OrmLiteSqliteOpenHelper helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

        try {
            patientDao = helper.getDao(Patient.class);
        } catch (SQLException e) {
            // nothing
        }
    }

    public static PatientFactory getInstance(Context context) {
        if (instance == null) {
            instance = new PatientFactory(context);
        }
        return instance;
    }

    public List<Patient> getItemList() throws SQLException {
        return patientDao.queryForAll();
    }

    public boolean addPatient(Patient newItem) throws SQLException {
        patientDao.create(newItem);
        return true;
    }

    public boolean removeItem(Patient toDelete) {
        try {
            patientDao.delete(toDelete);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }


    public Patient getPatientByLegajo(int legajo)throws SQLException{
        return patientDao.queryForId(legajo);
    }

}
