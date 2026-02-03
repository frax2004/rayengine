package rayengine.components;


import rayengine.core.Component;
import rayengine.core.Core;
import rayengine.core.GameObject;
import rayengine.core.Renderable2D;
import rayengine.core.Updatable;
import rayengine.core.Vector2;
import rayengine.ui.core.StatefullWidget;
import rayengine.ui.core.Widget;

public final class UI extends Component implements Updatable, Renderable2D {

  private Widget root = null;

  public UI(GameObject parent, Widget root) {
    super(parent);
    this.root = root;
  }

  public Widget getRootWidget() {
    return this.root;
  }
  
  public void setRootWidget(Widget root) {
    this.root = root;
  }

  @Override
  public void update() {
    if(!this.isActive()) return;
    if(this.root != null) {
      this.root.setPosition(Vector2.ZERO.copy());
      this.root.setSize(Core.getRenderContext().getRenderSize());

      if(this.root instanceof StatefullWidget widget)
        widget.update();

    }
  }
  
  @Override
  public void render() {
    if(this.isActive() && this.root != null) {
      this.root.render();
    }
  }

}
