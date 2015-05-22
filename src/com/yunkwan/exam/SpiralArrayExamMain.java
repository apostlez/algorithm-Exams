package com.yunkwan.exam;

import java.awt.Point;

public class SpiralArrayExamMain {
	Locator lo;
	Map map;
	
	public SpiralArrayExamMain(int x, int y) {
		lo = new Locator();
		map = new Map(x,y);
	}
	public void addBlock(int x, int y) {
		map.addBlock(x, y);
	}
	
	public void run() {
		lo.go(map);
		map.print();
	}
	
	class Map {
		final static int NOT_VISITED = -1;
		final static int BLOCKED = -2;
		int x = 0;
		int y = 0;
		Integer[][] _map = null;
		public Map(int x, int y) {
			this.x = x;
			this.y = y;
			_map = new Integer[x][y];
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					_map[i][j] = new Integer(NOT_VISITED);
				}
			}
		}
		void print() {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					if(_map[i][j] == BLOCKED) {
						System.out.print("X" + "\t");
					} else if(_map[i][j] == NOT_VISITED) {
						System.out.print("-" + "\t");
					} else {
						System.out.print(_map[i][j] + "\t");
					}
				}
				System.out.println();
			}
			
		}
		public boolean check(int x, int y) {
			if(x >= this.x || y >= this.y || x < 0 || y < 0) {
				return false;
			}
			if(_map[x][y] == NOT_VISITED) { 
				return true;
			}
			return false;
		}
		public void footPrint(Point p, int counter) {
			_map[p.x][p.y] = counter;
		}
		
		public void addBlock(int x, int y) {
			_map[x][y] = BLOCKED;
		}
	}
	
	public class Locator {
		Point current = new Point(0,0);
		int stepsCounter = 0;
		int direction = RIGHT;
		int blockedCounter = 0;

		static final int RIGHT = 0;
		static final int DOWN = 1;
		static final int LEFT = 2;
		static final int UP = 3;
		int[][] DIRECTION = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		
		public void visit(Map map) {
			map.footPrint(current, stepsCounter++);
			blockedCounter = 0;
		}
		
		public void go(Map map) {
			visit(map);
			while(true) {
				if(checkNext(map)) {
					goSingleStep();
					visit(map);
				} else {
					if(!turnRight()) {
						break;
					}
				}
			}
			
		}
		
		private boolean turnRight() {
			if(blockedCounter++ >= 4) {
				return false;
			}
			switch(direction) {
			case RIGHT:
				direction = DOWN;
				break;
			case LEFT:
				direction = UP;
				break;
			case UP:
				direction = RIGHT;
				break;
			case DOWN:
				direction = LEFT;
				break;
			}
			return true;
		}

		public boolean checkNext(Map map) {
			switch(direction) {
			case RIGHT:
				if(map.check(current.x, current.y+1)) {
					return true;
				}
				break;
			case LEFT:
				if(map.check(current.x, current.y-1)) {
					return true;
				}
				break;
			case UP:
				if(map.check(current.x-1, current.y)) {
					return true;
				}
				break;
			case DOWN:
				if(map.check(current.x+1, current.y)) {
					return true;
				}
				break;
			}
			return false;
		}
		
		public void goSingleStep() {
			switch(direction) {
			case RIGHT:
				current.y++;
				break;
			case LEFT:
				current.y--;
				break;
			case UP:
				current.x--;
				break;
			case DOWN:
				current.x++;
				break;
			}
		}
	}

	public static void main(String[] args) {
		SpiralArrayExamMain s = new SpiralArrayExamMain(5,5);
		s.addBlock(0, 4);
		s.addBlock(2, 4);
		s.addBlock(3, 3);
		s.run();
	}

}
