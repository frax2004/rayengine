package gum.scripts;


import rayengine.core.GameObject;
import rayengine.core.components.ModelRenderer;
import rayengine.core.components.Script;

public final class PlanetSelectionScript extends Script {
  private final ModelRenderer model;

  public PlanetSelectionScript(GameObject parent) {
    super(parent);
    model = parent.getComponent(ModelRenderer.class);
  }

  @Override
  public void update(float deltaTime) {
    
  }
  
}
