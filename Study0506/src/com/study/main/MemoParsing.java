package com.study.main;

public class MemoParsing implements Chart{
	String[][] table;  // chart 안에 들어있는 좌표 별 문자열 데이터
	int curRow; // x
	int curCol; // y
	
	@Override
	public void insertCommand(String s) {
		
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
	
	
}
