package gum.scripts;


import rayengine.components.ModelRenderer;
import rayengine.components.Script;
import rayengine.core.GameObject;

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
