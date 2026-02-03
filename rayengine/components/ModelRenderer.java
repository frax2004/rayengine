package rayengine.components;

import rayengine.assets.Model;
import rayengine.core.Color;
import rayengine.core.Component;
import rayengine.core.Core;
import rayengine.core.GameObject;
import rayengine.core.RenderContext;
import rayengine.core.Renderable3D;

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
    if(this.model == null) return;

    RenderContext ctx = Core.getRenderContext();
    Transform transform = this.getParent().getTransform();

    ctx.render(
      this.model,
      transform.getPosition(),
      transform.getRotation(),
      0.f,
      transform.getScale(),
      Color.WHITE
    );
  }
  
}
