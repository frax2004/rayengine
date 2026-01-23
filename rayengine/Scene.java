package rayengine;


import java.util.Map;
import java.util.TreeMap;


import com.raylib.Raylib;

import rayengine.components.Camera3D;

public final class Scene implements Updatable, Renderable {
  private String tag;
  private GameObject camera;
  private Map<String, GameObject> gameObjects;
  private RenderLayer layer2D;
  private RenderLayer layer3D;

  public Scene() {
    this("");
  }
  
  public Scene(String tag) {
    this.tag = tag;
    this.gameObjects = new TreeMap<>();
    this.layer2D = new RenderLayer();
    this.layer3D = new RenderLayer();
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
  public GameObject getByTag(String tag) {
    return this.gameObjects.get(tag);
  }

  public RenderLayer getLayer2D() {
    return layer2D;
  }

  public RenderLayer getLayer3D() {
    return layer3D;
  }

  public GameObject getCamera() {
    return this.camera;
  }

  public String getTag(){
    return this.tag;
  }

  public boolean setCamera(GameObject camera) {
    if(camera.getComponent(Camera3D.class) != null) {
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
    
    if(this.camera != null) {
      Raylib.BeginMode3D(this.camera.getComponent(Camera3D.class).unwrap());
      Raylib.DrawGrid(10, 3);
      this.layer3D.render();
      Raylib.EndMode3D();
    }
    
    this.layer2D.render();
  }

}