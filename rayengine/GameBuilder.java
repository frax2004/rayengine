package rayengine;

import java.util.ArrayList;
import java.util.List;

public final class GameBuilder implements Builder<Game> {
  private String name;
  private List<Scene> scenes = new ArrayList<>();
  private Scene activeScene = null;

  public GameBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public GameBuilder setActiveScene(Scene activeScene) {
    this.activeScene = activeScene;
    return this;
  }

  public GameBuilder addScene(Scene scene) {
    this.scenes.add(scene);
    return this;
  }

  @Override
  public Game build() {
    return new Game(this.name, this.scenes, this.activeScene);
  }
    
}
