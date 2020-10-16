package com.jacky.jikertime.javatraining.jvm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class HelloClassLoader extends ClassLoader {

  public static void main(String[] args) {
    try {
      Class cls  = new HelloClassLoader().findClass("Hello");
      Object instance =  cls.newInstance();
      Method helloMethod = cls.getDeclaredMethod("hello");
      helloMethod.invoke(instance);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] bytes = null;
    try {
      bytes = decode("/Users/jacky/Documents/sourcecode/learning/JavaCodingKata/src/main/java/com/jacky/jikertime/javatraining/jvm/Hello.xlass");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return defineClass(name, bytes, 0, bytes.length);
  }

  public byte[] decode(String fileName) throws IOException {
    File initialFile = new File(fileName);
    byte[] bytesBeforeConvert = Files.readAllBytes(initialFile.toPath());
    byte[] afterConvert = new byte[bytesBeforeConvert.length];
    for (int i = 0; i < bytesBeforeConvert.length; i++) {
      afterConvert[i] = (byte) (255 - bytesBeforeConvert[i]);
    }
    return afterConvert;
  }
}
