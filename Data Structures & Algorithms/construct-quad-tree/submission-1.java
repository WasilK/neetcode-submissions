/*
    ============================
       CONSTRUCT QUAD TREE
    ============================

    APPROACH:
    ----------
    This problem uses:

        1. Divide and Conquer
        2. Recursion

    For every square region of the grid:

        Step 1:
        Check whether all values in the current square
        are the same.

        Step 2:
        If all values are the same:
            -> Create a leaf node.
            -> No need to divide further.

        Step 3:
        If values are different:
            -> Divide the current square into 4 equal parts.
            -> Recursively construct a QuadTree for each part.

        The 4 parts are:

                    +-----------+-----------+
                    |           |           |
                    | Top Left  | Top Right |
                    |           |           |
                    +-----------+-----------+
                    |           |           |
                    | Bottom    | Bottom    |
                    | Left      | Right     |
                    +-----------+-----------+


    IMPORTANT IDEA:
    ---------------
    Think of it as:

        Is this entire square the same?
                    |
              +-----+-----+
              |           |
             YES          NO
              |           |
           Make leaf    Divide into 4
                           |
                     +-----+-----+
                     |     |     |
                    TL    TR    BL    BR
                     |     |     |     |
                  Recursively solve each


    PARAMETERS:
    -----------
    helper(grid, n, r, c)

        grid -> original matrix

        n -> size of the CURRENT square

        r -> starting row of the CURRENT square

        c -> starting column of the CURRENT square


    Example:

        helper(grid, 4, 0, 0)

        means:

        "Process a 4 x 4 square starting from
         row 0 and column 0."


    If n = 4:

        Current square:

            (r,c)
               |
               v
            +-------+-------+
            |       |       |
            |   TL  |   TR  |
            |       |       |
            +-------+-------+
            |       |       |
            |   BL  |   BR  |
            |       |       |
            +-------+-------+

        Each part has size:

            mid = n / 2

            mid = 4 / 2 = 2


    FOUR RECURSIVE CALLS:
    ---------------------

        Top Left:

            helper(grid, mid, r, c)

        Top Right:

            helper(grid, mid, r, c + mid)

        Bottom Left:

            helper(grid, mid, r + mid, c)

        Bottom Right:

            helper(grid, mid, r + mid, c + mid)


    WHY ARE r AND c CHANGED?

        Top Left:
            same row
            same column

            (r, c)


        Top Right:
            same row
            move right by mid

            (r, c + mid)


        Bottom Left:
            move down by mid
            same column

            (r + mid, c)


        Bottom Right:
            move down by mid
            move right by mid

            (r + mid, c + mid)


    CHECKING WHETHER THE REGION IS UNIFORM:
    ----------------------------------------

    We take the first element:

        grid[r][c]

    and compare every element in the current
    square against it.

    If we find:

        grid[r][c] != grid[i][j]

    then the current square contains both 0 and 1.

    Therefore:

        isSame = false

    and we must divide the square.


    IF EVERYTHING IS THE SAME:
    ---------------------------

        Example:

            1 1
            1 1

        All values are 1.

        Therefore:

            val = true
            isLeaf = true

        We return:

            new Node(true, true)


        Example:

            0 0
            0 0

        We return:

            new Node(false, true)


    IF VALUES ARE DIFFERENT:
    ------------------------

        Example:

            1 1
            1 0

        This region contains both 1 and 0.

        Therefore:

            isLeaf = false

        We divide it into 4 smaller regions.

        The current node stores the 4 children.


    WHY IS THE ROOT val FALSE WHEN IT IS NOT A LEAF?
    ------------------------------------------------

        For a non-leaf node, the value of val is not
        important according to the QuadTree representation.

        So we can simply use:

            false

        and:

            isLeaf = false

        The important information is its 4 children.


    RECURSION BASE CASE:
    ---------------------

    We don't explicitly write:

        if(n == 1)

    because a 1 x 1 region automatically contains
    only one value.

    Therefore:

        isSame = true

    and we create a leaf node.

*/


/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf,
                Node topLeft,
                Node topRight,
                Node bottomLeft,
                Node bottomRight) {

        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/


class Solution {

    /*
        ENTRY POINT
        ------------

        Initially, the entire grid is one square.

        If grid.length = 4:

            n = 4
            r = 0
            c = 0

        So we start with:

            helper(grid, 4, 0, 0)
    */
    public Node construct(int[][] grid) {

        return helper(grid, grid.length, 0, 0);
    }


    /*
        RECURSIVE FUNCTION
        ------------------

        n = size of current square
        r = starting row
        c = starting column
    */
    public Node helper(int grid[][], int n, int r, int c) {


        /*
            STEP 1:
            -------
            Check whether every value in the current
            square is the same.

            We initially assume that everything is same.

            If we find even ONE different value,
            we change it to false.
        */
        boolean isSame = true;


        /*
            We check only the current n x n region.

            Starting point:

                (r, c)

            Ending point:

                (r + n - 1, c + n - 1)

            That's why:

                i < n
                j < n

            and the actual grid position is:

                grid[r + i][c + j]
        */
        for(int i = 0; i < n; i++){

            for(int j = 0; j < n; j++){

                /*
                    Compare every element with the
                    first element of this region.

                    grid[r][c] = first element

                    grid[r + i][c + j] = current element
                */
                if(grid[r][c] != grid[r + i][c + j]){

                    /*
                        We found both 0 and 1
                        in the current region.

                        Therefore this region
                        cannot be a leaf.
                    */
                    isSame = false;

                    /*
                        No need to check more columns.
                    */
                    break;
                }
            }


            /*
                If we already know that the region
                contains different values, there is
                no need to continue checking rows.
            */
            if(!isSame){
                break;
            }
        }


        /*
            STEP 2:
            -------
            If every value in the current region
            is the same, create a LEAF node.

            Example:

                1 1
                1 1

            becomes:

                val = true
                isLeaf = true


            Example:

                0 0
                0 0

            becomes:

                val = false
                isLeaf = true
        */
        if(isSame){

            return new Node(
                grid[r][c] == 1,
                true
            );
        }


        /*
            STEP 3:
            -------
            Current region is NOT uniform.

            Therefore we divide it into 4 equal
            parts.

            Example:

                n = 4

                mid = 4 / 2
                    = 2

            So every child has size 2.
        */
        int mid = n / 2;


        /*
            TOP LEFT
            --------

                Same row
                Same column

                (r, c)
        */
        Node topLeft =
            helper(grid, mid, r, c);


        /*
            TOP RIGHT
            ---------

                Same row
                Move right by mid

                (r, c + mid)
        */
        Node topRight =
            helper(grid, mid, r, c + mid);


        /*
            BOTTOM LEFT
            -----------

                Move down by mid
                Same column

                (r + mid, c)
        */
        Node bottomLeft =
            helper(grid, mid, r + mid, c);


        /*
            BOTTOM RIGHT
            ------------

                Move down by mid
                Move right by mid

                (r + mid, c + mid)
        */
        Node bottomRight =
            helper(grid, mid, r + mid, c + mid);


        /*
            STEP 4:
            -------

            We have now constructed all 4 children.

                topLeft
                topRight
                bottomLeft
                bottomRight

            So create a parent node.

            Since this node is NOT a leaf:

                isLeaf = false

            Its val does not matter for a non-leaf
            node, so we use false.
        */
        return new Node(
            false,
            false,
            topLeft,
            topRight,
            bottomLeft,
            bottomRight
        );
    }
}


/*
    ==========================================
                 DRY RUN
    ==========================================

    Input:

        1 1 0 0
        1 1 0 0
        0 0 1 1
        0 0 1 1


    First call:

        helper(grid, 4, 0, 0)


    Whole grid contains both 1 and 0.

    Therefore:

        isSame = false

    Divide:

        mid = 4 / 2
            = 2


    Four calls:

        1. helper(grid, 2, 0, 0)
           -> Top Left

        2. helper(grid, 2, 0, 2)
           -> Top Right

        3. helper(grid, 2, 2, 0)
           -> Bottom Left

        4. helper(grid, 2, 2, 2)
           -> Bottom Right


    TOP LEFT:

        1 1
        1 1

        All values are same.

        Return:

            Node(true, true)


    TOP RIGHT:

        0 0
        0 0

        All values are same.

        Return:

            Node(false, true)


    BOTTOM LEFT:

        0 0
        0 0

        All values are same.

        Return:

            Node(false, true)


    BOTTOM RIGHT:

        1 1
        1 1

        All values are same.

        Return:

            Node(true, true)


    Finally root becomes:

                       ROOT
                  isLeaf = false
                   /   |   |   \
                  /    |   |    \
                 1     0   0     1
                leaf  leaf leaf  leaf


    ==========================================
                 COMPLEXITY
    ==========================================

    Let N = grid.length.

    TIME:
    -----

    In this implementation, for every recursive region
    we scan its cells to check whether all values are the same.

    In the worst case, this results in:

        O(N² log N)

    because:

        N² cells can be examined
        across approximately log N levels.


    SPACE:
    ------

    Recursion depth:

        O(log N)

    because the size keeps getting divided by 2:

        N
        N/2
        N/4
        N/8
        ...
        1

    Therefore auxiliary recursion space:

        O(log N)


    The QuadTree itself can contain O(N²) nodes
    in the worst case.

    Therefore:

        Auxiliary space = O(log N)
        Output space     = O(N²)


    ==========================================
                 CORE IDEA TO REMEMBER
    ==========================================

    Don't memorize the entire code.

    Remember these 4 steps:

        1. Check current square.

        2. If all values are same:
               return leaf.

        3. Otherwise:
               divide into 4.

        4. Recursively solve:
               TL
               TR
               BL
               BR


    In one sentence:

        "If the region is uniform, stop;
         otherwise divide it into four and repeat."
*/