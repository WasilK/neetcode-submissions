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
    public void reorderList(ListNode head) {
    
        ListNode mid = getMid(head);
        // reverse
        ListNode curr = mid.next;
        mid.next = null;
        ListNode prev = null;
        ListNode next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode head1 = head;
        ListNode head2 = prev;
        ListNode head1Next;
        ListNode head2Next;

        while(head1 != null && head2 != null){
            head1Next = head1.next;
            head1.next = head2;
            head2Next = head2.next;
            head2.next = head1Next;

            head1 = head1Next;
            head2 = head2Next;
        }
    }

    public ListNode getMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
