package com.jacky.jikertime.architectlesson.linkedlist;


import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedListHomework {
  public static class LinkedNode {
    public String val;
    public LinkedNode next;

    public LinkedNode(String value) {
      val = value;
    }
  }


  public static class CheckResult {
    boolean combined;
    LinkedNode combineNode;

    public boolean isCombined() {
      return combined;
    }

    public CheckResult(boolean combined, LinkedNode combineNode) {
      this.combined = combined;
      this.combineNode = combineNode;
    }

    public void setCombined(boolean combined) {
      this.combined = combined;
    }

    public LinkedNode getCombineNode() {
      return combineNode;
    }

    public void setCombineNode(LinkedNode combineNode) {
      this.combineNode = combineNode;
    }
  }


  public static void main(String[] args) {
    LinkedNode nodeA = new LinkedNode("a");
    LinkedNode nodeB = new LinkedNode("b");
    nodeA.next = nodeB;


    LinkedNode nodeD = new LinkedNode("d");
    LinkedNode nodeE = new LinkedNode("e");
    LinkedNode nodeF = new LinkedNode("f");

    nodeD.next = nodeE;
    nodeE.next = nodeF;

    LinkedNode nodeX = new LinkedNode("x");
    LinkedNode nodeY = new LinkedNode("y");
    LinkedNode nodeZ = new LinkedNode("z");
    nodeX.next = nodeY;
    nodeY.next = nodeZ;

    nodeB.next = nodeX;
    nodeF.next = nodeX;

    CheckResult checkResult = checkTwoLinkedListIsCombined(nodeA, nodeD);
    System.out.println("check Result is :" + checkResult.combined +" and the combined node is "+checkResult.combineNode.val);

  }

  private static CheckResult checkTwoLinkedListIsCombined(LinkedNode nodeA, LinkedNode nodeD) {
    Map<LinkedNode, LinkedNode> preNodesMap = new HashMap<>();
    goThroughLinkedList(nodeA, preNodesMap);
    LinkedNode combineNode = goThroughLinkedList(nodeD, preNodesMap);
    boolean combined = true;
    if (combineNode == null) {
      combined = false;
    }
    return new CheckResult(combined, combineNode);
  }

  private static LinkedNode goThroughLinkedList(LinkedNode nodeA, Map<LinkedNode, LinkedNode> preNodesMap) {
    LinkedNode currNode = nodeA;
    while (currNode.next != null) {
      if (preNodesMap.get(currNode.next) != null) {
        return currNode.next;
      }
      preNodesMap.put(currNode.next, currNode);
      currNode = currNode.next;
    }
    return null;
  }
}
