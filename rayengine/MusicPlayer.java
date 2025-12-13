package rayengine;

import java.util.Optional;
import java.util.function.Predicate;

import com.raylib.Raylib;

public final class MusicPlayer extends Component implements Updatable {
  private Music music = null;

  public MusicPlayer(GameObject parent, Music music) {
    super(parent);
    this.music = music;
  }

  public Optional<Music> unwrap() {
    return Optional.ofNullable(this.music);
  }

  public void update() {
    unwrap()
    .flatMap(Music::unwrap)
    .ifPresent(Raylib::UpdateMusicStream);
  }

  public void play() {
    unwrap()
    .flatMap(Music::unwrap)
    .filter(Predicate.not(Raylib::IsMusicStreamPlaying))
    .ifPresent(Raylib::PlayMusicStream);
  }

  public void pause() {
    unwrap()
    .flatMap(Music::unwrap)
    .filter(Raylib::IsMusicStreamPlaying)
    .ifPresent(Raylib::PauseMusicStream);
  }

  public void resume() {
    unwrap()
    .flatMap(Music::unwrap)
    .filter(Predicate.not(Raylib::IsMusicStreamPlaying))
    .ifPresent(Raylib::PauseMusicStream);
  }

  public void stop() {
    unwrap()
    .flatMap(Music::unwrap)
    .filter(Raylib::IsMusicStreamPlaying)
    .ifPresent(Raylib::StopMusicStream);
  }

  public void rewind() {
    unwrap()
    .flatMap(Music::unwrap)
    .ifPresent(x -> Raylib.SeekMusicStream(x, 0));
  }

  public boolean isPlaying() {
    return unwrap()
    .flatMap(Music::unwrap)
    .filter(Raylib::IsMusicStreamPlaying)
    .isPresent();
  }

  public boolean isPaused() {
    return unwrap()
    .flatMap(Music::unwrap)
    .filter(Predicate.not(Raylib::IsMusicStreamPlaying))
    .isPresent();
  }
}