package rayengine;

import java.util.Optional;

import com.raylib.Raylib;

public final class Music implements Resource {
  private Raylib.Music music = null;
  
  public Music(String path) {
    this.music = Raylib.LoadMusicStream(path);
  }
  @Override
  public void release() {
    Raylib.UnloadMusicStream(this.music);
    this.music = null;
  }
  public Optional<Raylib.Music> unwrap() {
    return Optional.ofNullable(this.music);
  }

  @Override
  public boolean isLoaded() {
    return Raylib.IsMusicValid(this.music); 
  }

}