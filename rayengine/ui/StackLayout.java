package rayengine.ui;


import rayengine.Vector2;

public final class StackLayout extends Layout {

  private final Direction direction;

  public StackLayout(Widget parent, Direction direction) {
    super(parent);
    this.direction = direction;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public boolean isHorizontal() {
    return this.direction == Direction.HORIZONTAL;
  }

  public boolean isVertical() {
    return this.direction == Direction.VERTICAL;
  }

  @Override
  public void dispose() {

    Vector2 position = this.getPosition();
    Vector2 size = this.getSize();
    float count = (float)this.getChildCount();
    float length = (this.direction == Direction.HORIZONTAL ? size.x : size.y)/count;
    int i = 0;

    for(Widget widget : this.getChildren()) { 
      float x, y, w, h;
      if(this.direction == Direction.HORIZONTAL) {
        x = position.x + i*length;
        y = position.y;
        w = length;
        h = size.y;
      } else {
        x = position.x;
        y = position.y + i*length;
        w = size.x;
        h = length;
      }
      
      widget.setPosition(new Vector2(x, y));
      widget.setSize(new Vector2(w, h));
      i++;
    }
  }
  
}
