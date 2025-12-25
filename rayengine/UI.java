package rayengine;

import java.util.Optional;

import com.raylib.Raylib;

import rayengine.ui.StatefullWidget;
import rayengine.ui.Widget;

public final class UI extends Component implements Updatable, Renderable {

  private Widget root = null;

  public UI(GameObject parent, Widget root) {
    super(parent);
    this.root = root;
  }

  public Optional<? extends Widget> getRootWidget() {
    return Optional.ofNullable(this.root);
  }
  
  public void setRootWidget(Widget root) {
    this.root = root;
  }

  @Override
  public void update() {
    if(this.root != null) {
      this.root.setPosition(Vector2.ZERO.copy());
      this.root.setSize(new Vector2(Raylib.GetRenderWidth(), Raylib.GetRenderHeight()));

      if(this.root instanceof StatefullWidget widget)
        widget.update();

    }
  }
  
  @Override
  public void render() {
    if(this.root != null) 
      this.root.render();
  }

}
