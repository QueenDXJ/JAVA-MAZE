package pa3;



//Name: Xijia Ding
//USC NetID: 7359647602
//CS 455 PA3
//Spring 2018

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;


/**
Maze class

Stores information about a maze and can find a path through the maze
(if there is one).

Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
(parameters to constructor), and the path:
  -- no outer walls given in mazeData -- search assumes there is a virtual 
     border around the maze (i.e., the maze path can't go outside of the maze
     boundaries)
  -- start location for a path is maze coordinate startLoc
  -- exit location is maze coordinate exitLoc
  -- mazeData input is a 2D array of booleans, where true means there is a wall
     at that location, and false means there isn't (see public FREE / WALL 
     constants below) 
  -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
  -- only travel in 4 compass directions (no diagonal paths)
  -- can't travel through walls

*/

public class Maze {

	public static final boolean FREE = false;
	public static final boolean WALL = true;
	public static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	private MazeCoord startLoc;
	private MazeCoord exitLoc;
	private boolean[][] mazeData; 
	private LinkedList<MazeCoord> path;
	private boolean[][] visited;
	
	
	/**
	   Constructs a maze.
	   @param mazeData the maze to search.  See general Maze comments above for what
	   goes in this array.
	   @param startLoc the location in maze to start the search (not necessarily on an edge)
	   @param exitLoc the "exit" location of the maze (not necessarily on an edge)
	   PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
	      and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length
	
	 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
		this.startLoc = startLoc;
		this.exitLoc = exitLoc;
		this.mazeData = mazeData;
		path = new LinkedList<>();
		visited = new boolean[numRows()][numCols()]; 
	
	}
	
	
	/**
	   Returns the number of rows in the maze
	   @return number of rows
	*/
	public int numRows() {
	   return mazeData.length;   // return the number of rows
	}
	
	
	/**
	   Returns the number of columns in the maze
	   @return number of columns
	*/   
	public int numCols() {
	   return mazeData[0].length;   // return the number of columns
	} 
	
	
	/**
	   Returns true iff there is a wall at this location
	   @param loc the location in maze coordinates
	   @return whether there is a wall here
	   PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
	*/
	public boolean hasWallAt(MazeCoord loc) {
	   return mazeData[loc.getRow()][loc.getCol()];   // WALL = true;
	}
	
	
	/**
	   Returns the entry location of this maze.
	 */
	public MazeCoord getEntryLoc() {
	   return startLoc;   // return the start point
	}
	
	
	/**
	  Returns the exit location of this maze.
	*/
	public MazeCoord getExitLoc() {
	   return exitLoc;   // return the destination point
	}
	
	
	/**
	   Returns the path through the maze. First element is start location, and
	   last element is exit location.  If there was not path, or if this is called
	   before a call to search, returns empty list.
	
	   @return the maze path
	 */
	public LinkedList<MazeCoord> getPath() {
	
	   return path;   // return the path
	}
	
	
	/**
	   Find a path from start location to the exit location (see Maze
	   constructor parameters, startLoc and exitLoc) if there is one.
	   Client can access the path found via getPath method.
	
	   @return whether a path was found.
	 */
	public boolean search()  {  
	   if(hasWallAt(startLoc)||hasWallAt(exitLoc)) return false;
	   for(int row = 0; row<numRows(); row++){
		   Arrays.fill(visited[row], false);
		   //can not use Arrays.fill(visited,false) directly
	   }
	   boolean res = search(startLoc);
	   if(path.size() == 0) return false;
	   reversePath();
	   return res;  
	
	}
	/**
	   Using backtracking(dfs) to find a path.
	   @param the current location 
	   @return whether a path was found.
	 */
	private boolean search(MazeCoord cur){
		if(hasWallAt(cur) || visited[cur.getRow()][cur.getCol()]) return false;
		if(cur.getRow()<0 || cur.getRow()>= numRows() ||cur.getCol()<0 || cur.getCol()>=numCols() ) return false;
		if(cur.equals(exitLoc)){
			path.add(cur);
			return true;
		}
		visited[cur.getRow()][cur.getCol()] = true;
		for(int[] dir: dirs){
			int nextRow = cur.getRow()+dir[0], nextCol = cur.getCol()+dir[1];
			if(nextRow>=0 && nextCol>=0 && nextCol<numCols() && nextRow<numRows())
			{
				MazeCoord next = new MazeCoord(nextRow, nextCol);
				if(search(next)){
					path.add(cur);
					return true;
				}
			}
		}
		return false; 
	}
	
	/**
	   Since we use backtracking to find path, if we output the path to string,
	   we can see that the path start from destination and end at start point.
	   Although, if I do not reverse the LinkedList, the path shown in MazeViewer is also the same, I still
	   reverse it because the reversed LinkedList is the path we want. 
	 */
	private void reversePath(){
		LinkedList<MazeCoord> temp = new LinkedList<>();
		Iterator<MazeCoord> iter = path.iterator();
		while(iter.hasNext()){
			MazeCoord cur = iter.next();
			temp.addFirst(cur);
		}
		path = temp;
	}	
}
 