import java.util.ArrayList;

public class Instructor {
  String name;
  ArrayList<String> courses;

  public Instructor(String name) {
    this.name = name;
    this.courses = new ArrayList<String> ();
  }

  public void teach(String courseName) {
    boolean inList = this.courses.contains(courseName);
    if ( ! inList ) {
      this.courses.add(courseName);
    }
  }

  public void teach(String[] courseNames) {
    for (int i=0; i<courseNames.length; ++i) {
      this.teach(courseNames[i]);
    }
  }

  public boolean canTeach(String courseName) {
    return this.courses.contains(courseName);
  }

  public String toString() {
    String s = this.name + ":";
    for (String course: this.courses) {
       s = s + " " + course;
    }
    return s;
  }
}
