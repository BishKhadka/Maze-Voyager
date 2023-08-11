//importing the required libraries
import java.awt.Color;
import java.awt.Graphics;

public class Cell{

    //declaring a type of object that can only take on specific values
    public enum Type{
        FREE, OBSTACLE, START, TARGET
    }

    //fields
    private boolean visited;
    private Cell prev;
    private int row, col;
    private Type type;

    //constructor
    public Cell(int row, int col, Type type){
        this.row = row;
        this.col = col;
        prev = null;
        this.type = type;
    }

    //returns the row of the cell
    public int getRow(){
        return this.row;
    }

    //returns the col of the cell
    public int getCol(){
        return this.col;
    }

    //returns teh type of the cell
    public Type getType(){
        return this.type;
    }

    //returns visited status
    public boolean getVisited(){
        return this.visited;
    }

    //sets the visited to a new boolean
    public void setVisited(boolean newVisited){
        this.visited = newVisited;
    }

    //returns teh previous cell
    public Cell getPrev(){
        return this.prev;
    }

    //sets previous node to be c
    public void visitFrom(Cell c){
        this.visited = true;
        this.prev = c;
    }

    //resets visited and previous cell
    public void reset(){
        this.visited = false;
        this.prev = null;
    }

    //string representation
    public String toString(){
        String res = "";
        if (this.getType() == Cell.Type.FREE){
            res += "   |";
        }
        else if (this.getType() == Cell.Type.OBSTACLE){
            res += " O |";
            
        }
        else if (this.getType() == Cell.Type.START){
            res += " S |";
            
        }
        else if (this.getType() == Cell.Type.TARGET){
            res += " T |";
        }
        return res;
    }

    //draw method
    public void draw(Graphics g, int scale, Landscape scape) {
        g.setColor(Color.BLACK);
        g.drawRect(getCol() * scale, getRow() * scale, scale, scale);
        switch (getType()) {
            case FREE:
                g.setColor(getVisited() ? Color.YELLOW : Color.GRAY);
                break;
            case OBSTACLE:
                g.setColor(Color.BLACK);
                break;
            case START:
                g.setColor(Color.WHITE);
                break;
            case TARGET:
                g.setColor(Color.WHITE);
                break;
        }
        g.fillRect(getCol() * scale + 2, getRow() * scale + 2, scale - 4, scale - 3);
        g.setColor(Color.RED);
    } 

    //testing in the main method
    public static void main(String[] args) {
        Cell c1 = new Cell(1,2, Cell.Type.FREE);
        System.out.println(c1.toString());
    }
}


