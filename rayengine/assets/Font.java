package rayengine.assets;

import com.raylib.Raylib;

import rayengine.core.Asset;

public final class Font implements Asset<Raylib.Font> {
  private final Raylib.Font font;
  private final static Font defaultFont = new Font();

  private Font() {
    this.font = Raylib.GetFontDefault();
  }

  public Font(String path) {
    this.font = Raylib.LoadFont(path);
  }

  @Override
  public void release() {
    Raylib.UnloadFont(this.font);
  }
  
  @Override
  public Raylib.Font unwrap() {
    return this.font;
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsFontValid(this.font); 
  }

  public static Font getDefault() {
    return Font.defaultFont;
  }

}