package gum;

import rayengine.GameObject;
import rayengine.Vector3;
import rayengine.components.Script;

public class RotateScript extends Script {
  private float theta = 0;
  private float rho = 7;

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
      (float)(this.rho*Math.cos(this.theta)), 
      0.f, 
      (float)(this.rho*Math.sin(this.theta)))
    );
  }
  
}
