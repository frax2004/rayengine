package rayengine.ui;

import com.raylib.Raylib;

import rayengine.core.Color;
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
    Vector2 position = this.getPosition();
    Vector2 size = this.getSize();

    Raylib.DrawRectangleV(position.unwrap(), size.unwrap(), this.color.unwrap());
  }

}
