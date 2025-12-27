package rayengine;

public interface Resource<T> {
  public boolean isLoaded();
  public void release();
  public T unwrap();
}


