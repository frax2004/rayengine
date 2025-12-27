package rayengine;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public final class Scene implements Updatable, Renderable {
  private String tag = "";
  private GameObject camera = null;
  private Map<String, GameObject> gameObjects = new TreeMap<>();

  public Scene() {}
  
  public Scene(String tag) {
    this.tag = tag;
  }

  public boolean add(GameObject gameObject, String tag) {
    return this.gameObjects.putIfAbsent(tag, gameObject) == null;
  }

  public boolean remove(String tag)  {
    return this.gameObjects.remove(tag) != null;
  }

  public GameObject getByTagOr(String tag, GameObject other)  {
    return this.gameObjects.getOrDefault(tag, other);
  }
  public Optional<GameObject> getByTag(String tag) {
    return Optional.ofNullable(this.gameObjects.get(tag));
  }

  public Optional<GameObject> getCamera() {
    return Optional.ofNullable(this.camera);
  }

  public String getTag(){
    return this.tag;
  }

  public boolean setCamera(GameObject camera) {
    if(camera.getComponent(Camera.class).isPresent()) {
      this.camera = camera;
      return true;
    }
    return false;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public void update()  {
    this.gameObjects.forEach((s, g) -> g.update());
  }
  
  @Override
  public void render() {
    this.gameObjects.forEach((s, g) -> g.render());
  }
}