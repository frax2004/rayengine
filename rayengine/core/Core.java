package rayengine.core;

import com.raylib.Raylib;

import rayengine.Game;

public final class Core {
  private Settings settings;
  private static Core instance;

  private Core(Settings settings) {
    this.settings = settings;
  }

  public static String getTitle() {
    return Core.instance.settings.title();
  }

  public static RenderContext getRenderContext() {
    return Core.instance.settings.renderContext();
  }

  public static int getFramerateLimit() {
    return Core.instance.settings.fps();
  }

  public static void run(Game game) {
    RenderContext ctx = Core.instance.settings.renderContext();

    while(!Raylib.WindowShouldClose()) {
      Scene scene = game.getActiveScene();
      
      if(scene != null) scene.update();

      ctx.start();
      if(scene != null) scene.render();
      ctx.finish();
    }

  }

  public static void initialize(Settings settings) {
    Core.instance = new Core(settings);
    int configFlags = Raylib.FLAG_WINDOW_RESIZABLE | Raylib.FLAG_MSAA_4X_HINT | Raylib.FLAG_WINDOW_HIGHDPI | Raylib.FLAG_WINDOW_ALWAYS_RUN;
    Vector2 size = Core.instance.settings.windowSize();

    Raylib.InitAudioDevice();
    Raylib.SetConfigFlags(configFlags);
    Raylib.InitWindow((int)size.x, (int)size.y, Core.instance.settings.title());
    Raylib.SetTargetFPS(Core.instance.settings.fps());

  }

  public static void shutdown() {
    Raylib.CloseWindow();
    Raylib.CloseAudioDevice();
  }

}
