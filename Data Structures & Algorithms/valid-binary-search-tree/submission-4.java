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

public class Solution {

    /*
     * LOGIC / APPROACH:
     *
     * In a valid BST:
     *      left subtree < node < right subtree
     *
     * We cannot check only the immediate parent.
     * A node must satisfy the restrictions imposed by ALL
     * of its ancestors.
     *
     * So, while doing DFS, we maintain a valid range:
     *
     *       left < node.val < right
     *
     * Initially, the root can have any value:
     *
     *       Long.MIN_VALUE < root.val < Long.MAX_VALUE
     *
     * When going LEFT:
     *      Current node becomes the UPPER bound.
     *
     *      valid(left child, left, node.val)
     *
     * When going RIGHT:
     *      Current node becomes the LOWER bound.
     *
     *      valid(right child, node.val, right)
     *
     * Example:
     *
     *              5
     *             / \
     *            3   7
     *           /
     *          1
     *
     * For 5:
     *      range = (-∞, +∞)
     *
     * For 3:
     *      range = (-∞, 5)
     *
     * For 1:
     *      range = (-∞, 3)
     *
     * This range keeps track of all restrictions created
     * by the ancestors.
     */

    public boolean isValidBST(TreeNode root) {

        // Initially, root can have any value.
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    /*
     * Checks whether the subtree rooted at 'node'
     * is valid when node is allowed to have a value
     * strictly between 'left' and 'right'.
     *
     * left  = minimum allowed value
     * right = maximum allowed value
     */
    public boolean valid(TreeNode node, long left, long right) {

        // Reached the end of a branch.
        // An empty subtree is a valid BST.
        if (node == null) {
            return true;
        }


        /*
         * Current node must satisfy:
         *
         *      left < node.val < right
         *
         * If it violates this range, the tree is NOT
         * a valid BST.
         */
        if (!(left < node.val && node.val < right)) {
            return false;
        }


        /*
         * Check LEFT subtree:
         *
         * Everything on the left must be smaller
         * than the current node.
         *
         * So current node becomes the new RIGHT boundary.
         *
         * Allowed range:
         *      left < value < node.val
         */
        boolean leftValid =
                valid(node.left, left, node.val);


        /*
         * Check RIGHT subtree:
         *
         * Everything on the right must be greater
         * than the current node.
         *
         * So current node becomes the new LEFT boundary.
         *
         * Allowed range:
         *      node.val < value < right
         */
        boolean rightValid =
                valid(node.right, node.val, right);


        // Both subtrees must be valid.
        return leftValid && rightValid;
    }
}