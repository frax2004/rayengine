package rayengine.ui;

import com.raylib.Raylib;

import rayengine.Vector2;

public final class Panel extends StatelessWidget {
  private Raylib.Color color;

  public Panel(Widget parent, Raylib.Color color) {
    super(parent);
    this.color = color;
  }

  public Raylib.Color getColor() {
    return this.color;
  }

  public void setColor(Raylib.Color color) {
    this.color = color;
  }

  @Override
  public void render() {
    Vector2 position = this.getPosition();
    Vector2 size = this.getSize();

    Raylib.DrawRectangleV(position.unwrap(), size.unwrap(), this.color);
  }

}
