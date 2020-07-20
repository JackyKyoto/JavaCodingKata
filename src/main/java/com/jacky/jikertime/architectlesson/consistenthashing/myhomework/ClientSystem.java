package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

import java.util.UUID;

public class ClientSystem {


  public static void main(String[] args) {
    System.out.println("----------------分割线----------------");
    CachService cachService = getCachService(new SimpleModHashCluster());
    add1millionKV(cachService);
    cachService.showNodesInfo();
    cachService.printStandardDevition();

    System.out.println("----------------分割线----------------");

    CachService cachService2 = getCachService(new NoVirtualNodeConsHashCluster());
    add1millionKV(cachService2);
    cachService2.showNodesInfo();
    cachService2.printStandardDevition();

    System.out.println("----------------分割线----------------");

    CachService cachService3 = getCachService(new VirtualNodeConsHashCluster());
    add1millionKV(cachService3);
    cachService3.showNodesInfo();
    cachService3.printStandardDevition();

    System.out.println("----------------分割线----------------");
  }

  private static CachService getCachService(AbstractCacheServerCluster simpleModHashCacheServerCluster) {
    CachService cachService = new CachService();
    mockUpTenNodes(simpleModHashCacheServerCluster);
    cachService.setCacheServerCluster(simpleModHashCacheServerCluster);
    return cachService;
  }

  private static void add1millionKV(CachService cachService) {
    for (int i = 0; i < 1000000; i++) {
      cachService.putKVToCache("key" + UUID.randomUUID(), "Data" + i);
    }
  }

  private static void mockUpTenNodes(AbstractCacheServerCluster abstractCacheServerCluster) {
    for (int i = 0; i < 10; i++) {
      CacheNode cacheNode = new CacheNode();
      cacheNode.setNodeIpAddr("192.168.0." + i);
      cacheNode.setNodeName("Node_" + i);
      abstractCacheServerCluster.addNewCacheNode(cacheNode);
    }
  }
}
