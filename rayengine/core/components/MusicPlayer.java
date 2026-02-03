package rayengine.core.components;

import com.raylib.Raylib;

import rayengine.core.Component;
import rayengine.core.GameObject;
import rayengine.core.Music;
import rayengine.core.Updatable;

public final class MusicPlayer extends Component implements Updatable {
  private Music music = null;

  public MusicPlayer(GameObject parent, Music music) {
    super(parent);
    this.music = music;
  }

  public Music unwrap() {
    return this.music;
  }

  @Override
  public void update() {
    if(this.isActive() && this.music != null) {
      Raylib.UpdateMusicStream(this.music.unwrap());
    }
  }

  public void play() {
    if(this.music != null && !Raylib.IsMusicStreamPlaying(this.music.unwrap())) {
      Raylib.PlayMusicStream(this.music.unwrap());
    }
  }

  public void pause() {
    if(this.music != null && Raylib.IsMusicStreamPlaying(this.music.unwrap())) {
      Raylib.PauseMusicStream(this.music.unwrap());
    }
  }

  public void setMusic(Music music) {
    if(this.isPlaying()) this.stop();
    this.music = music;
  }

  public Music getMusic() {
    return this.music;
  }

  public void resume() {
    if(this.music != null && !Raylib.IsMusicStreamPlaying(this.music.unwrap())) {
      Raylib.ResumeMusicStream(this.music.unwrap());
    }
  }

  public void stop() {
    if(this.music != null) {
      Raylib.StopMusicStream(this.music.unwrap());
    }
  }

  public void rewind() {
    if(this.music != null) {
      Raylib.SeekMusicStream(this.music.unwrap(), 0);
    }
  }

  public boolean isPlaying() {
    return this.music != null && Raylib.IsMusicStreamPlaying(this.music.unwrap());
  }

  public boolean isPaused() {
    return this.music != null && !Raylib.IsMusicStreamPlaying(this.music.unwrap());
  }
}