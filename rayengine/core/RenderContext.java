package rayengine.core;

import rayengine.assets.Font;
import rayengine.assets.Model;
import rayengine.assets.Texture;
import rayengine.components.Camera3D;

public interface RenderContext {
  public Vector2 getRenderSize();
  public Vector2 getTextSize(Font font, String text, float fontSize, float spacing);
  public Rectangle getScissorRectangle();
  public Camera3D getCamera();

  public void setScissorRectangle(Rectangle rectangle);
  public void setCamera(Camera3D camera);
  
  public void render(Font font, String text, Vector2 position, float fontSize, float spacing, Color color);
  public void render(Model model, Vector3 position, Vector3 rotation, float angle, Vector3 scale, Color tint);
  public void render(Rectangle rectangle, Color color);
  public void render(Texture texture, Rectangle source, Rectangle destination, Vector2 origin, float rotation, Color tint);
  
  public void start();
  public void finish();
}
