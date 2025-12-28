package rayengine;

import java.util.List;

import com.raylib.Raylib;

public class Game {
  private final String name;
  private List<Scene> scenes;
  private Scene activeScene;

  public Game(String name, List<Scene> scenes, Scene activeScene) {
    this.name = name;
    this.scenes = scenes;
    this.activeScene = activeScene;
  }

  public String getName() {
    return this.name;
  }

  public List<Scene> getScenes() {
    return this.scenes;
  }
  
  public Scene getActiveScene() {
    return this.activeScene;
  }

  public void setActiveScene(Scene activeScene) {
    this.activeScene = activeScene;
  }

  public static void initialize(String title) {
    Raylib.InitAudioDevice();
    Raylib.SetConfigFlags(Raylib.FLAG_WINDOW_RESIZABLE | Raylib.FLAG_MSAA_4X_HINT | Raylib.FLAG_WINDOW_HIGHDPI | Raylib.FLAG_WINDOW_ALWAYS_RUN);
    Raylib.InitWindow(1200, 800, title);
    Raylib.SetTargetFPS(60);
  }

  public static void shutdown() {
    Raylib.CloseWindow();
    Raylib.CloseAudioDevice();
  }
  
  public void run() {
    while(!Raylib.WindowShouldClose()) {
      if(this.activeScene != null)
        activeScene.update();

      Raylib.BeginDrawing();
      Raylib.ClearBackground(Raylib.GetColor(0x141C33FF));
      
      if(this.activeScene != null)
        activeScene.render();

      Raylib.EndDrawing();
    }

  }
}
