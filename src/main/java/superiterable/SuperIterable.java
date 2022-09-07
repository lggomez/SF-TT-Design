package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(SuperIterable<E> this,
                                 Predicate<E> pred) {
    List<E> results = new ArrayList<>();
    for (E s : this.self) {
      if (pred.test(s)) {
        results.add(s);
      }
    }
    return new SuperIterable<>(results);
  }

  // "Bucket o'data" with the ability to apply a transformation
  // to every item, and produce a new bucket of the same type
  // containing the results
  // Called "Functor" -- variation of Higher Order Function and Command
  public <F> SuperIterable<F> map(SuperIterable<E> this,
                                 Function<E, F> op) {
    List<F> results = new ArrayList<>();
    for (E s : this.self) {
      results.add(op.apply(s));
    }
    return new SuperIterable<>(results);
  }

  // This kind of operation makes the container a "Monad"
  public <F> SuperIterable<F> flatMap(SuperIterable<E> this,
                                 Function<E, SuperIterable<F>> op) {
    List<F> results = new ArrayList<>();
    for (E s : this.self) {
      SuperIterable<F> manyF = op.apply(s);
      for (F f : manyF) {
        results.add(f);
      }
    }
    return new SuperIterable<>(results);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
