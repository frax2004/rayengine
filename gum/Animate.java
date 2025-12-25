package gum;

import com.raylib.Raylib;

import rayengine.GameObject;
import rayengine.Script;
import rayengine.Vector2;
import rayengine.ui.Canvas;

public class Animate extends Script {
  private float x = 0;
  private float theta = 0;
  private float rate = 1;
  private float torque = 1;
  private Canvas canvas = null;

  public Animate(GameObject parent, Canvas canvas, float rate, float torque) {
    super(parent);
    this.rate = rate;
    this.torque = torque;
    this.canvas = canvas;
  }

  @Override
  protected void OnUpdate(float deltaTime) {
    x += deltaTime;
    theta += deltaTime;
    this.canvas.setSourcePosition(new Vector2((x*rate)/Raylib.GetRenderWidth(), 0));
    this.canvas.setRotation(theta*torque);
  }
    
}
