package pa3;

import java.awt.Color;
//import java.awt.Font;

//Name: Xijia Ding
//USC NetID: 7359647602
//CS 455 PA3
//Spring 2018

import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.font.FontRenderContext;
//import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
//import javax.swing.JOptionPane;

import java.util.ListIterator;

/**
MazeComponent class

A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int START_X = 10; // top left of corner of maze in frame
	private static final int START_Y = 10;
	private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
	private static final int BOX_HEIGHT = 20;
	private static final int INSET = 2;  
	                 // how much smaller on each side to make entry/exit inner box
	
	private Maze maze;
	
	/**
	   Constructs the component.
	   @param maze   the maze to display
	*/
	public MazeComponent(Maze maze) 
	{   
	   this.maze = maze;
	}
	
	
	/**
	  Draws the current state of maze including the path through it if one has
	  been found.
	  @param g the graphics context
	*/
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		int totalWidth = BOX_WIDTH * maze.numCols(),totalHeight = BOX_HEIGHT * maze.numRows();
		g2.setColor(Color.white);
		g2.fillRect(START_X, START_Y, totalWidth, totalHeight);
		g2.setColor(Color.black);
		drawWall(g2);
		g2.drawRect(START_X, START_Y, totalWidth, totalHeight);
		int pointWidth = BOX_WIDTH - 2*INSET, pointHeight = BOX_HEIGHT - 2*INSET, entry_X = START_X + maze.getEntryLoc().getCol()*BOX_WIDTH + INSET, entry_Y = START_Y + maze.getEntryLoc().getRow()*BOX_HEIGHT + INSET;
		//set the start Point
		g2.setColor(Color.YELLOW);
		g2.fillRect(entry_X, entry_Y, pointWidth, pointHeight);
		g2.setColor(Color.RED);
		g2.drawString("Start Here", entry_X, entry_Y);
		//set the destination Point
		int dest_X = START_X + maze.getExitLoc().getCol()*BOX_WIDTH + INSET,dest_Y = START_Y + maze.getExitLoc().getRow()*BOX_HEIGHT + INSET;
		g2.drawString("Stop Here", dest_X, dest_Y);
		g2.setColor(Color.GREEN);
		g2.fillRect(dest_X, dest_Y, pointWidth, pointHeight);
		//path
		g2.setColor(Color.blue);
		int halfWidth = BOX_WIDTH/2, halfHeight = BOX_HEIGHT/2;
		drawPath(g2,halfWidth,halfHeight);
		
	}
	/**
	  Draws the wall in the maze
	  @param g2 the graphics2D context
	*/
	private void drawWall(Graphics2D g2){
		for(int row = 0; row<maze.numRows(); row++){
			for(int col=0; col<maze.numCols();col++){
				if(maze.hasWallAt(new MazeCoord(row,col))){
					int wall_X = START_X + col*BOX_WIDTH,wall_Y = START_Y + row*BOX_HEIGHT;
					g2.fillRect(wall_X, wall_Y, BOX_WIDTH, BOX_HEIGHT);
				}
			}
		}
	}
	/**
	  Draws the path based on path list in the maze
	  @param g2 the graphics2D context
	*/
	private void drawPath(Graphics2D g2, int halfWidth, int halfHeight){
		if(maze.getPath()!=null && maze.getPath().size()!=0){
			ListIterator<MazeCoord> iter = maze.getPath().listIterator();
			MazeCoord cur = iter.next();
			while(iter.hasNext()){
				MazeCoord next = iter.next();
				int startPoint_X = START_X+cur.getCol()*BOX_WIDTH + halfWidth,startPoint_Y = START_Y+cur.getRow()*BOX_HEIGHT + halfHeight,endPoint_X = START_X +next.getCol()*BOX_WIDTH + halfWidth, endPoint_Y = START_Y +next.getRow()*BOX_HEIGHT + halfHeight;
				g2.drawLine(startPoint_X, startPoint_Y, endPoint_X, endPoint_Y);
				cur = next;
			}	
		}
	}
}
	
	
