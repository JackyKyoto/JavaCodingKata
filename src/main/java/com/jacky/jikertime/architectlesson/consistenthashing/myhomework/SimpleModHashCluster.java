package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

/**
 * hashcode取余的路由方式
 */
public class SimpleModHashCluster extends AbstractCacheServerCluster {
  @Override
  public CacheNode routeToCacheNodeByKey(String key) {
    return cacheNodes.get((key.hashCode() & Integer.MAX_VALUE) % cacheNodes.size());
  }

  @Override
  public String getAlgoType() {
    return "取模的hash算法:";
  }
}
