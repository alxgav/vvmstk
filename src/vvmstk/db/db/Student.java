package vvmstk.db.db;

import org.bson.types.Binary;

import java.util.Date;

public class Student {

    private Object id;
    private String group;
    private String surname;
    private String firstname;
    private String middlename;
    private String inn;
    private Date dataB;
    private String pasp;
    private String paspVyd;
    private String address;
    private Date paspData;
    private String medNum;
    private String hosp;
    private Date dataMed;
    private Binary foto;


    public Student(Object id, String group, String surname, String firstname, String middlename, String inn, Date dataB, String pasp, String paspVyd, String address, Date paspData, String medNum, String hosp, Date dataMed) {
        this.id = id;
        this.group = group;
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.inn = inn;
        this.dataB = dataB;
        this.pasp = pasp;
        this.paspVyd = paspVyd;
        this.address = address;
        this.paspData = paspData;
        this.medNum = medNum;
        this.hosp = hosp;
        this.dataMed = dataMed;
    }

    public Student(Object id, String group, String surname, String firstname, String middlename, String inn, Date dataB, String pasp, String paspVyd, String address, Date paspData, String medNum, String hosp, Date dataMed, Binary foto) {
        this.id = id;
        this.group = group;
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.inn = inn;
        this.dataB = dataB;
        this.pasp = pasp;
        this.paspVyd = paspVyd;
        this.address = address;
        this.paspData = paspData;
        this.medNum = medNum;
        this.hosp = hosp;
        this.dataMed = dataMed;
        this.foto = foto;
    }

    public Student(String group, String surname, String firstname, String middlename, String inn, Date dataB, String pasp, String paspVyd, String address, Date paspData, String medNum, String hosp, Date dataMed) {
        this.group = group;
        this.surname = surname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.inn = inn;
        this.dataB = dataB;
        this.pasp = pasp;
        this.paspVyd = paspVyd;
        this.address = address;
        this.paspData = paspData;
        this.medNum = medNum;
        this.hosp = hosp;
        this.dataMed = dataMed;
    }

//    public Student(byte[] foto) {
//        this.foto = foto;
//    }
//
//    public byte[] getFoto() {
//        return foto;
//    }
//
//    public void setFoto(byte[] foto) {
//        this.foto = foto;
//    }


    public Binary getFoto() {
        return foto;
    }

    public void setFoto(Binary foto) {
        this.foto = foto;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Date getDataB() {
        return dataB;
    }

    public void setDataB(Date dataB) {
        this.dataB = dataB;
    }

    public String getPasp() {
        return pasp;
    }

    public void setPasp(String pasp) {
        this.pasp = pasp;
    }

    public String getPaspVyd() {
        return paspVyd;
    }

    public void setPaspVyd(String paspVyd) {
        this.paspVyd = paspVyd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getPaspData() {
        return paspData;
    }

    public void setPaspData(Date paspData) {
        this.paspData = paspData;
    }

    public String getMedNum() {
        return medNum;
    }

    public void setMedNum(String medNum) {
        this.medNum = medNum;
    }

    public String getHosp() {
        return hosp;
    }

    public void setHosp(String hosp) {
        this.hosp = hosp;
    }

    public Date getDataMed() {
        return dataMed;
    }

    public void setDataMed(Date dataMed) {
        this.dataMed = dataMed;
    }
}
