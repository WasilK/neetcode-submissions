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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = getSize(head);

        if(size == n){
            return head.next;
        }

        ListNode temp = head;
        int steps = size - n - 1;

        while(steps > 0){
            temp = temp.next;
            steps--;
        }

        temp.next = temp.next.next;
        return head;
    }

    public int getSize(ListNode head){
        int size = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        return size;
    }
}
