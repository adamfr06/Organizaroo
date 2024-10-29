import java.util.*;
//import javax.swing.*;
import java.awt.*;
public class Course {

  
  int font;
  static int assignmentCount = 0;
  ArrayList<Reminder> reminders;
  String courseName;
  Color color;

  public Course(int font, String courseName, Color color) {
    this.font = font;
    this.courseName = courseName;
    this.color = color;
    //courseCount++;
    reminders = new ArrayList<Reminder>();

  }
  public static int getCount() {
    return assignmentCount;
  }
  public ArrayList<Reminder> getReminders() {
    return reminders;
  }
  public void makeReminder(int day, int month, int year, String name) {
    Reminder reminder = new Reminder(day, month, year, name);
    reminders.add(reminder);
    sortReminders();
    assignmentCount++;
  }

public void removeReminder(String name) {
    for (int i = 0; i < reminders.size(); i++) {
        if (reminders.get(i).getName().equals(name)) {
            reminders.remove(i);
            break;
        }
    }
  sortReminders();
  assignmentCount--;
}
  public void setReminder(int day, int month, int year, String name, String newName) {
    for (int i = 0; i < reminders.size(); i++) {
      if (reminders.get(i).getName().equals(name)) {
        reminders.get(i).setDate(day, month, year);
        reminders.get(i).setName(newName);
      }
    }
    sortReminders();
  }
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
  public void setColor(Color color) {
    this.color = color;
  }
  public String getCourseName() {
    return courseName;
  }
  public Color getColor() {
    return color;
  }
  public int getFont() {
      return font;
  }
  public void setFont(int font) {
      this.font = font;
  }












  
  public void sortReminders() {
      for (int i = 0; i < reminders.size() - 1; i++) {
          for (int j = 0; j < reminders.size() - i - 1; j++) {
              if (reminders.get(j).getYear() > reminders.get(j + 1).getYear() || //start with most common case to least common case
                  (reminders.get(j).getYear() == reminders.get(j + 1).getYear() &&
                    reminders.get(j).getMonth() > reminders.get(j + 1).getMonth()) ||
                  (reminders.get(j).getYear() == reminders.get(j + 1).getYear() &&
                    reminders.get(j).getMonth() == reminders.get(j + 1).getMonth() && 
                          reminders.get(j).getDay() > reminders.get(j + 1).getDay())) {

                  Reminder temp = reminders.get(j);
                  reminders.set(j, reminders.get(j + 1));
                  reminders.set(j + 1, temp);
              }
          }
      }
  }



}
