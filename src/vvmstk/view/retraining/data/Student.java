package vvmstk.view.retraining.data;

import org.bson.types.Binary;

import java.util.Date;


public class Student {

    private Object id;
    private String surname;
    private String firstname;
    private String middlename;
    private Date DataB;
    private Binary foto;

    public Student(Object id, String surname, String firstname, String middlename, Date dataB, Binary foto) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.DataB = dataB;
        this.foto = foto;
    }

    public Student(Object id, String surname, String firstname, String middlename, Binary foto) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.foto = foto;
    }

    public Student(String surname, String firstname, String middlename, Date dataB, Binary foto) {
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.DataB = dataB;
        this.foto = foto;
    }

    public Student(String surname, String firstname, String middlename, Binary foto) {
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.foto = foto;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getDataB() {
        return DataB;
    }

    public void setDataB(Date dataB) {
        DataB = dataB;
    }

    public Binary getFoto() {
        return foto;
    }

    public void setFoto(Binary foto) {
        this.foto = foto;
    }
}
