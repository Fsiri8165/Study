package com.study.main;

public interface Chart {
//	String[][] table;
//	int curRow;
//	int curCol;
	void insertCommand(String s);
	void move(String s);
	void delete();
	void print();
	void input(String s);
}
