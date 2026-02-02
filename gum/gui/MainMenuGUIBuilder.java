package gum.gui;

import com.raylib.Raylib;

import rayengine.Builder;
import rayengine.Font;
import rayengine.Game;
import rayengine.GameObject;
import rayengine.AssetManager;
import rayengine.Texture;
import rayengine.components.UI;
import rayengine.ui.ButtonBuilder;
import rayengine.ui.Canvas;
import rayengine.ui.Direction;
import rayengine.ui.Margin;
import rayengine.ui.StackLayout;

public class MainMenuGUIBuilder implements Builder<UI> {
  private final GameObject parent;

  public MainMenuGUIBuilder(GameObject parent) {
    this.parent = parent;
  }

  @Override
  public UI build() {
    
    Texture texture = AssetManager.get(Texture.class, "assets/textures/titlescreen.png");
    Texture btn = AssetManager.get(Texture.class, "assets/textures/button.png");
    Font font = AssetManager.get(Font.class, "assets/fonts/impact.ttf");

    StackLayout layout = new StackLayout(null, Direction.HORIZONTAL);
    layout.setHorizontalMargin(.5f);

    StackLayout inner = layout.add(new StackLayout(layout, Direction.VERTICAL));
    inner.setMargin(Margin.NORTH, .15f);
    inner.add(new Canvas(inner, texture));

    StackLayout buttons = inner.add(new StackLayout(inner, Direction.VERTICAL));
    ButtonBuilder builder = new ButtonBuilder();
    builder
    .setParent(buttons)
    .setFont(font)
    .setForeground(Raylib.GetColor(0xFFD700FF))
    .setTexture(btn)
    .setFontScale(.5f)
    .setOnHoverEnter((sender) -> {
      Raylib.SetMouseCursor(Raylib.MOUSE_CURSOR_POINTING_HAND);
      sender.setFontScale(.75f);
    }).setOnHoverExit((sender) -> {
      Raylib.SetMouseCursor(Raylib.MOUSE_CURSOR_ARROW);
      sender.setFontScale(.5f);
    });

    buttons.setVerticalMargin(.25f);
    buttons.setHorizontalMargin(.5f);
    buttons.setVerticalInnerMargin(.15f);
    buttons.add(
      builder
      .setText("START")
      .setOnMouseButtonPressed((sender, button) -> {        
        if(button == Raylib.MOUSE_BUTTON_LEFT) {
          Game game = this.parent.getParentScene().getParent();
          Raylib.SetMouseCursor(Raylib.MOUSE_CURSOR_ARROW);
          sender.setFontScale(.5f);
          game.setActiveScene(game.getScene("Lobby"));
        }
      }).build()
    );
    buttons.add(
      builder
      .setText("OPTIONS")
      .setOnMouseButtonPressed((__, ___) -> {})
      .build()
    );
    buttons.add(
      builder
      .setText("CREDITS")
      .setOnMouseButtonPressed((__, ___) -> {})
      .build()
    );

    return new UI(this.parent, layout);
  }
  
}
