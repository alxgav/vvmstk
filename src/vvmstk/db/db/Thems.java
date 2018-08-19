package vvmstk.db.db;

public class Thems {

    private String NUM_TEMA;
    private String T_TIME;
    private String T_OPIS;

    public Thems(String NUM_TEMA, String t_TIME, String t_OPIS) {
        this.NUM_TEMA = NUM_TEMA;
        T_TIME = t_TIME;
        T_OPIS = t_OPIS;
    }

    public String getNUM_TEMA() {
        return NUM_TEMA;
    }

    public void setNUM_TEMA(String NUM_TEMA) {
        this.NUM_TEMA = NUM_TEMA;
    }

    public String getT_TIME() {
        return T_TIME;
    }

    public void setT_TIME(String t_TIME) {
        T_TIME = t_TIME;
    }

    public String getT_OPIS() {
        return T_OPIS;
    }

    public void setT_OPIS(String t_OPIS) {
        T_OPIS = t_OPIS;
    }
}
