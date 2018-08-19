/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vvmstk.config;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.json.simple.parser.ParseException;


public class dateCalc {
    config c = new config();

    public dateCalc() {
    }


  public LocalDate getLastD(LocalDate d1,  int days){
       if (days < 1){
           return  d1;
       }
      int addedDays = 0;
      while (addedDays < days) {
          d1 = d1.plusDays(1);
          if (!(d1.getDayOfWeek() == DayOfWeek.SATURDAY ||
                  d1.getDayOfWeek() == DayOfWeek.SUNDAY)) {
              ++addedDays;
          }
      }
      return d1;
  }

  public ArrayList<Object> listOfDayas(LocalDate d1,  int days) throws IOException, ParseException {
        ArrayList <Object> o = new ArrayList();
        //ArrayList <Object> holydays = new  config().getListofValues("holydays");
        if (days < 1){
            return null;
        }
        int d =0;
        LocalDate day = d1.minusDays(1);
        while (d < days ){
            day = day.plusDays(1);
//            for (Object l: holydays){
//                System.out.println(l);
//            }
            if(!(day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY)){
                o.add(day);
                d++;
            }
        }
        return o;
  }
 /*
  * 
  */
 
// public List<String> getDates(LocalDate d1,LocalDate d2){
//    List<String> dates = new ArrayList<>();
//    int days = Days.daysBetween(d1, d2).getDays();
//    for (int i=0; i < days+1; i++) {
//    LocalDate d = d1.withFieldAdded(DurationFieldType.days(), i);
//    if(d.getDayOfWeek()!=6&&d.getDayOfWeek()!=7){
//       String date = d.toString("dd.MM.yyyy");
//       dates.add(date);
//    }
//
//}
//    return dates;
//}
//  public List<String> getDatesBetween(LocalDate d1,LocalDate d2){
//    List<String> dates = new ArrayList<>();
//    int days = Days.daysBetween(d1, d2).getDays();
//    for (int i=0; i < days+1; i++) {
//    LocalDate d = d1.withFieldAdded(DurationFieldType.days(), i);
//    String date = d.toString("dd.MM.yyyy");
//    dates.add(date);
//
//
//}
//    return dates;
//}
//
//  /*
//   * holydays
//   */
//   public List<String> getDatesBetweenWeekend(LocalDate d1,LocalDate d2){
//    List<String> dates = new ArrayList<>();
//    int days = Days.daysBetween(d1, d2).getDays();
//    for (int i=0; i < days+1; i++) {
//          LocalDate d = d1.withFieldAdded(DurationFieldType.days(), i);
//          if(d.getDayOfWeek()==6||d.getDayOfWeek()==7){
//            String date = d.toString("dd.MM.yyyy");
//            dates.add(date);
//          }
//
//}
//    return dates;
//}
//  public List<String> getDates(LocalDate d1,LocalDate d2,ArrayList<Integer> day){
//    List<String> dates = new ArrayList<>();
//    int days = Days.daysBetween(d1, d2).getDays();
//    for (int i=0; i < days+1; i++) {
//    LocalDate d = d1.withFieldAdded(DurationFieldType.days(), i);
//    for(int j=0;j<=day.size()-1;j++){
//        if(d.getDayOfWeek()==day.get(j)){
//           String date = d.toString("dd.MM.yyyy");
//           dates.add(date);
//           System.out.println(date);
//        }
//    }
//
//}
//    return dates;
//}
//  public List<String> getDates(LocalDate d1,int day,ArrayList<Integer> week,ArrayList<String> day_out){
//      List<String> dates = new ArrayList();
//      LocalDate d;
//      int day_ =1;
//      while(dates.size()<day){
//          d=d1.plusDays(day_);
//          String date = d.toString("dd.MM.yyyy");
//
//          for(int j=0;j<=week.size()-1;j++){
//
//              if(d.getDayOfWeek()==week.get(j)){
//                      dates.add(date);
//                   }
//
//          }
//          for(int e=0;e<=day_out.size()-1;e++){
//              if(date.equals(day_out.get(e))){
//                 dates.remove(day_out.get(e));
//              }
//          }
//          day_++;
//
//      }
//      return dates;
//  }
//  public String replaceDate(String d){
//      String result=null;
//      result = d.replace("-", "");
//      String day= result.substring(6, 8);
//      String m= result.substring(4, 6);
//      String y= result.substring(0, 4);
//
//      return day+"."+m+"."+y;
//  }
//
//  public Date getFirstDay(Date d,boolean max_min){
//      Calendar c = Calendar.getInstance();
//      c.setTime(d);
//      if(max_min){
//         c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
//      }else{
//         c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//      }
//
//      return c.getTime();
//  }
//
//    public  org.joda.time.LocalDate toJoda(java.time.LocalDate input) {
//        return new org.joda.time.LocalDate(input.getYear(),
//                input.getMonthValue(),
//                input.getDayOfMonth());
//    }
    
}
