package rayengine.core;

import com.raylib.Raylib;

public final class Music implements Asset<Raylib.Music> {
  private final Raylib.Music music;
  
  public Music(String path) {
    this.music = Raylib.LoadMusicStream(path);
  }

  @Override
  public void release() {
    Raylib.UnloadMusicStream(this.music);
  }

  @Override
  public Raylib.Music unwrap() {
    return this.music;
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsMusicValid(this.music); 
  }

}