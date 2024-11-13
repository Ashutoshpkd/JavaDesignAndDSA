package com.aip.design.twitter;

import java.util.*;

public class Twitter {
    private static long time = 0L;
    private Map<Integer, Set<Integer>> followersList;
    private Map<Integer, List<Tweet>> tweets;
    public Twitter() {
        this.followersList = new HashMap<>();
        this.tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        Twitter.time++;
        if (this.tweets.containsKey(userId)) {
            this.tweets.get(userId).add(new Tweet(time, tweetId));
        } else {
            List<Tweet> userTweet = new ArrayList<>();
            userTweet.add(new Tweet(time, tweetId));
            this.tweets.put(userId, userTweet);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        Queue<Tweet> latestTweets = new PriorityQueue<>((t1, t2) -> {
            if (t2.time > t1.time) return 1;
            else if (t2.time < t1.time) return -1;
            else return 0;
        });
        latestTweets.addAll(this.tweets.get(userId));

        Set<Integer> followeeList = this.followersList.getOrDefault(userId, new HashSet<>());

        for (int followeeId : followeeList) {
            latestTweets.addAll(this.tweets.get(followeeId));
        }
        return latestTweets.stream().limit(10).map(tweet -> tweet.tweetId).toList();
    }

    public void follow(int followerId, int followeeId) {
        if (this.followersList.containsKey(followeeId)) {
            this.followersList.get(followeeId).add(followerId);
        } else {
            Set<Integer> followers = new HashSet<>();
            followers.add(followerId);
            this.followersList.put(followeeId, followers);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (this.followersList.containsKey(followeeId)) {
            this.followersList.get(followeeId).remove(followerId);
        }
    }

    private class Tweet {
        long time;
        int tweetId;

        public Tweet(long time, int tweetId) {
            this.time = time;
            this.tweetId = tweetId;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
enum cats {ASH, }