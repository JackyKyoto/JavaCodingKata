package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 无虚拟节点的集群
 */
public class NoVirtualNodeConsHashCluster extends AbstractCacheServerCluster {
  //key表示服务器的hash值，value表示服务器
  private static SortedMap<Integer, CacheNode> sortedMap = new TreeMap<Integer, CacheNode>();
  @Override
  public CacheNode routeToCacheNodeByKey(String key) {
    int keyHashCode = getHashCode(key);
    //得到大于该Hash值的所有Map
    SortedMap<Integer, CacheNode> subMap = sortedMap.tailMap(keyHashCode);
    if (subMap.isEmpty()) {
      //如果没有比该key的hash值大的，则从第一个node开始
      Integer i = sortedMap.firstKey();
      //返回对应的服务器
      return sortedMap.get(i);
    } else {
      //第一个Key就是顺时针过去离node最近的那个结点
      Integer i = subMap.firstKey();
      //返回对应的服务器
      return subMap.get(i);
    }
  }


  @Override
  public void addNewCacheNode(CacheNode cacheNode) {
    super.addNewCacheNode(cacheNode);
    sortedMap.put(getHashCode(cacheNode.getNodeName()),cacheNode);
  }

  @Override
  public String getAlgoType() {
    return "无虚拟节点的一致性hash算法:";
  }
}
