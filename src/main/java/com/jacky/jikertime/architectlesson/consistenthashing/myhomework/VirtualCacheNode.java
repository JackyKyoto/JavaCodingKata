package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;


/**
 * 虚拟节点
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

  public String getVirtualCacheNodeName() {
    return virtualCacheNodeName;
  }


}
