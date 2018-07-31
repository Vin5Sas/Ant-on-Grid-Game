/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameant;
import java.io.*;
import java.util.*;

//OBJECT CLASSES

class Grid
{
    public ArrayList<ArrayList<Cell>> grid;
    
    Grid(int m)
    {
        grid = new ArrayList<ArrayList<Cell>>();
        for(int i=0;i<m;i++)                    //creates mxm grid
        {
            grid.add(new ArrayList<Cell>());    //creates rows
            for(int j=0;j<m;j++)
                grid.get(i).add(new Cell());    //creates columns with cells (cell objects)
        }
    }
    
    public void displayGrid(int m)      //displays grid
    {
        for(int i=m-1;i>=0;i--)                    
        {
            for(int j=0;j<m;j++)
                System.out.print(i + "" + j + "" + grid.get(i).get(j).currentColour + " ");
        System.out.println();
        }
    }
}

class Cell
{
   char currentColour;
   Colour colourObject;
   
   Cell()
   {
       colourObject = new White();
       currentColour = colourObject.returnColour();
   }
   
   public void changeColor()
   {
        this.colourObject = colourObject.flipColour();
        this.currentColour = colourObject.returnColour();
   }
}

abstract class Colour
{
    abstract public Colour flipColour();
    abstract public char returnColour();
}

class White extends Colour
{
    @Override
    public Colour flipColour()
    {
        return (new Black());
    }
    
    @Override
    public char returnColour()
    {
        return 'W';
    }
}

class Black extends Colour
{
    @Override
    public Colour flipColour()
    {
        return (new White());
    }
    
    @Override
    public char returnColour()
    {
        return 'B';
    }
}

abstract class Direction
{
    abstract public Direction turnRight();
    abstract public Direction turnLeft();
    abstract public char returnDirection();
}

class North extends Direction
{
    @Override
    public Direction turnRight()
    {
        //return 'E';
        return (new East());
    }
    
    @Override
    public Direction turnLeft()
    {
        //return 'W';
        return (new West());
    }
    
    @Override
    public char returnDirection()
    {
        return 'N';
    }
}

class South extends Direction
{
    @Override
    public Direction turnRight()
    {
        //return 'W';
        return (new West());
    }
    
    @Override
    public Direction turnLeft()
    {
        //return 'E';
        return (new East());
    }

    @Override
    public char returnDirection()
    {
        return 'S';
    }
}

class East extends Direction
{
    @Override
    public Direction turnRight()
    {
        //return 'S';
        return (new South());
    }
    
    @Override
    public Direction turnLeft()
    {
        //return 'N';
        return (new North());
    }
    
    @Override
    public char returnDirection()
    {
        return 'E';
    }
}

class West extends Direction
{
    @Override
    public Direction turnRight()
    {
        //return 'N';
        return (new North());
    }
    
    @Override
    public Direction turnLeft()
    {
        //return 'S';
        return (new South());
    }
    
    @Override
    public char returnDirection()
    {
        return 'W';
    }
}

class Position
{
    public int row, column;
    
    Position()
    {
        row=column=0;
    }

    public void Up()        //increases Row index
    {
        row+=1;
    }
    
    public void Down()      //decreases Row index
    {
        row-=1;
    }
    
    public void Left()      //decreases Column index
    {
        column-=1;
    }
    
    public void Right()     //increases Column index
    {
        column+=1;
    }
}

class Ant
{
    Direction currentDirection;
    Position currentPosition;

    Ant()
    {
        this.currentPosition = new Position();
        this.currentDirection = new North();
    }
    
    public void moveUp()
    {
        currentPosition.Up();
    }
    
    public void moveDown()
    {
        currentPosition.Down();
    }
    
    public void moveLeft()
    {
        currentPosition.Left();
    }
    
    public void moveRight()
    {
        currentPosition.Right();
    }
    
    public void turnAnt(Grid g1,Ant a)
    {
        if(g1.grid.get(a.currentPosition.row).get(a.currentPosition.column).currentColour == 'W')
        {
            currentDirection = currentDirection.turnRight();
        }
        else
        {
            currentDirection = currentDirection.turnLeft();
        }
    }
    
    public void resetAnt()
    {
        currentPosition.row = currentPosition.column = 0;
        currentDirection = new North();
    }
}

//MAIN CLASS
public class GameAnt {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Grid g;
        Ant ant;
        int dimension, moves;
        
        System.out.println("Enter the Grid's dimension: ");
        dimension = input.nextInt();
        
        g = new Grid(dimension);            //creates the grid
        
        g.displayGrid(dimension);           //initial grid status
        
        System.out.println("Enter the no.of moves: ");
        moves = input.nextInt();
        
        ant = new Ant();
        
        while(moves>0)
        {
            try
            {
                switch(ant.currentDirection.returnDirection())
                {
                    case 'N': ant.moveUp();
                              System.out.println("Moved to " + ant.currentPosition.row + "" + ant.currentPosition.column);
                              ant.turnAnt(g, ant);
                              System.out.println("Direction changed to " + ant.currentDirection.returnDirection());
                              g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).changeColor();    //gets the index of ant and changes that cell's colour
                              System.out.println("Color channged to " + g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).currentColour);
                              
                    break;
                    case 'S': ant.moveDown(); 
                              System.out.println("Moved to " + ant.currentPosition.row + "" + ant.currentPosition.column);
                              ant.turnAnt(g, ant);
                              System.out.println("Direction changed to " + ant.currentDirection.returnDirection());
                              g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).changeColor();
                              System.out.println("Color channged to " + g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).currentColour);
                              
                    break;
                    case 'E': ant.moveRight(); 
                              System.out.println("Moved to " + ant.currentPosition.row + "" + ant.currentPosition.column);
                              ant.turnAnt(g, ant);
                              System.out.println("Direction changed to " + ant.currentDirection.returnDirection());
                              g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).changeColor();  
                              System.out.println("Color channged to " + g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).currentColour);
                              
                    break;
                    case 'W': ant.moveLeft(); 
                              System.out.println("Moved to " + ant.currentPosition.row + "" + ant.currentPosition.column);
                              ant.turnAnt(g, ant);
                              System.out.println("Direction changed to " + ant.currentDirection.returnDirection());
                              g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).changeColor();  
                              System.out.println("Color channged to " + g.grid.get(ant.currentPosition.row).get(ant.currentPosition.column).currentColour);
                              
                    break;
                }
            }
            catch(IndexOutOfBoundsException e)
            {
                ant.resetAnt();
            }
            
            moves--;
        }
        /*g.grid.get(1).get(1).changeColor(); //test color change/toggle
        System.out.println(g.grid.get(1).get(1).currentColour);*/
        
       g.displayGrid(dimension);
       
       System.out.println("Ant's final position: " + ant.currentPosition.row + " " + ant.currentPosition.column + " " + ant.currentDirection.returnDirection());
    }
    
}
