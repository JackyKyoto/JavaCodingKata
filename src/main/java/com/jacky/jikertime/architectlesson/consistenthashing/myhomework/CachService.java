package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

public class CachService {
  private AbstractCacheServerCluster cacheServerCluster;

  public void setCacheServerCluster(AbstractCacheServerCluster cacheServerCluster) {
    this.cacheServerCluster = cacheServerCluster;
  }

  public void putKVToCache(String key, Object value){
    CacheNode cacheNode = cacheServerCluster.routeToCacheNodeByKey(key);
    cacheNode.putValue(key,value);
  }

  public void showNodesInfo() {
    cacheServerCluster.showNodesInfo();
  }
  public void printStandardDevition(){
    System.out.println("标准差为:"+cacheServerCluster.getStandardDevition());
  }

}
