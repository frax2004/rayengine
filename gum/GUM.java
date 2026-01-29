package gum;

import java.util.List;

import gum.scenes.Lobby;
import gum.scenes.MainMenu;
import rayengine.Game;
import rayengine.ResourceManager;
import rayengine.Scene;


public final class GUM extends Game {
  private final ResourceManager rm;

  public GUM(ResourceManager rm) {
    super(
      "G.U.M. (Galactic Unemployed Mercenaries)",
      List.of(), 
      null
    );

    this.rm = rm;

    Scene mainMenu = this.addScene(new MainMenu(this, this.rm));
    this.addScene(new Lobby(this, this.rm));
    
    this.setActiveScene(mainMenu);
  }
}
