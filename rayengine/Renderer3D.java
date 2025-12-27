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

    Raylib.DrawModelEx(
      this.model.unwrap(),
      transform.getPosition().unwrap(),
      transform.getRotation().unwrap(),
      0.f,
      transform.getScale().unwrap(),
      Raylib.GetColor(0xffffffff)
    );
  }
  
}
