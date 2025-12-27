package rayengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public final class GameObject implements Updatable, Renderable {
  private List<Component> components = new ArrayList<>();
  private Scene parentScene = null;
  private Transform transform = new Transform(this, Vector3.ZERO.copy(), Vector3.ZERO.copy(), Vector3.ONE.copy());

  public GameObject(Scene scene) {
    this.parentScene = scene;
    this.attach(this.transform);
  }

  public <T extends Component> Optional<T> attach(T component) {
    return Optional
    .ofNullable(component)
    .filter(c -> this.components.add((Component)c));
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

  public <T extends Component> Optional<T> getComponent(Class<T> type) {
    return this
    .components
    .stream()
    .filter(type::isInstance)
    .map(type::cast)
    .findFirst();
  }

  public <T extends Component> List<T> getComponents(Class<T> type) {
    return this
    .components
    .stream()
    .filter(type::isInstance)
    .map(type::cast)
    .toList();
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
