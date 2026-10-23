class Twitter {

    private static class Tweet{
        int id;
        int time;
        Tweet next;

        public Tweet(int id, int time){
            this.id = id;
            this.time = time;
        }
    }

    HashMap<Integer, Tweet> tweets;
    HashMap<Integer, Set<Integer>> following;

    int timestamp = 0;
    public Twitter() {
        tweets = new HashMap<>();
        following = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, timestamp++);
        tweet.next = tweets.get(userId);
        tweets.put(userId, tweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
        if(tweets.containsKey(userId)){
            pq.offer(tweets.get(userId));
        }

        Set<Integer> followees = following.getOrDefault(userId, Collections.emptySet());

        for(int followeeId : followees){
            pq.offer(tweets.get(followeeId));
        }

        while(!pq.isEmpty() && result.size() < 10){
            Tweet current = pq.poll();
            result.add(current.id);
            if(current.next != null){
                pq.offer(current.next);
            }
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        if(!following.containsKey(followerId)){
            following.put(followerId, new HashSet<>());
        }
        following.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(following.containsKey(followerId)){
            following.get(followerId).remove(followeeId);
        }
    }
}
