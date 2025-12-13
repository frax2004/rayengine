import rayengine.*;
import com.raylib.Raylib;

interface Main {
  static void main(String[] args) {
    Raylib.InitAudioDevice();
    Raylib.SetConfigFlags(Raylib.FLAG_WINDOW_RESIZABLE);
    Raylib.InitWindow(1200, 800, "G.U.M. (Galactic Unemployed Mercenaries)");
    Raylib.SetTargetFPS(60);

    Music mainMenuMusic = new Music("assets/music/main menu.mp3");
    GameObject musicPlayer = new GameObject();

    musicPlayer
    .attach(new MusicPlayer(musicPlayer, mainMenuMusic))
    .ifPresent(MusicPlayer::play);
    
    Scene scene = new SceneBuilder()
    .add("music player", musicPlayer)
    .build();

    while(! Raylib.WindowShouldClose()) {
    
      scene.update();

      Raylib.BeginDrawing();
      Raylib.ClearBackground(Raylib.GetColor(0));

      scene.render();
      
      Raylib.EndDrawing();
    }

    mainMenuMusic.release();

    Raylib.CloseWindow();
    Raylib.CloseAudioDevice();
  }
}