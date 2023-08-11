//libraries used
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Landscape {

    //fields
    private Cell[][] board;
    int rows, cols;
    private double chance;  //chance of being an obstacle
    private Cell start;
    private Cell target;

    //constructor
    public Landscape(int rows, int cols, double chance){
        this.rows = rows;
        this.cols = cols;
        this.chance = chance;
        board = new Cell[this.rows][this.cols];
        Random ran = new Random();

        //creating a cell in each grid
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                if (ran.nextDouble(1) < chance){  
                    this.board[i][j] = new Cell(i, j, Cell.Type.OBSTACLE); //the cell is an obstacle
                } else{
                    this.board[i][j] = new Cell(i, j, Cell.Type.FREE);  //the cell is not an obstacle
                }
            }
        }
        
        //initializing start and target position
        int startRow = this.rows - 1;  //starting at the bottom of the screen
        int targetRow = 0;   //ending at the top of the screen
        int startCol = 0;
        int targetCol = 0;
        while (startRow == targetRow && startCol==targetCol)  //giving a random starting point and ending point but not in the same col
            startCol = ran.nextInt(this.cols);
            targetCol = ran.nextInt(this.rows);
            this.start = new Cell(startRow, startCol, Cell.Type.START);
            this.board[startRow][startCol] = this.start;
            this.target = new Cell(targetRow, targetCol, Cell.Type.TARGET);
            this.board[targetRow][targetCol] = this.target;
    }

    //returns the start
    public Cell getStart(){
        return this.start;
    }

    //returns target
    public Cell getTarget(){
        return this.target;

    }

    //retuns rows
    public int getRows(){
        return this.rows;
    }

    //returns cols
    public int getCols(){
        return this.cols;
    }

    //returns the cell in row, col position
    public Cell getCell(int row, int col){
        return this.board[row][col];
    }

    //resets each of the cell in the Landscape
    public void reset(){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.cols; j++){
                this.board[i][j].reset();   
            }
        }
    }

    //returns an ArrayList of Cells that are neighbors of Cell c 
    public ArrayList<Cell> getNeighbors(Cell c){
        ArrayList<Cell> neighbors = new ArrayList<>();
        int row = c.getRow();
        int col = c.getCol();

        //adds the upper cell
        if (row - 1 >= 0 && this.getCell(row - 1, col).getType() != Cell.Type.OBSTACLE){
            neighbors.add(this.getCell(row - 1, col));
        }

        //adds the left cell
        if (col - 1 >= 0 && this.getCell(row, col - 1).getType() != Cell.Type.OBSTACLE){
                neighbors.add(this.getCell(row, col-1));
        }

        //adds the right cell
        if (col + 1 < this.cols && this.getCell(row, col + 1).getType() != Cell.Type.OBSTACLE  ){
                neighbors.add(this.getCell(row, col + 1));
        }

        //adds the lower cell
        if (row + 1 < this.rows && this.getCell(row + 1, col).getType() != Cell.Type.OBSTACLE){
                neighbors.add(this.getCell(row + 1, col));
        }
        return neighbors;
    }

    //string representation
    public String toString(){
        String res = "";
        for (int i = 0; i < this.rows; i++){
            res += " |";
            for (int j = 0; j < this.cols; j++){
                Cell c = this.getCell(i, j);
                res += c.toString();
            }
            res += "\n";
        }
        return res;
    }

    //draws method
    public void draw(Graphics g, int scale) {
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getCols(); c++){
                getCell(r, c).draw(g, scale, this);
            }
        }
        g.setColor(Color.RED);
        int cellCount = 0;
        CellQueue queue = new CellQueue();
        queue.offer(start);
        while (!(queue.size() == 0)) {
            Cell cur = queue.poll();
    
            for (Cell neighbor : getNeighbors(cur)) {
                if (neighbor.getPrev() == cur) {
                    queue.offer(neighbor);
                    g.drawLine(cur.getCol() * scale + scale / 2, cur.getRow() * scale + scale / 2,
                            neighbor.getCol() * scale + scale / 2, neighbor.getRow() * scale + scale / 2);
                    cellCount++;
                }
            }
        }
    
        if (target.getVisited()) {
            Cell cur = target.getPrev();
            while (cur != start) {
                g.setColor(Color.WHITE);
                g.fillRect(cur.getCol() * scale + 2, cur.getRow() * scale + 2, scale - 4, scale - 3);
                cur = cur.getPrev();
            }
            cur = target;
            int count = -1;     
            while (cur != start) {
                g.setColor(Color.BLUE);
                g.drawLine(cur.getCol() * scale + scale / 2, cur.getRow() * scale + scale / 2,
                        cur.getPrev().getCol() * scale + scale / 2, cur.getPrev().getRow() * scale + scale / 2);
                count++;
                cur = cur.getPrev();
            }
            System.out.println("Total number of vertices visited is " + cellCount);
            System.out.println("Number of vertices in the minimal length is " + count);
        }
    }

    //testing in the main method
    public static void main(String[] args) {
        Landscape ld = new Landscape(5, 5, 0.3);
        System.out.println(ld.toString());
        Cell c1 = ld.getCell(4,4);
        System.out.println(ld.getNeighbors(c1));
    }
}
