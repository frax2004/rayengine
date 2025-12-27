package rayengine;

import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {
  private Map<String, Resource<?>> resources = new HashMap<>();

  public <T extends Resource<T>> T get(Class<T> type, String name) {
    return type.cast(this.resources.get(name));
  }

  public <T extends Resource<T>> T add(String name, T resource) {
    this.resources.put(name, resource);
    return resource;
  }

  public void releaseAll() {
    this.resources.forEach((_, r) -> r.release());
  }
}
