package gum.scenes;

import java.util.List;

import com.raylib.Raylib;

import gum.scripts.Animate;
import gum.scripts.PlanetSelectionScript;
import gum.scripts.RotateScript;
import rayengine.Game;
import rayengine.AssetManager;
import rayengine.assets.Model;
import rayengine.assets.Texture;
import rayengine.components.Camera3D;
import rayengine.components.ModelRenderer;
import rayengine.components.Transform;
import rayengine.components.UI;
import rayengine.core.GameObject;
import rayengine.core.Scene;
import rayengine.core.Vector2;
import rayengine.core.Vector3;
import rayengine.ui.Canvas;

public class Lobby extends Scene {

  public Lobby(Game game) {
    super(game, "Lobby");

    Texture parallax = AssetManager.get(Texture.class, "preload/background.png");
    Texture stars = AssetManager.get(Texture.class, "preload/stars.png");
    Model sun = AssetManager.get(Model.class, "assets/models/sun.obj");

    List<Model> planets = List.of(
      AssetManager.get(Model.class, "assets/models/rocksy.obj"),
      AssetManager.get(Model.class, "assets/models/groover.obj"),
      AssetManager.get(Model.class, "assets/models/mars.obj"),
      AssetManager.get(Model.class, "assets/models/earth.obj"),
      AssetManager.get(Model.class, "assets/models/neptune.obj")
    );

    GameObject bg = new GameObject(this, "background");
    Canvas canv = new Canvas(null);
    canv.setTexture(parallax);
    canv.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv));
    bg.attach(new Animate(bg, canv, 7.5f, 0));

    Canvas canv2 = new Canvas(null);
    canv2.setTexture(stars);
    canv2.setSourceSize(new Vector2(.25f, .25f));
    bg.attach(new UI(bg, canv2));
    this.add(bg);

    GameObject sunObject = new GameObject(this, "sun");
    sunObject.getTransform().setScale(new Vector3(100, 100, 100));
    sunObject.attach(new ModelRenderer(sunObject, sun));

    int i = 1;

    for(Model model : planets) {
      GameObject planet = new GameObject(this, "planet#" + i);
      Transform transform = planet.getTransform();
      transform.setScale(new Vector3(100, 100, 100));
      planet.attach(new RotateScript(planet, i*7.f));
      planet.attach(new PlanetSelectionScript(planet));
      
      planet.attach(new ModelRenderer(planet, model));
      this.add(planet);
      i++;
    }

    GameObject camera = new GameObject(this, "camera");
    Camera3D cam = camera.attach(new Camera3D(camera));
    cam.setPosition(new Vector3(20f, 20f, 20f));
    cam.setTarget(new Vector3(0, 0, 0));
    cam.setUp(new Vector3(0, 1, 0));
    cam.setFovy(45);
    cam.setProjection(Raylib.CAMERA_ORTHOGRAPHIC);

    this.add(camera);
    this.add(sunObject);
    this.setCamera(camera);

  }
  

}
