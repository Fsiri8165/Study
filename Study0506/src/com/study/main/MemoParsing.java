package com.study.main;

public class MemoParsing implements Chart {
	 private String[][] grid = new String[50][50];
	 private int curRow = 0;
	 private int curCol = 0;
	    
	@Override
    public void insertCommand(String s) {
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
	public void print() {
		// TODO Auto-generated method stub

	}

	 @Override
	 public void input(String s) {
	     grid[curRow][curCol] = s.replace("'", "");
	    }
	}
