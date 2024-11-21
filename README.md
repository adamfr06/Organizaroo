# Organizaroo

**Organizaroo** is a desktop Java application for organizing courses and assignments. It uses a graphical user interface (GUI) to let users add, edit, and remove courses and their associated assignments. The application also supports saving and loading data from a text file for persistent storage.

---

## Features

- **Course Management:**
  - Add new courses with custom names, font styles, and colors.
  - Remove existing courses.
  - Edit course properties, including name, font, and color.

- **Assignment Management:**
  - Add assignments to courses with names and due dates.
  - Edit existing assignments' details.
  - Remove assignments.

- **Persistent Data:**
  - Save all courses and assignments to a file (`OrgData.txt`).
  - Load data from the file upon program startup.

- **User Interface:**
  - Uses `Swing` for an intuitive GUI.
  - Displays courses and assignments in a visually organized format with customizable colors and styles.

---

## How to Use

1. **Run the Program:**
   - Compile and run the program by executing `Main.java`.

2. **Main Window:**
   - View your courses and assignments displayed in an organized list.

3. **Menu Options:**
   - Use the menu (`≡`) to:
     - Add/Remove Courses
     - Add/Remove Assignments
     - Update the program's data file or reload it.
     - Edit course or assignment properties.

4. **Add/Remove Courses:**
   - Add a course by specifying a name, font style (normal, bold, italic), and color.
   - Remove a course from the list using a dropdown selector.

5. **Add/Remove Assignments:**
   - Add assignments to courses by specifying a name and due date.
   - Remove assignments from a selected course using a dropdown menu.

6. **Edit:**
   - Modify course names, fonts, and colors.
   - Update assignment details, including name and due date.

7. **Save/Load Data:**
   - The program automatically loads data from `OrgData.txt` on startup.
   - Save your changes to `OrgData.txt` using the "Update File" option.

---

## Requirements

- **Java JDK**: Version 8 or higher
- **Libraries Used**: `javax.swing`, `java.awt`

---

## File Structure

- **Main.java:** Entry point of the application.
- **Window.java:** Manages the GUI and main program logic.
- **Course.java:** Represents individual courses, including properties and assignment management.
- **Reminder.java:** Represents assignments tied to courses with due dates.
- **TxtManager.java:** Handles saving and loading data from `OrgData.txt`.
- **OrgData.txt:** Data file used for persistent storage of courses and assignments.

---

## Example Data (`OrgData.txt`)

```plaintext
Science
0
GREEN
Chemistry lab
2
5
2006
-
History
0
YELLOW
-
Math
0
RED
-
END
```

### Explanation:
- Each course starts with its name, font style (`0` = normal, `1` = bold, `2` = italic), and color.
- Assignments under the course are listed with:
  - Name
  - Day, Month, Year
- `-` marks the end of a course's assignments.
- `END` marks the end of the file.

---

## Version History

### v1.0
- Initial release with full course and assignment management features.
- File handling for persistent data storage.

---

**Author:** polp7  

