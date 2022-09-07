package students;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//interface Predicate<E> {
//  boolean test(E e);
//}

class SmartStudentPredicate implements Predicate<Student> {
  @Override
  public boolean test(Student student) {
    return student.getGpa() > 3;
  }
}

class EnthusiasticStudentPredicate implements Predicate<Student> {
  @Override
  public boolean test(Student student) {
    return student.getCourses().size() > 3;
  }
}

public class School {
  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("------------------");
  }

//  public static void setSmartThreshold(double threshold) {
//    School.threshold = threshold;
//  }

  // When used with behavioral expressions, we call this the
  // "Strategy" pattern. Sometimes referred to as "composition"
//  static double threshold = 3;
//  public static void showSmart(List<Student> ls) {
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        System.out.println("> " + s);
//      }
//    }
//    System.out.println("------------------");
//  }
//  public static List<Student> getSmart(List<Student> ls, double threshold) {
//    List<Student> results = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        results.add(s);
//      }
//    }
//    return results;
//  }

  // passing an argument primarily for the BEHAVIOR it contains
  // is called (in OO Design Patterns) the Command pattern
  // in FP this is merely a "Higher Order Function"
  public static List<Student> getByPredicate(Iterable<Student> ls,
                                            Predicate<Student> pred) {
    List<Student> results = new ArrayList<>();
    for (Student s : ls) {
      if (pred.test(s)) {
        results.add(s);
      }
    }
    return results;
  }

//  public static List<Student> getEnthusiastic(List<Student> ls, int threshold) {
  // make your interfaces as "accepting/flexible" as reasonably possible
  // in their argument types -- argument types constrain callers
  // return types constrain implementations
//  public static List<Student> getEnthusiastic(Iterable<Student> ls,
//                                              int threshold) {
//    List<Student> results = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        results.add(s);
//      }
//    }
//    return results;
//  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.8, "Math", "Physics",
            "Astrophysics", "Quantum Mechanics")
    );
    // print all students
    showAll(roster);
    // print all smart students
//    showAll(getSmart(roster, 3.2));
    // or sendToDatabase(getSmart(...

    System.out.println("smart:");
    showAll(getByPredicate(roster, new SmartStudentPredicate()));
    System.out.println("enthusiastic:");
    showAll(getByPredicate(roster, new EnthusiasticStudentPredicate()));
    System.out.println("un-enthusiastic:");
    showAll(getByPredicate(roster,
        student -> student.getCourses().size() < 3));
    // BEHAVIOR AS AN EXPRESSION!!!!
  }
}
