package rayengine.ui;

import rayengine.core.Color;
import rayengine.core.Core;
import rayengine.core.Rectangle;
import rayengine.core.RenderContext;
import rayengine.core.Vector2;
import rayengine.ui.core.StatelessWidget;
import rayengine.ui.core.Widget;

public final class Panel extends StatelessWidget {
  private Color color;

  public Panel(Widget parent, Color color) {
    super(parent);
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public void render() {
    final RenderContext ctx = Core.getRenderContext();
    final Vector2 position = this.getPosition();
    final Vector2 size = this.getSize();

    ctx.render(new Rectangle(position, size), color);
  }

}
