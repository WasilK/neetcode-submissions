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


class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        // If there are no lists, there is nothing to merge.
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Total number of linked lists.
        int n = lists.length;

        // We will first merge lists that are 1 position apart.
        //
        // Example:
        // interval = 1
        // [0] + [1]
        // [2] + [3]
        // [4] + [5]
        // ...
        //
        // Then interval becomes 2:
        // [0] + [2]
        // [4] + [6]
        //
        // Then interval becomes 4:
        // [0] + [4]
        //
        // So, in every round, the size of the groups being merged doubles.
        int interval = 1;

        // Keep merging until the interval becomes greater than
        // or equal to the number of lists.
        while (interval < n) {

            // Merge pairs of lists.
            //
            // i represents the first list of the pair.
            //
            // i + interval represents the second list of the pair.
            //
            // We move i by 2 * interval because after merging one pair,
            // we want to jump to the next pair.
            for (int i = 0; i + interval < n; i += interval * 2) {

                // Merge the two sorted lists.
                //
                // Store the merged list back at lists[i].
                //
                // Example:
                // lists[0] = [1,4,7]
                // lists[1] = [2,3,8]
                //
                // After merging:
                // lists[0] = [1,2,3,4,7,8]
                //
                // We don't need lists[1] anymore.
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }

            // Double the interval.
            //
            // This means the next round will merge bigger groups.
            //
            // Example:
            // 1 -> 2 -> 4 -> 8 -> ...
            interval *= 2;
        }

        // After all rounds, lists[0] contains the completely
        // merged sorted linked list.
        return lists[0];
    }


    // This function merges TWO sorted linked lists.
    //
    // Example:
    //
    // list1: 1 -> 4 -> 7
    // list2: 2 -> 3 -> 8
    //
    // Result:
    // 1 -> 2 -> 3 -> 4 -> 7 -> 8
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Dummy node makes it easier to build the result list.
        //
        // Instead of worrying about where the first node goes,
        // we always attach nodes after dummy.
        ListNode dummy = new ListNode(-1);

        // curr points to the last node of our result list.
        ListNode curr = dummy;


        // Continue while BOTH lists still have nodes.
        while (list1 != null && list2 != null) {

            // Compare the current nodes of both lists.
            if (list1.val <= list2.val) {

                // list1 has the smaller value,
                // so attach list1's node to the result.
                curr.next = list1;

                // Move list1 forward.
                list1 = list1.next;

            } else {

                // list2 has the smaller value,
                // so attach list2's node to the result.
                curr.next = list2;

                // Move list2 forward.
                list2 = list2.next;
            }

            // Move curr forward because we just added a node.
            curr = curr.next;
        }


        // At this point, one of the lists has become null.
        //
        // If list1 still has nodes, attach the remaining part
        // directly to the result.
        if (list1 != null) {
            curr.next = list1;
        }

        // If list2 still has nodes, attach the remaining part
        // directly to the result.
        if (list2 != null) {
            curr.next = list2;
        }


        // dummy itself is not part of the answer.
        // The actual result starts from dummy.next.
        return dummy.next;
    }
}


