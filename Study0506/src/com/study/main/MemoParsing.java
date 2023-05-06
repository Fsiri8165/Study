package com.study.main;

public class MemoParsing implements Chart {
	String[][] table;
	int curRow;
	int curCol;

	public MemoParsing() {
		table = new String[50][50];
		curRow = 0;
		curCol = 0;
	}


	@Override
	public void insertCommand(String[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i].contains(" "))
				move(s[i]);
		}
	}

	public void test() {
		System.out.printf("%d, %d\n", this.curRow, this.curCol);
		move("r r u d d 2");
		System.out.printf("%d, %d\n", this.curRow, this.curCol);
	}

	private void goLeft(int number) {
		if (curRow < number) {
			curRow = 0;
		} else {
			curRow -= number;
		}
	}

	private void goRight(int number) {
		if (curRow > 50 - number) {
			curRow = 49;
		} else {
			curRow += number;
		}
	}

	private void goUp(int number) {
		if (curCol < number) {
			curCol = 0;
		} else {
			curCol -= number;
		}
	}

	private void goDown(int number) {
		if (curCol > 50 - number) {
			curCol = 49;
		} else {
			curCol += number;
		}
	}

	private void moveTo(String where, Integer number) {
		if (where.equals("l")) {
			goLeft(number);
		} else if (where.equals("r")) {
			goRight(number);
		} else if (where.equals("u")) {
			goUp(number);
		} else if (where.equals("d")){
			goDown(number);
		}
	}
	@Override
	public void delete() {
		table[curRow][curCol] = null;
	}
	@Override
	public void input(String s) {
		
	}
	@Override
	public void move(String s) {
		
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void move(String s) {
		String[] c = s.split(" ");
		int number = 1;
		String where = "";
		for (int i = 0; i < c.length; i++) {
			if (Character.isDigit(c[i].charAt(0))) {
				number = Integer.parseInt(c[i]);
			} else {
				moveTo(where, number);
				number = 1;
				where = c[i];
			}
		}
		moveTo(where, number);
	}

	@Override
	public void delete() {

	}

	@Override
	public void print() {

	}

	@Override
	public void input(String s) {

	}

}
