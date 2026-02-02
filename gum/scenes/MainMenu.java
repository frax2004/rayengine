package gum.scenes;

import gum.Animate;
import gum.gui.MainMenuGUIBuilder;
import rayengine.Game;
import rayengine.GameObject;
import rayengine.Music;
import rayengine.AssetManager;
import rayengine.Scene;
import rayengine.Texture;
import rayengine.Vector2;
import rayengine.components.MusicPlayer;
import rayengine.components.UI;
import rayengine.ui.Canvas;

public class MainMenu extends Scene {

  public MainMenu(Game game) {
    super(game, "Main Menu");

    Texture parallax = AssetManager.get(Texture.class, "assets/textures/background.png");
    Texture stars = AssetManager.get(Texture.class, "assets/textures/stars.png");
    Music mainMenuMusic = AssetManager.get(Music.class, "assets/music/interstellar.mp3");


    GameObject bg = new GameObject(this, "Background");
    Canvas canv = new Canvas(null);
    canv.setTexture(parallax);
    canv.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv));
    bg.attach(new Animate(bg, canv, 7.5f, 0));
    Canvas canv2 = new Canvas(null);
    canv2.setTexture(stars);
    canv2.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv2));
    
    GameObject title = new GameObject(this, "Title");
    title.attach(new MainMenuGUIBuilder(title).build());
    MusicPlayer musicPlayer = title.attach(new MusicPlayer(title, mainMenuMusic));
    musicPlayer.play();
    
    this.add(bg);
    this.add(title);

  }
  
}
