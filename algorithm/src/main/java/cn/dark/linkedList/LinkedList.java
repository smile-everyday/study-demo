package cn.dark.linkedList;

import java.util.Objects;

/**
 * 单向链表
 *
 * @author dark
 * @date 2019-12-17
 */
public class LinkedList<T> {

    private Node<T> head;
    private Node<T> last;

    public LinkedList() {
        head = createNode(null);
        last = head;
    }

    public void printAll() {
        Node temp = head.next;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void addFirst(T data) {
        if (last == head) {
            addLast(data);
        } else {
            head.next = createNode(data, head.next);
        }
    }

    public void addAfter(T data, T target) {
        addAfter(data, createNode(target));
    }

    private void addAfter(T data, Node node) {
        Node index = index(node);
        if (index == null) {
            addLast(data);
            System.out.println("未找到符合的节点，已添加到链表末端");
            return;
        }

        index.next = createNode(data, index.next);
    }

    public void addLast(T data) {
        last.next = createNode(data);
        last = last.next;
    }

    public void removeFirst() {
        if (head.next == null) {
            return;
        }

        if (last == head.next) {
            last = head;
        }
        head.next = head.next.next;
    }

    public void remove(T data) {
        if (data == null) {
            return;
        }

        // 首先删除链表尾端所有相同的元素，便于处理last指针
        Node lastBeforeTemp = getLastBefore();
        // 空链表
        if (lastBeforeTemp == head) {
            return;
        }
        while (data.equals(lastBeforeTemp.next.getData())) {
            removeLast();

            lastBeforeTemp = getLastBefore();
        }

        // 从head节点遍历删除所有相同的节点
        Node temp = head;
        if (temp.next == null) {
            return;
        }
        while (temp.next != null) {
            if (data.equals(temp.next.getData())) {
                temp.next = temp.next.next;
                continue;
            }
            temp = temp.next;
        }
    }

    public void removeLast() {
        Node temp = getLastBefore();
        temp.next = null;
        last = temp;
    }

    /**
     * 删除倒数第k个元素
     *
     * @param k 从1开始
     * @return cn.dark.linkedList.LinkedList
     * @date 2019-12-18
     */
    public void removeLastKth(int k) {
        if (k < 1 || head.next == null) {
            return;
        }

        int size = getSize();
        if (size < k) {
            return;
        }

        // 找到待删除元素的前一个元素
        int num = size - k;
        Node temp = head;
        while (num-- > 0) {
            temp = temp.next;
        }
        // num == 0时当前节点即为待删除节点的前一个节点
        temp.next = temp.next.next;
    }

    /**
     * 通过快慢指针删除倒数第k个元素，这种方法的好处在于只遍历了一次链表
     *
     * @param k
     * @date 2019-12-18
     */
    public void removeLastKthBySlowAndFast(int k) {
        if (k < 1 || head.next == null) {
            return;
        }

        // 首先让快指针走k个元素，注意这里fast是以第一个元素开始的，所以当k=1时不需要移动指针
        Node slow = head.next, fast = head.next, slowPre = head;
        while (k-- > 1 && fast != null) {
            fast = fast.next;
        }
        // k大于链表长度
        if (fast == null) {
            return;
        }

        /**
         * 快慢指针同步走，直到快指针走到末端时，慢指针所指元素即为待删除元素，
         * 有可能在上面快指针已经走到了尾端，那么直接删除头节点
         */
        while (fast.next != null) {
            fast = fast.next;
            slowPre = slow;
            slow = slow.next;
        }
        slowPre.next = slowPre.next.next;
    }

    private int getSize() {
        Node temp = head;
        int i = 0;
        while (temp.next != null) {
            i++;
            temp = temp.next;
        }
        return i;
    }

    /**
     * 合并两个有序的list
     *
     * @param l1
     * @param l2
     * @return cn.dark.linkedList.LinkedList
     * @date 2019-12-18
     */
    public static LinkedList mergeSortList(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        if (l1 == null || l1.head.next == null) {
            return l2;
        }
        if (l2 == null || l2.head.next == null) {
            return l1;
        }

        LinkedList<Integer> mergeList = new LinkedList<>();
        Node<Integer> node = l1.head.next;
        Node<Integer> node1 = l2.head.next;
        while (node != null && node1 != null) {
            if (node.getData() < node1.getData()) {
                mergeList.addLast(node.getData());
                node = node.next;
            } else {
                mergeList.addLast(node1.getData());
                node1 = node1.next;
            }
        }

        while (node != null) {
            mergeList.addLast(node.getData());
            node = node.next;
        }
        while (node1 != null) {
            mergeList.addLast(node1.getData());
            node1 = node1.next;
        }
        return mergeList;
    }

    /**
     * 环检测
     *
     * @return boolean
     * @date 2019-12-18
     */
    public boolean checkCircle() {
        Node slow = head.next, fast = head.next;
        while (fast != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取中间节点
     *
     * @return T
     * @date 2019-12-18
     */
    public T getHalfNode() {
        Node slow = head.next, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return (T) slow.data;
    }

    /**
     * 链表反转
     *
     * @date 2019-12-18
     */
    public void reverse() {
        last = head.next;
        head.next = reverseByRecursion(head.next);
    }

    private Node reverseByIterate(Node node) {
        // pre保存新的链表
        Node pre = null, current = node, next;
        // 挨个处理原链表的节点，使之成为新链表的头
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }

        return pre;
    }

    private Node reverseByRecursion(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node head = reverseByRecursion(node.next);
        node.next.next = node;
        node.next = null;
        return head;
    }

    private Node getLastBefore() {
        Node temp = head;
        if (head == last) {
            return head;
        }

        while (temp.next != last) {
            temp = temp.next;
        }
        return temp;
    }

    private Node index(Node node) {
        if (node == null || node.getData() == null) {
            return null;
        }

        Node temp = head.next;
        while (temp != null) {
            if (temp.equals(node)) {
                return temp;
            }

            temp = temp.next;
        }

        return null;
    }

    private Node<T> createNode(T data) {
        return createNode(data, null);
    }

    private Node<T> createNode(T data, Node<T> next) {
        return new Node<>(data, next);
    }

    private static class Node<T> {
        private T data;
        private Node next;

        public Node() {
        }

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return this.data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

}
