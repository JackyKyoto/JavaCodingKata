package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

public class SimpleModHashCacheServerCluster extends AbstractCacheServerCluster {
  @Override
  public CacheNode routeToCacheNodeByKey(String key) {
    return cacheNodes.get((key.hashCode() & Integer.MAX_VALUE) % cacheNodes.size());
  }
}
