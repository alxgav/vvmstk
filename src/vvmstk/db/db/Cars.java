package vvmstk.db.db;

public class Cars {
    private String carname;
    private String carnumber;
    private String kategorja;

    public Cars(String carname, String carnumber, String kategorja) {
        this.carname = carname;
        this.carnumber = carnumber;
        this.kategorja = kategorja;
    }

    public Cars(String carname, String carnumber) {
        this.carname = carname;
        this.carnumber = carnumber;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getKategorja() {
        return kategorja;
    }

    public void setKategorja(String kategorja) {
        this.kategorja = kategorja;
    }
}
