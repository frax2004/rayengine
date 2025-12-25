package rayengine.ui;

import java.util.Optional;

import com.raylib.Raylib;

import rayengine.Rectangle;
import rayengine.Texture;
import rayengine.Vector2;

public final class Canvas extends StatelessWidget {
  private Vector2 srcPosition = Vector2.ZERO.copy(); // [0, 1]
  private Vector2 srcSize = Vector2.ONE.copy(); // [0, 1]
  private Optional<Texture> texture = null;
  private Rectangle scissor = null;
  private float rotation = 0;

  public Canvas(Widget parent) {
    super(parent);
    this.texture = Optional.empty();
  }

  public Canvas(Widget parent, Texture texture) {
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
    this.srcSize = size.copy();
  }

  public float getRotation() {
    return this.rotation;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  public void setSourcePosition(Vector2 position) {
    this.srcPosition = position.copy();
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

  @Override
  public void render() {
    float w = this.texture.flatMap(Texture::unwrap)
    .map(Raylib.Texture::width)
    .orElse(0);
    float h = this.texture.flatMap(Texture::unwrap)
    .map(Raylib.Texture::height)
    .orElse(0);
    
    final Rectangle source = new Rectangle(
      w*this.srcPosition.x,
      h*this.srcPosition.y,
      w*this.srcSize.x,
      h*this.srcSize.y
    );

    
    final Vector2 dstPosition = this.getPosition();
    final Vector2 dstSize = this.getSize();
    
    final Rectangle dest = new Rectangle(
      dstPosition.x,
      dstPosition.y,
      dstSize.x,
      dstSize.y
    );


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
        source.unwrap(),
        dest.unwrap(),
        Vector2.ZERO.unwrap(),
        this.rotation,
        Raylib.GetColor(0xffffffff)
      )
    );

    if(this.scissor != null)
      Raylib.EndScissorMode();
  }
  
}
