package com.jacky.leetcode.easy;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LinkedListSolutionsTest {
    @Test
    public void should_reverse_linked_list() {
        LinkNodeRelatedSolutions reverseLinkedListSolution = new LinkNodeRelatedSolutions();
        LinkNodeRelatedSolutions.ListNode node1 = this.getListNodes(Lists.newArrayList(1, 2, 3, 4, 5));
        String beforeReverse = showNodeInfoListsFromHeadToTail(node1);
        assertEquals("1->2->3->4->5->NULL", beforeReverse);
        LinkNodeRelatedSolutions.ListNode reversedNode = reverseLinkedListSolution.reverseListOffical2(node1);
        String afterReverse = showNodeInfoListsFromHeadToTail(reversedNode);
        assertEquals("5->4->3->2->1->NULL", afterReverse);
    }

    @Test
    public void should_check_has_cycle() {
        LinkNodeRelatedSolutions.ListNode node1 = getListNodes(Lists.newArrayList(1, 2, 3, 4));
        node1.next.next.next = node1.next;
        LinkNodeRelatedSolutions solution = new LinkNodeRelatedSolutions();
        assertEquals(true, solution.hasCycle(node1));
    }

    @Test
    public void should_swap_pair() {
        LinkNodeRelatedSolutions.ListNode node1 = getListNodes(Lists.newArrayList(1, 2, 3, 4));
        LinkNodeRelatedSolutions.ListNode node2 = getListNodes(Lists.newArrayList(1, 2, 3, 4));
        LinkNodeRelatedSolutions solution = new LinkNodeRelatedSolutions();
        String afterswap = showNodeInfoListsFromHeadToTail(solution.swapPairs(node1));
        assertEquals("2->1->4->3->NULL", afterswap);
        String afterswap2 = showNodeInfoListsFromHeadToTail(solution.swapPairsRecursive(node2));
        assertEquals("2->1->4->3->NULL", afterswap2);
    }

    @Test
    public void shouldDetectCycle() {
        LinkNodeRelatedSolutions.ListNode node1 = getListNodes(Lists.newArrayList(3, 2, 0, 4));
        node1.next.next.next.next = node1.next;
        System.out.println(showNodeInfoListsFromHeadToTail(node1));
        LinkNodeRelatedSolutions solution = new LinkNodeRelatedSolutions();
        LinkNodeRelatedSolutions.ListNode wrongNode = solution.detectCycle(node1);
        assertEquals(node1.next, wrongNode);
        LinkNodeRelatedSolutions.ListNode wrongNode2 = solution.detectCycleSolutionTwo(node1);
        assertEquals(node1.next, wrongNode2);
    }

    @Test
    public void shouldReverseKGroup() {
        LinkNodeRelatedSolutions.ListNode node1 = getListNodes(Lists.newArrayList(1, 2,3,4,5,6,7));
        LinkNodeRelatedSolutions solution = new LinkNodeRelatedSolutions();
        LinkNodeRelatedSolutions.ListNode afterReversed = solution.reverseKGroup2(node1,3);
        String afterReverse = showNodeInfoListsFromHeadToTail(afterReversed);
        assertEquals("2->1->4->3->NULL", afterReverse);
    }

    private <E> LinkNodeRelatedSolutions.ListNode getListNodes(ArrayList<E> newArrayList) {
        LinkNodeRelatedSolutions.ListNode headNode = new LinkNodeRelatedSolutions.ListNode((Integer) newArrayList.get(0));
        LinkNodeRelatedSolutions.ListNode currentNode = headNode;
        for (int i = 1; i < newArrayList.size(); i++) {
            currentNode.next = new LinkNodeRelatedSolutions.ListNode((Integer) newArrayList.get(i));
            currentNode = currentNode.next;
        }
        return headNode;
    }

    public static String showNodeInfoListsFromHeadToTail(LinkNodeRelatedSolutions.ListNode headNode) {
        String nodeInfo = "";
        int loopTime = 0;
        do {
            nodeInfo += headNode.val + "->";
            headNode = headNode.next;
            loopTime++;
            if (loopTime == 200) {
                return nodeInfo;
            }
        } while (headNode.next != null);

        if (headNode.next == null) {
            nodeInfo = nodeInfo + headNode.val + "->NULL";
        }
        return nodeInfo;
    }
}