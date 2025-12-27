package rayengine;


import com.raylib.Raylib;

public final class Camera extends Component implements Updatable {

  private int cameraMode = 0;
  private Raylib.Camera3D camera = new Raylib.Camera3D();

  public Camera(GameObject parent) {
    super(parent);
  }

  @Override
  public void update() {
    Raylib.UpdateCamera(this.camera, this.cameraMode);
  }
  
  public Raylib.Camera3D unwrap() {
    return this.camera;
  }
  
  public Vector3 getPosition() {
    return this.camera != null ? new Vector3(this.camera._position()) : Vector3.ZERO.copy();
  }
  
  public Vector3 getTarget() {
    return this.camera != null ? new Vector3(this.camera.target()) : Vector3.ZERO.copy();
  }
  
  public Vector3 getUp() {
    return this.camera != null ? new Vector3(this.camera.up()) : Vector3.ZERO.copy();
  }
  
  public float getFovy() {
    return this.camera != null ? this.camera.fovy() : 0;
  }
  
  public int getProjection() {
    return this.camera != null ? this.camera.projection() : Raylib.CAMERA_PERSPECTIVE;
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