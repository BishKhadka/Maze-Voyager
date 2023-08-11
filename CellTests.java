public class CellTests {
    
    public static void main(String[] args){
        // case 1: Constructor
        {
            // setup
            Cell c1 = new Cell(0,0,Cell.Type.FREE);
            Cell c2 = new Cell(0,0,Cell.Type.OBSTACLE);
            Cell c3 = new Cell(0,0,Cell.Type.START);

            // verify
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);

            // test
            assert c1!=null : "Error in Cell::Cell()";
            assert c2!=null : "Error in Cell::Cell()";
            assert c3!=null : "Error in Cell::Cell()";
        }

        // case 2: type
        {
            // setup
            Cell c1 = new Cell(0,0,Cell.Type.FREE);
            Cell c2 = new Cell(0,0,Cell.Type.OBSTACLE);
            Cell c3 = new Cell(0,0,Cell.Type.START);

            // verify
            System.out.println(c1.getType() + " == FREE");
            System.out.println(c2.getType() + " == OBSTACLE");
            System.out.println(c3.getType() + " == START");

            // test
            assert c1.getType() == Cell.Type.FREE : "Error in Cell::getType()";
            assert c2.getType() == Cell.Type.OBSTACLE : "Error in Cell::getType()";
            assert c3.getType() == Cell.Type.START : "Error in Cell::getType()";
        }

        // case 3: visited
        {
            // setup
            Cell c1 = new Cell(0,0,Cell.Type.FREE);
            Cell c2 = new Cell(0,0,Cell.Type.OBSTACLE);
            c2.visitFrom(c1);

            // verify
            System.out.println(c1.getVisited() + " == false");
            System.out.println(c2.getVisited() + " == true");

            // test
            assert c1.getVisited() == false : "Error in Cell::visitedFrom()";
            assert c2.getVisited() == true : "Error in Cell::visitedFrom()";
        }

        // case 4: getRow() & getCol()
        {
            // setup
            Cell c1 = new Cell(0,1,Cell.Type.FREE);
            Cell c2 = new Cell(2,3,Cell.Type.OBSTACLE);
            c2.visitFrom(c1);

            // verify
            System.out.println(c1.getRow() + " == 0");
            System.out.println(c2.getCol() + " == 3");

            // test
            assert c1.getRow() == 0 : "Error in Cell::getRow()";
            assert c2.getCol() == 3 : "Error in Cell::getCol";
        }
    }

}