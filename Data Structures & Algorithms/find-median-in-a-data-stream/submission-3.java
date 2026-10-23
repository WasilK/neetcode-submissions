class MedianFinder {

    // Max Heap:
    // Stores the SMALLER half of the numbers.
    // The largest number among the smaller half stays at the top.
    PriorityQueue<Integer> smallHeap;

    // Min Heap:
    // Stores the LARGER half of the numbers.
    // The smallest number among the larger half stays at the top.
    PriorityQueue<Integer> largeHeap;

    public MedianFinder() {

        // Max heap
        // Java's PriorityQueue is normally a min heap,
        // so we reverse the comparison to make it a max heap.
        smallHeap = new PriorityQueue<>((a, b) -> b - a);

        // Normal PriorityQueue = Min Heap
        largeHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {

        // STEP 1:
        // Initially put the new number into the small heap.
        smallHeap.offer(num);


        // STEP 2:
        // There are two possible problems:
        //
        // 1. smallHeap has become too large
        // 2. The largest element of smallHeap is greater than
        //    the smallest element of largeHeap.
        //
        // Example:
        //
        // smallHeap = [10, 5, 2]
        // largeHeap = [7, 12]
        //
        // 10 > 7, which violates our rule:
        // everything in smallHeap should be <= everything in largeHeap.
        //
        // So we move the largest element from smallHeap
        // into largeHeap.
        if (smallHeap.size() - largeHeap.size() > 1
                || !largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()) {

            // smallHeap.peek() gives the LARGEST element
            // because smallHeap is a max heap.
            //
            // poll() removes that largest element.
            //
            // Then we put it into largeHeap.
            largeHeap.offer(smallHeap.poll());
        }


        // STEP 3:
        // Now check whether largeHeap has become too large.
        //
        // We want the sizes to be either:
        //
        // small = large
        // OR
        // small = large + 1
        // OR
        // large = small + 1
        //
        // The difference should NEVER be greater than 1.
        if (largeHeap.size() - smallHeap.size() > 1) {

            // largeHeap is a min heap,
            // so poll() removes its SMALLEST element.
            //
            // Move that element back to smallHeap.
            smallHeap.offer(largeHeap.poll());
        }
    }
    
    public double findMedian() {

        // CASE 1:
        // Both heaps have the same number of elements.
        //
        // Example:
        //
        // smallHeap = [3, 2]
        // largeHeap = [4, 5]
        //
        // The two middle values are:
        // 3 and 4
        //
        // Median = (3 + 4) / 2
        if (smallHeap.size() == largeHeap.size()) {

            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        }


        // CASE 2:
        // smallHeap has one extra element.
        //
        // Example:
        //
        // smallHeap = [4, 3, 2]
        // largeHeap = [5, 6]
        //
        // The middle element is the largest element
        // of the smaller half.
        //
        // Since smallHeap is a MAX heap,
        // peek() gives us that element.
        else if (smallHeap.size() > largeHeap.size()) {

            return (double) smallHeap.peek();
        }


        // CASE 3:
        // largeHeap has one extra element.
        //
        // Example:
        //
        // smallHeap = [2, 3]
        // largeHeap = [4, 5, 6]
        //
        // The middle element is the smallest element
        // of the larger half.
        //
        // Since largeHeap is a MIN heap,
        // peek() gives us that element.
        else {

            return (double) largeHeap.peek();
        }
    }
}