package vvmstk.config;

public class stringSetting {

    public stringSetting() {
    }

    public String getSurnameInic(String data){
        String[] text = data.split(" ");
        return text[0]+" "+text[1].charAt(0)+". "+text[2].charAt(0)+".";

    }
}
