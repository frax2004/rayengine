package rayengine.core;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import rayengine.Game;
import rayengine.core.components.Camera3D;


public class Scene implements Updatable, Renderable {
  private String tag;
  private GameObject camera;
  private final Map<String, GameObject> gameObjects;
  private final Game parent;

  public Scene(Game parent, String tag) {
    this.tag = tag;
    this.gameObjects = new TreeMap<>();
    this.parent = parent;
  }

  public final boolean add(GameObject gameObject) {
    return this.gameObjects.putIfAbsent(gameObject.getTag(), gameObject) == null;
  }
  
  public final boolean remove(String tag)  {
    return this.gameObjects.remove(tag) != null;
  }

  public final Game getParent() {
    return this.parent;
  }

  public final Collection<GameObject> getGameObjects() {
    return this.gameObjects.values();
  }

  public final GameObject getOr(String tag, GameObject other)  {
    return this.gameObjects.getOrDefault(tag, other);
  }

  public final GameObject get(String tag) {
    return this.gameObjects.get(tag);
  }

  public final GameObject getCamera() {
    return this.camera;
  }

  public final String getTag() {
    return this.tag;
  }

  public final boolean setCamera(GameObject camera) {
    if(camera.getComponent(Camera3D.class) != null && this.gameObjects.containsValue(camera)) {
      this.camera = camera;
      return true;
    }
    return false;
  }

  public final void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public final void update() {
    for(GameObject gameObject : this.gameObjects.values()) {
      if(gameObject.isActive()) gameObject.update();
    }
  }
  
  @Override
  public final void render() {
    Renderer ctx = Core.getRenderContext();

    Camera3D cam = this.camera != null ? this.camera.getComponent(Camera3D.class) : null;
    
    ctx.setCamera(cam);
    for(GameObject gameObject : this.gameObjects.values()) {
      if(gameObject.isActive()) gameObject.render();
    }
    ctx.setCamera(null);
  }

}