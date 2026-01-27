package rayengine;

import java.util.ArrayList;
import java.util.List;

import rayengine.components.Transform;


public final class GameObject implements Updatable {
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

  public <T extends Component> 
  T attach(T component) {
    this.components.add(component);
    return component;
  }

  public <T extends Component> 
  boolean detach(T component) {
    return this.components.remove(component);
  }

  public boolean isActive() {
    return this.active;
  }

  public String getTag() {
    return this.tag;
  }

  public Transform getTransform() {
    return this.transform;
  }

  public Scene getParentScene() {
    return this.parentScene;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public <T extends Component> 
  T getComponent(Class<T> type) {
    for(Component component : this.components) {
      if(type.isInstance(component)) {
        return type.cast(component);
      }
    }
    
    return null;
  }

  public <T extends Component> 
  List<T> getComponents(Class<T> type) {
    return this
    .components
    .stream()
    .filter(type::isInstance)
    .map(type::cast)
    .toList();
  }

  public List<Component> getComponents() {
    return List.copyOf(this.components);
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override 
  public void update() {
    if(!this.active) return;
    for(Component component : this.components) {
      if(component instanceof Updatable updatable && component.isActive()) {
        updatable.update();
      }
    }
  }
}
