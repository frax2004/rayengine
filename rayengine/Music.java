package rayengine;

import com.raylib.Raylib;

public final class Music implements Asset<Raylib.Music> {
  private Raylib.Music music = null;
  
  public Music(String path) {
    this.music = Raylib.LoadMusicStream(path);
  }

  @Override
  public void release() {
    Raylib.UnloadMusicStream(this.music);
    this.music = null;
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