/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            TreeNode level = null;
            int len = 0;
            for(int i = q.size(); i > 0; i--){
                TreeNode curr = q.poll();
                if(curr != null){
                    q.add(curr.left);
                    q.add(curr.right);
                    level = curr;
                    len++;
                }
            }
            if(len > 0){
                res.add(level.val);
            }
        }
        return res;
    }
}
