package driverFile;

import java.io.IOException;
import java.util.ArrayList;

public class DemoArray {
	
	public static void main(String[] args) {
		OneDArray();
		TwoDArray();
		try {
			DataProvider.GetDataFromExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		public static void OneDArray() {
			
		String[] name = new String[] {"abc","def"};
		//name[0]="abc";
		//name[1]="def";
		for(int i=0;i<name.length;i++) {
			System.out.println(name[i]);
		}
	
}
		
		public static void TwoDArray() {
			
			String[][] name = new String[][] { {"abc","def","efg","akl" },
											   {"ghu","ijk","klm"},
											   {"sdr","fgt","ghy","ghyui","lkop"} };
		int[][] num =new int[][] { {1213,124,125},
								{234,456,4544,567},
								{345,321,231,222,456}};
		
		for(int i=0;i<num.length;i++){
			
			for(int j=0;j<num[i].length;j++) {
				System.out.println(num[i][j]+"  ");
			}
			
		}
			System.out.println();								   
		}
		
		public static void OneDArraylist() {
			
			ArrayList<Object> arr = new ArrayList<Object>();
			
		}
		
		public static void TwoDArrayList() {
			
		}
}
