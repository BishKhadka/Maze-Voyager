public class CellQueueTests {

    public static void main(String[] args){
        // case 1: Constructor
        {
            // setup
            CellQueue queue = new CellQueue();

            // verify
            System.out.println(queue);

            // test
            assert queue != null : "Error in CellQueue::CellQueue()";
        }

        // case 2: offer()
        {
            // setup
            CellQueue queue = new CellQueue();
            for(int i = 0; i < 5; i++){
                queue.offer(new Cell(0,0,Cell.Type.FREE));
            }

            // verify
            System.out.println(queue.size() + " == 5");
            
            // test
            assert queue.size() == 5 : "Error in CellQueue::offer()";
        }

        // case 3: peek()
        {
            // setup
            CellQueue queue = new CellQueue();
            for(int i = 1; i < 5; i++){
                queue.offer(new Cell(i, i, Cell.Type.OBSTACLE));
            }
            Cell peek = queue.peek();

            // verify
            System.out.println(peek.getType() + " == OBSTACLE");
            System.out.println(queue.size() + " == 4");

            // test
            assert peek.getType() == Cell.Type.OBSTACLE : "Error in CellStack::peek()"; 
            assert queue.size() == 4 : "Error in CellStack::peek()";
        }

        // case 4: pop()
        {
            // setup
            CellQueue queue = new CellQueue();
            for(int i = 1; i < 5; i++){
                queue.offer(new Cell(i, i, Cell.Type.START));
            }
            Cell pop = queue.poll();

            // verify
            System.out.println(pop.getType() + " == START");
            System.out.println(queue.size() + " == 3");

            // test
            assert pop.getType() == Cell.Type.START : "Error in CellStack::pop()"; 
            assert queue.size() == 3 : "Error in CellStack::pop()";
        }
    }

}