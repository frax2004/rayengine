package rayengine.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public abstract class Layout extends StatefullWidget {
  private List<Widget> children = new ArrayList<>();

  public Layout(Widget parent) {
    super(parent);
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
