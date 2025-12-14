package rayengine;

import java.util.Optional;
import com.raylib.Raylib;

public class Texture implements Resource {
  private Raylib.Texture texture = null;

  public Texture(String path) {
    this.texture = Raylib.LoadTexture(path);
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsTextureValid(this.texture);
  }

  public Optional<Raylib.Texture> unwrap() {
    return Optional.ofNullable(this.texture);
  }

  @Override
  public void release() {
    Raylib.UnloadTexture(this.texture);
    this.texture = null;
  }
  
}
