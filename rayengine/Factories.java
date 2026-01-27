package rayengine;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public interface Factories {

  static private int getInheritanceDistance(Class<?> base, Class<?> derived) {
    if (derived == null || base == null || derived.equals(base)) return 0;
        
    if (base.isInterface()) {
      return 1; 
    }

    int distance = 0;
    while (derived != null && !derived.equals(base)) {
      derived = derived.getSuperclass();
      distance++;
    }

    return distance;
  }

  /** 
   * Constructs a generic object of type {@code T} with the provided arguments
   * @param <T> The type of the object to construct
   * @param type the class of the object to construct
   * @param args the constructor arguments
   * @return An optional containig the possibly constructed object
   * @apiNote The object is succesfully constructed {@code if and only if} there is
   * at least a constructor of class {@code T} that matches the arguments types.
   * Otherwise, this function fails succesfully with an empty optional.
   * @apiNote The object is succesfully constructed {@code if and only if} the 
   * instance is succesfully constructed, that is, 
   * the actual constructed does not throws an exception 
   */ 
  @SuppressWarnings("unchecked")
  static <T> Optional<T> make(Class<T> type, Object... args) {
    try {
      return Optional.of(
        List.of(type.getDeclaredConstructors())
        .stream()
        .map(c -> (Constructor<T>)c)
        .filter(c -> c.getParameterCount() == args.length)
        .filter(
          c -> IntStream
          .iterate(0, i -> i+1)
          .limit(c.getParameterCount())
          .allMatch(
            i -> 
            c.getParameterTypes()[i]
            .isAssignableFrom(args[i].getClass())
          )
        )
        .min((C1, C2) -> IntStream
          .iterate(0, i -> i+1)
          .limit(C1.getParameterCount())
          .map(i -> getInheritanceDistance(C1.getParameterTypes()[i], args[i].getClass()))
          .boxed()
          .reduce(0, Integer::sum)
          .compareTo(
            IntStream
            .iterate(0, i -> i+1)
            .limit(C2.getParameterCount())
            .map(i -> getInheritanceDistance(C2.getParameterTypes()[i], args[i].getClass()))
            .sum()
          )
        )
        .orElse(null)
        .newInstance(args)
      );
    } catch(Exception e) {
      return Optional.empty();
    }
  }
}

