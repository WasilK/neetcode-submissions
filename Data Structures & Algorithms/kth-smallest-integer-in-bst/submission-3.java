
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

    // Keeps track of how many nodes we have visited
    // during inorder traversal.
    int count = 0;

    public int kthSmallest(TreeNode root, int k) {

        /*
         * IMPORTANT BST PROPERTY:
         *
         * In a Binary Search Tree:
         *     left < root < right
         *
         * Therefore, inorder traversal:
         *     left -> root -> right
         *
         * visits nodes in ascending sorted order.
         *
         * Example:
         *
         *         5
         *        / \
         *       3   7
         *      / \
         *     2   4
         *
         * Inorder:
         *     2 -> 3 -> 4 -> 5 -> 7
         *
         * Therefore, the kth node visited during
         * inorder traversal is the kth smallest.
         */
        return inorder(root, k);
    }

    public int inorder(TreeNode root, int k) {

        // Base case:
        // If there is no node, there is nothing to search.
        if(root == null) {
            return -1;
        }

        /*
         * STEP 1:
         * Visit the left subtree first.
         *
         * Since this is a BST, all values in the
         * left subtree are smaller than the current node.
         */
        int left = inorder(root.left, k);

        /*
         * If the kth smallest element was found
         * somewhere inside the left subtree,
         * return it immediately.
         *
         * This prevents us from doing unnecessary work.
         */
        if(left != -1) {
            return left;
        }

        /*
         * STEP 2:
         * Visit the current node.
         *
         * Every time we visit a node during inorder,
         * we increase count.
         *
         * count represents the position of this node
         * in the sorted order.
         */
        count++;

        /*
         * If this is the kth visited node,
         * then this is the kth smallest element.
         */
        if(count == k) {
            return root.val;
        }

        /*
         * STEP 3:
         * Visit the right subtree.
         *
         * Values in the right subtree are greater
         * than the current node.
         */
        return inorder(root.right, k);
    }
}


// ### Remember this pattern

// ```text
// BST
//  ↓
// Inorder traversal
//  ↓
// Ascending sorted order
//  ↓
// kth visited node = kth smallest
// ```

// For **kth largest**, simply remember the reverse:

// ```text
// BST
//  ↓
// Reverse inorder
//  ↓
// right → root → left
//  ↓
// Descending order
//  ↓
// kth visited node = kth largest
// ```

// **Complexity:**

// * Time: `O(n)` worst case
// * Space: `O(h)` recursion stack
// * No extra `List` or `PriorityQueue` is required.
