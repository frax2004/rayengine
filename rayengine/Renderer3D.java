package rayengine;

import com.raylib.Raylib;

public final class Renderer3D extends Component implements Renderable {
  private Model model = null;

  public Renderer3D(GameObject parent, Model model) {
    super(parent);
    this.model = model;
  }

  @Override
  public void render() {
    if(this.model == null) return;
    Transform transform = this.getParent().getTransform();
    Camera camera = this.getParent()
    .getParentScene()
    .getCamera()
    .flatMap(g -> g.getComponent(Camera.class))
    .orElse(null);
    
    if(camera == null) return;
    Raylib.BeginMode3D(camera.unwrap().get());
    Raylib.UpdateCamera(camera.unwrap().get(), Raylib.CAMERA_FREE);
    Raylib.DrawModelEx(
      this.model.unwrap().get(),
      transform.getPosition().unwrap(),
      transform.getRotation().unwrap(),
      0.f,
      transform.getScale().unwrap(),
      Raylib.GetColor(0xffffffff)
    );

    Raylib.EndMode3D();
  }
  
}
