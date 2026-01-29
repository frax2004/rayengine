package rayengine;

import java.util.Map;
import java.util.TreeMap;

import com.raylib.Raylib;

import rayengine.components.Camera3D;

public class Scene implements Updatable, Renderable {

  private String tag;
  private GameObject camera;
  private Map<String, GameObject> gameObjects;
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

  public final GameObject getOr(String tag, GameObject other)  {
    return this.gameObjects.getOrDefault(tag, other);
  }
  public final GameObject get(String tag) {
    return this.gameObjects.get(tag);
  }

  public final GameObject getCamera() {
    return this.camera;
  }

  public final String getTag(){
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
    Raylib.Camera3D cam = this.camera != null ? this.camera.getComponent(Camera3D.class).unwrap() : null;

    for(GameObject gameObject : this.gameObjects.values()) {
      if(!gameObject.isActive()) continue;

      for(Component component : gameObject.getComponents()) {
        if(!component.isActive()) continue;

        if(component instanceof Renderable3D renderable && cam != null) {
          Raylib.BeginMode3D(cam);
          renderable.render();
          Raylib.EndMode3D();
        } else if(component instanceof Renderable2D renderable) renderable.render();
      }

    }
  }

}