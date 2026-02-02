package rayengine.core;

import com.raylib.Raylib;


public final class Vector2 {
  public float x = 0;
  public float y = 0;
  public final static Vector2 X = new Vector2(1, 0);
  public final static Vector2 Y = new Vector2(0, 1);
  public final static Vector2 ZERO = new Vector2(0, 0);
  public final static Vector2 ONE = new Vector2(1, 1);

  public Vector2(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public Vector2() {}

  public Vector2(Raylib.Vector2 other) {
    this.x = other.x();
    this.y = other.y();
  }
  
  public Vector2 xx() {
    return new Vector2(this.x, this.x);
  }
  public Vector2 yy() {
    return new Vector2(this.y, this.y);
  }
  public Vector2 yx() {
    return new Vector2(this.y, this.x);
  }
  public Vector2 copy() {
    return new Vector2(this.x, this.y);
  }
  public float dot(Vector2 other) {
    return this.x * other.x + this.y * other.y;
  }
  public float norm() {
    return (float)Math.sqrt((double)this.dot(this));
  }
  public Vector2 normalized() {
    float n = this.norm();
    return n != 0 ? new Vector2(this.x / n, this.y / n) : new Vector2(0, 0);
  }
  public float sum() {
    return this.x + this.y;
  }
  public float max() {
    return Math.max(this.x, this.y);
  }
  public float min() {
    return Math.min(this.x, this.y);
  }
  public Raylib.Vector2 unwrap() {
    Raylib.Vector2 vec = new Raylib.Vector2();
    vec.x(this.x).y(this.y);
    return vec;
  }
  @Override
  public String toString() {
    return "[%s, %s]".formatted(this.x, this.y);
  }
}
