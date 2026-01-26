package gum;

import java.util.Random;

import rayengine.GameObject;
import rayengine.Vector3;
import rayengine.components.Script;

public class RotateScript extends Script {
  private float theta = 0;
  private float rho = 7;
  private float scale = new Random().nextFloat()*2.0f - 1.0f;
  private float offset = new Random().nextFloat()*(float)(2*Math.PI);

  public RotateScript(GameObject parent, float rho) {
    super(parent);
    this.rho = rho;
  }

  @Override
  protected void OnUpdate(float deltaTime) {
    this.theta += deltaTime*(1/this.rho)*2;
    this.getParent()
    .getTransform()
    .setPosition(new Vector3(
      (float)(this.rho*Math.cos(this.theta*scale + offset)), 
      0.f, 
      (float)(this.rho*Math.sin(this.theta*scale + offset)))
    );
  }
  
}
