package rayengine.assets;

import com.raylib.Raylib;

import rayengine.core.Asset;


public final class Model implements Asset<Raylib.Model> {
  private Raylib.Model model = null;
  
  public Model(String path) {
    this.model = Raylib.LoadModel(path);
  }
  
  @Override
  public void release() {
    Raylib.UnloadModel(this.model);
    this.model = null;
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