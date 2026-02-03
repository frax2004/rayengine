package gum.scenes;

import gum.gui.MainMenuGUIBuilder;
import gum.scripts.Animate;
import rayengine.Game;
import rayengine.AssetManager;
import rayengine.core.GameObject;
import rayengine.core.Music;
import rayengine.core.Scene;
import rayengine.core.Texture;
import rayengine.core.Vector2;
import rayengine.core.components.MusicPlayer;
import rayengine.core.components.UI;
import rayengine.ui.Canvas;

public class MainMenu extends Scene {

  public MainMenu(Game game) {
    super(game, "Main Menu");

    Texture parallax = AssetManager.get(Texture.class, "preload/background.png");
    Texture stars = AssetManager.get(Texture.class, "preload/stars.png");
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
