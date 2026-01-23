package rayengine;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.Set;


public class Docs {
  public static List<Class<?>> getClasses(String packageName) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources; 
    List<Class<?>> classes = new ArrayList<>();
    try {
      resources = classLoader.getResources(path);
    } catch(Exception e) {
      return classes;
    }

    while(resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      String protocol = resource.getProtocol();

      if ("file".equals(protocol)) {
        try {
          classes.addAll(findClasses(new File(URLDecoder.decode(resource.getFile(), "UTF-8")), packageName));
        } catch(Exception e) {}
      }
    }
    return classes;
  }

  private static List<Class<?>> findClasses(File directory, String packageName) {
    if (!directory.exists()) {
      return new ArrayList<>();
    }
    
    Function<String, Class<?>> mapper = s -> {
      try {
        return Class.forName(s);
      } catch(Exception e) {
        return null;
      }
    };

    try {
      return Arrays.stream(directory.listFiles())
      .filter(f -> f.getName().endsWith(".class"))
      .map(f -> packageName + '.' + f.getName().substring(0, f.getName().length() - 6))
      .map(mapper)
      .filter(Objects::nonNull)
      .toList();
    } catch(Exception e) {
      return new ArrayList<>();
    }
  }

  public static String getClassDescription(Class<?> c, String indent) {

    Function<Field, String> getFieldDescription = (Field field) -> {
      int mods = field.getModifiers();
      return 
      indent + "  "
      + (Modifier.isPublic(mods) ? "+ " : Modifier.isProtected(mods) ? "# " : "- ")
      + (Modifier.isStatic(mods) ? "{static} " : "")
      + field.getName() 
      + ": "
      + field.getType().getSimpleName();
    };

    Function<Constructor<?>, String> getConstructorDescription = (Constructor<?> constructor) -> {
      int mods = constructor.getModifiers();
      return 
      indent + "  "
      + (Modifier.isPublic(mods) ? "+ " : Modifier.isProtected(mods) ? "# " : "- ")
      + (Modifier.isStatic(mods) ? "{static} " : "")
      + constructor.getName()
      + "("
      + Arrays
      .stream(constructor.getParameters())
      .map(param -> param.getName() + ": " + param.getType().getSimpleName())
      .collect(Collectors.joining(", "))
      + ")";
    };

    Function<Method, String> getMethodDescription = (Method method) -> {
      int mods = method.getModifiers();
      return 
      indent + "  "
      + (Modifier.isPublic(mods) ? "+ " : Modifier.isProtected(mods) ? "# " : "- ")
      + (Modifier.isStatic(mods) ? "{static} " : "")
      + method.getName()
      + "("
      + Arrays
      .stream(method.getParameters())
      .map(param -> param.getName() + ": " + param.getType().getSimpleName())
      .collect(Collectors.joining(", "))
      + "): "
      + method.getReturnType().getSimpleName();
    };

    int mods = c.getModifiers();
    return indent + (Modifier.isAbstract(mods) && !c.isInterface() ? "abstract " : "")
    + (c.isInterface() ? "interface " : "class ")
    + c.getSimpleName()
    + " "
    + Optional
    .ofNullable(c.getSuperclass())
    .filter(Predicate.not(Object.class::equals))
    .map(s -> "extends " + s.getSimpleName() + " ")
    .orElse("")
    + (
      c.getInterfaces().length != 0 ?
      "implements "
      +
      Arrays
      .stream(c.getInterfaces())
      .map(Class<?>::getSimpleName)
      .collect(Collectors.joining(", ")) : ""
    )
    + "{\n"
    + Arrays
    .stream(c.getDeclaredFields())
    .filter(Predicate.not(Field::isSynthetic))
    .map(getFieldDescription)
    .collect(Collectors.joining("\n"))
    + (c.getDeclaredFields().length != 0 ? ("\n" + indent + "  " + "-----\n") : "")
    + Arrays
    .stream(c.getDeclaredConstructors())
    .filter(Predicate.not(Constructor::isSynthetic))
    .map(getConstructorDescription)
    .collect(Collectors.joining("\n"))
    + (c.getDeclaredConstructors().length != 0 ? ("\n" + indent + "  " + "-----\n") : "")
    + Arrays
    .stream(c.getDeclaredMethods())
    .filter(Predicate.not(Method::isSynthetic))
    .map(getMethodDescription)
    .collect(Collectors.joining("\n"))
    + "\n"
    + indent + "}";
  }

  static List<String> getSubpackages(String packageName) {
    List<String> subPackages = new ArrayList<>();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String path = packageName.replace('.', '/');

    try {
      Enumeration<URL> resources = classLoader.getResources(path);
      while (resources.hasMoreElements()) {
        URL resource = resources.nextElement();
        
        if ("file".equals(resource.getProtocol())) {
          File directory = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
          if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if(files == null) return new ArrayList<>();

            for(File file : files) {
              if(file.isDirectory()) {
                String subpackage = packageName + "." + file.getName();
                subPackages.add(subpackage);
                subPackages.addAll(getSubpackages(subpackage));
              }
            }
          }
        }
      }
    } catch (Exception e) { e.printStackTrace(); }
    return subPackages;
  }
  
  public static String getPackageDescription(String packageName, String indent) {
    Predicate<Class<?>> isProjectClass = (c) -> {
    return c != null 
      && !c.isPrimitive() 
      && !c.isArray()
      && c.getClassLoader() != null 
      && !c.getName().startsWith("java.") 
      && !c.getName().startsWith("javax.");
    };

    Function<List<Class<?>>, List<String>> getLinks = (classes) -> {
      Function<Class<?>, Stream<String>> linksOf = (Class<?> c) -> {
        Set<Class<?>> usedTypes = Arrays
        .stream(c.getDeclaredFields())
        .filter(Predicate.not(Field::isSynthetic))
        .map(Field::getType)
        .collect(Collectors.toSet());

        usedTypes.addAll(
          Arrays
          .stream(c.getDeclaredConstructors())
          .filter(Predicate.not(Constructor<?>::isSynthetic))
          .flatMap((Constructor<?> constructor) -> Arrays.stream(constructor.getParameterTypes()))
          .toList()
        );

        usedTypes.addAll(
          Arrays
          .stream(c.getDeclaredMethods())
          .filter(Predicate.not(Method::isSynthetic))
          .flatMap((Method method) -> Arrays.stream(method.getParameterTypes()))
          .toList()
        );

        usedTypes.addAll(
          Arrays
          .stream(c.getDeclaredMethods())
          .filter(Predicate.not(Method::isSynthetic))
          .map((Method method) -> method.getReturnType())
          .toList()
        );

        return usedTypes
        .stream()
        .filter(Predicate.not(c::equals))
        .filter(isProjectClass)
        .map(type -> indent + "  " + c.getSimpleName() + " --> " + type.getSimpleName() + ": uses");
      };
    
      return classes
      .stream()
      .flatMap(linksOf)
      .toList();
    };

    List<Class<?>> classes = getClasses(packageName);
    return 
    indent + "package " 
    + packageName.substring(packageName.lastIndexOf('.') + 1)
    + " {\n"
    + "\n\n" 
    + indent + "  " + "'===================== CLASSES =====================\n\n"
    + classes
    .stream()
    .map(cl -> Docs.getClassDescription(cl, indent + "  "))
    .collect(Collectors.joining("\n\n"))
    + "\n\n" 
    + indent + "  " + "'==================== SUBPACKAGES ===================\n\n"
    + getSubpackages(packageName)
    .stream()
    .map(name -> Docs.getPackageDescription(name, indent + "  "))
    .collect(Collectors.joining("\n\n"))
    + "\n\n" 
    + indent + "  " + "'====================== LINKS ======================\n\n"
    + getLinks
    .apply(classes)
    .stream()
    .collect(Collectors.joining("\n"))
    + "\n"
    + indent + "}\n\n";
  }

  public static String getDocumentation(String packageName) {
    return
    "@startuml \"docs-" 
    + packageName 
    + "\"\n\n"
    + getPackageDescription(packageName, "")
    + "\n\n@enduml";
  }

  public static void main(String[] args) {
    System.out.println(getDocumentation("rayengine"));
  }

}
