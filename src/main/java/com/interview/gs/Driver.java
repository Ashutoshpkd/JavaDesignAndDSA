package com.interview.gs;/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.

 You have been given a 2D matrix of size N x M. For any element matrix[i][j] equal to 0, you need to transform the entire ith row and the entire jth column to 0. 
You are only allowed to use O(1) additional space, and the result is supposed to be returned in the original matrix.

Example


[0, 5, 0, 10]
[0, 0, 0, 0]
[0, -8, 0, 9]
[0, 0, 0, 0]
[0, 5, 0, -11]

1  2
|
3
Determine the minimum cost to provide library access to all citizens of wonderland. 
There are n cities numbered from 1 to n, currently there is no libraries and the cities are not connected. 
Bidirectonal roads may be build between any city  pair listed in the cities. 
Acitizen has access to library if:
  1. Their city contain a library
  2. They can travel  by road from their city to a city containing libraray
you will be given n = no of cities, m = no of roads, costLib = cost to build a libraray and costRoad = cost to build a road

Ex : 
3 3 2 1
1 2
3 1
2 3
output: 4

6 6 2 5
1 3
3 4
2 4
1 2
2 3
5 6
output: 12

5 3 6 7
1 2
1 3
1 4
output : 15
 */

class Solution {
    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        // int row = input.nextInt();
        // int col = input.nextInt();

         int[][] matrix = new int[][] {{1, 5, 0, 10}, {46, -7, 6, 2}, {1, -8, 6, 9}, {3, 5, 0, 7}, {23, 5, 6, -11}};

         makeZeros(matrix);

         for (int r=0; r<matrix.length; r++) {
           for (int c=0; c<matrix[0].length; c++) {
             System.out.print(matrix[r][c] + ",  ");
           }
           System.out.println();
         }
         System.out.println(getMinCost(new int[][] {{0, 2}, {2, 3}, {1, 3}, {0, 1}, {1, 2}, {4, 5}}, 2, 5, 6));


    }

//    [[1, 2, 3], [0], [0], [0], []]

    private static List<Integer>[] createAdjList(int[][] cities, int numCities) {
        // Initialize the adjacency list array with the generic type List<Integer>
        List<Integer>[] adj = new ArrayList[numCities];

        // Initialize each list in the array
        for (int i = 0; i < numCities; i++) {
            adj[i] = new ArrayList<>();
        }

        // Populate the adjacency list with edges from the cities array
        for (int i = 0; i < cities.length; i++) {
            adj[cities[i][0]].add(cities[i][1]);
            adj[cities[i][1]].add(cities[i][0]);
        }
        return adj;
    }

    private static int getMinCost(int[][] cities, int libCost, int rCost, int numCities) {
        List<Integer>[] adj = createAdjList(cities, numCities);
        Set<Integer> visited = new HashSet<>();
        int totalCost = 0;
        int libBuildEachCityCost = libCost * numCities;

        for (int i=0; i<numCities; i++) {
            if (!visited.contains(i)) {
                int connectedNodes = dfs(adj, i, visited);
                System.out.println("Connected = " + connectedNodes);
                totalCost += rCost * (connectedNodes - 1) + libCost;
            }
        }

        return libBuildEachCityCost > totalCost ? totalCost : libBuildEachCityCost;
    }

    private static int dfs(List[] adj, int currCity, Set<Integer> visited) {
        int nodes = 1;
        visited.add(currCity);

        for (int i=0; i<adj[currCity].size(); i++) {
            int curNode = (int) adj[currCity].get(i);
            if (!visited.contains(curNode)) {
                nodes += dfs(adj, curNode, visited);
            }
        }

        return nodes;
    }

    private static void makeZeros(int[][] matrix) {
        boolean isFirstRowZero = false;

        for (int r=0; r<matrix.length; r++) {
            for (int c=0; c<matrix[0].length; c++) {
                if (matrix[r][c] == 0) {
                    matrix[0][c] = 0;

                    if (r == 0) {
                        isFirstRowZero = true;
                        continue;
                    }

                    matrix[r][0] = 0;
                }
            }
        }

        for (int r=1; r<matrix.length; r++) {
            if (matrix[r][0] == 0) {
                for (int c=0; c<matrix[0].length; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        for (int c=0; c<matrix[0].length; c++) {
            if (matrix[0][c] == 0) {
                for (int r=0; r<matrix.length; r++) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (isFirstRowZero) {
            for (int c=0; c<matrix[0].length; c++) {
                matrix[0][c] = 0;
            }
        }
    }
}


// Your previous Plain Text content is preserved below:

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you'd like to use for your interview,
// simply choose it from the dots menu on the tab, or add a new language
// tab using the Languages button on the left.

// You can also change the default language your pads are created with
// in your account settings: https://app.coderpad.io/settings

// Enjoy your interview!


/*
given an integer array of size n
and an integer k

find the diff bw min and max for all k sized windows.

arr = [4, 2, 10, 9, 11, 3, 6, 9, 12, 6]
k = 3

[3]

for (i 0 to k - 1) max.add(num)

l = 0
r = k - 1

while (r < len) 
while (r - max.peek().idx > k) max.poll()
max.add(n, r)
res <- max.peek()
l++
r++

[1, 2, 3, 3, 3, 4], toFind = 3 O(n)

l <- 0
r <- len - 1
res <- -1

while (l <= r) then

  mid <- (l + r) / 2

  if (nums[mid] = toFind) then
    if (mid -1 >= 0 && nums[mid - 1] = nums[mid]) r <- mid - 1
    else then
      res <- mid
      break

    else if (nums[mid] < nums[r]) l = mid + 1
    else r = mid - 1


return res
*/



// YouTube  - view count

// Eventual consistent
// Read / Write heavy

//Read - Part
// Seperate API (Veiws)

// Write Part
// fetch video -> (On click)
// POST view/ -> ()

// Gateway -> Aggreation-service PUT /v1/api/view/{id} -> TOPIC view

// AccessController
// AggreationService.

//incrementCount()



// Service Layer
// respository.getById(id)
// .switchIfEmpty(e -> Mono.error)
//. flatMap(doc -> doc.setViewCount(doc.getViewCount() + 5))
//.save()

// 10M -

// M -> R -> R

// NoSQL -> DB