package SinglyLinkedList;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList linkedList = new SinglyLinkedList();

        linkedList.addLast(1);
        linkedList.addLast(5);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(2);
        linkedList.addLast(4);
        linkedList.addLast(3);
        linkedList.addLast(3);

        linkedList.display();
        linkedList.getLength();
        linkedList.getHead();
        linkedList.getTail();

        linkedList.addFirst(5);
        System.out.println(linkedList.removeFirst().value);
        System.out.println(linkedList.removeLast().value);
        System.out.println(linkedList.getNode(0).value);
        System.out.println(linkedList.insertNode(1,100));
        System.out.println(linkedList.setValue(0, 100));
        System.out.println(linkedList.removeNode(1));
        linkedList.reverse();
        linkedList.removeDuplicatesFromUnsortedList();


//      ## Create a cyclic linked list & calling the hasCycle() && getCycleBeginNode() methods ##
        linkedList.addLast(10);
        SinglyLinkedList.Node cycle = new SinglyLinkedList.Node(20);
        linkedList.addNodeToLast(cycle);
        linkedList.addLast(30);
        linkedList.addLast(40);
        linkedList.addNodeToLast(cycle);
        System.out.println(linkedList.hasCycle(linkedList.getHead()));
        System.out.println(linkedList.getCycleBeginNode(linkedList.getHead()).value);

    }
}