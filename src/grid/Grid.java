package grid;

import TeamRocketProject.AbstractModel; // -JC
import Character.Character;
//import grid.GridController.Border;
/**
 * @module Grid
 * @author Jared M Scott
 * Class: Grid
 * Date: March 9th 2015
 * Update:3/13/15
 * 
 * Its been decided that String[][] is too limiting. I am changing it to Object[][]
 * so that a Character, Item, etc can be inserted into an [x, y] location
 * /

/**
 * 
 * The Grid class contains a double array of type Object which
 * is represented by rows and columns denoted as xAxis and yAxis.
 * The class is initialized once two int parameters are passed to set the 
 * xAxis and yAxis. While the double array is of type Object, it can has two
 * set functions for placing 1)Objects and 2)Character classes inside of the grid.
 * Other than the typical getters and setters, there is also an empty function to
 * test if an Object or Character can be placed within a specific grid location
 */
public class Grid extends AbstractModel {

    //properties
    public static final String 
            ELEMENT_XAXIS_PROPERTY = "xAxis",
            ELEMENT_YAXIS_PROPERTY = "yAxis",
            ELEMENT_GRID_PROPERTY = "grid",
            ELEMENT_GRID_CONTENTS_PROPERTY = "gridContents",
            ELEMENT_NULL_LOCATION_PROPERTY = "nullLocation";
    
    //the xAxis and yAxis represent the cols and rows of the double array grid
    int xAxis;
    int yAxis;
    Object[][] grid;
    Border border;
    //the constructor must accept two values to determine the grid's size
    public Grid(int rows, int cols) {
        int oldXAxis = this.xAxis;
        int oldYAxis = this.yAxis;
        border = new Border();
        this.xAxis = cols;
        this.yAxis = rows;
        //the grid array is initialized with passed parameters
        grid = new Object[rows][cols];  
        
        firePropertyChange(ELEMENT_XAXIS_PROPERTY, oldXAxis, cols);
        firePropertyChange(ELEMENT_YAXIS_PROPERTY, oldYAxis, rows);
        firePropertyChange(ELEMENT_GRID_PROPERTY, null, grid);
    }
  
    public class Border {
        String name = "Border";

        @Override
        public String toString() {
            return name;
        }
    }
    
    //determine if grid[xx][yy] contains any Object or Character
    //calls the getContent function
    boolean empty(int[] location){
        int[] position = {location[0], location[1]};
        if(getContent(position) == null)
            return true;
        
        return false;
    }
    
    //return the contents of grid[x,y]
    Object getContent(int position[]) {
        //error checking is performed to prevent an out of bounds exception
        //a better form of error checking will be added in later revisions
        if (position[0] > yAxis || position[0] < 0 || position[1] > xAxis || position[1] < 0) {
            System.err.println("Error: the x and/or y location is out of bounds");
            return null;
        }   
        int i = position[0];
        int j = position[1];
        return this.grid[i][j];
    }

    //places an instance of class Character in the specificed row and colum
      public void setPosition(Character character, int position[]) {
          //never let a Character be set to a row 0 or col 0
          if(position[0] == 0 || position[1] == 0){
          System.err.println("Error: Cannot set a Character on a Border\n"
                  + "Exception Handling needed");
          }
          
        Character oldCharacter = (Character)this.grid[position[0]][position[1]]; 
        this.grid[position[0]][position[1]] = character;
        firePropertyChange(ELEMENT_GRID_CONTENTS_PROPERTY, oldCharacter, character);        
    }
      
      public void setPositionToNull(int[] position){
        Object oldObj = this.grid[position[0]][position[1]];
        this.grid[position[0]][position[1]] = null;
        firePropertyChange(ELEMENT_NULL_LOCATION_PROPERTY, position, position);
       // firePropertyChange(ELEMENT_GRID_CONTENTS_PROPERTY, oldObj, null);
      }
      
      //this version is for adding Borders to the parameter of the grid
      public void setBorderPosition(int[] position) {
         // System.out.println("Setting position with values: " + position[0] + " " + position[1]);    
        Object oldObj = this.grid[position[0]][position[1]];
        this.grid[position[0]][position[1]] = getBorder();
        firePropertyChange(ELEMENT_GRID_CONTENTS_PROPERTY, oldObj, border);
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }
      
    //the following are basic setters and getters for the class variables 
    //TODO add error checking for setters and getters
    public void setxAxis(int xAxis) {
        int oldXAxis = this.xAxis;
        this.xAxis = xAxis;
        firePropertyChange(ELEMENT_XAXIS_PROPERTY, oldXAxis, xAxis);
    }

    public void setyAxis(int yAxis) {
        int oldYAxis = this.yAxis;
        this.yAxis = yAxis;
        firePropertyChange(ELEMENT_YAXIS_PROPERTY, oldYAxis, yAxis);
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setGrid(Object[][] grid){
        Object oldGrid = this.grid;
        this.grid = grid;
        firePropertyChange(ELEMENT_GRID_PROPERTY, oldGrid, grid);
    }
    
    public int getSize(){
        return (getyAxis() * getxAxis());
    }
    
    public Object[][] getGrid() {
        return grid;
    }
}