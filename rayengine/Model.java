package rayengine;

import java.util.Optional;
import com.raylib.Raylib;


public final class Model implements Resource {
  private Raylib.Model model = null;
  
  public Model(String path) {
    this.model = Raylib.LoadModel(path);
  }
  @Override
  public void release() {
    Raylib.UnloadModel(this.model);
    this.model = null;
  }
  public Optional<Raylib.Model> unwrap() {
    return Optional.ofNullable(this.model);
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsModelValid(this.model); 
  }

}