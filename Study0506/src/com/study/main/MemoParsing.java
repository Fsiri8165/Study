package com.study.main;

public class MemoParsing implements Chart {
	String[][] table;
	int curRow, curCol;
	int firstRow, lastRow;
	int firstCol, lastCol;
	boolean isMulti;
	int mode;
	
	public MemoParsing() {
		table = new String[50][50];
		curRow = 0;
		curCol = 0;
		isMulti = false;
		mode = 0;
	}

	@Override
	public void insertCommand(String[] s) {
		for (String cmd : s) {
			if (cmd.startsWith("DELETE")) {
				delete();
			} else if (cmd.startsWith("PRINT")) {
				print();
			} else if (cmd.startsWith("'")) {
				input(cmd);
			} else if (cmd.startsWith("shift")) {
				shift(cmd.replace("shift ", ""));
			} else if (cmd.startsWith("REPLACE")) {
				replace(cmd.replace("REPLACE ", ""));
			} else if (cmd.startsWith("MODE")) {
				mode(cmd.replace("MODE ", ""));
			} else {
				move(cmd);
			}
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
		} else if (where.equals("d")) {
			goDown(number);
		}
	}

	// 진재언
	@Override
	public void delete() {
		if (isMulti) {
			range(new Worker() {

				@Override
				public void work(Chart c, int x, int y, String... args) {
					((MemoParsing) c).deleteOne(x, y);
				}
			});
		} else {
			table[curRow][curCol] = null;
		}
	}

	public void deleteOne(int x, int y) {
		table[x][y] = null;
	}

	public void range(Worker w) {
		for (int i = firstRow; i <= lastRow; i++) {
			for (int j = firstCol; j <= lastCol; j++) {
				w.work(this, i, j);
			}
		}
	}

	// 이미주
	@Override
	public void print() {
		String ss = table[curRow][curCol];
		StringBuffer sb = new StringBuffer();
		if (table[curRow][curCol] == null) {
			System.out.println("EMPTY");
		} else {
			if (ss.contains("\\")) {
				for (int j = 0; j < ss.length(); j++) {
					if (ss.charAt(j) == '\\') {
						j++;
						if (ss.charAt(j) == 'n') {
							sb.append("\n");
						} else if (ss.charAt(j) == '\\') {
							sb.append("\\");
						} else if (ss.charAt(j) == '\'') {
							sb.append("\'");
						}
					} else {
						sb.append(ss.charAt(j));
					}
				}
				System.out.println(sb);
			} else {
				System.out.println(ss);
			}
		}
	}

	public void shift(String s) {
		firstRow = curRow;
		firstCol = curCol;
		move(s);
		lastRow = Math.max(firstRow, curRow);
		firstRow = Math.min(firstRow, curRow);
		lastCol = Math.max(firstCol, curCol);
		firstCol = Math.min(firstCol, curCol);
		isMulti = true;
	}

	// 신용대
	@Override
	public void move(String s) {
		isMulti = false;
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

	// 나준호
	@Override
	public void input(String s) {
		if (isMulti) {
			range(new Worker() {

				@Override
				public void work(Chart c, int x, int y, String... args) {
					((MemoParsing) c).inputOne(x, y, s);
				}
			});
		} else {
			inputOne(curRow, curCol, s);
		}
	}

	public void inputOne(int x, int y, String s) {
		s = s.substring(1, s.length() - 1);
		// insert모드
		if (mode == 1) {
			if (table[x][y] == null || table[x][y].length() < s.length()) {
				table[x][y] = s;
			} else {
				table[x][y] = s + table[x][y].substring(s.length());
			}
		} else if (mode == 2) {
			// append모드
			if (table[x][y] == null) {
				table[x][y] = s;
			} else {
				table[x][y] += s;
			}
		} else {
			table[x][y] = s;
		}
	}

	public void showChart() {
		System.out.printf("%d, %d\n", curRow, curCol);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(table[j][i] + ",");
			}
			System.out.println();
		}
		System.out.println("--------------------------------");
	}

	public void replaceOne(int x, int y, String s) {
		String ts = table[x][y];
		s = s.replace("\\'", "▒").replace("\' \'", "♨♨♨").replace("\\", "▧");
		String[] ss = s.split("♨♨♨");
		ss[0] = ss[0].replace("▒", "\\'").replace("▧", "\\");
		ss[1] = ss[1].replace("▒", "\\'").replace("▧", "\\");
		ts = ts.replace(ss[0].substring(1), ss[1].substring(0, ss[1].length() - 1));
		table[x][y] = ts;
	}

	@Override
	public void replace(String s) {
		if (isMulti) {
			range(new Worker() {

				@Override
				public void work(Chart c, int x, int y, String... args) {
					((MemoParsing) c).replaceOne(x, y, s);
				}
			});
		} else {
			replaceOne(curRow, curCol, s);
		}
	}

	@Override
	public void mode(String s) {
		if (s.equals("insert")) {
			mode = 1;
		} else if (s.equals("append")) {
			mode = 2;
		} else {
			mode = 0;
		}
	}

}
