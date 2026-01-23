import gum.GUM;
import rayengine.Docs;

interface Main {
  static void main(String[] args) {
    String doc = Docs.getDocumentation("rayengine");
    System.out.println(doc);
    // GUM.run();
  }
}
