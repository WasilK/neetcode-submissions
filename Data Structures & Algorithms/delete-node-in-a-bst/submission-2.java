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

    public TreeNode deleteNode(TreeNode root, int key) {

        // If the tree is empty, there is nothing to delete.
        if(root == null){
            return null;
        }

        // Special case:
        // If the root itself contains the key, we cannot simply modify
        // root's parent because the root has no parent.
        // So, helper() finds the new root after deleting this node.
        if(root.val == key){
            return helper(root);
        }

        // Keep a reference to the original root.
        // During the while loop, 'root' will move through the tree,
        // so we need 'dummy' to return the original root at the end.
        TreeNode dummy = root;

        // Search for the node that contains 'key'.
        // Since this is a BST, we can decide whether to go left or right
        // by comparing root.val with key.
        while(root != null){

            // If current value is greater than key,
            // the key can only exist in the LEFT subtree.
            if(root.val > key){

                // Check whether the left child is the node we want to delete.
                // We check the child because we need access to its parent
                // so that we can reconnect the parent's left pointer.
                if(root.left != null && root.left.val == key){

                    // Delete the left child and connect the parent
                    // directly to the new subtree returned by helper().
                    root.left = helper(root.left);

                    // Deletion is complete.
                    break;

                }else{

                    // Key is not the immediate left child,
                    // so continue searching in the left subtree.
                    root = root.left;
                }

            }else{

                // If current value is smaller than key,
                // the key can only exist in the RIGHT subtree.

                // Check whether the right child is the node we want to delete.
                if(root.right != null && root.right.val == key){

                    // Delete the right child and connect the parent
                    // directly to the new subtree returned by helper().
                    root.right = helper(root.right);

                    // Deletion is complete.
                    break;

                }else{

                    // Key is not the immediate right child,
                    // so continue searching in the right subtree.
                    root = root.right;
                }
            }
        }

        // Return the original root of the tree.
        // 'dummy' still points to it even though 'root' has moved.
        return dummy;
    }


    /*
     * helper() deletes the given node and returns the root of the
     * resulting subtree.
     *
     * There are 3 cases:
     *
     * 1. Node has no left child
     * 2. Node has no right child
     * 3. Node has both left and right children
     */
    public TreeNode helper(TreeNode root){

        // CASE 1:
        // No left subtree.
        //
        // The right subtree can directly take this node's position.
        //
        // Before:       X
        //                 \
        //                  R
        //
        // After:         R
        if(root.left == null){
            return root.right;

        // CASE 2:
        // No right subtree.
        //
        // The left subtree can directly take this node's position.
        //
        // Before:       X
        //              /
        //             L
        //
        // After:       L
        }else if(root.right == null){
            return root.left;
        }


        // CASE 3:
        // Node has BOTH left and right children.
        //
        // We cannot simply return one child because we must also
        // reconnect the other subtree while maintaining BST ordering.

        // Save the right subtree.
        TreeNode rightChild = root.right;

        // Find the rightmost (largest) node in the left subtree.
        //
        // Example:
        //
        //             X
        //            / \
        //           L   R
        //            \
        //             A
        //              \
        //               B
        //
        // B is the largest node in X's left subtree.
        TreeNode lastRight = findLastRight(root.left);

        // Attach X's right subtree to the rightmost node
        // of X's left subtree.
        //
        // This is safe because every value in the right subtree
        // is greater than X, and every value in the left subtree
        // is smaller than X.
        //
        // Result:
        //
        //             L
        //            / \
        //          ...  A
        //                \
        //                 B
        //                  \
        //                   R
        lastRight.right = rightChild;

        // The left subtree becomes the new root of this subtree.
        return root.left;
    }


    /*
     * Finds the rightmost node of a subtree.
     *
     * In a BST, the rightmost node contains the largest value.
     *
     * Example:
     *
     *       3
     *        \
     *         5
     *          \
     *           7
     *
     * findLastRight(3) returns node 7.
     */
    public TreeNode findLastRight(TreeNode root){

        // If there is no right child, this is the rightmost node.
        if(root.right == null){
            return root;
        }

        // Otherwise, keep moving to the right until
        // we reach the rightmost node.
        return findLastRight(root.right);
    }
}