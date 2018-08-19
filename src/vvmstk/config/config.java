package vvmstk.config;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class config {
    public config() {

    }

    private JSONObject connect() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new FileReader("config.json"));

    }

    public ArrayList<Object> getListofValues(String key) throws IOException, ParseException {
        return new ArrayList<Object>((JSONArray) connect().get(key));
    }


   public ArrayList<Object> getTeacher(String predmet) throws IOException, ParseException {
        ArrayList<Object> t = new ArrayList<>();
       JSONArray r = (JSONArray) connect().get("teacher");
       for (int i =0; i<=r.size()-1; i++){
           JSONObject v = (JSONObject) r.get(i);
           if (v.get("predmet").equals(predmet)){
               t.add(v.get("name"));
           }
       }
       return  t;
   }

    public ArrayList<String> getCar(String Kateg) throws IOException, ParseException {
        ArrayList<String> t = new ArrayList<>();
        t.clear();
        JSONArray r = (JSONArray) connect().get("cars");
        for (int i =0; i<=r.size()-1; i++){
            JSONObject v = (JSONObject) r.get(i);
            if (v.get("kateg").equals(Kateg)){
                t.add(v.get("name")+" "+v.get("car_num"));
            }
        }
        return  t;
    }


   String getValue(String root, String key)  {
        String value = null;
       try {
           JSONObject r = (JSONObject) connect().get(root);
           value = (String) r.get(key);
       } catch (IOException | ParseException e) {
           e.printStackTrace();
       }
       return value;
   }





}
