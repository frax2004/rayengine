package rayengine;

import com.raylib.Raylib;

import rayengine.core.Color;
import rayengine.core.Font;
import rayengine.core.Model;
import rayengine.core.Rectangle;
import rayengine.core.Renderer;
import rayengine.core.Texture;
import rayengine.core.Vector2;
import rayengine.core.Vector3;
import rayengine.core.components.Camera3D;

public final class RaylibRenderer implements Renderer {

  private Camera3D camera = null;
  private Rectangle scissor = null;

  @Override
  public Vector2 getRenderSize() {
    return new Vector2(Raylib.GetRenderWidth(), Raylib.GetRenderHeight());
  }

  @Override
  public Camera3D getCamera() {
    return this.camera;
  }

  @Override
  public void setCamera(Camera3D camera) {
    this.camera = camera;
  }

  @Override
  public void start() {
    Raylib.BeginDrawing();
    Raylib.ClearBackground(Raylib.GetColor(0xffffffff));
  }

  @Override
  public void finish() {
    Raylib.EndDrawing();
  }

  @Override
  public void render(Model model, Vector3 position, Vector3 rotation, float angle, Vector3 scale, Color tint) {
    if(this.camera == null) return;

    Raylib.BeginMode3D(this.camera.unwrap());
    Raylib.DrawModelEx(
      model.unwrap(),
      position.unwrap(),
      rotation.unwrap(),
      angle,
      scale.unwrap(),
      tint.unwrap()
    );

    Raylib.EndMode3D();
  }

  @Override
  public void render(Rectangle rectangle, Color color) {
    Raylib.DrawRectangleRec(rectangle.unwrap(), color.unwrap());
  }

  @Override
  public Rectangle getScissorRectangle() {
    return this.scissor;
  }

  @Override
  public void setScissorRectangle(Rectangle rectangle) {
    this.scissor = rectangle;
    if(rectangle != null) {
      Raylib.BeginScissorMode(
        (int)rectangle.x,
        (int)rectangle.y,
        (int)rectangle.width,
        (int)rectangle.height
      );
    } else Raylib.EndScissorMode();
  }

  @Override
  public void render(Texture texture, Rectangle source, Rectangle destination, Vector2 origin, float rotation, Color tint) {
    Raylib.DrawTexturePro(
      texture.unwrap(),
      source.unwrap(),
      destination.unwrap(),
      origin.unwrap(),
      rotation,
      tint.unwrap()
    );
  }

  @Override
  public void render(Font font, String text, Vector2 position, float fontSize, float spacing, Color color) {
    Raylib.DrawTextEx(
      font.unwrap(),
      text,
      position.unwrap(),
      fontSize,
      spacing,
      color.unwrap()
    );
  }

  @Override
  public Vector2 getTextSize(Font font, String text, float fontSize, float spacing) {
    return new Vector2(Raylib.MeasureTextEx(font.unwrap(), text, fontSize, spacing));
  }
  
}
