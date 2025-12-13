package rayengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
  private final String name;
  private List<Scene> scenes = new ArrayList<>();
  private Scene activeScene = null;

  public Game() {
    this.name = "";
  }
  
  public Game(String name) {
    this.name = name;
  }

  public Game(String name, List<Scene> scenes) {
    this.name = name;
    this.scenes = scenes;
  }

  public String getName() {
    return this.name;
  }

  public List<Scene> getScenes() {
    return this.scenes;
  }
  
  public Optional<Scene> getActiveScene() {
    return Optional.ofNullable(this.activeScene);
  }
}
