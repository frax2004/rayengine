import gum.GUM;
import rayengine.Game;
import rayengine.AssetManager;

interface Main {
  static void main(String[] args) {
    Game.initialize("G.U.M. (Galactic Unemployed Mercenaries)");
    System.out.println("----------------- Loading assets ------------------");

    AssetManager.loadAssets(
      "assets", 
      "assets/models/.*\\.png", "assets/models/.*\\.mtl"
    );
    System.out.println("---------------------------------------------------");
    
    new GUM().run();

    AssetManager.releaseAll();
    Game.shutdown();
  }
}
