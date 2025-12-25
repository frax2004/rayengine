@echo off

javac -d out -cp lib/jaylib-5.5.0-2.jar rayengine/*.java rayengine/ui/*.java gum/*.java Main.java

cd out && jar xf ../lib/jaylib-5.5.0-2.jar && cd ..

jar cvfe GUM.jar Main -C out .