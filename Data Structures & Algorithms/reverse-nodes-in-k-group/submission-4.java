/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { 
 *         this.val = val; 
 *         this.next = next; 
 *     }
 * }
 */

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {

        // Create a dummy node before the actual head.
        // dummy -> head
        //
        // Example:
        // dummy -> 1 -> 2 -> 3 -> 4 -> 5
        ListNode dummy = new ListNode(0);

        // Connect dummy node to the actual head.
        dummy.next = head;

        // groupPrev points to the node just before
        // the group that we are going to reverse.
        //
        // Initially:
        // dummy -> 1 -> 2 -> 3 -> 4 -> 5
        //   ^
        // groupPrev
        ListNode groupPrev = dummy;


        // Keep reversing groups until we don't have
        // enough nodes for another complete group.
        while (true) {

            // Find the kth node starting from groupPrev.
            //
            // If k = 2:
            //
            // dummy -> 1 -> 2 -> 3 -> 4 -> 5
            //   ^
            // groupPrev
            //
            // kth will point to node 2.
            ListNode kth = getKth(groupPrev, k);


            // If kth is null, there are fewer than k nodes
            // remaining, so we should NOT reverse them.
            if (kth == null) {
                break;
            }


            // curr points to the first node of the group.
            //
            // If:
            // dummy -> 1 -> 2 -> 3 -> 4 -> 5
            //
            // curr = 1
            ListNode curr = groupPrev.next;


            // prev starts at the node AFTER the current group.
            //
            // If the group is:
            // 1 -> 2
            //
            // then kth = 2
            // kth.next = 3
            //
            // So:
            // prev = 3
            //
            // This allows the reversed group to connect
            // directly to the remaining list.
            ListNode prev = kth.next;


            // groupNext represents the first node AFTER
            // the group we are reversing.
            //
            // Example:
            //
            // 1 -> 2 -> 3 -> 4 -> 5
            //      ^
            //     kth
            //
            // groupNext = 3
            ListNode groupNext = kth.next;


            // Reverse all nodes from curr up to,
            // but NOT including, groupNext.
            //
            // Example:
            //
            // Before:
            // 1 -> 2 -> 3
            //
            // After:
            // 2 -> 1 -> 3
            while (curr != groupNext) {

                // Save the next node before changing
                // curr.next.
                ListNode next = curr.next;


                // Reverse the current node's pointer.
                //
                // Example:
                // 1 -> 3
                //
                // instead of:
                // 1 -> 2
                curr.next = prev;


                // Move prev forward.
                prev = curr;


                // Move curr forward using the saved node.
                curr = next;
            }


            // Save the original first node of the group.
            //
            // After reversal, this node becomes
            // the LAST node of the group.
            //
            // Example:
            //
            // Before:
            // 1 -> 2
            //
            // After:
            // 2 -> 1
            //
            // temp = 1
            ListNode temp = groupPrev.next;


            // Connect groupPrev to the new first node
            // of the reversed group.
            //
            // Example:
            //
            // Before:
            // dummy -> 1 -> 2 -> 3
            //
            // After:
            // dummy -> 2 -> 1 -> 3
            //
            // kth = 2
            groupPrev.next = kth;


            // Move groupPrev to the end of the reversed group.
            //
            // temp is the old first node, which is now
            // the last node of the reversed group.
            //
            // Example:
            //
            // dummy -> 2 -> 1 -> 3
            //             ^
            //          groupPrev
            groupPrev = temp;
        }


        // dummy.next points to the actual head
        // of the final modified list.
        //
        // Example:
        // dummy -> 2 -> 1 -> 4 -> 3 -> 5
        //
        // Return:
        // 2 -> 1 -> 4 -> 3 -> 5
        return dummy.next;
    }


    // This method finds the kth node starting from curr.
    //
    // Example:
    //
    // dummy -> 1 -> 2 -> 3 -> 4 -> 5
    //   ^
    // curr
    //
    // k = 2
    //
    // Move 1 time:
    // curr -> 1
    //
    // Move 2 times:
    // curr -> 2
    //
    // Return node 2.
    public ListNode getKth(ListNode curr, int k) {

        // Move k nodes forward.
        while (curr != null && k > 0) {

            // Move to the next node.
            curr = curr.next;

            // One step completed.
            k--;
        }

        // Return the kth node.
        //
        // If there are fewer than k nodes,
        // curr becomes null.
        return curr;
    }
}

