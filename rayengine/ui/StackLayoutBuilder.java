package rayengine.ui;

import java.util.List;

import rayengine.Builder;

public class StackLayoutBuilder implements Builder<StackLayout> {
  private Widget parent;
  private List<Widget> children;
  private Direction direction;

  @Override
  public StackLayout build() {
    StackLayout layout = new StackLayout(this.parent, this.direction);
    this.children.forEach(layout::add);
    return layout;
  }
  
}
