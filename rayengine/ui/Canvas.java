package rayengine.ui;

import rayengine.assets.Texture;
import rayengine.core.Color;
import rayengine.core.Core;
import rayengine.core.Rectangle;
import rayengine.core.RenderContext;
import rayengine.core.Vector2;
import rayengine.ui.core.StatelessWidget;
import rayengine.ui.core.Widget;

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
    final RenderContext ctx = Core.getRenderContext();
    final Vector2 textureSize = this.texture != null ? this.texture.getSize() : Vector2.ZERO;
    
    final Rectangle source = new Rectangle(
      textureSize.x * this.srcPosition.x,
      textureSize.y * this.srcPosition.y,
      textureSize.x * this.srcSize.x,
      textureSize.y * this.srcSize.y
    );

    
    final Vector2 dstPosition = this.getPosition();
    final Vector2 dstSize = this.getSize();
    
    final Rectangle dest = new Rectangle(
      dstPosition.x,
      dstPosition.y,
      dstSize.x,
      dstSize.y
    );

    if(this.scissor != null) {
      final Vector2 renderSize = ctx.getRenderSize();
      
      ctx.setScissorRectangle(
        new Rectangle(
          renderSize.x*scissor.x,
          renderSize.y*scissor.y,
          renderSize.x*scissor.width,
          renderSize.y*scissor.height
        )
      );
    }

    if(this.texture != null) {
      ctx.render(texture, source, dest, Vector2.ZERO, rotation, Color.WHITE);
    }

    if(this.scissor != null)
      ctx.setScissorRectangle(null);
  }
  
}
