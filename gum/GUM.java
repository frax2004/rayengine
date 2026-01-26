package gum;

import java.util.List;
import java.util.Random;

import com.raylib.Raylib;

import rayengine.Font;
import rayengine.Game;
import rayengine.GameBuilder;
import rayengine.GameObject;
import rayengine.Music;
import rayengine.ResourceManager;
import rayengine.Scene;
import rayengine.Texture;
import rayengine.Model;

import rayengine.ui.ButtonBuilder;
import rayengine.ui.Canvas;
import rayengine.ui.Direction;
import rayengine.ui.Margin;
import rayengine.ui.StackLayout;
import rayengine.ui.Widget;
import rayengine.Vector2;
import rayengine.Vector3;

import rayengine.components.Camera3D;
import rayengine.components.MusicPlayer;
import rayengine.components.Transform;
import rayengine.components.ModelRenderer;
import rayengine.components.UI;


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
      .setOnMouseButtonPressed((__, ___) -> {})
      .build()
    );
    buttons.add(
      builder
      .setText("CREDITS")
      .setOnMouseButtonPressed((__, ___) -> {})
      .build()
    );

    return layout;
  }

  private static Scene buildLobby() {
    
    Texture parallax = GUM.resourceManager.get(Texture.class, "background");
    Texture stars = GUM.resourceManager.get(Texture.class, "stars");
    Model sun = GUM.resourceManager.get(Model.class, "sun");

    List<Model> planets = List.of(
      GUM.resourceManager.get(Model.class, "rocksy"),
      GUM.resourceManager.get(Model.class, "groover"),
      GUM.resourceManager.get(Model.class, "mars"),
      GUM.resourceManager.get(Model.class, "earth"),
      GUM.resourceManager.get(Model.class, "neptune")
    );

    Scene scene = new Scene("Lobby");

    GameObject bg = new GameObject(scene);
    Canvas canv = new Canvas(null);
    canv.setTexture(parallax);
    canv.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv));
    bg.attach(new Animate(bg, canv, 7.5f, 0));
    Canvas canv2 = new Canvas(null);
    canv2.setTexture(stars);
    canv2.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv2));
    scene.add(bg, "background");

    GameObject sunObject = new GameObject(scene);
    sunObject.getTransform().setScale(new Vector3(100, 100, 100));
    sunObject.attach(new ModelRenderer(sunObject, sun));

    int i = 1;
    for(Model model : planets) {

      GameObject planet = new GameObject(scene);
      Transform transform = planet.getTransform();
      transform.setScale(new Vector3(100, 100, 100));
      planet.attach(new RotateScript(planet, i*7.f));
      planet.attach(new PlanetSelectionScript(planet));
      
      planet.attach(new ModelRenderer(planet, model));
      scene.add(planet, "planet#" + i);
      i++;
    }

    GameObject camera = new GameObject(scene);
    Camera3D cam = camera.attach(new Camera3D(camera));
    cam.setPosition(new Vector3(20f, 20f, 20f));
    cam.setTarget(new Vector3(0, 0, 0));
    cam.setUp(new Vector3(0, 1, 0));
    cam.setFovy(45);
    cam.setProjection(Raylib.CAMERA_ORTHOGRAPHIC);

    scene.add(camera, "Camera");
    scene.add(sunObject, "Sun");
    scene.setCamera(camera);

    return scene;
  }

  private static Scene buildMainMenu() {

    Texture parallax = GUM.resourceManager.get(Texture.class, "background");
    Texture stars = GUM.resourceManager.get(Texture.class, "stars");
    Music mainMenuMusic = GUM.resourceManager.get(Music.class, "mainMenuMusic");
    
    Scene scene = new Scene("Main Menu");

    GameObject bg = new GameObject(scene);
    Canvas canv = new Canvas(null);
    canv.setTexture(parallax);
    canv.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv));
    bg.attach(new Animate(bg, canv, 7.5f, 0));
    Canvas canv2 = new Canvas(null);
    canv2.setTexture(stars);
    canv2.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv2));
    
    GameObject title = new GameObject(scene);
    title.attach(new UI(title, GUM.buildUI()));
    MusicPlayer musicPlayer = title.attach(new MusicPlayer(title, mainMenuMusic));
    musicPlayer.play();
    
    scene.add(bg, "background");
    scene.add(title, "ui");

    return scene;
  }

  private static void loadAllResources() {
    System.out.println("----------------- Loading assets ------------------");
    GUM.resourceManager.add("space", new Model("assets/models/space.obj"));
    GUM.resourceManager.add("earth", new Model("assets/models/earth.obj"));
    GUM.resourceManager.add("mars", new Model("assets/models/mars.obj"));
    GUM.resourceManager.add("groover", new Model("assets/models/groover.obj"));
    GUM.resourceManager.add("neptune", new Model("assets/models/neptune.obj"));
    GUM.resourceManager.add("rocksy", new Model("assets/models/rocksy.obj"));
    GUM.resourceManager.add("sun", new Model("assets/models/sun.obj"));

    GUM.resourceManager.add("mainMenuMusic", new Music("assets/music/interstellar.mp3"));
    GUM.resourceManager.add("titleScreen", new Texture("assets/textures/titlescreen.png"));
    GUM.resourceManager.add("button", new Texture("assets/textures/button.png"));
    GUM.resourceManager.add("impact", new Font("C:/Windows/Fonts/impact.ttf"));
    GUM.resourceManager.add("background", new Texture("assets/textures/background.png"));
    GUM.resourceManager.add("stars", new Texture("assets/textures/stars.png"));
    System.out.println("---------------------------------------------------");
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
