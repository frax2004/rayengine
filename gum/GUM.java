package gum;
import gum.scenes.Lobby;
import gum.scenes.MainMenu;
import rayengine.Game;


public final class GUM extends Game {

  private GUM() {
    super("G.U.M. (Galactic Unemployed Mercenaries)");

    this.addScene(new MainMenu(this));
    this.addScene(new Lobby(this));
    this.setActiveScene(this.getScene("Main Menu"));
  }

  public static void launch() {
    Game.initialize("G.U.M. (Galactic Unemployed Mercenaries)");
    Game.loadAssets(
      "assets", 
      "assets/models/.*\\.png", "assets/models/.*\\.mtl"
    );
    
    new GUM().run();
    
    Game.shutdown();
  }
}
