import gum.GUM;
import rayengine.Game;
import rayengine.ResourceManager;

interface Main {
  static void main(String[] args) {
    Game.initialize("G.U.M. (Galactic Unemployed Mercenaries)");
    System.out.println("----------------- Loading assets ------------------");
    ResourceManager resourceManager = ResourceManager.loadFromFolder(
      "assets", 
      "assets/models/.*\\.png", "assets/models/.*\\.mtl"
    );
    System.out.println("---------------------------------------------------");
    
    new GUM(resourceManager).run();

    resourceManager.releaseAll();
    Game.shutdown();
  }
}
