import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class TxtManager {
  static File txt;
  Scanner scanny;
  public TxtManager() {

    try {


      txt = new File("OrgData.txt");
      scanny= new Scanner(txt);
      while (scanny.hasNextLine()) {
        String data = scanny.nextLine();
        System.out.println(data);
      }
      scanny.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
public static LinkedList<Course> updateProgram() {
    txt = new File("OrgData.txt");
    LinkedList<Course> courses = new LinkedList<>();
    try {

      Scanner scanner = new Scanner(txt);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        if (data.equals("END")) {
          break;
        }
        int font = Integer.parseInt(scanner.nextLine());
        String colorString = scanner.nextLine();
        Color color = Color.GREEN;

        if (colorString.equals("RED")) {
          color = Color.RED;
        }
        if (colorString.equals("BLACK")) {
          color = Color.BLACK;
        }
        if (colorString.equals("BLUE")) {
          color = Color.BLUE;
        }
        if (colorString.equals("GREEN")) {
          color = Color.GREEN;
        }   
        if (colorString.equals("DARK_GRAY")) {
          color = Color.DARK_GRAY;
        }
        if (colorString.equals("GRAY")) {
          color = Color.GRAY;
        }
        if (colorString.equals("LIGHT_GRAY")) {
          color = Color.LIGHT_GRAY;
        }
        if (colorString.equals("MAGENTA")) {
          color = Color.MAGENTA;
        }
        if (colorString.equals("ORANGE")) {
          color = Color.ORANGE;
        }
        if (colorString.equals("PINK")) {
          color = Color.PINK;
        }
        if (colorString.equals("WHITE")) {
          color = Color.WHITE;
        }
        if (colorString.equals("YELLOW")) {
          color = Color.YELLOW;
        }
        if (colorString.equals("CYAN")) {
          color = Color.CYAN;
        }


        Course course = new Course(font, data, color);
        while (true) {
          String reminderName = scanner.nextLine();
          if (reminderName.equals("-")) {
            break;
          }
          int day = Integer.parseInt(scanner.nextLine());
          int month = Integer.parseInt(scanner.nextLine());
          int year = Integer.parseInt(scanner.nextLine());
          course.makeReminder(day, month, year, reminderName);
        }
        courses.add(course);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return courses;
  }
  public static void updateFile(LinkedList<Course> courses) {
    //order:
    //Course Name
    //font (0 for normal, 1 for bold, 2 for italic)
    //color
    //For Each Reminder:
    //Title
    //Day
    //Month
    //Year
    //"-" after course if finished
    try {
      FileWriter writer = new FileWriter("OrgData.txt");
      for (int i = 0; i < courses.size(); i++) {
        writer.write(courses.get(i).getCourseName());
        writer.write("\n");
        writer.write(Integer.toString(courses.get(i).getFont()));
        writer.write("\n");



        Color color = courses.get(i).getColor();
        if (color.equals(Color.RED)) {
          writer.write("RED");
        }
        if (color.equals(Color.BLACK)) {
          writer.write("BLACK");
        }
        if (color.equals(Color.BLUE)) {
          writer.write("BLUE");
        }
        if (color.equals(Color.CYAN)) {
          writer.write("CYAN");
        }
        if (color.equals(Color.DARK_GRAY)) {
          writer.write("DARK_GRAY");
        }
        if (color.equals(Color.DARK_GRAY)) {
          writer.write("DARK_GRAY");
        }
        if (color.equals(Color.GRAY)) {
          writer.write("GRAY");
        }
        if (color.equals(Color.GREEN)) {
          writer.write("GREEN");
        }
        if (color.equals(Color.LIGHT_GRAY)) {
          writer.write("LIGHT_GRAY");
        }
        if (color.equals(Color.MAGENTA)) {
          writer.write("MAGENTA");
        }
        if (color.equals(Color.ORANGE)) {
          writer.write("ORANGE");
        }
        if (color.equals(Color.PINK)) {
          writer.write("PINK");
        }
        if (color.equals(Color.WHITE)) {
          writer.write("WHITE");
        }
        if (color.equals(Color.YELLOW)) {
          writer.write("YELLOW");
        }







        writer.write("\n");
        for (int j = 0; j < courses.get(i).getReminders().size(); j++) {
          if (!courses.get(i).getReminders().isEmpty()) {


          writer.write(courses.get(i).getReminders().get(j).getName());
          writer.write("\n");
          writer.write(Integer.toString(courses.get(i).getReminders().get(j).getDay()));

          writer.write("\n");
          writer.write(Integer.toString(courses.get(i).getReminders().get(j).getMonth()));
          writer.write("\n");
          writer.write(Integer.toString(courses.get(i).getReminders().get(j).getYear()));
          writer.write("\n");


          }
        }
        writer.write("-");
         writer.write("\n");
      }


      writer.write("END");
      writer.close(); 

    } catch (IOException e) {
      System.out.println("ERROR!");
      e.printStackTrace();
    }
  }
}