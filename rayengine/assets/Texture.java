package rayengine.assets;

import com.raylib.Raylib;

import rayengine.core.Asset;

public class Texture implements Asset<Raylib.Texture> {
  private Raylib.Texture texture = null;

  public Texture(String path) {
    this.texture = Raylib.LoadTexture(path);
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsTextureValid(this.texture);
  }

  @Override
  public Raylib.Texture unwrap() {
    return this.texture;
  }

  @Override
  public void release() {
    Raylib.UnloadTexture(this.texture);
    this.texture = null;
  }
  
}
