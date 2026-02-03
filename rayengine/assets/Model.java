package rayengine.assets;

import com.raylib.Raylib;

import rayengine.core.Asset;


public final class Model implements Asset<Raylib.Model> {
  private final Raylib.Model model;
  
  public Model(String path) {
    this.model = Raylib.LoadModel(path);
  }
  
  @Override
  public void release() {
    Raylib.UnloadModel(this.model);
  }

  @Override
  public Raylib.Model unwrap() {
    return this.model;
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsModelValid(this.model); 
  }

}