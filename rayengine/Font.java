package rayengine;

import java.util.Optional;

import com.raylib.Raylib;

public final class Font implements Resource {
  private Raylib.Font font = null;
  
  public Font(String path) {
    this.font = Raylib.LoadFont(path);
  }
  @Override
  public void release() {
    Raylib.UnloadFont(this.font);
    this.font = null;
  }
  public Optional<Raylib.Font> unwrap() {
    return Optional.ofNullable(this.font);
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsFontValid(this.font); 
  }

}