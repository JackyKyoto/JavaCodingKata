package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class HashUtil {
  public static int hash(String keyStr) {

    byte[] key = keyStr.getBytes(Charset.defaultCharset());
    ByteBuffer buf = ByteBuffer.wrap(key);
    int seed = 0x1234ABCD;

    ByteOrder byteOrder = buf.order();
    buf.order(ByteOrder.LITTLE_ENDIAN);

    long m = 0xc6a4a7935bd1e995L;
    int r = 47;

    int h = (int) (seed ^ (buf.remaining() * m));

    long k;
    while (buf.remaining() >= 8) {
      k = buf.getLong();

      k *= m;
      k ^= k >>> r;
      k *= m;

      h ^= k;
      h *= m;
    }

    if (buf.remaining() > 0) {
      ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      // for big-endian version, do this first:
      // finish.position(8-buf.remaining());
      finish.put(buf).rewind();
      h ^= finish.getLong();
      h *= m;
    }

    h ^= h >>> r;
    h *= m;
    h ^= h >>> r;

    buf.order(byteOrder);
    return h;
  }
}
