package vvmstk.raport.xls.raport;

public class Tdata {
    private String tema;
    private String tema_t;
    private String descr;
    private String data;

    public Tdata(String tema, String tema_t, String descr, String data) {
        this.tema = tema;
        this.tema_t = tema_t;
        this.descr = descr;
        this.data = data;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTema_t() {
        return tema_t;
    }

    public void setTema_t(String tema_t) {
        this.tema_t = tema_t;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
