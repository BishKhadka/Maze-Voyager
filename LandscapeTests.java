public class LandscapeTests {
    
    public static void main(String[] args){
        // case 1: Constructor
        {
            // setup
            Landscape c1 = new Landscape(5,5,0.5);

            // verify
            System.out.println(c1);

            // test
            assert c1!=null : "Error in Landscape::Landscape()";
        }

        // case 2: reset
        {
            // setup
            Landscape l1 = new Landscape(5,5,0.5);
            Cell c1 = new Cell(9,9, Cell.Type.FREE);
            l1.getCell(4, 4).visitFrom(c1);
            l1.reset();

            // verify
            System.out.println(l1);

            // test
            for (int i = 0; i < 5; i++){
                for (int j =0 ; j < 5; j++){
                    assert l1.getCell(i, j).getVisited() == false : "Error in Landscape::reset()";
                    assert l1.getCell(i, j).getPrev() == null : "Error in Landscape::reset()";
                }
            }
        }
    }

}