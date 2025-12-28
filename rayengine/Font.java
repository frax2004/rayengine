package rayengine;

import com.raylib.Raylib;

public final class Font implements Resource<Raylib.Font> {
  private Raylib.Font font;
  
  public Font(String path) {
    this.font = Raylib.LoadFont(path);
  }

  @Override
  public void release() {
    Raylib.UnloadFont(this.font);
    this.font = null;
  }
  
  @Override
  public Raylib.Font unwrap() {
    return this.font;
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsFontValid(this.font); 
  }

}