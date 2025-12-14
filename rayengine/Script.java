package rayengine;

import com.raylib.Raylib;

public abstract class Script extends Component implements Updatable {

  public Script(GameObject parent) {
    super(parent);
  }

  protected abstract void OnUpdate(float deltaTime);

  @Override
  public void update() {
    this.OnUpdate(Raylib.GetFrameTime());
  }
  
}
