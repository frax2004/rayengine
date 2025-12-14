package rayengine;

import java.util.Optional;

import com.raylib.Raylib;

public final class Canvas extends Component implements Renderable {
  private Vector2 srcPosition = Vector2.ZERO.copy(); // [0, 1]
  private Vector2 srcSize = Vector2.ONE.copy(); // [0, 1]
  private Vector2 dstPosition = Vector2.ZERO.copy(); // [0, 1]
  private Vector2 dstSize = Vector2.ONE.copy(); // [0, 1]
  private Optional<Texture> texture = null;
  private Rectangle scissor = null;
  private float rotation = 0;

  public Canvas(GameObject parent) {
    super(parent);
  }

  public Canvas(GameObject parent, Texture texture) {
    super(parent);
    this.texture = Optional.ofNullable(texture);
  }
  
  public Optional<Rectangle> getScissorRect() {
    return Optional.ofNullable(this.scissor);
  }

  public void setScissorRect(Rectangle rect) {
    this.scissor = new Rectangle(
      Math.clamp(rect.x, 0, 1),
      Math.clamp(rect.y, 0, 1),
      Math.clamp(rect.width, 0, 1),
      Math.clamp(rect.height, 0, 1)
    );
  }

  public void setTexture(Texture texture) {
    this.texture = Optional.ofNullable(texture);
  }
  
  public void setSourceSize(Vector2 size) {
    this.srcSize = new Vector2(
      size.x,
      size.y
    );
  }

  public float getRotation() {
    return this.rotation;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  public void setSourcePosition(Vector2 position) {
    this.srcPosition = new Vector2(
      position.x,
      position.y
    );
    
  }

  public void setDestinationSize(Vector2 size) {
    this.dstSize = new Vector2(
      Math.clamp(size.x, 0.f, 1.f),
      Math.clamp(size.y, 0.f, 1.f)
    );
  }
  
  public void setDestinationPosition(Vector2 position) {
    this.dstPosition = new Vector2(
      Math.clamp(position.x, 0.f, 1.f),
      Math.clamp(position.y, 0.f, 1.f)
    );
    
  }


  public Optional<Texture> getTexture() {
    return this.texture;
  }

  public Vector2 getSourcePosition() {
    return this.srcPosition;
  }
  
  public Vector2 getSourceSize() {
    return this.srcSize;
  }

  public Vector2 getDestinationPosition() {
    return this.dstPosition;
  }
  
  public Vector2 getDestinationSize() {
    return this.dstSize;
  }

  @Override
  public void render() {
    float w = this.texture.flatMap(Texture::unwrap)
    .map(Raylib.Texture::width)
    .orElse(0);
    float h = this.texture.flatMap(Texture::unwrap)
    .map(Raylib.Texture::height)
    .orElse(0);
    
    final Raylib.Rectangle source = new Rectangle(
      w*this.srcPosition.x,
      h*this.srcPosition.y,
      w*this.srcSize.x,
      h*this.srcSize.y
    ).unwrap();

    final Raylib.Rectangle dest = new Rectangle(
      Raylib.GetRenderWidth()*this.dstPosition.x,
      Raylib.GetRenderHeight()*this.dstPosition.y,
      Raylib.GetRenderWidth()*this.dstSize.x,
      Raylib.GetRenderHeight()*this.dstSize.y
    ).unwrap();

    if(this.scissor != null)
      Raylib.BeginScissorMode(
        (int)(Raylib.GetRenderWidth()*scissor.x), 
        (int)(Raylib.GetRenderHeight()*scissor.y), 
        (int)(Raylib.GetRenderWidth()*scissor.width), 
        (int)(Raylib.GetRenderHeight()*scissor.height)
      );

    this.texture
    .flatMap(Texture::unwrap)
    .ifPresent((t) ->
      Raylib.DrawTexturePro(
        t,
        source,
        dest,
        Vector2.ZERO.unwrap(),
        this.rotation,
        Raylib.ColorFromHSV(0, 0, 1)
      )
    );

    if(this.scissor != null)
      Raylib.EndScissorMode();
  }
  
}
