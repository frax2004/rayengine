package rayengine.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Patterns {

  public static final class Match<T> {
    private T pattern;
    private List<Predicate<T>> filters;
    private List<Function<? super T, ?>> mappers;

    private Match(T pattern) {
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
    return new Match<>(pattern);
  }
}
