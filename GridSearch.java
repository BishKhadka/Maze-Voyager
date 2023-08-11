/*
Guidelines for Program Execution
On your terminal:
- Compile: `javac *.java`

For a solution:
- Run: `java GridSearch anypath`

For the shortest path:
- Run: `java GridSearch shortestpath`
 */

public class GridSearch{
    Landscape ld;
    LandscapeDisplay scape;

    public GridSearch(){

        ld = new Landscape(25, 25, 0.3);
        scape = new LandscapeDisplay(ld, 25);
    }

    public boolean depthFirstSearch(int delay) throws InterruptedException {
        try {
            CellStack stack = new CellStack();
            ld.getStart().setVisited(true);
            stack.push(ld.getStart());
            while (stack != null){
                if (delay > 0){
                    Thread.sleep(delay);
                    scape.repaint();
                }
                Cell current = stack.pop();
                for (Cell neighbor : ld.getNeighbors(current)){
                
                    if (neighbor.getType() != Cell.Type.OBSTACLE && neighbor.getVisited() == false){
                        neighbor.visitFrom(current);
                        if (neighbor == ld.getTarget()){
                            return true;
                        }
                        stack.push(neighbor);
    
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No solution to the maze");
        }
        return false;
    }

    public boolean breadthFirstSearch(int delay) throws InterruptedException {
        CellQueue queue = new CellQueue();
        ld.getStart().setVisited(true);
  
        queue.offer(ld.getStart());
        while (queue!= null){

            if (delay > 0){
                Thread.sleep(delay);
                scape.repaint();
            }

            Cell current = queue.poll();
            for (Cell neighbor : ld.getNeighbors(current)){

                if (neighbor.getType() != Cell.Type.OBSTACLE && neighbor.getVisited() == false){
                    neighbor.visitFrom(current);
                    if (neighbor == ld.getTarget()){
                        return true;
                    }
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        GridSearch grid = new GridSearch();
    
        if (args.length > 0) {
            if (args[0].equals("anypath")) {
                grid.depthFirstSearch(30);
            } else if (args[0].equals("shortestpath")) {
                grid.breadthFirstSearch(20);
            }
        }
    }
}
