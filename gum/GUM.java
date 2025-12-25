package gum;

import com.raylib.Raylib;

import rayengine.Font;
import rayengine.Game;
import rayengine.GameBuilder;
import rayengine.GameObject;
import rayengine.Music;
import rayengine.MusicPlayer;
import rayengine.ResourceManager;
import rayengine.Scene;
import rayengine.SceneBuilder;
import rayengine.Texture;
import rayengine.ui.ButtonBuilder;
import rayengine.ui.Canvas;
import rayengine.ui.Direction;
import rayengine.ui.Margin;
import rayengine.ui.StackLayout;
import rayengine.ui.Widget;
import rayengine.UI;
import rayengine.Vector2;


public class GUM {
  static ResourceManager resourceManager = new ResourceManager();
  static Game instance = null;
  static Scene mainMenu = null;
  static Scene lobby = null;

  private static Widget buildUI() {
    Texture texture = GUM.resourceManager.get(Texture.class, "titleScreen");
    Texture btn = GUM.resourceManager.get(Texture.class, "button");
    Font font = GUM.resourceManager.get(Font.class, "impact");

    StackLayout layout = new StackLayout(null, Direction.HORIZONTAL);
    layout.setHorizontalMargin(.5f);

    StackLayout inner = layout.add(new StackLayout(layout, Direction.VERTICAL));
    inner.setMargin(Margin.NORTH, .15f);
    inner.add(new Canvas(inner, texture));

    StackLayout buttons = inner.add(new StackLayout(inner, Direction.VERTICAL));
    ButtonBuilder builder = new ButtonBuilder();
    builder
    .setParent(buttons)
    .setFont(font)
    .setForeground(Raylib.GetColor(0xFFD700FF))
    .setTexture(btn)
    .setFontScale(.5f)
    .setOnHoverEnter((sender) -> {
      Raylib.SetMouseCursor(Raylib.MOUSE_CURSOR_POINTING_HAND);
      sender.setFontScale(.75f);
    }).setOnHoverExit((sender) -> {
      Raylib.SetMouseCursor(Raylib.MOUSE_CURSOR_ARROW);
      sender.setFontScale(.5f);
    });

    buttons.setVerticalMargin(.25f);
    buttons.setHorizontalMargin(.5f);
    buttons.setVerticalInnerMargin(.15f);
    buttons.add(
      builder
      .setText("START")
      .setOnMouseButtonPressed((sender, button) -> {        
        if(button == Raylib.MOUSE_BUTTON_LEFT) {
          Raylib.SetMouseCursor(Raylib.MOUSE_CURSOR_ARROW);
          sender.setFontScale(.5f);
          GUM.instance.setActiveScene(GUM.lobby);
        }
      }).build()
    );
    buttons.add(
      builder
      .setText("OPTIONS")
      .setOnMouseButtonPressed((_, _) -> {})
      .build()
    );
    buttons.add(
      builder
      .setText("CREDITS")
      .setOnMouseButtonPressed((_, _) -> {})
      .build()
    );

    return layout;
  }

  private static Scene buildLobby() {
    return new Scene();
  }

  private static Scene buildMainMenu() {

    Texture parallax = GUM.resourceManager.get(Texture.class, "background");
    Texture stars = GUM.resourceManager.get(Texture.class, "stars");
    Music mainMenuMusic = GUM.resourceManager.get(Music.class, "mainMenuMusic");

    GameObject bg = new GameObject();
    Canvas canv = new Canvas(null);
    canv.setTexture(parallax);
    canv.setSourceSize(new Vector2(.25f, .25f));
    UI _ = bg.attach(new UI(bg, canv)).get();
    bg.attach(new Animate(bg, canv, 7.5f, 0));
    Canvas canv2 = new Canvas(null);
    canv2.setTexture(stars);
    canv2.setSourceSize(new Vector2(.25f, .25f));
    UI _ = bg.attach(new UI(bg, canv2)).get();
    
    GameObject title = new GameObject();
    UI _ = title.attach(new UI(title, GUM.buildUI())).get();
    MusicPlayer musicPlayer = title.attach(new MusicPlayer(title, mainMenuMusic)).get();
    musicPlayer.play();
    
    return new SceneBuilder()
    .setTag("Main Menu")
    .add("Parallax", bg)
    .add("Title", title)
    .build();
  }

  private static void loadAllResources() {
    GUM.resourceManager.add("mainMenuMusic", new Music("assets/music/interstellar.mp3"));
    GUM.resourceManager.add("titleScreen", new Texture("assets/textures/titlescreen.png"));
    GUM.resourceManager.add("button", new Texture("assets/textures/button.png"));
    GUM.resourceManager.add("impact", new Font("C:/Windows/Fonts/impact.ttf"));
    GUM.resourceManager.add("background", new Texture("assets/textures/background.png"));
    GUM.resourceManager.add("stars", new Texture("assets/textures/stars.png"));
  }

  public static void run() {
    Game.initialize("G.U.M. (Galactic Unemployed Mercenaries)");
    GUM.loadAllResources();
    
    GUM.mainMenu = GUM.buildMainMenu();
    GUM.lobby = GUM.buildLobby();

    GUM.instance = new GameBuilder()
    .setName("G.U.M. (Galactic Unemployed Mercenaries)")
    .addScene(GUM.mainMenu)
    .addScene(GUM.lobby)
    .setActiveScene(GUM.mainMenu)
    .build();

    GUM.instance.run();
    resourceManager.releaseAll();

    Game.shutdown();
  }
}
