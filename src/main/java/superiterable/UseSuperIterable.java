package superiterable;

import java.util.List;
import java.util.function.Predicate;

public class UseSuperIterable {
  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
    return e -> first.test(e) && second.test(e);
  }

  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        List.of("Fred", "Jim", "Sheila")
    );

//    SuperIterable<String> longStrings = sis.filter(s -> s.length() > 3);
    Predicate<String> intermediatePredicate =
        and(s -> s.length() > 3, s -> s.length() < 6);
    SuperIterable<String> interestingStrings =
        sis
//            .filter(s -> s.length() > 3)
//            .filter(s -> s.length() < 6);
//            .filter(s -> s.length() > 3 && s.length() < 6);
            .filter(intermediatePredicate);
    for (String s : interestingStrings) {
      System.out.println(s);
    }


  }
}
