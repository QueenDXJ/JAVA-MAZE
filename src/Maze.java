package pa3;

import java.util.Arrays;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA3
//Spring 2018

import java.util.LinkedList;

/**
 * Maze class
 * 
 * Stores information about a maze and can find a path through the maze (if
 * there is one).
 * 
 * Assumptions about structure of the maze, as given in mazeData, startLoc, and
 * endLoc (parameters to constructor), and the path: -- no outer walls given in
 * mazeData -- search assumes there is a virtual border around the maze (i.e.,
 * the maze path can't go outside of the maze boundaries) -- start location for
 * a path is maze coordinate startLoc -- exit location is maze coordinate
 * exitLoc -- mazeData input is a 2D array of booleans, where true means there
 * is a wall at that location, and false means there isn't (see public FREE /
 * WALL constants below) -- in mazeData the first index indicates the row. e.g.,
 * mazeData[row][col] -- only travel in 4 compass directions (no diagonal paths)
 * -- can't travel through walls
 * 
 */

public class Maze {

	public static final boolean FREE = false;
	public static final boolean WALL = true;

	private boolean[][] mazeData;
	private MazeCoord startLoc;
	private MazeCoord exitLoc;
	private boolean[][] visited;
	// to keep the path
	private LinkedList<MazeCoord> coordOfPath;

	/**
	 * Constructs a maze.
	 * 
	 * @param mazeData
	 *            the maze to search. See general Maze comments above for what goes
	 *            in this array.
	 * @param startLoc
	 *            the location in maze to start the search (not necessarily on an
	 *            edge)
	 * @param exitLoc
	 *            the "exit" location of the maze (not necessarily on an edge) PRE:
	 *            0 <= startLoc.getRow() < mazeData.length and 0 <=
	 *            startLoc.getCol() < mazeData[0].length and 0 <= endLoc.getRow() <
	 *            mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length
	 * 
	 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
		this.mazeData = mazeData;
		this.startLoc = startLoc;
		this.exitLoc = exitLoc;
		coordOfPath = new LinkedList<>();
		visited = new boolean[numRows()][numCols()];
	}

	/**
	 * Returns the number of rows in the maze
	 * 
	 * @return number of rows
	 */
	public int numRows() {
		return mazeData.length;
	}

	/**
	 * Returns the number of columns in the maze
	 * 
	 * @return number of columns
	 */
	public int numCols() {
		return mazeData[0].length;
	}

	/**
	 * Returns true iff there is a wall at this location
	 * 
	 * @param loc
	 *            the location in maze coordinates
	 * @return whether there is a wall here PRE: 0 <= loc.getRow() < numRows() and 0
	 *         <= loc.getCol() < numCols()
	 */
	public boolean hasWallAt(MazeCoord loc) {
		int locRow = loc.getRow();
		int locCol = loc.getCol();
		if (mazeData[locRow][locCol] == WALL) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the entry location of this maze.
	 */
	public MazeCoord getEntryLoc() {
		return startLoc;
	}

	/**
	 * Returns the exit location of this maze.
	 */
	public MazeCoord getExitLoc() {
		return exitLoc;
	}

	public String toString() {
		return "Maze: [rows=" + numRows() + ",cols=" + numCols() + "]" + " startLoc: " + getEntryLoc() + " exitLoc"
				+ getExitLoc();
	}

	/**
	 * Returns the path through the maze. First element is start location, and last
	 * element is exit location. If there was not path, or if this is called before
	 * a call to search, returns empty list.
	 * 
	 * @return the maze path
	 */
	
	public LinkedList<MazeCoord> getPath() {
		return coordOfPath;
	}
	
		/*
		//LinkedList<MazeCoord> pathFound = new LinkedList<>();
		// ListNode head = new ListNode(startLoc);
		//if (!search()) {
			//return new LinkedList<MazeCoord>();
		//}
		
		if (coordOfPath.size() < 1) {
			return new LinkedList<MazeCoord>();
		}
		LinkedList<MazeCoord> reverseList = new LinkedList<>();
		reverseList.add(startLoc);
		while (coordOfPath.size() > 0) {
			reverseList.add(coordOfPath.removeLast());
		}
		
		return reverseList;
		/*if (visited[startLoc.getRow()][startLoc.getCol()] == true) {
			pathFound.add(startLoc);
		}
		while () {
			
		}
		getPathProcess(startLoc, pathFound);
		return pathFound;
		}
		*/

/*
	private void getPathProcess(MazeCoord preCoord, LinkedList<MazeCoord> pathFound) {

		MazeCoord up = new MazeCoord(preCoord.getRow(), preCoord.getCol() - 1);
		MazeCoord right = new MazeCoord(preCoord.getRow() + 1, preCoord.getCol());
		MazeCoord down = new MazeCoord(preCoord.getRow(), preCoord.getCol() + 1);
		MazeCoord left = new MazeCoord(preCoord.getRow() - 1, preCoord.getCol());
		if (up.getRow() >= 0 && up.getRow() < visited.length && up.getCol() >= 0 && up.getCol() < visited[0].length
				&& visited[up.getRow()][up.getCol()] == true) {
			pathFound.add(up);
		}
		if (right.getRow() >= 0 && right.getRow() < visited.length && right.getCol() >= 0
				&& right.getCol() < visited[0].length && visited[right.getRow()][right.getCol()] == true) {
			pathFound.add(right);
		}
		if (down.getRow() >= 0 && down.getRow() < visited.length && down.getCol() >= 0
				&& down.getCol() < visited[0].length && visited[down.getRow()][down.getCol()] == true) {
			pathFound.add(down);
		}
		if (left.getRow() >= 0 && left.getRow() < visited.length && left.getCol() >= 0
				&& left.getCol() < visited[0].length && visited[left.getRow()][left.getCol()] == true) {
			pathFound.add(left);
		}

	}*/

	/**
	 * Find a path from start location to the exit location (see Maze constructor
	 * parameters, startLoc and exitLoc) if there is one. Client can access the path
	 * found via getPath method.
	 * 
	 * @return whether a path was found.
	 */
	public boolean search() {
		if(hasWallAt(startLoc)||hasWallAt(exitLoc)) return false;
		/*
		 * MazeCoord[] neighbors = { new MazeCoord(startLoc.getRow(), startLoc.getCol()
		 * - 1), new MazeCoord(startLoc.getRow() + 1, startLoc.getCol()), new
		 * MazeCoord(startLoc.getRow(), startLoc.getCol() + 1), new
		 * MazeCoord(startLoc.getRow() - 1, startLoc.getCol()) };
		 */
		for(int row =0; row<numRows();row++){
			Arrays.fill(visited[row], false);
		}
		return findPath(startLoc);

	}

	private boolean findPath(MazeCoord oneCoord) {
		
		
		// if out of the boundary, false
		/*if (oneCoord.getRow() < 0 || oneCoord.getRow() > mazeData[0].length - 1 || oneCoord.getCol() < 0
				|| oneCoord.getCol() > mazeData.length - 1) {
			return false;
		}
		*/
		if(oneCoord.getRow()<0 || oneCoord.getRow()>= numRows() ||oneCoord.getCol()<0 || oneCoord.getCol()>=numCols() ) return false;
		// if it is a wall, false
		if (mazeData[oneCoord.getRow()][oneCoord.getCol()] == WALL) {
			return false;
		}
		// if visited, false
		if (visited[oneCoord.getRow()][oneCoord.getCol()] == true) {
			return false;
		}
		// if found the exitLoc, true!
		if (oneCoord.equals(exitLoc)) {
			coordOfPath.add(exitLoc);
			return true;
		}

		visited[oneCoord.getRow()][oneCoord.getCol()] = true;
		//coordOfPath.add(oneCoord);

		if(oneCoord.getCol()>0){
			MazeCoord up = new MazeCoord(oneCoord.getRow(), oneCoord.getCol() - 1);
			if (findPath(up)) {
				//visited[up.getRow()][up.getCol()] = true;
				coordOfPath.add(oneCoord);
				return true;
			}
		}
		//coordOfPath.remove(up);
		if(oneCoord.getRow()<numRows()-1){
			MazeCoord right = new MazeCoord(oneCoord.getRow() + 1, oneCoord.getCol());
			if (findPath(right)) {
				//visited[right.getRow()][right.getCol()] = true;
				coordOfPath.add(oneCoord);
				return true;
			}
		}
		//coordOfPath.remove(right);
		if(oneCoord.getCol()<numCols()-1){
			MazeCoord down = new MazeCoord(oneCoord.getRow(), oneCoord.getCol() + 1);
			if (findPath(down)) {
				//visited[down.getRow()][down.getCol()] = true;
				coordOfPath.add(oneCoord);
				return true;
			}
		}
		//coordOfPath.remove(down);
		//
		if(oneCoord.getRow()>0){
			MazeCoord left = new MazeCoord(oneCoord.getRow() - 1, oneCoord.getCol());
			if (findPath(left)){
				//visited[left.getRow()][left.getCol()] = true;
				coordOfPath.add(oneCoord);
				return true;
			}
		}
		//coordOfPath.remove(left);

		return false;
	}

}