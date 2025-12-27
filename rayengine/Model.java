package rayengine;

import com.raylib.Raylib;


public final class Model implements Resource<Raylib.Model> {
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