package vvmstk.view.retraining.data;

import vvmstk.config.CustomDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class R_data {
    private Object id;
    private Object r_id;
    private Date dateBegin;
    private Date dateEnd;
    private String kateg;
    private String instr;
    private String car;
    private  String  numDov;
    private Date dateV;
    private ArrayList<LocalDate> dataStady;

    public R_data(Object id, Object r_id, Date dateBegin, Date dateEnd, String kateg, String instr, String car, String numDov, Date dateV, ArrayList<LocalDate> dataStady) {
        this.id = id;
        this.r_id = r_id;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.kateg = kateg;
        this.instr = instr;
        this.car = car;
        this.numDov = numDov;
        this.dateV = dateV;
        this.dataStady = dataStady;
    }

    public R_data(Object r_id, Date dateBegin, Date dateEnd, String kateg, String instr, String car, String numDov, Date dateV, ArrayList<LocalDate> dataStady) {
        this.r_id = r_id;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.kateg = kateg;
        this.instr = instr;
        this.car = car;
        this.numDov = numDov;
        this.dateV = dateV;
        this.dataStady = dataStady;
    }

    public R_data(String kateg, String instr, String car, String numDov, ArrayList<LocalDate> dataStady) {
        this.kateg = kateg;
        this.instr = instr;
        this.car = car;
        this.numDov = numDov;
        this.dataStady = dataStady;
    }

    public R_data(Date dateBegin, Date dateEnd, String kateg, String instr, String car, String numDov, ArrayList<LocalDate> dataStady) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.kateg = kateg;
        this.instr = instr;
        this.car = car;
        this.numDov = numDov;
        this.dataStady = dataStady;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getR_id() {
        return r_id;
    }

    public void setR_id(Object r_id) {
        this.r_id = r_id;
    }

    public Date getDateBegin() {
        return new CustomDate (dateBegin.getTime());
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return new CustomDate (dateEnd.getTime());
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getKateg() {
        return kateg;
    }

    public void setKateg(String kateg) {
        this.kateg = kateg;
    }

    public String getInstr() {
        return instr;
    }

    public void setInstr(String instr) {
        this.instr = instr;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getNumDov() {
        return numDov;
    }

    public void setNumDov(String numDov) {
        this.numDov = numDov;
    }

    public Date getDateV() {
        return new CustomDate(dateV.getTime());
    }

    public void setDateV(Date dateV) {
        this.dateV = dateV;
    }

    public ArrayList<LocalDate> getDataStady() {
        return dataStady;
    }

    public void setDataStady(ArrayList<LocalDate> dataStady) {
        this.dataStady = dataStady;
    }
}
