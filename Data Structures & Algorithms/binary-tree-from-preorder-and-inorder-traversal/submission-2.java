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

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        /*
         * We need to quickly find the position of a value
         * in the inorder array.
         *
         * Instead of searching the inorder array every time
         * (which would take O(n)), store:
         *
         * value -> index in inorder
         *
         * Example:
         *
         * inorder = [9, 3, 15, 20, 7]
         *
         * map:
         * 9  -> 0
         * 3  -> 1
         * 15 -> 2
         * 20 -> 3
         * 7  -> 4
         */
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        /*
         * Initially, we want to build the tree using
         * the complete preorder and inorder arrays.
         */
        return build(
            preorder,
            0,
            preorder.length - 1,
            inorder,
            0,
            inorder.length - 1,
            map
        );
    }


    /*
     * Build the tree using the current ranges:
     *
     * preorder[preStart ... preEnd]
     * inorder[inStart ... inEnd]
     */
    public TreeNode build(
        int[] preorder,
        int preStart,
        int preEnd,
        int[] inorder,
        int inStart,
        int inEnd,
        HashMap<Integer, Integer> map
    ){

        /*
         * Base case:
         *
         * If the range is invalid, there is no subtree
         * to build.
         */
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }


        /*
         * PREORDER PROPERTY:
         *
         * preorder = root -> left -> right
         *
         * Therefore, the first element of the current
         * preorder range is the root.
         */
        TreeNode root = new TreeNode(preorder[preStart]);


        /*
         * Find the root's position in inorder.
         *
         * INORDER:
         * left -> root -> right
         *
         * Therefore:
         *
         * elements before inRoot  = left subtree
         * elements after inRoot   = right subtree
         */
        int inRoot = map.get(root.val);


        /*
         * Calculate how many nodes are present
         * in the left subtree.
         *
         * Example:
         *
         * inorder:
         * [9, 3, 15, 20, 7]
         *     ^
         *   root = 3
         *
         * inStart = 0
         * inRoot  = 1
         *
         * numsLeft = 1 - 0 = 1
         *
         * So there is 1 node in the left subtree.
         */
        int numsLeft = inRoot - inStart;


        /*
         * BUILD LEFT SUBTREE
         *
         * Current root occupies:
         * preorder[preStart]
         *
         * The next numsLeft elements in preorder
         * belong to the left subtree.
         *
         * Therefore:
         *
         * preorder range:
         * preStart + 1
         *      to
         * preStart + numsLeft
         *
         * In inorder:
         * inStart
         *      to
         * inRoot - 1
         */
        root.left = build(
            preorder,
            preStart + 1,
            preStart + numsLeft,
            inorder,
            inStart,
            inRoot - 1,
            map
        );


        /*
         * BUILD RIGHT SUBTREE
         *
         * After:
         * 1 root
         * + numsLeft nodes of left subtree
         *
         * the right subtree starts at:
         *
         * preStart + numsLeft + 1
         *
         * In inorder, everything after the root
         * belongs to the right subtree.
         */
        root.right = build(
            preorder,
            preStart + numsLeft + 1,
            preEnd,
            inorder,
            inRoot + 1,
            inEnd,
            map
        );


        /*
         * Return the root of the current subtree.
         */
        return root;
    }
}
