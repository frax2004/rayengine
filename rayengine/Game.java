package rayengine;

import static rayengine.Console.println;

import java.util.ArrayList;
import java.util.List;

import com.raylib.Raylib;

public class Game {
  private final String name;
  private List<Scene> scenes;
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
  }

  public static void initialize(String title) {
    Raylib.InitAudioDevice();
    Raylib.SetConfigFlags(Raylib.FLAG_WINDOW_RESIZABLE | Raylib.FLAG_MSAA_4X_HINT | Raylib.FLAG_WINDOW_HIGHDPI | Raylib.FLAG_WINDOW_ALWAYS_RUN);
    Raylib.InitWindow(1200, 800, title);
    Raylib.SetTargetFPS(60);
  }

  public static void loadAssets(String folderPath, String... toExclude) {
    println("----------------- Loading assets ------------------");
    AssetManager.loadAssets(folderPath, toExclude);
    println("---------------------------------------------------");
  }

  public static void shutdown() {
    AssetManager.releaseAll();
    Raylib.CloseWindow();
    Raylib.CloseAudioDevice();
  }
  
  public final void run() {
    while(!Raylib.WindowShouldClose()) {
      if(this.activeScene != null)
        activeScene.update();

      Raylib.BeginDrawing();
      Raylib.ClearBackground(Raylib.GetColor(0x000000ff));
      
      if(this.activeScene != null)
        activeScene.render();

      Raylib.EndDrawing();
    }

  }
}
