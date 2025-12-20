import java.util.stream.IntStream;

public interface RandomManager {
  /// 13 => venerdi 13
  /// 67 => six-seven
  /// 89 => il capobranco dei suoi paguri
  /// 101 => la caricano in 101
  static final int[] PRIMES = new int[] { 1, 13, 67, 89, 101 };

  static int convolution(int[] lhs, int[] rhs) {
    assert lhs.length == rhs.length;
    return IntStream
    .range(0, lhs.length)
    .map(i -> lhs[i]*rhs[i])
    .sum();
  }

  static int getMappedLevel(int seed, int levelIndex) {
    return convolution(PRIMES, new int[] {seed, levelIndex, 0, 0, 0 }) % Levels.getPossibleLevels().length;
  }

  static int getMappedScenario(int seed, int levelIndex, int scenarioIndex) {
    return convolution(PRIMES, new int[] {seed, levelIndex, scenarioIndex, 0, 0 }) % Scenarioes.getPossibleScenarios().length;
  }

  static int getMappedEvent(int seed, int levelIndex, int scenarioIndex, int eventIndex) {
    return convolution(PRIMES, new int[] {seed, levelIndex, scenarioIndex, eventIndex, 0 }) % Events.getPossibleEvents().length;
  }
  
  static int getMappedReward(int seed, int levelIndex, int scenarioIndex, int eventIndex, int playerIndex, int rewardLength) {
    return convolution(PRIMES, new int[] {seed, levelIndex, scenarioIndex, eventIndex, playerIndex }) % rewardLength;
  }
  
}



void use_case() {

  List<Class<? extends Event>> events = Arrays.asList(Events.getPossibileEvents());
  


  int[] indexes = new int[] {
    /// seed 0, first level, first scenario, first possibile event index
    RandomManager.getMappedEvent(0, 0, 0, 0),
    /// seed 0, first level, first scenario, second possibile event index
    RandomManager.getMappedEvent(0, 0, 0, 1),
    /// seed 0, first level, first scenario, third possibile event index
    RandomManager.getMappedEvent(0, 0, 0, 2)
  };

  /// 0 - 1 - 2
  int chosen = ctx.getInputManager().getUserInteger();

  Event event = Factories.make(events[indexes[chosen]], possibileConstructorParams);

  // ...
  event.execute(ctx);
  // ...
}