package rayengine.assets;

import com.raylib.Raylib;

import rayengine.core.Asset;
import rayengine.core.Vector2;

public final class Texture implements Asset<Raylib.Texture> {
  private final Raylib.Texture texture;

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
  }

  public Vector2 getSize() {
    return this.texture != null ? 
      new Vector2(this.texture.width(), this.texture.height()) : Vector2.ZERO.copy();
  }

}
