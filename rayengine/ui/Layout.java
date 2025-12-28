package rayengine.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import rayengine.Vector2;

public abstract class Layout extends StatefullWidget {
  private List<Widget> children = new ArrayList<>();
  private float[] margins = new float[4];

  public Layout(Widget parent) {
    super(parent);
  }

  @Override
  public Vector2 getPosition() {
    Vector2 position = super.getPosition();
    Vector2 size = super.getSize();
    return new Vector2(
      position.x + 0.5f*size.x*margins[Margin.EAST.ordinal()], 
      position.y + 0.5f*size.y*margins[Margin.NORTH.ordinal()]
    );
  }

  @Override 
  public Vector2 getSize() {
    Vector2 size = super.getSize();
    return new Vector2(
      size.x*(1.f - 0.5f*(margins[Margin.EAST.ordinal()] + margins[Margin.WEST.ordinal()])),
      size.y*(1.f - 0.5f*(margins[Margin.NORTH.ordinal()] + margins[Margin.SOUTH.ordinal()]))
    );
  }

  public float[] getMargins() {
    return Arrays.copyOf(this.margins, this.margins.length);
  }

  public float getMargin(Margin margin) {
    return this.margins[margin.ordinal()];
  }

  public void setHorizontalMargin(float value) {
    this.margins[Margin.EAST.ordinal()] = value;
    this.margins[Margin.WEST.ordinal()] = value;
  }
  
  public void setVerticalMargin(float value) {
    this.margins[Margin.NORTH.ordinal()] = value;
    this.margins[Margin.SOUTH.ordinal()] = value;
  }

  public void setMargins(float north, float south, float east, float west) {
    this.margins[Margin.NORTH.ordinal()] = Math.clamp(north, 0, 1);
    this.margins[Margin.SOUTH.ordinal()] = Math.clamp(south, 0, 1);
    this.margins[Margin.EAST.ordinal()] = Math.clamp(east, 0, 1);
    this.margins[Margin.WEST.ordinal()] = Math.clamp(west, 0, 1);
  }

  public void setMargin(Margin margin, float value) {
    this.margins[margin.ordinal()] = Math.clamp(value, 0, 1);
  }

  public Iterator<Widget> iterator() {
    return this.children.iterator();
  }

  public Stream<Widget> stream() {
    return this.children.stream();
  }

  public List<Widget> getChildren() {
    return List.copyOf(this.children);
  }

  public <T extends Widget> T add(T widget) {
    this.children.add(widget);
    return widget;
  }

  public void remove(Widget widget) {
    this.children.remove(widget);
  }

  public Optional<Widget> getChild(int index) {
    return Optional.ofNullable(
      index < this.children.size() ? 
        this.children.get(index) : null
    );
  }

  public int getChildCount() {
    return this.children.size();
  }
  
  public abstract void dispose();

  @Override
  public void update() {
    for(Widget widget : this.children) {
      if(widget instanceof StatefullWidget w) 
        w.update();
    }
    this.dispose();
  }

  @Override
  public void render() {
    for(Widget widget : this.children) {
      widget.render();
    }
  }
  
}
