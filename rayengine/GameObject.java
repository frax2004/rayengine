package rayengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public final class GameObject implements Updatable, Renderable {
  private Map<Class<? extends Component>, Component> components = new HashMap<>();

  public GameObject() {}

  public <T extends Component> Optional<T> attach(T component) {
    return Optional.ofNullable(
      this.components.putIfAbsent(component.getClass(), component) == null ? component : null
    );
  }
  public <T extends Component> boolean detach(T component) {
    return this.components.remove(component.getClass()) != null;
  }
  public <T extends Component> Optional<T> getComponent(Class<T> type) {
    return Optional.ofNullable(
      type.cast(this.components.getOrDefault(type, null))
    );
  }
  @Override 
  public void update() {
    this.components
    .values()
    .stream()
    .filter(Updatable.class::isInstance)
    .map(Updatable.class::cast)
    .forEach(Updatable::update);
  }
  @Override 
  public void render() {
    this.components
    .values()
    .stream()
    .filter(Renderable.class::isInstance)
    .map(Renderable.class::cast)
    .forEach(Renderable::render);    
  }

}
