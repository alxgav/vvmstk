package vvmstk.config;

public class common {
    public final  String dbname = new config().getValue("database","dbname");
    private final  String user = new config().getValue("database","user");
    private final  String password = new config().getValue("database","password");

    public final String CONNECT_MONGO = "mongodb+srv://"+user+":"+password+"#@cluster0-czm9l.mongodb.net/test";

}
