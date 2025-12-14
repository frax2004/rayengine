package rayengine.ui;

import java.util.Optional;

import rayengine.Renderable;
import rayengine.Updatable;
import rayengine.Vector2;

public abstract class Widget implements Updatable, Renderable {
  private Widget parent = null;
  private Vector2 position = Vector2.ZERO.copy();
  private Vector2 size = Vector2.ZERO.copy();

  public Widget(Widget parent) {
    this.parent = parent;
  }

  public Optional<? extends Widget> getParentWidget() {
    return Optional.ofNullable(this.parent);
  }

  public Vector2 getSize() {
    return this.size;
  }

  public Vector2 getPosition() {
    return this.position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public void setSize(Vector2 size) {
    this.size = size;
  }

}
