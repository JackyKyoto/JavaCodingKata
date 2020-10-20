package com.jacky.jikertime.javatraining.jvm;

/**
 * 自己写一个简单的Hello.java，里面需要涉及基本类型，四则运行，if和for，然后自 己分析一下对应的字节码，有问题群里讨论。
 */

public class HelloBytecode {

  /**
   * 基本类型包括 byte, int , short , long , boolean , char , double, float
   * byte  单字节
   * short char  双字节
   * int float   四字节
   * long  double 八字节
   * boolean  类型占了单独使用是4个字节（用int存储)，在数组中又是1个字节。 https://www.cnblogs.com/xichji/p/12009229.html  https://www.cnblogs.com/wangtianze/p/6690665.html
   */

  public int calcPrimitiveTypes(int int1) {
    byte byte1 = -127;
    long long1 = 10000L;
    int calcResult = (int) (int1 * byte1 + (long1 - 10) / 2);
    return calcResult;
  }

  public void testForAndIf() {
    int[] intArray = {1, 68, 79, 91, 85, 62};
    for (int i = 0; i < intArray.length; i++) {
      int result = calcPrimitiveTypes(intArray[i]);
      if (result > 100) {
        break;
      }
    }
  }


  public static void main(String[] args) {
    HelloBytecode helloBytecode = new HelloBytecode();
    helloBytecode.testForAndIf();
  }
}
