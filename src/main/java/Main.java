import com.aip.datastructures.*;
import com.aip.design.abstractClasses.Impl;
import com.aip.design.abstractClasses.Name;
import com.aip.design.map.MyHashMap;
import com.aip.design.queue.MyQueue;
import org.apache.commons.codec.binary.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    static {
        System.out.println("Static block");
    }

    public void Main() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        List<Integer> listArr = List.of(1,2,3,4,5,6);

       listArr.stream().filter(num -> num % 2 == 0)
               .forEach(num -> {
                   System.out.println(num);
               });
//        System.out.println("Hello world!");
//        MyQueue q = new MyQueue();
//        q.add(1);
//        q.add(2);
//        System.out.println(q.peek());
//        System.out.println(q.poll());
//        System.out.println(q.peek());
//        System.out.println(q.isEmpty());
//
//        q.add(3);
//        q.add(4);
//        q.add(5);
//        System.out.println("-------------------------");
//
//        System.out.println(q.peek());
//        System.out.println(q.poll());
//        System.out.println(q.peek());
//        System.out.println(q.isEmpty());
//        MinWindowSubstring s = new MinWindowSubstring();
//        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));
//
//        ReverseWords r = new ReverseWords();
//        r.reverseWords("a good   example");
//        r.reverseWords("  hello world  ");
//        Random random = new Random();
//        int randomNum = random.nextInt(0,5);
//        System.out.println(randomNum);
        int[] nums = new int[] {1,2,3};
        System.out.println(Arrays.toString(moveAllNegToBegining(nums)));
        maxTurbulenceSize(nums);
        Arrays.stream(nums).sum();
        Set<String> set = new HashSet<>();
        set.add("Ashutosh");
        String[] objs = set.toArray(new String[set.size()]);

        System.out.println(Arrays.toString(objs));

//        Arrays.stream(nums).sum();
//        int max = Arrays.stream(nums).max().orElse(0);

//        MyHashMap map = new MyHashMap();
//        map.put(1, 1);
//        map.put(2, 2);
//
//        System.out.println(map.get(1));
//        System.out.println(map.get(2));
//        System.out.println(map.get(3));
//        System.out.println(map.getOrDefault(3, 0));
//
//        System.out.println(map.remove(1));
//        System.out.println(map.remove(2));
//        System.out.println(map.remove(3));
//
//        map.put(1, 1);
//        map.put(2, 2);
//        map.put(1, 10);
//        map.put(2, 20);
//
//        System.out.println(map.get(1));
//        System.out.println(map.get(2));
//        System.out.println(map.get(3));
//        System.out.println(map.getOrDefault(2, 0));
//
//        System.out.println(map.remove(1));
//        System.out.println(map.remove(2));
//        System.out.println(map.remove(3));
//
//        new InnerClass();
//
//        Name name = new Impl();
//        name.printName();
//        name.randomNumber();
//
//        List<String> fruits = new ArrayList<>();
//        fruits.add("Apple");
//        fruits.add("Apple");
//        fruits.add("Mango");
//
//        Map<String, Long> count = fruits.stream()
//                .collect(Collectors.groupingBy(fruit -> fruit, Collectors.counting()));
//
        int[] hand = new int[] {10,9,8,1,2,3,2,3,4,4,5,6,10,11,12};
        isPossibleDivide(hand, 3);
        int[][] d2 = new int[][] { {0,0}, {-1,1}, {-1, 0} };

        Arrays.sort(d2, (a, b) -> {
            if (a[0] < b[0]) return -1;
            else if (a[0] > b[0]) return 1;
            else {
                return a[1] - b[1];
            }
        });

        for (int i=0; i<d2.length; i++) {
            for (int j=0; j<2; j++) System.out.print(d2[i][j] + " ");
            System.out.println();
        }

        MyQueue q = new MyQueue();
        MyQueue q2 = new MyQueue();
        List<Integer> ll = List.of(1,2,3,4,5,6,7);
        ll = ll.stream().filter(num -> num != 5).toList();

        Function<Integer, Integer> sq = x -> x * x;
        System.out.println(sq.apply(2) + ": sq.apply(2)");
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(map.put(1,1));
        System.out.println(map.put(1,2));
        System.out.println();
        SlidingWindowMaximum swm = new SlidingWindowMaximum();
//        System.out.println("SWM: " + swm.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3));
//        ReverseWords rw = new ReverseWords();
//        int[] arr = new int[] {1,2,3,4,5,6,7};
//        rw.rotate(arr, 3);
//        System.out.println(Arrays.toString(arr));
        System.out.println((new MaxProduct()).maxProduct(new int[] {-1,-2,-9,-6}));
        System.out.println();
//
//        Arrays.stream(hand).boxed()
//                .collect(Collectors.groupingBy(num -> num, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .forEach(e -> System.out.println("Key = " + e.getKey() + ", Value = " + e.getValue()));

    }

    public static int maxSubarraySumCircular(int[] nums) {
        int curr = 0;
        int idx = 0;
        int max = Integer.MIN_VALUE;
        int sumStart = -1;

        for (int i=0; i<nums.length * 2; i++) {
            idx = i % nums.length;
            curr += nums[idx];

            if (idx != sumStart && max <= curr) {
                max = curr;
                if (sumStart == -1) {
                    sumStart = idx;
                }
            }

            if (curr < 0) {
                curr = 0;
                sumStart = -1;
            }
        }

        return max;
    }

    public static int maxTurbulenceSize(int[] arr) {
        int l=0, r=1, max=1;
        char sign = '=';

        while (r < arr.length) {
            if (arr[r] < arr[r-1] && sign != '<') {
                sign = '<';
                max = Math.max(r - l + 1, max);
            } else if (arr[r] > arr[r-1] && sign != '>') {
                sign = '>';
                max = Math.max(r - l + 1, max);
            } else {
                r = arr[r] == arr[r-1] ? r+1 : r;
                sign = '=';
                l = r - 1;
            }
            r++;
        }

        return max;
    }

    public static boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;

        Map<Integer, Integer> count = new HashMap<>();
        // Queue<Integer> minQ = new PriorityQueue<>((a, b) -> a - b);

        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);
        Arrays.sort(nums);
        int numCount = 0;

        for (int n : nums) {
            numCount = count.get(n);

            if (numCount == 0) continue;
            count.put(n, numCount-1);

            for (int i=1; i<k; i++) {
                if (!count.containsKey(n+i)) return false;

                int nextNum = count.get(n+i);

                if (nextNum <= 0) return false;

                count.put(n+i, nextNum-1);
            }
        }
        return true;
    }

    public static int[] moveAllNegToBegining(int[] nums) {
        BiConsumer<Integer, Integer> swap = (i, j) -> {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        };

        int start = 0, end = 0;
        while (end < nums.length) {
            while (start < nums.length && nums[start] < 0) {
                start++;
                if (end <= start) end = start + 1;
            }
            if (end < nums.length && nums[end] < 0) {
                swap.accept(start, end);
                start++;
            }
            end++;
        }

        return nums;
    }
}