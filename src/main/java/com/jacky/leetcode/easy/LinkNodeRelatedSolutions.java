package com.jacky.leetcode.easy;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*

https://leetcode-cn.com/tag/linked-list/

精选 5 个常见的链表操作。
单链表反转
链表中环的检测
两个有序的链表合并
删除链表倒数第 n 个结点
求链表的中间结点

 */
public class LinkNodeRelatedSolutions {


    public ListNode reverseList(ListNode headNode) {
        ListNode nextNode = null;
        ListNode preNode = null;
        while (headNode.next != null) {
            nextNode = headNode.next;
            headNode.next = preNode;
            preNode = headNode;
            headNode = nextNode;
        }
        headNode.next = preNode;
        return headNode;
    }

    public ListNode reverseListOffical1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
//
//        作者：LeetCode
//        链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    public ListNode reverseListOffical2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseListOffical2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public boolean hasCycle(ListNode node1) {
        //https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode/
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode currentNode = node1;
        while (currentNode.next != null) {
            if (nodeSet.contains(currentNode)) {
                return true;
            } else {
                nodeSet.add(currentNode);
            }
            currentNode = currentNode.next;
        }
        return false;
    }

//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//    https://leetcode-cn.com/problems/swap-nodes-in-pairs/
//
//    给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
//    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//    示例:
//
//    给定 1->2->3->4, 你应该返回 2->1->4->3.
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public ListNode swapPairs(ListNode head) {
        // Dummy node acts as the prevNode for the head node
        // of the list and hence stores pointer to the head node.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;

        while ((head != null) && (head.next != null)) {

            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // Swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next; // jump
        }

        // Return the new head node.
        return dummy.next;
//
//        作者：LeetCode
//        链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-19/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    public ListNode swapPairsRecursive(ListNode head) {
//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-19/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//
        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next = swapPairsRecursive(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }

    public ListNode detectCycle(ListNode node1) {
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode currentNode = node1;
        while (currentNode.next != null) {
            if (!nodeSet.contains(currentNode)) {
                nodeSet.add(currentNode);
            } else {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public ListNode detectCycleSolutionTwo(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;

//        作者：LeetCode
//        链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

//    给你这个链表：1->2->3->4->5
//
//    当 k = 2 时，应当返回: 2->1->4->3->5
//
//    当 k = 3 时，应当返回: 3->2->1->4->5
//

    /**
     * 我的一个思路
     * 1, 计算整个队列长度为L
     * 2，然后根据L和K计算可以把队列拆成N个子队列，并进行拆分。
     * 3，每个子对列做倒序。最后一个子队列的长度如果小于K则不做倒序。
     * 4，把N个子队列合并
     *
     * @param head
     * @param k
     * @return
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;
        int totalLength = calcLength(head);
        if (totalLength < k) {
            return head;
        }
        List<ListNode> splittedSubNodes = splitByKAndLength(k, totalLength, head);

        ListNode newHead = null;
        ListNode previous = null;
        for (int i = 0; i < splittedSubNodes.size(); i++) {
            ListNode currentInLoop = splittedSubNodes.get(i);
            if (i == 0) {
                newHead = reverseList(currentInLoop);
                previous = newHead;
            }else if (i== splittedSubNodes.size()-1){
                if(calcLength(currentInLoop)<k) {
                    combineTwoNode(previous, currentInLoop);
                }else {
                    combineTwoNode(previous,reverseList(currentInLoop));

                }
            }else {
                combineTwoNode(previous,reverseList(currentInLoop));
            }
        }
        return newHead;

    }

    private void combineTwoNode(ListNode previous, ListNode currentInLoop) {
        ListNode currentNode = previous;
        while(currentNode.next!=null){
            currentNode= currentNode.next;
        }
        currentNode.next= currentInLoop;
    }


    private List<ListNode> splitByKAndLength(int k, int totalLength, ListNode head) {
        int offset = 0;
        ListNode currentNode = head;
        List<ListNode> tailNodes = Lists.newLinkedList();
        List<ListNode> headNodes = Lists.newLinkedList();
        for (int i = 0; i < totalLength; i++) {
            if (offset == 0) {
                headNodes.add(currentNode);
            }
            if (offset == k - 1) {
                tailNodes.add(currentNode);
                offset = 0;
            } else {
                offset++;
            }
            currentNode = currentNode.next;
        }
        for (ListNode tailNode : tailNodes) {
            tailNode.next = null;
        }
        return headNodes;
    }


    private int calcLength(ListNode head) {
        int length = 1;
        while (head.next != null) {
            length++;
            head = head.next;
        }
        return length;
    }


    /**
     *     作者：reals
     *     链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }


}
