package rayengine.ui;


import java.util.Arrays;

import rayengine.core.Vector2;
import rayengine.ui.core.Layout;
import rayengine.ui.core.Widget;

public final class StackLayout extends Layout {

  private final Direction direction;
  private float[] innerMargins = new float[4];

  public StackLayout(Widget parent, Direction direction) {
    super(parent);
    this.direction = direction;
  }

  public float[] getInnerMargins() {
    return Arrays.copyOf(this.innerMargins, this.innerMargins.length);
  }

  public float getInnerMargin(Margin margin) {
    return this.innerMargins[margin.ordinal()];
  }

  public void setHorizontalInnerMargin(float value) {
    this.innerMargins[Margin.EAST.ordinal()] = value;
    this.innerMargins[Margin.WEST.ordinal()] = value;
  }
  
  public void setVerticalInnerMargin(float value) {
    this.innerMargins[Margin.NORTH.ordinal()] = value;
    this.innerMargins[Margin.SOUTH.ordinal()] = value;
  }

  public void setInnerMargins(float north, float south, float east, float west) {
    this.innerMargins[Margin.NORTH.ordinal()] = Math.clamp(north, 0, 1);
    this.innerMargins[Margin.SOUTH.ordinal()] = Math.clamp(south, 0, 1);
    this.innerMargins[Margin.EAST.ordinal()] = Math.clamp(east, 0, 1);
    this.innerMargins[Margin.WEST.ordinal()] = Math.clamp(west, 0, 1);
  }

  public void setInnerMargin(Margin margin, float value) {
    this.innerMargins[margin.ordinal()] = Math.clamp(value, 0, 1);
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

    float east = this.innerMargins[Margin.EAST.ordinal()];
    float west = this.innerMargins[Margin.WEST.ordinal()];
    float north = this.innerMargins[Margin.NORTH.ordinal()];
    float south = this.innerMargins[Margin.SOUTH.ordinal()];
    for(Widget widget : this.getChildren()) {
      float x, y, w, h;
      if(this.direction == Direction.HORIZONTAL) {
        w = length*(1.f - 0.5f*(east + west));
        h = size.y*(1.f - 0.5f*(north + south));
        x = position.x + i*length + 0.5f*w*east;
        y = position.y + 0.5f*h*north;
      } else {
        w = size.x*(1.f - 0.5f*(east + west));
        h = length*(1.f - 0.5f*(north + south));
        x = position.x + 0.5f*w*east;
        y = position.y + i*length + 0.5f*h*north;
      }
      
      widget.setPosition(new Vector2(x, y));
      widget.setSize(new Vector2(w, h));
      i++;
    }
  }
  
}
