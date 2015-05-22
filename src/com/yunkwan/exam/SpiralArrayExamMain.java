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
		int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		
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
			direction = (direction + 1) % 4;
			return true;
		}

		private boolean checkNext(Map map) {
			if(map.check(current.x + DIRECTIONS[direction][0], current.y + DIRECTIONS[direction][1])) {
				return true;
			}
			return false;
		}

		private void goSingleStep() {
			current.x += DIRECTIONS[direction][0];
			current.y += DIRECTIONS[direction][1];
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
