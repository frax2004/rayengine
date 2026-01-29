package gum;


import rayengine.GameObject;
import rayengine.components.ModelRenderer;
import rayengine.components.Script;

public final class PlanetSelectionScript extends Script {
  private final ModelRenderer model;

  public PlanetSelectionScript(GameObject parent) {
    super(parent);
    model = parent.getComponent(ModelRenderer.class);
  }

  @Override
  protected void OnUpdate(float deltaTime) {

  }
  
}
