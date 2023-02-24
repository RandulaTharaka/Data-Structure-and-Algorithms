package SinglyLinkedList;

import java.util.ArrayList;

public class SinglyLinkedList {

    private Node head;
    private Node tail;
    private int length;

     static class Node {
        int value;
        Node next;

        Node(int value){
            this.value = value;
            this.next = null;
        }
    }

    public void display(){
        if(!isListEmpty()){
            System.out.print("Singly linked list: ");
            Node temp = head;
            while(temp !=null){
                System.out.print(temp.value + " ");
                temp = temp.next;
                if(temp==null) System.out.println(); //line break at end of list
            }
        }
    }

    public Node getHead(){
        if (!isListEmpty()) {
            System.out.println("Head: " + head.value);
            return head;
        }else{
            return null;
        }
    }

    public void getTail(){
        if (!isListEmpty()) System.out.println("Tail: " + tail.value);
    }

    public void getLength(){
        if(!isListEmpty()) System.out.println("Length: " +length);
    }

    public boolean isListEmpty(){
        if(head == null){
            System.out.println("List is empty");
            return true;
        }else {
            return false;
        }
    }

    public void addLast(int value){     //append
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public void addFirst(int value){ //prepend
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            Node temp = head;
            head = newNode;
            newNode.next = temp;
        }
        length++;
    }

    public Node removeFirst(){
        if(isListEmpty()){
            return null;
        }else{
            Node temp = head;
            head = head.next;
            length--;
            return temp;
        }
    }

    public Node removeLast(){
         if(isListEmpty()){
             return null;
         }else if(head.next == null){ // edge case: if only have one node
             Node temp = head;
             head = null;
             tail = null;
             length--;
             return temp;
         }else{
             Node temp = head;
             Node tempTail = tail;
             while(temp.next != tail){
                temp = temp.next;
            }
             tail = temp;
             tail.next = null;
             length--;
             return tempTail;
         }
    }

    // removeLast2 - alternative solution
    public Node removeLast2(){
         if(isListEmpty()){
             return null;
         }else{
             Node temp = head;
             Node tempTail = tail;
             while(temp.next != tail){
                temp = temp.next;
                if(temp == null){
                    head = temp;
                    break;
                }
            }
             tail = temp;
             if(tail != null) tail.next = null;
             length--;
             return tempTail;
         }
    }

    // removeLast3 - alternative solution
    public Node removeLast3() {
        if (isListEmpty()){
            return null;
        }
        Node temp = head;
        Node previousNode = head;
        while(temp.next != null) {
            previousNode = temp;
            temp = temp.next;
        }
        tail = previousNode;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public Node getNode(int index){
        if(isListEmpty()){
            return null;
        }
        else if(index < 0 || index > length){
            System.out.println("Error: provided index is out of bound");
            return null;
        }else{
            Node temp = head;
            for(int i=0; i < index; i++){
                temp = temp.next;
            }
            return temp;
    }
}

    public boolean insertNode(int index, int value){
        if(index <0 || index > length){
            return false;
        }else if(index == 0){
            addFirst(value);
            return true;
        }else if(index == length){
            addLast(value);
            return true;
        }else{
            Node newNode = new Node(value);
            Node temp = head;
            for(int i=0; i <index-1; i++){
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
            length++;
            return true;
        }
    }

    public Node removeNode(int index){
        if(index < 0 || index >= length){
            return null;
        }else if(index == 0){
            return removeFirst();
        }else if (index == length-1){
            return removeLast();
        }else{ // if given index is in the middle
            Node temp = head;
            Node prev = head;

            for(int i=0; i<index; i++){
                prev = temp;
                temp = temp.next;
            }
            prev.next = temp.next;
            temp.next = null; // important point
            length--;
            return temp;
        }
    }

    public boolean setValue(int index, int value){
        Node temp = getNode(index);
        if(temp != null){
            temp.value = value;
            return true;
        }else{
            return false;
        }
    }

    public Node reverse(){ //Time: O(n) | Space: O(1)

        if(isListEmpty()){
            return null;
        }else if(head.next == null){
            return head;
        }else{
            Node current = head;
            Node before = null;
            Node after = null;

            // Point next pointer to previous node
            while(current != null){
                after = current.next;
                current.next = before;
                before = current;
                current = after;
            }
            tail = head;
            head = before;
            return head;
        }
    }

//    reverse2 - alternative solution
    public void reverse2(){
         if(!isListEmpty() && head.next != null){
             Node current = head;
             Node before = null;
             Node after = current.next;

             // Point next pointer to previous node
             for(int i=0; i<length; i++){
                 current.next = before;
                 before = current;
                 current = after;
                 if(after != null) after = after.next;
             }

             Node temp = head;
             head = tail;
             tail =temp;
         }
    }

    public void removeDuplicatesFromUnsortedList() {
        Node pointer1 = head;
        Node pointer2 = null;

        // Pick elements one by one
        while (pointer1 != null && pointer1.next != null) {
            pointer2 = pointer1;

            // Compare the picked element with the rest of the elements
            while (pointer2.next != null) {
                if (pointer1.value == pointer2.next.value) { // If a duplicate then delete it
                    pointer2.next = pointer2.next.next;
                    length--;
                }else { // if not duplicate move to next node
                    pointer2 = pointer2.next;
                }
            }
            pointer1 = pointer1.next;
        }
        tail = pointer1;
    }

//   removeDuplicatesFromUnsortedList2 - alternative solution
    public void removeDuplicatesFromUnsortedList2(){
        Node temp1 = head;
        Node temp2 = null;
        Node beforeTemp2 = null;

        // Pick elements one by one
        while(temp1 != null && temp1.next !=null){
            temp2 = temp1.next;
            beforeTemp2 = temp1;
            // Compare the picked element with the rest of the elements
            while(temp2 != null){
                if(temp1.value == temp2.value){ // If a duplicate then delete it
                    beforeTemp2.next = temp2.next;
                    length--;
                }else {
                    beforeTemp2 = beforeTemp2.next; // if not duplicate move to next node
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        tail = temp1;
    }

    public void addNodeToLast(Node node){
        if(isListEmpty()){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
        length++;
    }

    public boolean hasCycle(Node head){ // | Time: o(n) | Space: o(1)

        if(isListEmpty()) return false;
        Node slow = head;
        Node fast = head;

        //Check if fast meets slow at some point
        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }


//    hasCycle2 - alternative solution | Time: o(n) | Space: o(1)
    public boolean hasCycle2(Node head) {
        Node temp = head;

        for(int i=0; i<=10000; i++){ //Time: o(n)
            if(temp ==null) return false;
            temp = temp.next;
        }
        return true;
    }

    //    hasCycle3 - alternative solution | Time: o(n) | Space: o(n)
    public boolean hasCycle3(Node head){ //Time: o(n^2)
            ArrayList<Node> arrayList = new ArrayList<Node>();
            Node temp = head;
            while(temp.next!=null){ //o(n)
                arrayList.add(temp); //Space: o(n)
                if(isTempNextPointingToAnyPreviousNode(temp.next, arrayList)) return true;
                temp = temp.next;
            }
            return false;
        }

    public boolean isTempNextPointingToAnyPreviousNode(Node tempNext, ArrayList<Node> arrayList){
        for (Node node : arrayList) { //o(n)
                if (tempNext == node) return true;
        }
        return false;
    }

    public Node getCycleBeginNode(Node head){

        if(isListEmpty()) return null;
        Node slow = head;
        Node fast = head;
        Node start = null;

        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) {
               start = head;
               while(start != slow){
                   start = start.next;
                   slow = slow.next;
               }
               return slow;
            }
        }
        return null;
    }
}


