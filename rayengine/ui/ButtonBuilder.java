package rayengine.ui;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import rayengine.assets.Font;
import rayengine.assets.Texture;
import rayengine.core.Builder;
import rayengine.core.Color;
import rayengine.ui.core.Widget;

public final class ButtonBuilder implements Builder<Button> {
  private Widget parent = null;
  private Texture texture = null;
  private Color color = Color.from(0x2f2f2fff);
  private Color foreground = Color.from(0xffffffff);
  private String text = "";
  private Font font = null;
  private BiConsumer<Button, Integer> onMouseButtonPressed = (sender, btn) -> {};
  private BiConsumer<Button, Integer> onMouseButtonReleased = (sender, btn) -> {};
  private Consumer<Button> onHoverEnter = (sender) -> {};
  private Consumer<Button> onHover = (sender) -> {};
  private Consumer<Button> onHoverExit = (sender) -> {};
  private float fontScale = 1;

  public ButtonBuilder setParent(Widget parent) {
    this.parent = parent;
    return this;
  }

  public ButtonBuilder setColor(Color color) {
    this.color = color;
    return this;
  }

  public ButtonBuilder setFont(Font font) {
    this.font = font;
    return this;
  }

  public ButtonBuilder setFontScale(float fontScale) {
    this.fontScale = fontScale;
    return this;
  }

  public ButtonBuilder setForeground(Color foreground) {
    this.foreground = foreground;
    return this;
  }

  public ButtonBuilder setText(String text) {
    this.text = text;
    return this;
  }

  public ButtonBuilder setTexture(Texture texture) {
    this.texture = texture;
    return this;
  }

  public ButtonBuilder setOnHover(Consumer<Button> onHover) {
    this.onHover = onHover;
    return this;
  }

  public ButtonBuilder setOnMouseButtonPressed(BiConsumer<Button, Integer> onMouseButtonPressed) {
    this.onMouseButtonPressed = onMouseButtonPressed;
    return this;
  }

  public ButtonBuilder setOnMouseButtonReleased(BiConsumer<Button, Integer> onMouseButtonReleased) {
    this.onMouseButtonReleased = onMouseButtonReleased;
    return this;
  }

  public ButtonBuilder setOnHoverEnter(Consumer<Button> onHoverEnter) {
    this.onHoverEnter = onHoverEnter;
    return this;
  }

  public ButtonBuilder setOnHoverExit(Consumer<Button> onHoverExit) {
    this.onHoverExit = onHoverExit;
    return this;
  }

  @Override
  public Button build() {
    return new Button(
      this.parent,
      this.texture,
      this.color,
      this.foreground,
      this.text,
      this.font,
      this.onMouseButtonPressed,
      this.onMouseButtonReleased,
      this.onHoverEnter,
      this.onHover,
      this.onHoverExit,
      this.fontScale
    );
  }
  
}
