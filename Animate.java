
import com.raylib.Raylib;

import rayengine.Canvas;
import rayengine.GameObject;
import rayengine.Script;
import rayengine.Vector2;

public class Animate extends Script {
  float x = 0;
  float theta = 0;
  float rate = 1;
  float torque = 1;
  
  public Animate(GameObject parent, float rate, float torque) {
    super(parent);
    this.rate = rate;
    this.torque = torque;
  }

  @Override
  protected void OnUpdate(float deltaTime) {
    x += deltaTime;
    theta += deltaTime;
    Canvas canvas = this.getParent().getComponent(Canvas.class).get();
    canvas.setSourcePosition(new Vector2((x*rate)/Raylib.GetRenderWidth(), 0));
    canvas.setRotation(theta*torque);
  }
    
}
