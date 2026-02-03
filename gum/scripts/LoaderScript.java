package gum.scripts;

import static rayengine.utility.Console.println;

import gum.scenes.Lobby;
import gum.scenes.MainMenu;
import rayengine.AssetManager;
import rayengine.Game;
import rayengine.components.Script;
import rayengine.core.GameObject;

public final class LoaderScript extends Script {

  private Thread loader;
  private boolean loaded = false;

  public LoaderScript(GameObject parent) {
    super(parent);
    
  }

  @Override
  public void start() {
    this.loader = Thread.ofPlatform().factory().newThread(() -> {

      for(int i = 0; i < 10; ++i) {
        println("%s", i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) { e.printStackTrace(); }
      }

      loaded = true;
    });

    this.loader.start();
  }

  @Override
  public void update(float deltaTime) {
    Game game = this.getParent().getParentScene().getParent();
    if(loaded) {
      AssetManager.loadAssets(
        "assets", 
        "assets/models/.*\\.png", "assets/models/.*\\.mtl"
      );
      
      game.addScene(new MainMenu(game));
      game.addScene(new Lobby(game));
      
      game.setActiveScene(game.getScene("Main Menu"));
    }
  }
  
}
