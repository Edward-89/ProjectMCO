package com.diplom.test.algoritm;

import java.util.Arrays;

public class SortArrayTest {
	
	public static void main(String[] args) {
		double[] array = new double[13];
		for(int i = 0; i < array.length; i++){
			array[i] = Math.random();
		}
		System.out.println("Input not sorting array : ");
		System.out.println(Arrays.toString(array));
		Arrays.sort(array);
		System.out.println("Output sorted array : ");
		System.out.println(Arrays.toString(array));
		
	}

}
