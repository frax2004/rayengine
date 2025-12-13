package rayengine;

import com.raylib.Raylib;

public final class Vector3 {
  
  public float x = 0;   
  public float y = 0;
  public float z = 0;  
  public final static Vector3 X = new Vector3(1, 0, 0);
  public final static Vector3 Y = new Vector3(0, 1, 0);
  public final static Vector3 Z = new Vector3(0, 0, 1);
  public final static Vector3 ZERO = new Vector3(0, 0, 0);
  public final static Vector3 ONE = new Vector3(1, 1, 1);

  public Vector3() {}

  public Vector3(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public Vector3(Raylib.Vector3 other) {
    this.x = other.x();
    this.y = other.y();
    this.z = other.z();
  }

  public Vector3 xxx() {
    return new Vector3(this.x, this.x, this.x);
  }
  public Vector3 yyy() {
    return new Vector3(this.y, this.y, this.y);
  }
  public Vector3 zzz() {
    return new Vector3(this.z, this.z, this.z);
  }
  public Vector3 copy() {
    return new Vector3(this.x, this.y, this.z);
  }
  public float dot(Vector3 other) {
    return this.x*other.x + this.y*other.y + this.z*other.z;
  }
  public float norm() {
    return (float)Math.sqrt((double)this.dot(this));
  }
  public Vector3 normalized() {
    float n = this.norm();
    return n != 0 ? new Vector3(this.x / n, this.y / n, this.z / n) : new Vector3(0, 0, 0);
  }
  public float sum() {
    return this.x + this.y + this.z;
  }
  public float max() {
    return Math.max(Math.max(this.x, this.y), this.z);
  }
  public float min() {
    return Math.min(Math.min(this.x, this.y), this.z);
  }
  public Raylib.Vector3 unwrap() {
    Raylib.Vector3 vec = new Raylib.Vector3();
    vec.x(this.x).y(this.y).z(this.z);
    return vec;
  }  
}; 

