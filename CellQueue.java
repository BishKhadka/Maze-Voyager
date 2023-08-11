public class CellQueue{

    //creating a Node class
    private class Node{
        Node next;
        Node prev;
        Cell cell;
        
        //constructor
        public Node(Cell c){
            next = null;
            prev = null;
            cell = c;
        }
    }

    //fields of CellQueue
    private Node head;
    private Node tail;
    private int size;

    //constructor of CellQueue
    public CellQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //pushes the Cell to the end of the Queue
    public void offer(Cell c){
        Node newNode = new Node(c);
        if (size == 0){
            this.head = newNode;
            this.tail = newNode;
        } else{
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
        }
        size ++;
    }

    //returns the head cell in the queue
    public Cell peek(){
        return this.head.cell;
    }

    //removes and returns the head of the queue
    public Cell poll(){
        Cell oldHead = null;
        if (size == 0){
            System.out.println("Empty queue, cannot poll");
            return oldHead;
        } else if (size == 1){
            oldHead = this.head.cell;
            this.tail = null;
            this.head = null;
        }else {
            oldHead = this.head.cell;
            this.head = this.head.next;
            this.head.prev = null;
        }
        size --;
        return oldHead;
    }

    //returns the size of the queue
    public int size(){
        return this.size;
    }

    //testing in the main method
    public static void main(String[] args) {
        CellQueue queue = new CellQueue();
        Cell c1 = new Cell(0,0,Cell.Type.OBSTACLE);
        queue.offer(c1);
    }
}