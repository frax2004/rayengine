package rayengine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import rayengine.assets.Font;
import rayengine.assets.Model;
import rayengine.assets.Music;
import rayengine.assets.Texture;
import rayengine.core.Asset;

public final class AssetManager {
  private final static Map<String, Asset<?>> resources = new HashMap<>();
  
  private static Stream<File> getFiles(File directory, String... excludePaths) {
    Predicate<File> included = Arrays
    .stream(excludePaths)
    .map(regex -> (Predicate<File>)file -> !file.getPath().replace("\\", "/").matches(regex))
    .reduce(x -> true, Predicate::and);

    try {
      return Files
      .walk(directory.toPath())
      .filter(Files::isRegularFile)
      .map(Path::toFile)
      .filter(included);
    } catch(Exception e) {
      return Stream.empty();
    }

  }

  public static void loadAssets(String folderPath, String... excludePaths) {
    List<String> extensions = List.of(".mp3", ".ttf", ".png", ".obj");

    String[] paths = AssetManager
    .getFiles(new File(folderPath), excludePaths)
    .map(File::getPath)
    .filter(path -> extensions.contains(path.substring(path.lastIndexOf('.')).toLowerCase()))
    .toArray(String[]::new);

    for(String path : paths) {
      String key = path.replace("\\", "/");
      Asset<?> value = switch(path.substring(path.lastIndexOf('.')).toLowerCase()) {
        case ".mp3" -> new Music(path);
        case ".ttf" -> new Font(path);
        case ".png" -> new Texture(path);
        case ".obj" -> new Model(path);
        default -> null;
      };

      AssetManager.resources.put(key, value);
    }
  }

  public static <T extends Asset<?>> 
  T get(Class<T> type, String name) {
    return type.cast(AssetManager.resources.get(name));
  }

  public static <T extends Asset<?>> 
  T add(String name, T resource) {
    AssetManager.resources.put(name, resource);
    return resource;
  }

  public static void releaseAll() {
    AssetManager.resources.forEach((__, r) -> r.release());
  }
}
