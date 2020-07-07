package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author jacky
 */
public abstract class AbstractCacheServerCluster {
  protected List<CacheNode> cacheNodes = Lists.newArrayList();

  public void addNewCacheNode(CacheNode cacheNode) {
    cacheNodes.add(cacheNode);
  }

  abstract public CacheNode routeToCacheNodeByKey(String key);

  public int getHashCode(String key) {
    //return key.hashCode() & Integer.MAX_VALUE;
    final int p = 16777619;
    int hash = (int)2166136261L;
    for (int i = 0; i < key.length(); i++) {
      hash = (hash ^ key.charAt(i)) * p;
    }
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;

    // 如果算出来的值为负数则取其绝对值
    if (hash < 0) {
      hash = Math.abs(hash);
    }
    return hash;
  }
  //均值
  public double getAverage() {
    int sum = 0;
    for (int i = 0; i < cacheNodes.size(); i++) {
      sum += cacheNodes.get(i).getKVSize();
    }
    return (double) (sum / cacheNodes.size());
  }

  //标准差
  public double getStandardDevition() {
    double sum = 0;
    for (int i = 0; i < cacheNodes.size(); i++) {
      sum += Math.sqrt(((double) cacheNodes.get(i).getKVSize() - getAverage()) * (cacheNodes.get(i).getKVSize() - getAverage()));
    }
    return (sum / (cacheNodes.size() - 1));
  }

  public void showNodesInfo() {
    for (CacheNode cacheNode : cacheNodes) {
      System.out.println(String.format("CacheNode Name:%s,IP:%s , KV size:%s", cacheNode.getNodeName(), cacheNode.getNodeIpAddr(), cacheNode.getKVSize()));
    }
  }
}
