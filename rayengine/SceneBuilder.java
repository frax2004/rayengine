package rayengine;

import java.util.Map;
import java.util.TreeMap;

public final class SceneBuilder {
  private String tag = "";
  private Map<String, GameObject> gameObjects = new TreeMap<>();

  public SceneBuilder() {}

  public SceneBuilder setTag(String tag) {
    this.tag = tag;
    return this;
  }

  public SceneBuilder add(String id, GameObject gameObject) {
    this.gameObjects.putIfAbsent(id, gameObject);
    return this;
  }

  public Scene build() {
    Scene scene = new Scene(this.tag);
    gameObjects
    .entrySet()
    .stream()
    .forEach(e -> scene.add(e.getValue(), e.getKey()));
    return scene;
  }
}
