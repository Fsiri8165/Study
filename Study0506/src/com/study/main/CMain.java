package com.study.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMain {
	public static String[] ReadFile(String s) {
		BufferedReader br = null;
		String[] ret = null;
		try {
			FileInputStream fis = new FileInputStream(s);
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			int i = Integer.parseInt(br.readLine());
			ret = new String[i];
			for (int j = 0; j < ret.length; j++) {
				ret[j] = br.readLine();
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {br.close();} catch (IOException e) {}
		}
	}
	
	public static void main(String[] args) {
		String[] p = ReadFile("C:\\Users\\신용대\\Desktop\\TestCase\\Level1\\ex_lv1_tc4.txt");
		Chart c = new MemoParsing();
		c.insertCommand(p);
	}
	
}
