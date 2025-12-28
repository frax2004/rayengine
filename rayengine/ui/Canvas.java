package rayengine.ui;


import com.raylib.Raylib;

import rayengine.Rectangle;
import rayengine.Texture;
import rayengine.Vector2;

public final class Canvas extends StatelessWidget {
  private Vector2 srcPosition;
  private Vector2 srcSize;
  private Texture texture;
  private Rectangle scissor;
  private float rotation;

  public Canvas(Widget parent) {
    this(parent, null);
  }

  public Canvas(Widget parent, Texture texture) {
    super(parent);
    this.texture = texture;
    this.rotation = 0;
    this.srcPosition = Vector2.ZERO.copy();
    this.srcSize = Vector2.ONE.copy();
  }
  
  public Rectangle getScissorRect() {
    return this.scissor;
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
    this.texture = texture;
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

  public Texture getTexture() {
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
    float w = this.texture != null ? this.texture.unwrap().width() : 0;
    float h = this.texture != null ? this.texture.unwrap().height() : 0;
    
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

    if(this.texture != null) {
      Raylib.DrawTexturePro(
        this.texture.unwrap(),
        source.unwrap(),
        dest.unwrap(),
        Vector2.ZERO.unwrap(),
        this.rotation,
        Raylib.GetColor(0xffffffff)
      );
    }

    if(this.scissor != null)
      Raylib.EndScissorMode();
  }
  
}
