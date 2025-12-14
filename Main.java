import rayengine.*;
import com.raylib.Raylib;


interface Main {
  static void main(String[] args) {
    Raylib.InitAudioDevice();
    Raylib.SetConfigFlags(Raylib.FLAG_WINDOW_RESIZABLE);
    Raylib.InitWindow(1200, 800, "G.U.M. (Galactic Unemployed Mercenaries)");
    Raylib.SetTargetFPS(60);

    ResourceManager resourceManager = new ResourceManager();
    Music mainMenuMusic = resourceManager.add("mainMenuMusic", new Music("assets/music/main menu.mp3"));
    Texture titleScreen = resourceManager.add("titleScreen", new Texture("assets/textures/titlescreen.png"));
    Texture parallax = resourceManager.add("parallax", new Texture("assets/textures/parallax.png"));
    Texture buttonTexture = resourceManager.add("button", new Texture("assets/textures/button.png"));
    Font arial = resourceManager.add("arial", new Font("C:/Windows/Fonts/arial.ttf"));

    SceneBuilder sceneBuilder = new SceneBuilder()
    .setTag("Main Menu");

    float[] factors = {-.75f, 1, -1.5f};
    for(int i = 0; i < 3; ++i) {
      GameObject o = new GameObject();
      Canvas canv = o.attach(new Canvas(o)).get();
      canv.setTexture(parallax);
      canv.setScissorRect(new Rectangle(0, i*.333f, 1, .333f));
      o.attach(new Animate(o, factors[i]));
      sceneBuilder.add("Parallax" + (i+1), o);
    }

    
    GameObject obj1 = new GameObject();
    Canvas canvas = obj1.attach(new Canvas(obj1)).get();
    canvas.setTexture(titleScreen);
    canvas.setDestinationPosition(new Vector2(.2f, 0.f));
    canvas.setDestinationSize(new Vector2(.6f, .55f));
    MusicPlayer musicPlayer = obj1.attach(new MusicPlayer(obj1, mainMenuMusic)).get();
    musicPlayer.play();

    sceneBuilder.add("Title", obj1);
    
    Scene scene = sceneBuilder.build();

    while(! Raylib.WindowShouldClose()) {

      scene.update();

      Raylib.BeginDrawing();
      Raylib.ClearBackground(Raylib.GetColor(0x141C33FF));
      scene.render();

      Raylib.EndDrawing();

    }

    resourceManager.releaseAll();
    
    Raylib.CloseWindow();
    Raylib.CloseAudioDevice();
  }
}