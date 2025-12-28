package rayengine.ui;

import rayengine.Renderable2D;
import rayengine.Vector2;

public abstract class Widget implements Renderable2D {
  private Widget parent;
  private Vector2 position;
  private Vector2 size;

  public Widget(Widget parent) {
    this.parent = parent;
    this.position = Vector2.ZERO.copy();
    this.size = Vector2.ZERO.copy();
  }

  public Widget getParentWidget() {
    return this.parent;
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
