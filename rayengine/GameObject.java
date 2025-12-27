package rayengine;

import java.util.ArrayList;
import java.util.List;


public final class GameObject implements Updatable, Renderable {
  private List<Component> components;
  private Scene parentScene;
  private Transform transform;

  public GameObject(Scene scene) {
    this.parentScene = scene;
    this.components = new ArrayList<>();
    this.transform = this.attach(new Transform(this));
  }

  public <T extends Component> T attach(T component) {
    this.components.add(component);
    return component;
  }

  public <T extends Component> boolean detach(T component) {
    return this.components.remove(component);
  }

  public Transform getTransform() {
    return this.transform;
  }

  public Scene getParentScene() {
    return this.parentScene;
  }

  public <T extends Component> T getComponent(Class<T> type) {
    return this.components.stream().filter(type::isInstance).map(type::cast).findFirst().orElse(null);
  }

  public <T extends Component> List<T> getComponents(Class<T> type) {
    return this.components.stream().filter(type::isInstance).map(type::cast).toList();
  }

  @Override 
  public void update() {
    this.components
    .stream()
    .filter(Updatable.class::isInstance)
    .map(Updatable.class::cast)
    .forEach(Updatable::update);
  }

  @Override 
  public void render() {
    this.components
    .stream()
    .filter(Renderable.class::isInstance)
    .map(Renderable.class::cast)
    .forEach(Renderable::render);    
  }

}
