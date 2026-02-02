package rayengine.core;

public abstract class Component {
  private final GameObject parent;
  private boolean active;

  public Component(GameObject parent) {
    this.parent = parent;
    this.active = true;
  }
  
  public final boolean isActive() {
    return this.active;
  }

  public final void setActive(boolean active) {
    this.active = active;
  }

  public final GameObject getParent() {
    return parent;
  }

}
