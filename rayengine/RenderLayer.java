package rayengine;

import java.util.ArrayList;
import java.util.List;

import rayengine.core.Renderable;

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

  public List<Renderable> getRenderables() {
    return new ArrayList<>(this.renderables);
  }

  @Override
  public void render() {
    renderables.forEach(Renderable::render);
  }
  
}
