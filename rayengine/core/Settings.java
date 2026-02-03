package rayengine.core;

public record Settings(
  String title, Vector2 windowSize, int fps, RenderContext renderContext
) {}
