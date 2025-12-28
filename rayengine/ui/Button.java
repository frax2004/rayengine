package rayengine.ui;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.raylib.Raylib;
import com.raylib.Raylib.Color;

import rayengine.Font;
import rayengine.Rectangle;
import rayengine.Texture;
import rayengine.Vector2;

public final class Button extends StatefullWidget {

  private Texture texture = null;
  private Color color;
  private Color foreground;
  private String text;
  private Font font;
  private BiConsumer<Button, Integer> onMouseButtonPressed;
  private BiConsumer<Button, Integer> onMouseButtonReleased;
  private Consumer<Button> onHoverEnter;
  private Consumer<Button> onHover;
  private Consumer<Button> onHoverExit;
  private float fontScale;
  private boolean isHovering;

  public Button(Widget parent) {
    this(
      parent,
      null,
      Raylib.GetColor(0x2f2f2fff),
      Raylib.GetColor(0xffffffff),
      "",
      null,
      (sender, btn) -> {},
      (sender, btn) -> {},
      (sender) -> {},
      (sender) -> {},
      (sender) -> {},
      1
    );
  }
  
  public Button(
    Widget parent, 
    Texture texture, 
    Color color, 
    Color foreground, 
    String text, 
    Font font,
    BiConsumer<Button, Integer> onMouseButtonPressed,
    BiConsumer<Button, Integer> onMouseButtonReleased,
    Consumer<Button> onHoverEnter,
    Consumer<Button> onHover,
    Consumer<Button> onHoverExit,
    float fontScale
  ) {
    super(parent);
    this.texture = texture;
    this.color = color;
    this.foreground = foreground;
    this.text = text;
    this.font = font;
    this.onMouseButtonPressed = onMouseButtonPressed;
    this.onMouseButtonReleased = onMouseButtonReleased;
    this.onHover = onHover;
    this.fontScale = fontScale;
    this.onHoverEnter = onHoverEnter;
    this.onHoverExit = onHoverExit;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setFont(Font font) {
    this.font = font;
  }

  public void setFontScale(float fontScale) {
    this.fontScale = fontScale;
  }

  public void setForeground(Color foreground) {
    this.foreground = foreground;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }

  public Color getColor() {
    return this.color;
  }

  public Font getFont() {
    return this.font;
  }

  public float getFontScale() {
    return this.fontScale;
  }

  public Color getForeground() {
    return this.foreground;
  }

  public String getText() {
    return this.text;
  }

  public Texture getTexture() {
    return this.texture;
  }

  public void setOnHover(Consumer<Button> onHover) {
    this.onHover = onHover;
  }

  public void setOnMouseButtonPressed(BiConsumer<Button, Integer> onMouseButtonPressed) {
    this.onMouseButtonPressed = onMouseButtonPressed;
  }

  public void setOnMouseButtonReleased(BiConsumer<Button, Integer> onMouseButtonReleased) {
    this.onMouseButtonReleased = onMouseButtonReleased;
  }


  @Override
  public void update() {

    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_LEFT))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_LEFT);
    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_RIGHT))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_RIGHT);
    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_MIDDLE))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_MIDDLE);
    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_SIDE))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_SIDE);
    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_EXTRA))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_EXTRA);
    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_FORWARD))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_FORWARD);
    if(Raylib.IsMouseButtonReleased(Raylib.MOUSE_BUTTON_BACK))
      this.onMouseButtonReleased.accept(this, Raylib.MOUSE_BUTTON_BACK);

    Rectangle rect = new Rectangle(
      this.getPosition().x,
      this.getPosition().y,
      this.getSize().x,
      this.getSize().y
    );


    boolean hover = Raylib.CheckCollisionPointRec(Raylib.GetMousePosition(), rect.unwrap());
    if(hover && !isHovering) this.onHoverEnter.accept(this);
    if(!hover && isHovering) this.onHoverExit.accept(this);
    isHovering = hover;
    if(!isHovering) return;
  
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_LEFT))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_LEFT);
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_RIGHT))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_RIGHT);
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_MIDDLE))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_MIDDLE);
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_SIDE))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_SIDE);
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_EXTRA))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_EXTRA);
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_FORWARD))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_FORWARD);
    if(Raylib.IsMouseButtonPressed(Raylib.MOUSE_BUTTON_BACK))
      this.onMouseButtonPressed.accept(this, Raylib.MOUSE_BUTTON_BACK);


    this.onHover.accept(this);
  }

  private static float getFitFontSize(Raylib.Font font, String text, float maxWidth, float maxHeight) {
    if (text == null || text.isEmpty()) return 10.0f;

    float referenceFontSize = 100.0f;
    float spacing = referenceFontSize / 10.0f;
    
    Raylib.Vector2 textSize = Raylib.MeasureTextEx(font, text, referenceFontSize, spacing);

    float scaleX = maxWidth / textSize.x();
    float scaleY = maxHeight / textSize.y();

    float scale = Math.min(scaleX, scaleY);

    return referenceFontSize * scale;
  }

  @Override
  public void render() {
    Raylib.Rectangle dest = new Rectangle(
      this.getPosition().x,
      this.getPosition().y,
      this.getSize().x,
      this.getSize().y
    ).unwrap();

    if(this.texture != null) {
      Raylib.Texture handle = this.texture.unwrap();
      Raylib.DrawTexturePro(
        handle,
        new Rectangle(
          0, 
          0, 
          handle.width(),
          handle.height()
        ).unwrap(), 
        dest,
        Vector2.ZERO.unwrap(),
        0.f,
        Raylib.GetColor(0xffffffff)
      );
    } else Raylib.DrawRectangleRec(dest, this.color);

    final Raylib.Font font = this.font != null ? this.font.unwrap() : Raylib.GetFontDefault();
    final float fontSize = Button.getFitFontSize(font, text, dest.width(), dest.height())*fontScale;
    final float spacing =  fontSize/10.f;
    final Raylib.Vector2 textSize = Raylib.MeasureTextEx(font, text, fontSize, spacing);
    final Raylib.Vector2 textPosition = new Vector2(
      dest.x() + 0.5f*(dest.width() - textSize.x()),
      dest.y() + 0.5f*(dest.height() - textSize.y())
    ).unwrap();

    Raylib.DrawTextEx(
      font,
      text,
      textPosition,
      fontSize,
      spacing,
      this.foreground
    );
  }
  
}
