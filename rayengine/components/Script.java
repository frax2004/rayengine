package rayengine.components;

import com.raylib.Raylib;

import rayengine.core.Component;
import rayengine.core.GameObject;
import rayengine.core.Updatable;

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
