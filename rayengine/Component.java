package rayengine;

public abstract class Component {
  private final GameObject parent;

  public Component(GameObject parent) {
    this.parent = parent;
  }

  public final GameObject getParent() {
    return parent;
  }
}
