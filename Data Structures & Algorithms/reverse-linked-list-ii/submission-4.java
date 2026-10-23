
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // If list is empty OR there is nothing to reverse
        if (head == null || left == right) return head;

        // Dummy node helps us handle the case when left == 1
        // dummy -> head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Move prev to the node just BEFORE the reversal starts
        //
        // Example:
        // 1 -> 2 -> 3 -> 4 -> 5
        //      ^
        //     left
        //
        // prev will point to 1
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // curr is the FIRST node of the part we want to reverse.
        //
        // Example:
        // 1 -> 2 -> 3 -> 4 -> 5
        //      ^
        //     curr
        ListNode curr = prev.next;

        // We need (right - left) rearrangements.
        //
        // Example:
        // left = 2, right = 4
        // We need 2 operations:
        //
        // 1 -> 2 -> 3 -> 4 -> 5
        //      ↓
        // 1 -> 3 -> 2 -> 4 -> 5
        //
        // 1 -> 3 -> 2 -> 4 -> 5
        //      ↓
        // 1 -> 4 -> 3 -> 2 -> 5
        for (int i = 0; i < right - left; i++) {

            // next is the node immediately AFTER curr.
            //
            // We are going to take this node
            // and move it to the FRONT of the reversed portion.
            ListNode next = curr.next;

            // Remove 'next' from its current position.
            //
            // Example:
            // 2 -> 3 -> 4
            //
            // curr = 2
            // next = 3
            //
            // After this:
            // 2 -> 4
            //
            // Node 3 is temporarily disconnected.
            curr.next = next.next;

            // Put 'next' before the current beginning of
            // the reversed portion.
            //
            // Example:
            // 1 -> 2 -> 4
            //
            // next = 3
            //
            // After this:
            // 3 -> 2
            next.next = prev.next;

            // Connect prev to the newly moved node.
            //
            // Before:
            // 1 -> 2
            //
            // After:
            // 1 -> 3 -> 2
            prev.next = next;
        }

        // dummy.next is the new head of the list.
        return dummy.next;
    }
}

