package rayengine.ui;

import rayengine.core.Updatable;

public abstract class StatefullWidget extends Widget implements Updatable {

  public StatefullWidget(Widget parent) {
    super(parent);
  }
  
}
