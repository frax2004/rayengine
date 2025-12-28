package rayengine.components;

import com.raylib.Raylib;

import rayengine.Component;
import rayengine.GameObject;
import rayengine.Model;
import rayengine.Renderable3D;

public final class ModelRenderer extends Component implements Renderable3D {
  private Model model = null;

  public ModelRenderer(GameObject parent, Model model) {    
    super(parent);
    this.model = model;
  }

  public Model getModel() {
    return this.model;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  @Override
  public void render() {
    if(!this.isActive() || this.model == null) return;
    Transform transform = this.getParent().getTransform();

    Raylib.DrawModelEx(
      this.model.unwrap(),
      transform.getPosition().unwrap(),
      transform.getRotation().unwrap(),
      0.f,
      transform.getScale().unwrap(),
      Raylib.GetColor(0xffffffff)
    );
  }
  
}
