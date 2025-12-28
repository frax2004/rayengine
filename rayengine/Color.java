package rayengine;

import com.raylib.Raylib;

public final class Color {
  public byte red;
  public byte green;
  public byte blue;
  public byte alpha;

  public Color(Raylib.Color color) {
    this.red = color.r();
    this.green = color.g();
    this.blue = color.b();
    this.alpha = color.a();
  }

  public Color(int red, int green, int blue, int alpha) {
    this.red = (byte)Math.clamp(red, 0, 255);
    this.green = (byte)Math.clamp(green, 0, 255);
    this.blue = (byte)Math.clamp(blue, 0, 255);
    this.alpha = (byte)Math.clamp(alpha, 0, 255);
  }

  public Color(int red, int green, int blue) {
    this(red, green, blue, 255);
  }

  public static Color fromNormalized(float red, float green, float blue, float alpha) {
    return new Color(
      (int)(red*255),
      (int)(green*255),
      (int)(blue*255),
      (int)(alpha*255)
    );
  }

  public static Color fromHSV(float hue, float saturation, float value) {
    return new Color(Raylib.ColorFromHSV(hue, saturation, value));
  }

  public static Color from(int hex) {
    return new Color(
      (hex & 0xff000000), 
      (hex & 0x00ff0000) >> 8, 
      (hex & 0x0000ff00) >> 16, 
      (hex & 0x000000ff) >> 24
    );
  }

  public int toInteger() {
    return (
      this.red 
      | (this.green << 8) 
      | (this.blue << 16) 
      | (this.alpha << 24)
    );
  }

  public Raylib.Color unwrap() {
    return Raylib.GetColor(this.toInteger());
  }

  @Override
  public String toString() {
    return "[%d, %d, %d, %d]".formatted((int)this.red, (int)this.green, (int)this.blue, (int)this.alpha);
  }

}
