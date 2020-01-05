package com.zw.test;

import com.zw.util.B_Tree;

import junit.framework.TestCase;

public class B_TreeTest extends TestCase {
	public void arrayMidSplitTest() {
		double[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		double[] array2 = new double[10];
		B_Tree bt = new B_Tree();
		
		int length = bt.arrayMidSplit(array1, array2);
		assertTrue(length == 0);
	}
}
