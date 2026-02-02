package gum;

import gum.scenes.Lobby;
import gum.scenes.MainMenu;
import rayengine.Game;


public final class GUM extends Game {

  public GUM() {
    super("G.U.M. (Galactic Unemployed Mercenaries)");

    this.addScene(new MainMenu(this));
    this.addScene(new Lobby(this));

    this.setActiveScene(this.getScene("Main Menu"));
  }
}
