package vooga.games.tronupdate.util;

import java.util.Random;
import java.util.Stack;

public class RandomLayoutGenerator {
	private int difficultyLevel = 3;
	private int row,col;
	public void setDifficultyLevel(int diff){
		difficultyLevel = diff;
	}
	public Grid[][] generateGrid(int numRow,int numCol,int[] startX,int[] startY){
		int preventRangeX = numCol/20;
		int preventRangeY = numRow/20;
		row = numRow; col=numCol;
		int wallStd = (numCol+numRow)/10;
		int blockStd = (numCol+numRow)/20; 
		Grid[][] grid = new Grid[numRow][numCol];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				grid[i][j] = new Grid();
			}
		}
		if(Mode.getEnvironment()==2) grid = initWalls(grid,wallStd);
		if(Mode.getEnvironment()==3) grid = initBlocks(grid,blockStd);
		grid = preventStart(grid,preventRangeX,preventRangeY,startX,startY);
		return grid;
		
		//grid = initWalls(grid,wallStd);
		//grid = initBlocks(grid,blockStd);
		//return grid;
	}
	
	public Stack<Integer> randomBonus(int dimension){
		Stack<Integer> bonusPos = new Stack<Integer>();
		for(int i=0;i<dimension/difficultyLevel;i++){
			bonusPos.add((int)(Math.random()*dimension));
		}
		return bonusPos;
	}
	private Grid[][] initWalls(Grid[][] grid,int wallStd){
		boolean horizontal = false;
		for(int i=0;i<difficultyLevel;i++){
			horizontal = (Math.random()>0.5);
			int centerX = (int) (Math.random()*col);
			int centerY = (int) (Math.random()*row);
			Random r = new Random();
			if(horizontal){
				int Xleft = centerX - (int) Math.abs(r.nextGaussian()*wallStd);
				if(Xleft<0) Xleft=0;
				int Xright = centerX + (int) Math.abs(r.nextGaussian()*wallStd);
				if(Xright>=col) Xright=col-1;
				for(int x=Xleft;x<=Xright;x++){
					grid[centerY][x].setTaken(true);
					grid[centerY][x].setWall(true);
				}
			}
			else{
				int Yup = centerY - (int) Math.abs(r.nextGaussian()*wallStd);
				if(Yup<0) Yup=0;
				int Ydown = centerY + (int) Math.abs(r.nextGaussian()*wallStd);
				if(Ydown>=row) Ydown=row-1;
				for(int y=Yup;y<=Ydown;y++){
					grid[y][centerX].setTaken(true);
					grid[y][centerX].setWall(true);
				}
			}			
		}
		return grid;
	}
	
	private Grid[][] initBlocks(Grid[][] grid,int blockStd){
		for(int i=0;i<difficultyLevel;i++){
			int centerX = (int) (Math.random()*row);
			int centerY = (int) (Math.random()*col);
			Random r = new Random();
			int Xleft = centerX - (int) Math.abs(r.nextGaussian()*blockStd);
			if(Xleft<0) Xleft=0;
			int Xright = centerX + (int) Math.abs(r.nextGaussian()*blockStd);
			if(Xright>=col) Xright=col-1;
			int Yup = centerY - (int) Math.abs(r.nextGaussian()*blockStd);
			if(Yup<0) Yup=0;
			int Ydown = centerY + (int) Math.abs(r.nextGaussian()*blockStd);
			if(Ydown>=row) Ydown=row-1;
			for(int x=Xleft;x<=Xright;x++){
				for(int y=Yup;y<=Ydown;y++){
					grid[y][x].setTaken(true);
					grid[y][x].setWall(true);
				}
			}
		}
		return grid;
	}
	
	private Grid[][] preventStart(Grid[][] grid, int rangeX,int rangeY,int[] startX,int[] startY){
		for(int i=0;i<startX.length;i++){
			int Xleft = (startX[i]-rangeX>=0)? (startX[i]-rangeX):0;
			int Xright = (startX[i]+rangeX<col)? (startX[i]+rangeX):col-1;
			int Yup = (startY[i]-rangeY>=0)? (startY[i]-rangeY):0;
			int Ydown = (startY[i]+rangeY<row)? (startY[i]+rangeY):row-1;
			for(int x=Xleft;x<=Xright;x++){
				for(int y = Yup;y<=Ydown;y++){
					grid[y][x].setTaken(false);
					grid[y][x].setWall(false);
				}
			}
		}
		return grid;
	}
	
}
