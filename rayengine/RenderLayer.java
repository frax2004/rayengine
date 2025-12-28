package rayengine;

import java.util.ArrayList;
import java.util.List;

public final class RenderLayer implements Renderable {
  private List<Renderable> renderables;

  public RenderLayer() {
    this.renderables = new ArrayList<>();
  }

  public void add(Renderable renderable) {
    this.renderables.add(renderable);
  }

  public void remove(Renderable renderable) {
    this.renderables.remove(renderable);
  }

  @Override
  public void render() {
    renderables.forEach(Renderable::render);
  }
  
}
