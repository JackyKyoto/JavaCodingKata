package com.jacky.jikertime.architectlesson.consistenthashing.myhomework;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
  private Map<String,Object> kvMap = new HashMap();
  private String nodeName;
  private String nodeIpAddr;

  public String getNodeName() {
    return nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public String getNodeIpAddr() {
    return nodeIpAddr;
  }

  public void setNodeIpAddr(String nodeIpAddr) {
    this.nodeIpAddr = nodeIpAddr;
  }

  public void putValue(String key, Object value){
    kvMap.put(key,value);
  }
  public Object getValue(String key){
    return kvMap.get(key);
  }
  public int getKVSize(){
    return kvMap.size();
  }
}
