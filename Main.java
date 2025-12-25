
import com.raylib.Raylib;

import rayengine.Font;
import rayengine.GameObject;
import rayengine.Music;
import rayengine.MusicPlayer;
import rayengine.ResourceManager;
import rayengine.Scene;
import rayengine.SceneBuilder;
import rayengine.Texture;
import rayengine.ui.Direction;
import rayengine.ui.Panel;
import rayengine.ui.StackLayout;
import rayengine.ui.Widget;
import rayengine.UI;


interface Main {

  private static Widget buildUI() {
    StackLayout layout = new StackLayout(null, Direction.VERTICAL);
    layout.add(new Panel(layout, Raylib.GetColor(0xff0000_ff)));
    layout.add(new Panel(layout, Raylib.GetColor(0xffff00_ff)));
    layout.add(new Panel(layout, Raylib.GetColor(0x00ff00_ff)));
    layout.add(new Panel(layout, Raylib.GetColor(0x0000ff_ff)));
    return layout;
  }

  private static GameObject buildBg() {
    GameObject bg = new GameObject();
    bg.attach(new UI(bg, Main.buildUI()));
    return bg;
  }

  static void main(String[] args) {
    Raylib.InitAudioDevice();
    Raylib.SetConfigFlags(Raylib.FLAG_WINDOW_RESIZABLE);
    Raylib.InitWindow(1200, 800, "G.U.M. (Galactic Unemployed Mercenaries)");
    Raylib.SetTargetFPS(60);

    ResourceManager resourceManager = new ResourceManager();
    Music mainMenuMusic = resourceManager.add("mainMenuMusic", new Music("assets/music/interstellar.mp3"));
    Texture titleScreen = resourceManager.add("titleScreen", new Texture("assets/textures/titlescreen.png"));
    Texture buttonTexture = resourceManager.add("button", new Texture("assets/textures/button.png"));
    Font arial = resourceManager.add("arial", new Font("C:/Windows/Fonts/arial.ttf"));
    Texture parallax = resourceManager.add("parallax", new Texture("assets/textures/background.png"));
    Texture stars = resourceManager.add("parallax", new Texture("assets/textures/stars.png"));

    // Canvas canv = bg.attach(new Canvas(bg)).get();
    // canv.setTexture(parallax);
    // canv.setSourceSize(new Vector2(.25f, .25f));
    // bg.attach(new Animate(bg, 7.5f, 0));
    // Canvas canv2 = bg.attach(new Canvas(bg)).get();
    // canv2.setTexture(stars);
    // canv2.setSourceSize(new Vector2(.25f, .25f));
    
    GameObject bg = buildBg();

    GameObject title = new GameObject();
    // Canvas canvas = title.attach(new Canvas(title)).get();
    // canvas.setTexture(titleScreen);
    // canvas.setDestinationPosition(new Vector2(.2f, 0.f));
    // canvas.setDestinationSize(new Vector2(.6f, .55f));
    MusicPlayer musicPlayer = title.attach(new MusicPlayer(title, mainMenuMusic)).get();
    musicPlayer.play();
    
    Scene scene = new SceneBuilder()
    .setTag("Main Menu")
    .add("Parallax", bg)
    .add("Title", title)
    .build();
    
    while(!Raylib.WindowShouldClose()) {
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
