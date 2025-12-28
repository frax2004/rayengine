package rayengine.components;

import com.raylib.Raylib;

import rayengine.Component;
import rayengine.GameObject;
import rayengine.Updatable;

public abstract class Script extends Component implements Updatable {

  public Script(GameObject parent) {
    super(parent);
  }

  protected abstract void OnUpdate(float deltaTime);

  @Override
  public void update() {
    if(!this.isActive()) return;
    this.OnUpdate(Raylib.GetFrameTime());
  }
  
}
