package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 带虚拟节点的集群
 */
public class VirtualNodeConsHashCluster extends AbstractCacheServerCluster {
  //key表示服务器的hash值，value表示服务器
  private static SortedMap<Integer, VirtualCacheNode> sortedMap = new TreeMap<Integer, VirtualCacheNode>();
  private Integer VIRTUALNODECOUNT = 2500;

  @Override
  public CacheNode routeToCacheNodeByKey(String key) {
    int keyHashCode = getHashCode(key);
    //得到大于该Hash值的所有Map
    SortedMap<Integer, VirtualCacheNode> subMap = sortedMap.tailMap(keyHashCode);
    if (subMap.isEmpty()) {
      //如果没有比该key的hash值大的，则从第一个node开始
      Integer i = sortedMap.firstKey();
      //返回对应的服务器
      return sortedMap.get(i).getActualCacheNode();
    } else {
      //第一个Key就是顺时针过去离node最近的那个结点
      Integer i = subMap.firstKey();
      //返回对应的服务器
      return subMap.get(i).getActualCacheNode();
    }
  }
  @Override
  public String getAlgoType() {
    return "带虚拟节点的一致性hash算法:";
  }
  @Override
  public void addNewCacheNode(CacheNode cacheNode) {
    super.addNewCacheNode(cacheNode);
    for (int i = 0; i < VIRTUALNODECOUNT; i++) {
      VirtualCacheNode virtualCacheNode = new VirtualCacheNode(cacheNode, cacheNode.getNodeName() + "_VIRTUAL" + UUID.randomUUID());
      sortedMap.put(getHashCode(virtualCacheNode.getVirtualCacheNodeName()), virtualCacheNode);
    }
  }
}
