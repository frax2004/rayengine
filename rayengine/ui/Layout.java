package rayengine.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class Layout extends Widget {
  private List<? extends Widget> children = new ArrayList<>();

  public Layout(Widget parent) {
    super(parent);
  }

  public Iterator<? extends Widget> iterator() {
    return this.children.iterator();
  }

  public Optional<? extends Widget> getChild(int index) {
    return Optional.ofNullable(
      index < this.children.size() ? 
        this.children.get(index) : null
    );
  }

  public int getChildCount() {
    return this.children.size();
  }
  
  @Override
  public void update() {
  
  }

  @Override
  public void render() {
    
  }
  
}
