package rayengine;

import java.util.Map;
import java.util.TreeMap;


import com.raylib.Raylib;

import rayengine.components.Camera3D;

public final class Scene implements Updatable, Renderable {

  private String tag;
  private GameObject camera;
  private Map<String, GameObject> gameObjects;
  private RenderLayer layer;

  public Scene() {
    this("");
  }
  
  public Scene(String tag) {
    this.tag = tag;
    this.gameObjects = new TreeMap<>();
    this.layer = new RenderLayer();
  }

  public boolean add(GameObject gameObject, String tag) {
    if(this.gameObjects.putIfAbsent(tag, gameObject) == null) {
      for(Component component : gameObject.getComponents()) {
        if(component instanceof Renderable renderable) {
          this.layer.add(renderable);
        }
      }
      return true;
    } else return false;
  }
  
  public boolean remove(String tag)  {
    GameObject removed = this.gameObjects.remove(tag);
    if(removed != null) {
      for(Component component : removed.getComponents()) {
        if(component instanceof Renderable renderable) {
          this.layer.remove(renderable);
        }
      }
      return true;
    } else return false;
  }

  public GameObject getByTagOr(String tag, GameObject other)  {
    return this.gameObjects.getOrDefault(tag, other);
  }
  public GameObject getByTag(String tag) {
    return this.gameObjects.get(tag);
  }

  public GameObject getCamera() {
    return this.camera;
  }

  public String getTag(){
    return this.tag;
  }

  public boolean setCamera(GameObject camera) {
    if(camera.getComponent(Camera3D.class) != null && this.gameObjects.containsValue(camera)) {
      this.camera = camera;
      return true;
    }
    return false;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public void update() {
    this.gameObjects.forEach((s, g) -> g.update());
  }
  
  @Override
  public void render() {

    for(Renderable renderable : this.layer.getRenderables()) {
      if(renderable instanceof Renderable3D && this.camera != null) {
        Raylib.BeginMode3D(this.camera.getComponent(Camera3D.class).unwrap());
        renderable.render();
        Raylib.EndMode3D();
      } else renderable.render();
    }
    
  }

}