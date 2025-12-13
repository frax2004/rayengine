package rayengine;

import java.util.Optional;
import com.raylib.Raylib;

public final class Camera extends Component implements Updatable {

  private int cameraMode = 0;
  private Raylib.Camera3D camera = null;

  public Camera(GameObject parent) {
    super(parent);
  }

  @Override
  public void update() {
    Raylib.UpdateCamera(this.camera, this.cameraMode);
  }
  
  public Optional<Raylib.Camera3D> unwrap() {
    return Optional.ofNullable(this.camera);
  }
  
  public Vector3 getPosition() {
    return unwrap()
    .map(Raylib.Camera3D::_position)
    .map(Vector3::new)
    .orElse(Vector3.ZERO);
  }
  
  public Vector3 getTarget() {
    return unwrap()
    .map(Raylib.Camera3D::target)
    .map(Vector3::new)
    .orElse(Vector3.ZERO);
  }
  
  public Vector3 getUp() {
    return unwrap()
    .map(Raylib.Camera3D::up)
    .map(Vector3::new)
    .orElse(Vector3.ZERO);
  }
  
  public float getFovy() {
    return unwrap()
    .map(Raylib.Camera3D::fovy)
    .orElse(0.f);
  }
  
  public int getProjection() {
    return unwrap()
    .map(Raylib.Camera3D::projection)
    .orElse(0);
  }
  
  public int getMode() {
    return this.cameraMode;
  }
  
  public void setPosition(Vector3 position) {
    this.camera._position(position.unwrap());
  }
  
  public void setTarget(Vector3 target) {
    this.camera.target(target.unwrap());
  }
  
  public void setUp(Vector3 up) {
    this.camera.up(up.unwrap());
  }
  
  public void setFovy(float fovy) {
    this.camera.fovy(fovy);
  }
  
  public void setProjection(int projection) {
    this.camera.projection(projection);
  }
  
  public void setMode(int mode) {
    this.cameraMode = mode;
  }
  
}