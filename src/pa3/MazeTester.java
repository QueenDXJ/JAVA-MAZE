package pa3;


public class MazeTester {
	public static void main(String[] args){
		boolean[][] mazeData = new boolean[][]{
			{false,false,false,true},
			{false,false,false,false},
			{true,true,true,false}
		 };
		MazeCoord start = new MazeCoord(1,1);
		MazeCoord exit = new MazeCoord(2,3);
		Maze test = new Maze(mazeData,start,exit);
		test.search();
		System.out.println("start at "+test.getEntryLoc().toString());
		System.out.println("exit at "+test.getExitLoc().toString());
		System.out.println("the path is" + test.getPath().toString());	
	}
}