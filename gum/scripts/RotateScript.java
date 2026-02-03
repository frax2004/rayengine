package gum.scripts;

import java.util.Random;

import rayengine.components.Script;
import rayengine.core.GameObject;
import rayengine.core.Vector3;

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
  public void update(float deltaTime) {
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
