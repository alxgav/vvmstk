package vvmstk.db.db;


public class Teacher {

    private String teach;
    private String predmet;

    public Teacher(String teach, String predmet) {
        this.teach = teach;
        this.predmet = predmet;
    }

    public String getTeach() {
        return teach;
    }

    public void setTeach(String teach) {
        this.teach = teach;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }
}
