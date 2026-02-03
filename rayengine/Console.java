package rayengine;


public interface Console {

  public static void errorln(String fmt, Object... args) {
    System.err.println(fmt.formatted(args));
  }

  public static void println(String fmt, Object... args) {
    System.out.println(fmt.formatted(args));
  }

  public static void print(String fmt, Object... args) {
    System.out.print(fmt.formatted(args));
  }
}
