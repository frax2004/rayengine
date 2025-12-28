package rayengine;

import java.util.ArrayList;
import java.util.List;

import rayengine.components.Transform;


public final class GameObject implements Updatable {
  private List<Component> components;
  private Scene parentScene;
  private Transform transform;
  private boolean active;

  public GameObject(Scene scene) {
    this.parentScene = scene;
    this.components = new ArrayList<>();
    this.transform = this.attach(new Transform(this));
    this.active = true;
  }

  public <T extends Component> T attach(T component) {
    this.components.add(component);
    if(component instanceof Renderable2D renderable) {
      this.parentScene.getLayer2D().add(renderable);
    } else if(component instanceof Renderable3D renderable) {
      this.parentScene.getLayer3D().add(renderable);
    }
    return component;
  }

  public <T extends Component> boolean detach(T component) {
    if(this.components.remove(component)) {
      if(component instanceof Renderable3D renderable) {
        this.parentScene.getLayer2D().remove(renderable);
      } else if(component instanceof Renderable2D renderable) {
        this.parentScene.getLayer3D().remove(renderable);
      }
      return true;
    }
    return false;
  }

  public boolean isActive() {
    return this.active;
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

  public <T extends Component> T getComponent(Class<T> type) {
    return this.components.stream().filter(type::isInstance).map(type::cast).findFirst().orElse(null);
  }

  public <T extends Component> List<T> getComponents(Class<T> type) {
    return this.components.stream().filter(type::isInstance).map(type::cast).toList();
  }

  public List<Component> getComponents() {
    return List.copyOf(this.components);
  }

  @Override 
  public void update() {
    if(!this.active) return;
    this.components
    .stream()
    .filter(Updatable.class::isInstance)
    .map(Updatable.class::cast)
    .forEach(Updatable::update);
  }
}
