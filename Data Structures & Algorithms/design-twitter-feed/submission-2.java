class Twitter {

    /*
     * Represents ONE tweet.
     *
     * Each tweet stores:
     * 1. id   -> tweet ID
     * 2. time -> timestamp used to determine which tweet is newer
     * 3. next -> points to the user's previous/older tweet
     *
     * Example:
     *
     * User 1 posts:
     * 101 -> 102 -> 103
     *
     * We actually store it as:
     *
     * 103 -> 102 -> 101 -> null
     *
     * So the latest tweet is always at the HEAD.
     */
    private static class Tweet {

        int id;
        int time;
        Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }


    /*
     * tweets:
     *
     * Maps:
     * userId -> latest tweet of that user
     *
     * Example:
     *
     * 1 -> Tweet(103)
     * 2 -> Tweet(205)
     *
     * Since each Tweet has a "next" pointer,
     * we can reach all older tweets.
     *
     * Example:
     *
     * 1 -> 103 -> 102 -> 101 -> null
     */
    HashMap<Integer, Tweet> tweets;


    /*
     * following:
     *
     * Maps:
     * followerId -> set of users they follow
     *
     * Example:
     *
     * 1 -> {2, 3}
     *
     * This means:
     * User 1 follows User 2 and User 3.
     *
     * We use HashSet because:
     * - No duplicate follow relationships
     * - Average O(1) add/remove
     * - Average O(1) lookup
     */
    HashMap<Integer, Set<Integer>> following;


    /*
     * Global timestamp.
     *
     * Every time a tweet is posted,
     * we give it a unique increasing timestamp.
     *
     * Example:
     *
     * First tweet  -> time = 0
     * Second tweet -> time = 1
     * Third tweet  -> time = 2
     *
     * Therefore:
     *
     * Larger time = newer tweet
     */
    int timestamp = 0;


    /*
     * Constructor.
     *
     * Initially there are:
     * - No tweets
     * - No follow relationships
     */
    public Twitter() {
        tweets = new HashMap<>();
        following = new HashMap<>();
    }


    /*
     * POST TWEET
     *
     * Time Complexity: O(1)
     *
     * Idea:
     *
     * Always insert the new tweet at the HEAD of
     * the user's linked list.
     */
    public void postTweet(int userId, int tweetId) {

        /*
         * Create a new Tweet.
         *
         * timestamp++ means:
         * use current timestamp, then increase it.
         *
         * Example:
         *
         * timestamp = 5
         *
         * New tweet gets:
         * time = 5
         *
         * Then timestamp becomes 6.
         */
        Tweet tweet = new Tweet(tweetId, timestamp++);


        /*
         * Get the user's current latest tweet
         * and make it the next/older tweet.
         *
         * Example before:
         *
         * tweets.get(1):
         *
         * 102 -> 101 -> null
         *
         * New tweet = 103
         *
         * After:
         *
         * 103 -> 102 -> 101 -> null
         */
        tweet.next = tweets.get(userId);


        /*
         * Update the user's latest tweet.
         *
         * User 1 now starts from tweet 103.
         */
        tweets.put(userId, tweet);
    }


    /*
     * GET NEWS FEED
     *
     * Return at most 10 most recent tweets.
     *
     * We need tweets from:
     *
     * 1. The user's own tweets
     * 2. Tweets from everyone the user follows
     *
     * The difficult part:
     *
     * Each user's tweets are already sorted
     * from NEWEST -> OLDEST.
     *
     * Therefore this becomes:
     *
     * "Merge multiple sorted lists and get
     * the 10 newest elements."
     *
     * We use a MAX HEAP (PriorityQueue).
     */
    public List<Integer> getNewsFeed(int userId) {

        /*
         * Stores the final answer.
         */
        List<Integer> result = new ArrayList<>();


        /*
         * Java PriorityQueue is normally a MIN HEAP.
         *
         * We want the NEWEST tweet first,
         * so we create a MAX HEAP based on time.
         *
         * Newer tweet = larger timestamp.
         *
         * Example:
         *
         * time 10
         * time 8
         * time 5
         *
         * 10 will come out first.
         */
        PriorityQueue<Tweet> pq =
                new PriorityQueue<>(
                        (a, b) -> Integer.compare(b.time, a.time)
                );


        /*
         * Add the user's own latest tweet.
         *
         * IMPORTANT:
         *
         * We don't add ALL tweets here.
         *
         * We only add the latest tweet.
         *
         * Why?
         *
         * Because once we remove this tweet from
         * the heap, we can access its next older tweet
         * using:
         *
         * current.next
         *
         * This keeps the heap efficient.
         */
        if (tweets.containsKey(userId)) {
            pq.offer(tweets.get(userId));
        }


        /*
         * Get everyone this user follows.
         *
         * If the user doesn't follow anyone,
         * return an empty Set instead of null.
         */
        Set<Integer> followees =
                following.getOrDefault(
                        userId,
                        Collections.emptySet()
                );


        /*
         * Add the LATEST tweet of every followed user.
         *
         * Example:
         *
         * User 1 follows:
         *
         * User 2 -> latest tweet = 50
         * User 3 -> latest tweet = 45
         * User 4 -> latest tweet = 40
         *
         * Heap initially contains:
         *
         * 50
         * 45
         * 40
         *
         * We DON'T add all their tweets.
         */
        for (int followeeId : followees) {

            /*
             * A user may be followed even if they
             * haven't posted any tweet.
             *
             * So check first.
             */
            if (tweets.containsKey(followeeId)) {
                pq.offer(tweets.get(followeeId));
            }
        }


        /*
         * Now we repeatedly take the newest tweet.
         *
         * Stop when:
         *
         * 1. Heap becomes empty
         * OR
         * 2. We already have 10 tweets
         */
        while (!pq.isEmpty() && result.size() < 10) {


            /*
             * Remove the MOST RECENT tweet from heap.
             *
             * Because pq is a MAX HEAP,
             * the tweet with the largest timestamp
             * comes out first.
             */
            Tweet current = pq.poll();


            /*
             * Add its tweet ID to our answer.
             */
            result.add(current.id);


            /*
             * IMPORTANT PART OF THE ALGORITHM
             *
             * current.next is the next OLDER tweet
             * from the SAME user.
             *
             * Example:
             *
             * User A:
             *
             * 10 -> 7 -> 3
             *
             * If we just removed 10,
             * current.next = 7.
             *
             * So we put 7 into the heap.
             *
             * This is basically performing a
             * K-WAY MERGE of multiple sorted lists.
             */
            if (current.next != null) {
                pq.offer(current.next);
            }
        }


        /*
         * Return the 10 newest tweets.
         */
        return result;
    }


    /*
     * FOLLOW
     *
     * followerId = person who wants to follow
     * followeeId = person being followed
     *
     * Example:
     *
     * follow(1, 2)
     *
     * means:
     *
     * User 1 follows User 2.
     */
    public void follow(int followerId, int followeeId) {


        /*
         * If followerId doesn't have a Set yet,
         * create one.
         *
         * Example:
         *
         * Before:
         *
         * following:
         * 1 -> doesn't exist
         *
         * After:
         *
         * 1 -> {}
         */
        if (!following.containsKey(followerId)) {
            following.put(followerId, new HashSet<>());
        }


        /*
         * Add the followee to the Set.
         *
         * HashSet automatically prevents duplicates.
         *
         * Example:
         *
         * 1 -> {2}
         *
         * Calling follow(1, 2) again
         * still gives:
         *
         * 1 -> {2}
         */
        following.get(followerId).add(followeeId);
    }


    /*
     * UNFOLLOW
     *
     * Remove followeeId from followerId's
     * following set.
     */
    public void unfollow(int followerId, int followeeId) {


        /*
         * First check whether the follower
         * has a following set.
         *
         * If not, there is nothing to remove.
         */
        if (following.containsKey(followerId)) {

            /*
             * Remove the followee.
             *
             * If the followee wasn't present,
             * HashSet.remove() simply does nothing.
             */
            following.get(followerId).remove(followeeId);
        }
    }
}