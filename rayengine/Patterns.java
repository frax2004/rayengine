package rayengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Patterns {

  private static Patterns instance = new Patterns();
  
  private Patterns() {}

  private class Match<T> {
    private T pattern;
    private List<Predicate<T>> filters;
    private List<Function<? super T, ?>> mappers;

    public Match(T pattern) {
      this.pattern = pattern;
      this.filters = new ArrayList<>();
    }

    public Match<T> with(Predicate<T> match, Function<? super T, ?> mapper) {
      filters.add(match);
      mappers.add(mapper);
      return this;
    }

    public Match<T> with(Predicate<T> match) {
      filters.add(match);
      mappers.add(Function.identity());
      return this;
    }

    public <U> Optional<U> get(Class<? extends U> target) {
      for(int i = 0; i < filters.size(); ++i) {
        if(filters.get(i).test(pattern)) 
          return Optional.ofNullable(
            this.mappers
            .get(i)
            .apply(this.pattern)
          )
          .map(target::cast);
      }

      return Optional.empty();
    }

    public <U> U orElse(Class<? extends U> target, U other) {
      for(int i = 0; i < filters.size(); ++i) {
        if(filters.get(i).test(pattern)) 
          return target
          .cast(this.mappers
            .get(i)
            .apply(this.pattern)
          );
      }

      return other;
    }

  }

  public static <T> Match<T> match(T pattern) {
    return Patterns.instance.new Match<>(pattern);
  }
  
  private enum Color {
    RED, GREEN, BLUE;
  }
  
  void use() {
    Color color = Color.RED;

    String name = Patterns.match(color)
    .with(Color.RED::equals   , c -> "red"     )
    .with(Color.GREEN::equals , c -> "green"   )
    .with(Color.BLUE::equals  , c -> "blue"    )
    .with(i -> true           , c -> "unknown" )
    .orElse(String.class, "");

  }
}
