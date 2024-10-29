import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class Window extends JFrame {
  LinkedList<Course> courses;
  JTextPane coursesPane = null;
  JScrollPane pane = null;
  JLabel count = null;


    public Window() {


      courses = new LinkedList<Course>();

      setSize(750, 500);
      setLayout(null); 

      setResizable(false);




      String html = "<html><h1>Organizaroo</h1></html>";      
      JLabel titleLabel = new JLabel(html);
      titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
      titleLabel.setBounds(0, 10, 750, 36); 
      add(titleLabel);
      courses = TxtManager.updateProgram();
      makeBoxes();
      JMenuBar menuBar = new JMenuBar();
      JMenu menu = new JMenu("\u2261");
      JMenuItem item1, item2, item3, item4;
      item1 = new JMenuItem("Add/Remove Course");
      item2 = new JMenuItem("Add/Remove Assignment");
      item3 = new JMenuItem("Update File/Program");
      item4 = new JMenuItem("Edit");
      menu.add(item1);
      menu.add(item2);
      menu.add(item3);
      menu.add(item4);
      menuBar.add(menu);
      setJMenuBar(menuBar);

    item1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddRemoveCourseWindow();

        }
    });
    item2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddRemoveAssignmentWindow();

        }
    });
    item3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showFileUpdatedMessage();

        }
    });
    item4.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            openEditWindow();

        } 
      });

      //makeBoxes();
      count = new JLabel("<html><h4>Assignment Count: " + Course.getCount() + "</h4></html>");
      count.setHorizontalAlignment(SwingConstants.CENTER);

      JLabel line = new JLabel("<html><h2>--------------------------------</h2></html>");
      line.setHorizontalAlignment(SwingConstants.CENTER);
      line.setBounds((getWidth() - 350)/2, 33, 350, 20);
      count.setBounds((getWidth() - 250)/2, 50, 250, 20);
      add(line);
      add(count);
      setVisible(true);
    }








    private void openEditWindow() {
      JFrame editWindow = new JFrame("Edit");
      editWindow.setLocationRelativeTo(this);
      JButton courses = new JButton("Courses");
      JButton assignments = new JButton("Assignments");
      editWindow.setLayout(new FlowLayout());
      editWindow.add(courses);
      editWindow.add(assignments);
      editWindow.setSize(300, 150);
      editWindow.setVisible(true);
      courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditCourses();       
            }
        });
        assignments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditAssignments();
            }
      });

    }




    private void openEditAssignments() {

      final JComboBox<String> assignmentComboBox = new JComboBox<String>();

      JFrame editWindow = new JFrame("Edit Assignments");
      editWindow.setLocationRelativeTo(this);
      editWindow.setLayout(new GridLayout(7,2));
      editWindow.add(new JLabel("Course:"));
      JComboBox<String> courseBox = new JComboBox<String>();
      for (Course c : courses) {
        courseBox.addItem(c.getCourseName());
      }
      editWindow.add(courseBox);
      editWindow.add(new JLabel("Assignment:"));
      //final Course[] selected = {courses.get(courseBox.getSelectedIndex())};
      JButton save = new JButton("Save");
      String[] dList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
      JComboBox Day = new JComboBox(dList);
      String[] mList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
      JComboBox Month = new JComboBox(mList);

      final JTextField[] namePlaceholderList = new JTextField[1];

      JTextField Year = new JTextField(4); //4


      Vector<String> assignmentNames = new Vector<String>();

      courseBox.setBounds(0, 0, 200, 20);

      final Course[] selectedCourse = {courses.get(0)};
      for (int i = 0; i < selectedCourse[0].getReminders().size(); i++) {
        assignmentNames.add(selectedCourse[0].getReminders().get(i).getName());
      }
      for (String b : assignmentNames) {
        assignmentComboBox.addItem(b);
      }

      courseBox.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
          selectedCourse[0] = courses.get(courseBox.getSelectedIndex());
          if (selectedCourse[0].getReminders().size() <= 0) {
            assignmentComboBox.removeAllItems();

          }
          else {
            assignmentComboBox.removeAllItems();
            assignmentNames.clear();
          for (int i = 0; i < selectedCourse[0].getReminders().size(); i++) {
            assignmentNames.add(selectedCourse[0].getReminders().get(i).getName());
          }

          assignmentComboBox.removeAllItems();
          for (String b : assignmentNames) {
            assignmentComboBox.addItem(b);
          }
          }
        }
      });
      if (selectedCourse[0].getReminders().size() != 0) {
      namePlaceholderList[0] = new JTextField(selectedCourse[0].getReminders().get(0).getName(), 20);
      Day.setSelectedIndex(selectedCourse[0].getReminders().get(0).getDay() - 1);
      Month.setSelectedIndex(selectedCourse[0].getReminders().get(0).getMonth() - 1);

      assignmentComboBox.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          //namePlaceholderList[0] = new JTextField(selected[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getName(), 20);

          if (assignmentComboBox.getSelectedIndex() == -1) {
            return;
          }
          namePlaceholderList[0].setText(selectedCourse[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getName());
          Year.setText(Integer.toString(selectedCourse[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getYear()));
          Day.setSelectedIndex(selectedCourse[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getDay() - 1);
          Month.setSelectedIndex(selectedCourse[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getMonth() - 1);
        }
      });
    }
      save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String name = namePlaceholderList[0].getText();
          int day = Day.getSelectedIndex() + 1;

          int month = Month.getSelectedIndex() + 1;
          int year = Integer.parseInt(Year.getText());
          selectedCourse[0] = courses.get(courseBox.getSelectedIndex());


          selectedCourse[0].setReminder(day, month, year,selectedCourse[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getName(), name);
          makeBoxes();
          editWindow.dispose();

        }
      });

      editWindow.add(assignmentComboBox);
      editWindow.add(new JLabel("Day"));
      editWindow.add(Day);
      editWindow.add(new JLabel("Month"));
      editWindow.add(Month);
      editWindow.add(new JLabel("Year"));
      Year.setText(Integer.toString(selectedCourse[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getYear()));
      editWindow.add(Year);
      editWindow.add(new JLabel("Name"));
      editWindow.add(namePlaceholderList[0]);

      editWindow.add(save);


      editWindow.setSize(300, 400);
      editWindow.setVisible(true);
    }
    private void openEditCourses() {
      JFrame editWindow = new JFrame("Edit Course");
      editWindow.setLocationRelativeTo(this);
      editWindow.setLayout(new GridLayout(5,2));

      JComboBox<String> courseBox = new JComboBox<String>();
      for (Course c : courses) {
        courseBox.addItem(c.getCourseName());
      }

      //JTextField name = new JTextField(courses.get(courseBox.getSelectedIndex()).getCourseName());


      Color[] colorOptions = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, Color.BLACK};
      String[] colorNames = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE", "PINK", "CYAN", "MAGENTA", "DARK_GRAY", "GRAY", "LIGHT_GRAY", "WHITE", "BLACK"};
      //JComboBox<String> colorDropdown = new JComboBox<String>(colorNames);
      int[] fontNum = {0, 1, 2};
      //String[] fontStr = {"Normal", "Bold", "Italic"};
      JComboBox<String> fontDropdown = new JComboBox<String>(new String[] {"Normal", "Bold", "Italic"});

      JButton save = new JButton("Save");

      Course[] selectedCourse = {courses.get(0)};
      final JComboBox<String> colorList = new JComboBox<String>(colorNames);
      final JTextField[] namePlaceholderList = new JTextField[1];
      //if (true) {
        namePlaceholderList[0] = new JTextField(selectedCourse[0].getCourseName(), 20);
        int b = 0;
          while (courses.get(0).getColor() != colorOptions[b]) {
            b++;
          }
          int v = 0;
          while (courses.get(0).getFont() != fontNum[v]) {
            v++;
          }
      colorList.setSelectedIndex(b);
      fontDropdown.setSelectedIndex(v);

        courseBox.addItemListener(new ItemListener() {
          public void itemStateChanged(ItemEvent e) {
            //namePlaceholderList[0] = new JTextField(selected[0].getReminders().get(assignmentComboBox.getSelectedIndex()).getName(), 20);
            if (courseBox.getSelectedIndex() == -1) {
              return;
            }
            selectedCourse[0] = courses.get(courseBox.getSelectedIndex());
            namePlaceholderList[0].setText(selectedCourse[0].getCourseName());
            int i = 0;
            while (selectedCourse[0].getColor() != colorOptions[i]) {
              i++;
            }
            int z = 0;
            while (selectedCourse[0].getFont() != fontNum[z]) {
              z++;
            }
            fontDropdown.setSelectedIndex(z);
            colorList.setSelectedIndex(i);
          }
        });
      //}
      save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Course course = courses.get(courseBox.getSelectedIndex());
          course.setCourseName(namePlaceholderList[0].getText());
          course.setColor(colorOptions[colorList.getSelectedIndex()]);
          course.setFont(fontDropdown.getSelectedIndex());
          makeBoxes();
          editWindow.dispose();
        }
      });
      editWindow.add(new JLabel("Course: "));
      editWindow.add(courseBox);
      editWindow.add(new JLabel("Name: "));
      editWindow.add(namePlaceholderList[0]);
      editWindow.add(new JLabel("Color: "));
      editWindow.add(colorList);
      editWindow.add(new JLabel("Font: "));
      editWindow.add(fontDropdown);


      editWindow.add(save);
      editWindow.setSize(300, 400);
      editWindow.setVisible(true);

    }
    private void openAddRemoveAssignmentWindow() {

        JFrame addRemoveAssignmentWindow = new JFrame("Add/Remove Assignment");
        addRemoveAssignmentWindow.setLocationRelativeTo(this);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");



        addRemoveAssignmentWindow.setLayout(new FlowLayout());
        addRemoveAssignmentWindow.add(addButton);
        addRemoveAssignmentWindow.add(removeButton);

        addRemoveAssignmentWindow.setSize(300, 150);
        addRemoveAssignmentWindow.setVisible(true);
        addRemoveAssignmentWindow.setLocationRelativeTo(null);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddAssignmentWindow();       
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRemoveAssignmentWindow();
            }
      });
    }










    private void openRemoveAssignmentWindow() {

        JFrame removeAssignmentWindow = new JFrame("Remove Assignment");
        removeAssignmentWindow.setLocationRelativeTo(this);
        JLabel courseLabel = new JLabel("Course:");
        final JComboBox<String> assignmentComboBox = new JComboBox<String>();

        String[] courseOptions = new String[courses.size()];

        for (int i = 0; i < courses.size(); i++) {
          courseOptions[i] = courses.get(i).getCourseName();
        }
      JComboBox<String> courseComboBox = new JComboBox<String>(courseOptions);
        Vector<String> assignmentNames = new Vector<String>();

        courseComboBox.setBounds(0, 0, 200, 20);

        final Course[] selectedCourse = {courses.get(0)};
        for (int i = 0; i < selectedCourse[0].getReminders().size(); i++) {
          assignmentNames.add(selectedCourse[0].getReminders().get(i).getName());
        }
        for (String b : assignmentNames) {
          assignmentComboBox.addItem(b);
        }
        courseComboBox.addItemListener(new ItemListener() {
          @Override
          public void itemStateChanged(ItemEvent e) {
            selectedCourse[0] = courses.get(courseComboBox.getSelectedIndex());
            if (selectedCourse[0].getReminders().size() <= 0) {
              assignmentComboBox.removeAllItems();

            }
            else {
              assignmentComboBox.removeAllItems();
              assignmentNames.clear();
            for (int i = 0; i < selectedCourse[0].getReminders().size(); i++) {
              assignmentNames.add(selectedCourse[0].getReminders().get(i).getName());
            }

            assignmentComboBox.removeAllItems();
            for (String b : assignmentNames) {
              assignmentComboBox.addItem(b);
            }
            }
          }
        });



        if (selectedCourse[0] == null) {
          assignmentNames.add("Select a course first");
        }
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCourse[0].getReminders().size() <= 0) {
                  removeAssignmentWindow.dispose();
                  return;
                }
                String selectedAssignment = assignmentComboBox.getSelectedItem().toString();
                selectedCourse[0].removeReminder(selectedAssignment);
                assignmentComboBox.removeAllItems();
                for (String b : assignmentNames) {
                  assignmentComboBox.addItem(b);
                }
              makeBoxes();
              count.setText(("<html><h4>Assignment Count: " + Course.getCount() + "</h4></html>"));
                removeAssignmentWindow.dispose();

            }
        });



        assignmentComboBox.setBounds(0, 0, 200, 20);
        removeAssignmentWindow.setSize(350, 260);
        removeAssignmentWindow.setLayout(new GridLayout(3,2));
        removeAssignmentWindow.add(courseLabel);
        removeAssignmentWindow.add(courseComboBox);
        removeAssignmentWindow.add(new JLabel("Assignment:"));
        removeAssignmentWindow.add(assignmentComboBox);
        removeAssignmentWindow.add(removeButton);
        removeAssignmentWindow.setVisible(true);

    }

















    private void openAddAssignmentWindow() {
      JFrame addAssignmentWindow = new JFrame("Add Assignment");
      addAssignmentWindow.setLocationRelativeTo(this);
      JLabel courseLabel = new JLabel("Course:");
      JComboBox<String> courseComboBox;
      String[] courseOptions = new String[courses.size()];
      int index = 0;
      for (Course i : courses) {
        courseOptions[index]= i.getCourseName();
        index++;
      }
      courseComboBox = new JComboBox<String>(courseOptions);
      courseComboBox.setBounds(0, 0, 200, 20);


      final Course[] selected = {courses.get(courseComboBox.getSelectedIndex())};
      JButton create = new JButton("Create");
      String[] dList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
      JComboBox Day = new JComboBox(dList);
      String[] mList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
      JComboBox Month = new JComboBox(mList);

      JTextField Name = new JTextField(20); //20
      JTextField Year = new JTextField(4); //4


      create.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String name = Name.getText();
          int day = Integer.parseInt(Day.getSelectedItem().toString());

          int month = Month.getSelectedIndex() + 1;
          int year = Integer.parseInt(Year.getText());
          selected[0] = courses.get(courseComboBox.getSelectedIndex());
          selected[0].makeReminder(day, month, year, name);
          makeBoxes();
          addAssignmentWindow.dispose();
          count.setText(("<html><h4>Assignment Count: " + Course.getCount() + "</h4></html>"));
        }
      });





      addAssignmentWindow.setSize(350, 260);
      addAssignmentWindow.add(courseLabel);
      addAssignmentWindow.add(courseComboBox);
      addAssignmentWindow.add(new JLabel("Day:"));
      addAssignmentWindow.add(Day);
      addAssignmentWindow.add(new JLabel("Month:"));
      addAssignmentWindow.add(Month);
      addAssignmentWindow.add(new JLabel("Year:"));
      addAssignmentWindow.add(Year);
      addAssignmentWindow.add(new JLabel("Name:"));
      addAssignmentWindow.add(Name);


      addAssignmentWindow.add(create);

      addAssignmentWindow.setVisible(true);

      addAssignmentWindow.setLayout(new GridLayout(6,2));
      addAssignmentWindow.setLocationRelativeTo(null);

    }
    private void openAddRemoveCourseWindow() {
        JFrame addRemoveCourseWindow = new JFrame("Add/Remove Course");
        addRemoveCourseWindow.setLocationRelativeTo(this);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addRemoveCourseWindow.setLayout(new FlowLayout());
        addRemoveCourseWindow.add(addButton);
        addRemoveCourseWindow.add(removeButton);

        addRemoveCourseWindow.setSize(300, 150);
        addRemoveCourseWindow.setLocationRelativeTo(null);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateCoursePage();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              openRemoveCoursePage();
            }
        });

        addRemoveCourseWindow.setVisible(true);
    }
    private void openRemoveCoursePage() {
      if (courses.size() == 0) {
          JOptionPane.showMessageDialog(null, "No courses to remove.", "No Courses", JOptionPane.INFORMATION_MESSAGE);
          return; 
      }
      JFrame removeCourseWindow = new JFrame("Remove Course");
      removeCourseWindow.setLocationRelativeTo(this);
      removeCourseWindow.setBounds(100,100,300,250);
      String[] courseNames = new String[courses.size()];
      int index = 0;
      for (Course i : courses) {
        courseNames[index] = i.getCourseName();
        index++;
      }
      removeCourseWindow.setLocationRelativeTo(this);
      JComboBox<String> courseDropdown = new JComboBox<String>(courseNames);
      courseDropdown.setPreferredSize(new Dimension(250, 100));

      JButton removeButton = new JButton("Remove");
      removeButton.addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e) {
          courses.remove(courseDropdown.getSelectedIndex());
          makeBoxes();


          removeCourseWindow.dispose();

        }
      });
      removeCourseWindow.setLayout(new GridLayout(2, 1));
      removeCourseWindow.add(courseDropdown);
      removeCourseWindow.add(removeButton);
      //removeCourseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      removeCourseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      removeCourseWindow.pack();
      removeCourseWindow.setVisible(true);

    }
    private void openCreateCoursePage() {
        JFrame createCourseWindow = new JFrame("Create Course");
        createCourseWindow.setLocationRelativeTo(this);
        JLabel fontLabel = new JLabel("Select Font:");
        JLabel colorLabel = new JLabel("Select Color:");
        JLabel nameLabel = new JLabel("Enter Course Name:");

        String[] fontOptions = {"Normal", "Bold", "Italic"};
        JComboBox<String> fontDropdown = new JComboBox<>(fontOptions);

        Color[] colorOptions = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, Color.BLACK};
        String[] colorNames = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE", "PINK", "CYAN", "MAGENTA", "DARK_GRAY", "GRAY", "LIGHT_GRAY", "WHITE", "BLACK"};
        JComboBox<String> colorDropdown = new JComboBox<String>(colorNames);


        JTextField nameTextField = new JTextField(20);

        JButton createButton = new JButton("Create");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedFont = fontDropdown.getSelectedIndex(); 
              int index = 0;
                for (int i = 0; i < colorOptions.length; i++) {
                  if (colorNames[i].equals(colorDropdown.getSelectedItem())) {
                    index = i;
                    break;
                  }
                }
                Color selectedColor = colorOptions[index];

                String courseName = nameTextField.getText();

                Course newCourse = new Course(selectedFont, courseName, selectedColor);
                courses.add(newCourse);

                makeBoxes();
                createCourseWindow.dispose();
            }
        });


        createCourseWindow.setLayout(new GridLayout(4, 2));
        createCourseWindow.add(fontLabel);
        createCourseWindow.add(fontDropdown);
        createCourseWindow.add(colorLabel);
        createCourseWindow.add(colorDropdown);
        createCourseWindow.add(nameLabel);
        createCourseWindow.add(nameTextField);
        createCourseWindow.add(createButton);
        createCourseWindow.setSize(400, 200);
        createCourseWindow.setLocationRelativeTo(null); 
        createCourseWindow.setVisible(true);

    }
    private void showFileUpdatedMessage() {

        JFrame fileUpdate = new JFrame("File Update");
        JButton FLButton = new JButton("Update File");
        JButton PRButton = new JButton("Update Program");
        FLButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            TxtManager.updateFile(courses);

          JOptionPane.showMessageDialog(null, "File Updated", "File Update", JOptionPane.INFORMATION_MESSAGE);
            makeBoxes();
            fileUpdate.dispose();
          }
        });
        PRButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            courses = TxtManager.updateProgram();

            JOptionPane.showMessageDialog(null, "Program Updated", "Program Update", JOptionPane.INFORMATION_MESSAGE);
            makeBoxes();
            fileUpdate.dispose();
          }
        });
        fileUpdate.setLayout(new FlowLayout());
        fileUpdate.add(FLButton);
        fileUpdate.add(PRButton);

        fileUpdate.setSize(300, 150);
        fileUpdate.setVisible(true);
        fileUpdate.setLocationRelativeTo(null);


    }




 private void makeBoxes() {
    SwingUtilities.invokeLater(() -> {
      
    if (pane != null && coursesPane !=null) {
    remove(pane);
    remove(coursesPane);
    }

    pane = new JScrollPane(); 
    coursesPane = new JTextPane();
    coursesPane.setEditable(false);
    String html = "<html>";
   pane.setBounds(50, 100, this.getWidth() - 100, this.getHeight() - 190);
    for (int i = 0; i < courses.size(); i++) { //use RGB instead of color so I dont have to use the long list of string converter i used for the file
      
      html += "<div style='background-color: rgb(" + courses.get(i).getColor().getRed() + ", " + courses.get(i).getColor().getGreen() + ", " + courses.get(i).getColor().getBlue() + "); border: 2px solid black;'>";

      html += "<h1 style='font-weight: normal'>";
      if (courses.get(i).getFont() == 1) {
        if (courses.get(i).getColor() == Color.BLACK || courses.get(i).getColor() == Color.DARK_GRAY|| courses.get(i).getColor() == Color.BLUE) {
          html += "<b><font color=\"#FFFFFF\">" + courses.get(i).getCourseName() + "</b></font>";
        }
        else {
        html += "<b>" + courses.get(i).getCourseName() + "</b>"; 
        }
      }
      if (courses.get(i).getFont() == 2) {

        if (courses.get(i).getColor() == Color.BLACK || courses.get(i).getColor() == Color.DARK_GRAY|| courses.get(i).getColor() == Color.BLUE) {
          html += "<i><font color=\"#FFFFFF\">" + courses.get(i).getCourseName() + "</i></font>";
        }
        else {

        html += "<i>" + courses.get(i).getCourseName() + "</i>"; 
        }
      }
      if (courses.get(i).getFont() == 0) {
        if (courses.get(i).getColor() == Color.BLACK || courses.get(i).getColor() == Color.DARK_GRAY || courses.get(i).getColor() == Color.BLUE) {
          html += "<font color=\"#FFFFFF\">" + courses.get(i).getCourseName() + "</font>";
        }
        else {
        html += "" + courses.get(i).getCourseName() + "";
        }
      }
      html += "</h1>";


      for (int j = 0; j < courses.get(i).getReminders().size(); j++) {
   


        if (courses.get(i).getColor() == Color.BLACK || courses.get(i).getColor() == Color.DARK_GRAY || courses.get(i).getColor() == Color.BLUE) {
       html += "<font color=\"#FFFFFF\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- " + courses.get(i).getReminders().get(j).getName() + "&nbsp;&nbsp;&nbsp;&nbsp;" + courses.get(i).getReminders().get(j).dateToString() + "</font><br>";
       html += "<font color=\"#FFFFFF\">" + "" + "</font>";
        }
        else {

          html += "<li>" + courses.get(i).getReminders().get(j).getName() + "&nbsp;&nbsp;&nbsp;&nbsp;" + courses.get(i).getReminders().get(j).dateToString() + "</li>";
        }
      }
      html += "</div>";
      html += "<br>";
    }
    html += "</html>";
    coursesPane.setContentType("text/html");
    coursesPane.setText(html);
    pane.setViewportView(coursesPane);

    add(pane);
    revalidate();

  });
 }
}



 // }

