package gum;
import gum.scenes.LoadingScreen;
import rayengine.AssetManager;
import rayengine.Game;
import rayengine.RaylibRenderer;
import rayengine.core.Core;
import rayengine.core.Settings;
import rayengine.core.Vector2;


public final class GUM extends Game {

  private GUM() {
    super("G.U.M. (Galactic Unemployed Mercenaries)");

    this.addScene(new LoadingScreen(this));
    this.setActiveScene(this.getScene("Loading Screen"));
  }

  public static void launch() {
    Core.initialize(
      new Settings(
        "G.U.M. (Galactic Unemployed Mercenaries)",
        new Vector2(1200, 800),
        60,
        new RaylibRenderer()
      )
    );

    AssetManager.loadAssets("preload");

    Core.run(new GUM());

    AssetManager.releaseAll();
    
    Core.shutdown();
  }
}
