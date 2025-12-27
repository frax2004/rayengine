package rayengine;

public final class Transform extends Component {
  private Vector3 position;
  private Vector3 rotation;
  private Vector3 scale;

  public Transform(GameObject parent) {
    super(parent);
    this.position = Vector3.ZERO.copy();
    this.rotation = Vector3.ZERO.copy();
    this.scale = Vector3.ONE.copy();
  }

  public Transform(GameObject parent, Vector3 position, Vector3 rotation, Vector3 scale) {
    super(parent);
    this.position = position;
    this.rotation = rotation;
    this.scale = scale;
  }

  public Vector3 getPosition() {
    return this.position;
  }

  public Vector3 getRotation() {
    return this.rotation;
  }

  public Vector3 getScale() {
    return this.scale;
  }

  public void setPosition(Vector3 position) {
    this.position = position;
  }

  public void setRotation(Vector3 rotation) {
    this.rotation = rotation;
  }

  public void setScale(Vector3 scale) {
    this.scale = scale;
  }
}