package rayengine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ResourceManager {
  private Map<String, Asset<?>> resources = new HashMap<>();

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

  public static ResourceManager loadFromFolder(String folderPath, String... excludePaths) {
    ResourceManager self = new ResourceManager();
    List<String> extensions = List.of(".mp3", ".ttf", ".png", ".obj");

    self.resources = ResourceManager
    .getFiles(new File(folderPath), excludePaths)
    .map(File::getPath)
    .filter(path -> extensions.contains(path.substring(path.lastIndexOf('.')).toLowerCase()))
    .collect(
      Collectors.toMap(
        path -> path.replace("\\", "/"),
        path -> switch(path.substring(path.lastIndexOf('.')).toLowerCase()) {
          case ".mp3" -> new Music(path);
          case ".ttf" -> new Font(path);
          case ".png" -> new Texture(path);
          case ".obj" -> new Model(path);
          default -> null;
        }
      )
    );

    return self;
  }

  public <T extends Asset<?>> 
  T get(Class<T> type, String name) {
    return type.cast(this.resources.get(name));
  }

  public <T extends Asset<?>> 
  T add(String name, T resource) {
    this.resources.put(name, resource);
    return resource;
  }

  public void releaseAll() {
    this.resources.forEach((__, r) -> r.release());
  }
}
