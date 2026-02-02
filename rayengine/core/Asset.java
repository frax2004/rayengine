package rayengine.core;

public interface Asset<T> {
  public boolean isLoaded();
  public void release();
  public T unwrap();
}


