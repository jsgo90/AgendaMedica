package infosw.agenda.com.agendamedica.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


@DatabaseTable(tableName = "patient")
public class Patient implements Serializable {

    @DatabaseField
    private String nombre;

    @DatabaseField
    private String apellido;

    @DatabaseField(id = true, unique = true, throwIfNull = true)
    public int legajo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public Patient() {
    }

    public Patient(String nombre, String apellido, int legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
    }

}
