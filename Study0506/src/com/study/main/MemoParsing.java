package com.study.main;

public class MemoParsing implements Chart{
	String[][] table;
	int curRow;
	int curCol;

	@Override
	public void insertCommand(String s) {
		
	}
	
	
	@Override
	public void print() {
		if (table[curRow][curCol] == null) {
			System.out.println("EMPTY");
		} else {
			System.out.println(table[curRow][curCol]);
		}
	}

	@Override
	public void move(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input(String s) {
		// TODO Auto-generated method stub
		
	}
	
}
