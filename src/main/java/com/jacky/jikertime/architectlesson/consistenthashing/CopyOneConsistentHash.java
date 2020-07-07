package com.jacky.jikertime.architectlesson.consistenthashing;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/*
代码来源于 https://xie.infoq.cn/article/0c660a8f26689cb4ac94e8b17
 */
public class CopyOneConsistentHash {
  // 服务器列表
  private List<String> servers = null;
  // 服务器虚拟节点
  private TreeMap<Integer, String> virtualNodes = new TreeMap<>();
  // 服务器访问统计
  private TreeMap<String, Integer> serverVisit = new TreeMap<>();
  // 虚拟节点个数
  private int virtualFactor = 180;

  public CopyOneConsistentHash(List<String> servers, int virtualFactor) {
    this.servers = servers;
    this.virtualFactor = virtualFactor;
    this.servers.forEach(server -> this.addServer(server));
  }

  /**
   * 获取字符串的hash值
   *
   * @param str
   * @return
   */
  public int getHash(String str) {
    final int p = 16777619;
    int hash = (int) 2166136261L;
    for (int i = 0; i < str.length(); i++) {
      hash = (hash ^ str.charAt(i)) * p;
    }
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;
    return hash;
  }

  /**
   * 添加server节点
   *
   * @param server
   */
  public void addServer(String server) {
    if (!serverVisit.containsKey(server)) {
      serverVisit.put(server, 0);
      for (int i = 0; i < virtualFactor; i++) {
        virtualNodes.put(getHash(server + "?VNODE=" + i), server);
      }
    }
  }

  /**
   * 移除server节点
   *
   * @param server
   */
  public void removeServer(String server) {
    if (serverVisit.containsKey(server)) {
      serverVisit.remove(server);
      for (int i = 0; i < virtualFactor; i++) {
        Object o = virtualNodes.remove(getHash(server + "?VNODE=" + i));
      }
    }
  }

  /**
   * 模拟请求获取服务地址
   *
   * @param reqKey
   * @return
   */
  public String request(String reqKey) {
    String server = null;
    int reqHash = getHash(reqKey);
    SortedMap<Integer, String> greaterMap = virtualNodes.tailMap(reqHash, true);
    if (greaterMap.isEmpty()) {
      // 如果没有比reqHash大的值，则返回第一个虚拟服务器节点
      server = virtualNodes.get(virtualNodes.firstKey());
    } else {
      // greaterMap第一个值就是顺时针下离reqHash最近的虚拟服务器节点
      server = greaterMap.get(greaterMap.firstKey());
    }
    // 记录访问次数
    serverVisit.put(server, serverVisit.get(server) + 1);
    return server;
  }

  /**
   * 计算请求分布的标准差
   *
   * @return
   */
  public double calcStd() {
    Integer[] visitData = new Integer[serverVisit.size()];
    serverVisit.values().toArray(visitData);
    double avg = Arrays.stream(visitData).mapToInt(Integer::intValue).average().orElse(0d);
    double avgStd = Arrays.stream(visitData).map(count -> Math.pow(count - avg, 2)).mapToDouble(Double::doubleValue).average().orElse(0d);
    double std = Math.sqrt(avgStd);
    return std;
  }

  public static void main(String[] args) {
    // 模拟请求次数
    for (int j = 1; j < 1000; j++) {
      int times = 1000000;
      List<String> servers = Arrays.asList("192.168.1.1:8080", "192.168.1.2:8080", "192.168.1.3:8080");
      CopyOneConsistentHash consistentHash = new CopyOneConsistentHash(servers, j);
      long s = System.currentTimeMillis();
      for (int i = 0; i < times; i++) {
        consistentHash.request(UUID.randomUUID().toString());
      }
      System.out.println("耗时：" + (System.currentTimeMillis() - s) + " 标准差：" + consistentHash.calcStd());

      // 打印模拟请求分布情况
      consistentHash.serverVisit.forEach((k, v) -> {
        System.out.println(
          k + " - [ " + v + " - " + times / consistentHash.serverVisit.size() + " = " + (v - times / consistentHash.serverVisit.size()) + " ]");
      });


      System.out.println("=======================================================");
      // 清除模拟请求分布数据
      consistentHash.serverVisit.forEach((k, v) -> {
        consistentHash.serverVisit.put(k, 0);
      });

      // 模拟移除一台服务器
      consistentHash.removeServer("192.168.1.1:8080");

      s = System.currentTimeMillis();
      for (int i = 0; i < times; i++) {
        consistentHash.request(UUID.randomUUID().toString());
      }
      System.out.println("耗时：" + (System.currentTimeMillis() - s) + " 标准差：" + consistentHash.calcStd());

      consistentHash.serverVisit.forEach((k, v) -> {
        System.out.println(
          k + " - [ " + v + " - " + times / consistentHash.serverVisit.size() + " = " + (v - times / consistentHash.serverVisit.size()) + " ]");
      });


      System.out.println("=======================================================");
      // 清除模拟请求分布数据
      consistentHash.serverVisit.forEach((k, v) -> {
        consistentHash.serverVisit.put(k, 0);
      });

      // 模拟添加几台服务器
      consistentHash.addServer("192.168.1.1:8080");
      consistentHash.addServer("192.168.1.4:8080");
      consistentHash.addServer("192.168.1.5:8080");
      consistentHash.addServer("192.168.1.6:8080");
      consistentHash.addServer("192.168.1.7:8080");
      consistentHash.addServer("192.168.1.8:8080");
      consistentHash.addServer("192.168.1.9:8080");
      consistentHash.addServer("192.168.1.10:8080");

      s = System.currentTimeMillis();
      for (int i = 0; i < times; i++) {
        consistentHash.request(UUID.randomUUID().toString());
      }
      System.out.println(consistentHash.virtualFactor + "\t" + (System.currentTimeMillis() - s) + "\t" + consistentHash.calcStd());
    }

  }


}
