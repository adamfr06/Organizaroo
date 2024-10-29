import java.util.*;
public class Reminder {
  int day;
  int month;
  int year;
  //static int assignmentCount;
  String name;
  public Reminder(int day, int month, int year, String name) {
    this.day = day;
    this.month = month;
    this.year = year;
    this.name = name;
    //assignmentCount++;
  }
  public String dateToString() {
    return month + "/" + day + "/" + year;
  }
  public void setDate(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setMonth(int month) {
    this.month = month;
  }
  public void setYear(int year) {
    this.year = year;
  }
  public void setDay(int day) {
    this.day = day;
  }
  public int getDay() {
    return day;
  }
  public int getMonth() {
    return month;
  }
  public int getYear() {
    return year;
  }

}























  
  