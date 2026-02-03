package rayengine;

import java.util.ArrayList;
import java.util.List;

import rayengine.core.Scene;
import rayengine.core.components.Script;

public class Game {
  private final String name;
  private final List<Scene> scenes;
  private Scene activeScene;

  public Game(String name) {
    this.name = name;
    this.scenes = new ArrayList<>();
    this.activeScene = null;
  }

  public Game(String name, List<Scene> scenes, Scene activeScene) {
    this.name = name;
    this.scenes = new ArrayList<>(scenes);
    this.activeScene = activeScene;
  }

  public final String getName() {
    return this.name;
  }

  public final <T extends Scene>
  T addScene(T scene) {
    this.scenes.add(scene);
    return scene;
  }

  public final List<Scene> getScenes() {
    return this.scenes;
  }

  public final Scene getScene(String tag) {
    for(Scene scene : this.scenes) {
      if(scene.getTag().equals(tag)) return scene;
    }

    return null;
  }
  
  public final Scene getActiveScene() {
    return this.activeScene;
  }

  public final void setActiveScene(Scene activeScene) {
    this.activeScene = activeScene;
    if(this.activeScene != null) {
      this.activeScene
      .getGameObjects()
      .stream()
      .flatMap(g -> g.getComponents(Script.class).stream())
      .forEach(Script::start);
    }
  }

}
