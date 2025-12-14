package rayengine;

import java.util.Optional;
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
  public void render() {
    this.getRootWidget().ifPresent(Widget::render);
  }

  @Override
  public void update() {
    this.getRootWidget().ifPresent(Widget::update);
  }
  
}
