
import com.raylib.Raylib;

import rayengine.Canvas;
import rayengine.GameObject;
import rayengine.Script;
import rayengine.Vector2;

public class Animate extends Script {
  float x = 0;
  float rate = 1;
  
  public Animate(GameObject parent, float rate) {
    super(parent);
    this.rate = rate;
  }

  @Override
  protected void OnUpdate(float deltaTime) {
    x += 50*deltaTime;
    Canvas canvas = this.getParent().getComponent(Canvas.class).get();
    canvas.setSourcePosition(new Vector2((x*rate)/Raylib.GetRenderWidth(), 0));
  }
    
}
