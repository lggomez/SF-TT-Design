package students;

import java.util.List;
import java.util.function.Predicate;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  // Never expose a public constructor. It invites clients using new
  private Student(String name, double gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  // Factory :) much better idea (or use a Builder)
  public static Student of(String name, double gpa, String ... courses) {
    return new Student(name, gpa, List.of(courses));
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  // returning an object primarily for its behavior is
  // sometimes called a behavior factory
  public static Predicate<Student> getSmartPredicate(double threshold) {
    // Java closures must be final or effectively final
//    threshold++; // NOT PERMITTED
    return s -> s.gpa > threshold;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}
