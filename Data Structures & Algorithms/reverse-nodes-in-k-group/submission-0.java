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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;

        int cnt = k;
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;

        while(curr != null && cnt > 0){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            cnt--;
        }

        if(curr == null && cnt > 0){
            curr = prev;
            prev = null;
            next = null;
            while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
           }
        }

        if(cnt > 0){
            return prev;
        }else{
            head.next = reverseKGroup(curr, k);
        }

        return prev;
    }
}
