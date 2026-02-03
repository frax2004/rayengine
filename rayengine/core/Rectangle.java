package rayengine.core;

import com.raylib.Raylib;

public final class Rectangle {
  public float x, y, width, height;

  public Rectangle() {}

  public Rectangle(Rectangle other) {
    this(other.x, other.y, other.width, other.height);
  }

  public Rectangle(Raylib.Rectangle rect) {
    this(rect.x(), rect.y(), rect.width(), rect.height());
  }

  public Rectangle(Vector2 position, Vector2 size) {
    this(position.x, position.y, size.x, size.y);
  }

  public Rectangle(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width; 
    this.height = height;
  }

  public Vector2 getPosition() {
    return new Vector2(this.x, this.y);
  }
  
  public Vector2 getSize() {
    return new Vector2(this.width, this.height);
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

  public boolean intersectsWith(Rectangle other) {
    return Raylib.CheckCollisionRecs(this.unwrap(), other.unwrap());
  }

  public boolean intersectsWith(Vector2 other) {
    return Raylib.CheckCollisionPointRec(other.unwrap(), this.unwrap());
  }

  public Rectangle intersect(Rectangle other) {
    return new Rectangle(Raylib.GetCollisionRec(this.unwrap(), other.unwrap()));
  }

  @Override
  public String toString() {
    return "[%s, %s, %s, %s]".formatted(this.x, this.y, this.width, this.height);
  }
}
