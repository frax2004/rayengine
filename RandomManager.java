public interface RandomManager {
  /// 13 => venerdi 13
  /// 67 => six-seven
  /// 89 => il capobranco dei suoi paguri
  static final int[] PRIMES = new int[] { 13, 67, 89 };
  static final int LEVEL_COUNT = 5;
  static final int SCENARIO_COUNT = 4;
  static final int EVENT_COUNT = 3;

  static int getMappedLevel(int seed, int levelIndex) {
    return (seed + PRIMES[0]*levelIndex) % LEVEL_COUNT;
  }

  static int getMappedScenario(int seed, int levelIndex, int scenarioIndex) {
    return (seed + PRIMES[0]*levelIndex + PRIMES[1]*scenarioIndex) % SCENARIO_COUNT;
  }

  static int getMappedEvent(int seed, int levelIndex, int scenarioIndex, int eventIndex) {
    return (seed + PRIMES[0]*levelIndex + PRIMES[1]*scenarioIndex + PRIMES[2]*eventIndex) % EVENT_COUNT;
  }

}


void use_case() {
  Class<? extends Event>[] events = Events.getPossibileEvents();

  /// seed 0, first level, first scenario, first possibile event index
  int[] indexes = new int[] {
    RandomManager.getMappedEvent(0, 0, 0, 0),
    RandomManager.getMappedEvent(0, 0, 0, 1),
    RandomManager.getMappedEvent(0, 0, 0, 2)
  };

  /// 0 - 1 - 2
  int chosen = ctx.getInputManager().getUserInteger();

  Event event = Factories.make(events[indexes[chosen]], possibileConstructorParams);

  // ...
  event.execute(ctx);
  // ...
}