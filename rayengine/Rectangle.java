package rayengine;

import com.raylib.Raylib;

public final class Rectangle {
  public float x, y, width, height;

  public Rectangle(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width; 
    this.height = height;
  }

  public Raylib.Rectangle unwrap() {
    Raylib.Rectangle rect = new Raylib.Rectangle();
    rect
    .x(this.x)
    .y(this.y)
    .width(this.width)
    .height(this.height);
    return rect;
  }

  @Override
  public String toString() {
    return "[%s, %s, %s, %s]".formatted(this.x, this.y, this.width, this.height);
  }
}
