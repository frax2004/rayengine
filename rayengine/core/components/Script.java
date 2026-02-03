package rayengine.core.components;

import com.raylib.Raylib;

import rayengine.core.Component;
import rayengine.core.GameObject;
import rayengine.core.Updatable;

public abstract class Script extends Component implements Updatable {

  public Script(GameObject parent) {
    super(parent);
  }

  public void start() {}
  public void awake() {}
  public void update(float deltaTime) {}

  @Override
  public final void update() {
    if(!this.isActive()) return;
    this.update(Raylib.GetFrameTime());
  }
  
}
