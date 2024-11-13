package com.aip.design;

public class Interview {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    static class LinkedListSolution {

        public ListNode greaterThanOrEqualCount(ListNode head) {
            if (head == null) return null;

            ListNode current = head;
            ListNode dummy = new ListNode(0);  // Dummy node for the result linked list
            ListNode resultPointer = dummy;

            // Traverse the list
            while (current != null) {
                int count = 0;
                ListNode temp = current.next;

                // Compare the current node with all subsequent nodes
                while (temp != null) {
                    if (temp.val >= current.val) {
                        count++;
                    }
                    temp = temp.next;
                }

                // Add the count for the current node
                resultPointer.next = new ListNode(count);
                resultPointer = resultPointer.next;

                // Move to the next node
                current = current.next;
            }

            return dummy.next; // Return the new list starting from the next node of dummy
        }

        public static void main(String[] args) {
            // Construct the linked list: 11 -> 4 -> 23 -> 43 -> 5
            ListNode head = new ListNode(11);
            head.next = new ListNode(4);
            head.next.next = new ListNode(23);
            head.next.next.next = new ListNode(43);
            head.next.next.next.next = new ListNode(5);

            LinkedListSolution solution = new LinkedListSolution();
            ListNode result = solution.greaterThanOrEqualCount(head);

            // Print the result linked list: 2 -> 4 -> 1 -> 0 -> 3
            System.out.print("Output: ");
            while (result != null) {
                System.out.print(result.val + " ");
                result = result.next;
            }
        }
    }

}
