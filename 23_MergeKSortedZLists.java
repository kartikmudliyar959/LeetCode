
import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap =
            new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();

            tail.next = smallest;
            tail = smallest;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        tail.next = null;

        return dummy.next;
    }
}
