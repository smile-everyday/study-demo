package cn.dark.linkedList;

/**
 * @author dark
 * @date 2019-12-17
 */
public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // 边界测试
        list.removeFirst();
        list.removeLast();
        list.remove(1);

        // 增删测试
        list.addLast(1);
        list.removeLast();
        list.removeFirst();
        list.addLast(2);
        list.addLast(6);
        list.addLast(7);
        list.removeLast();
        list.printAll();

        list.addFirst(0);
        list.addFirst(-1);
        list.removeFirst();
        list.printAll();

        list.addAfter(4, 2);
        list.addAfter(3, 2);
        list.addAfter(5, 4);
        list.printAll();

        list.addAfter(10, 9);
        list.addAfter(10, 9);
        list.addAfter(11, 10);
        list.removeLast();
        list.printAll();

        list.addAfter(1, 0);
        list.addAfter(7, 6);
        list.addAfter(8, 7);
        list.printAll();

        list.addFirst(1);
        list.addAfter(1, 1);
        list.addAfter(1, 4);
        list.addAfter(1, 4);
        list.addLast(1);
        list.addLast(1);
        list.addLast(1);
        list.remove(1);
        list.printAll();

        // 有序list合并
        LinkedList list1 = new LinkedList();
        list1.addLast(-1);
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(9);
        list1.addLast(13);
        list1.addLast(14);
        System.out.print("list1：");
        list1.printAll();
        LinkedList list2 = LinkedList.mergeSortList(list, list1);
        System.out.print("list2：");
        list2.printAll();

        list.reverse();
        System.out.print("反转后链表：");
        list.printAll();

        System.out.println("中间节点：" + list.getHalfNode());

        System.out.print("删除倒数第k个节点前：");
        list.printAll();
        list.removeLastKth(9);
        System.out.print("删除倒数第k个节点后：");
        list.printAll();



    }

}
