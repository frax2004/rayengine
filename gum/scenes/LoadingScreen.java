package gum.scenes;

import gum.scripts.Animate;
import gum.scripts.LoaderScript;
import rayengine.AssetManager;
import rayengine.Game;
import rayengine.assets.Texture;
import rayengine.components.UI;
import rayengine.core.GameObject;
import rayengine.core.Scene;
import rayengine.core.Vector2;
import rayengine.ui.Canvas;
import rayengine.ui.Direction;
import rayengine.ui.StackLayout;
import rayengine.ui.core.Widget;

public final class LoadingScreen extends Scene {

  public static Widget buildUI() {
    Texture texture = AssetManager.get(Texture.class, "preload/titlescreen.png");
    StackLayout layout = new StackLayout(null, Direction.VERTICAL);
    layout.setMargins(.15f, .15f, .15f, .15f);
    layout.add(new Canvas(layout, texture));

    return layout;
  }

  public LoadingScreen(Game parent) {
    super(parent, "Loading Screen");

    GameObject loader = new GameObject(this, "loader");
    loader.attach(new LoaderScript(loader));
    this.add(loader);
    
    Texture parallax = AssetManager.get(Texture.class, "preload/background.png");
    Texture stars = AssetManager.get(Texture.class, "preload/stars.png");

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
    title.attach(new UI(title, LoadingScreen.buildUI()));
    
    this.add(bg);
    this.add(title);

  }
  
}
