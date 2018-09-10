/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vvmstk.config;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;


public class dateCalc {

    public dateCalc() {
    }


//  public LocalDate getLastD(LocalDate d1,  int days){
//       if (days < 1){
//           return  d1;
//       }
//      int addedDays = 0;
//      while (addedDays < days) {
//          d1 = d1.plusDays(1);
//          if (!(d1.getDayOfWeek() == DayOfWeek.SATURDAY ||
//                  d1.getDayOfWeek() == DayOfWeek.SUNDAY)) {
//              ++addedDays;
//          }
//      }
//      return d1;
//  }

  public ArrayList<Object> listOfDayas(LocalDate d1,  int days) throws IOException, ParseException {
        ArrayList <Object> o = new ArrayList();
        ArrayList <Object> holydays = new  config().getListofValues("holydays");
        ArrayList <Object> workdays = new config().getListofValues("workdays");
        if (days < 1){
            return null;
        }
        int d =0;
        LocalDate day = d1.minusDays(1);
        while (d < days ){
            day = day.plusDays(1);
            for(Object l: holydays){
                if (l.equals(day.toString())){
                    day =  day.plusDays(1);
                }
            }
            for (Object l: workdays){
                if (l.equals(day.toString())){
                    o.add(day);
                    d++;
                }
            }
            if(!(day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY)){
                o.add(day);
                d++;
            }
        }
        return o;
  }

    public ArrayList<LocalDate> listDaysOfPlane_B(LocalDate d1,  int days,ArrayList<Integer> week) throws IOException, ParseException {
        ArrayList <LocalDate> o = new ArrayList();
        ArrayList <Object> holydays = new  config().getListofValues("holydays");
        ArrayList <Object> workdays = new config().getListofValues("workdays");
        if (days < 1){
            return null;
        }
        int d =0;
        LocalDate day = d1.minusDays(1);
        while (d < days ){
            day = day.plusDays(1);
            for(Object l: holydays){
                if (l.equals(day.toString())){
                    day =  day.plusDays(1);
                }
            }
            for (Object l: workdays){
                if (l.equals(day.toString())){
                    o.add(day);
                    d++;
                }
            }
            for(int i=0;i<=week.size()-1;i++){
               if(day.getDayOfWeek() == DayOfWeek.of(week.get(i))){
                   System.out.println(day);
                    o.add(day);
                    d++;
                    }
               }

//            if(!(day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY)){
//                o.add(day);
//                d++;
//            }
        }
        return o;
    }



    
}
