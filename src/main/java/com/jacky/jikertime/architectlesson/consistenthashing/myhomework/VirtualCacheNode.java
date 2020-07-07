package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;


/**
 * @author jacky
 */
public class VirtualCacheNode {
  private CacheNode actualCacheNode;
  private String  virtualCacheNodeName;

  public VirtualCacheNode(CacheNode actualCacheNode, String virtualCacheNodeName) {
    this.actualCacheNode = actualCacheNode;
    this.virtualCacheNodeName = virtualCacheNodeName;
  }

  public CacheNode getActualCacheNode() {
    return actualCacheNode;
  }

  public void setActualCacheNode(CacheNode actualCacheNode) {
    this.actualCacheNode = actualCacheNode;
  }

  public String getVirtualCacheNodeName() {
    return virtualCacheNodeName;
  }

  public void setVirtualCacheNodeName(String virtualCacheNodeName) {
    this.virtualCacheNodeName = virtualCacheNodeName;
  }
}
