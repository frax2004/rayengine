package rayengine.core;

import java.util.ArrayList;
import java.util.List;

import rayengine.core.components.Transform;

public final class GameObject implements Updatable, Renderable {
  private List<Component> components;
  private Scene parentScene;
  private Transform transform;
  private String tag;
  private boolean active;

  public GameObject(Scene parent, String tag) {
    this.parentScene = parent;
    this.components = new ArrayList<>();
    this.transform = this.attach(new Transform(this));
    this.active = true;
    this.tag = tag;
  }

  public final <T extends Component> 
  T attach(T component) {
    this.components.add(component);
    return component;
  }

  public final <T extends Component> 
  boolean detach(T component) {
    return this.components.remove(component);
  }

  public final boolean isActive() {
    return this.active;
  }

  public final String getTag() {
    return this.tag;
  }

  public final Transform getTransform() {
    return this.transform;
  }

  public final Scene getParentScene() {
    return this.parentScene;
  }

  public final void setActive(boolean active) {
    this.active = active;
  }

  public final <T extends Component> 
  T getComponent(Class<T> type) {
    for(Component component : this.components) {
      if(type.isInstance(component)) {
        return type.cast(component);
      }
    }
    
    return null;
  }

  public final <T extends Component> 
  List<T> getComponents(Class<T> type) {
    return this
    .components
    .stream()
    .filter(type::isInstance)
    .map(type::cast)
    .toList();
  }

  public final List<Component> getComponents() {
    return List.copyOf(this.components);
  }

  public final void setTag(String tag) {
    this.tag = tag;
  }

  @Override 
  public final void update() {
    for(Component component : this.components) {
      if(component instanceof Updatable updatable && component.isActive()) {
        updatable.update();
      }
    }
  }

  @Override
  public final void render() {
    
    for(Component component : this.getComponents()) {
      if(component instanceof Renderable renderable && component.isActive())
        renderable.render();
    }

  }

}
