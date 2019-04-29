package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryHeapTest {
	
	@Test
	public void testIsFullOrEmpty() throws Overflow {
		BinaryHeap heap = new BinaryHeap(-1);
		assertFalse(heap.isFull());
		assertTrue(heap.isEmpty());
		
		heap = new BinaryHeap(0);
		assertTrue(heap.isFull());
		assertTrue(heap.isEmpty());
		
		heap = new BinaryHeap(1);
		heap.insert(3);
		assertTrue(heap.isFull());
		assertFalse(heap.isEmpty());
		
		heap.deleteMin();
		assertFalse(heap.isFull());
		assertTrue(heap.isEmpty());
	}

	@Test
	public void test() {
		new BinaryHeap();
	}
	
	@Test
	public void testConstruction() {
		BinaryHeap heap1 = new BinaryHeap();
		
		BinaryHeap heap2 = new BinaryHeap(-1);
	}
	
	@Test
	public void testFindMin() throws Throwable {
		BinaryHeap heap1 = new BinaryHeap();
		
		assertEquals(-1, heap1.findMin());
		
		heap1.insert(3);
		assertEquals(3, heap1.findMin());
		
		heap1.insert(-2);
		assertEquals(-2, heap1.findMin());
		
		heap1.insert(1);
		assertEquals(-2, heap1.findMin());
	}
	
	@Test
	public void testDeleteMin() throws Throwable {
		BinaryHeap heap = new BinaryHeap(4);
		
		assertEquals(-1, heap.deleteMin());
		
		heap.insert(3);
		heap.insert(-3);
		heap.insert(-2);
		heap.insert(1);
		
		assertTrue(heap.isFull());
		assertFalse(heap.isEmpty());
		assertEquals(-3, heap.deleteMin());
		
		assertEquals(-2, heap.deleteMin());
		
		
		assertFalse(heap.isFull());
		assertFalse(heap.isEmpty());
		assertEquals(1, heap.deleteMin());
		assertFalse(heap.isEmpty());
		assertEquals(3, heap.deleteMin());
		assertTrue(heap.isEmpty());
		assertEquals(-1, heap.deleteMin());
	
	}
	
	@Test(expected = Overflow.class)
	public void testInsert() throws Throwable {
		BinaryHeap heap = new BinaryHeap(1);
		
		heap.insert(1);
		
		heap.insert(2);
	}
	
	@Test
	public void testInsertALot() throws Throwable {
		BinaryHeap heap = new BinaryHeap();
		int[] data = new int[] { 0,5,4,3,2,1, 120,2402,-292, 2298,2,-4,202};
		for (int i: data) {
			assertTrue(heap.wellFormed());
			heap.insert(i);
		}
	}
	
	@Test
	public void testMakeEmpty() throws Throwable {
		BinaryHeap heap = new BinaryHeap(3);
		
		heap.insert(3);
		heap.insert(-2);
		heap.insert(1);
		assertTrue(heap.isFull());
		assertFalse(heap.isEmpty());
		assertEquals(-2, heap.findMin());
		
		
		heap.makeEmpty();
		
		assertTrue(heap.isEmpty());
		assertFalse(heap.isFull());
		assertEquals(-1, heap.findMin());
	}
	
	private void insertMultiple(int... arr) throws Exception {
		BinaryHeap heap = new BinaryHeap();
		for (int a: arr) {
			heap.insert(a);
			assertTrue(heap.wellFormed());
			heap.buildHeap();
			assertTrue(heap.wellFormed());
		}
	}
	
	public List<int[]> combination(int n) {
	    List<int[]> combinations = new ArrayList<>();
	    help(new int[n], 0, combinations);
	    return combinations;
	}

	public void help(int[] combination, int index, List<int[]> result) {
		if (index == combination.length) {
			result.add(combination.clone());
		} else {
			for (int i=1;i<=combination.length;i++) {
				int[] clone = combination.clone();
				clone[index] = i;
				help(clone, index+1, result);
			}
		}
	}
	
	@Test(timeout = 4000)
	public void mytest02() throws Throwable {
		BinaryHeap binaryHeap0 = new BinaryHeap(1);
		binaryHeap0.insert(-1);
		binaryHeap0.buildHeap();
		assertEquals(-1, binaryHeap0.findMin());
	} // 112
	
	@Test
	public void testWellFormedBad() throws Throwable {
		BinaryHeap heap = new BinaryHeap();
		assertTrue(heap.wellFormed());

		BinaryHeap heap2 = new BinaryHeap(-1);
		assertFalse(heap2.wellFormed());
		
		// generate all combinations from 1-5

		for (int i=1;i<=5;i++) {
			List<int[]> combinations = combination(i);
			for (int[] combination: combinations) {
				insertMultiple(combination);
			}
		}
		
		// repeat
		insertMultiple(1,1,1);
		insertMultiple(1,2,1);
		insertMultiple(3,3,2);
		insertMultiple(1,2,3,4,2,3,1);
		
		
		
		
	}

	private void insertAndTest(int[] toInsert, int... expected) throws Overflow {
		BinaryHeap heap = new BinaryHeap(toInsert.length);
		for (int i: toInsert) {
			heap.insert(i);
			heap.buildHeap();
			assertTrue(heap.wellFormed());
		}
		
		heap.buildHeap();
		
		int i=0;
		while (!heap.isEmpty()) {
			assertEquals(expected[i++], heap.deleteMin());
			assertTrue(heap.wellFormed());
		}
		
	}
	
	@Test
	public void testInsertAndTestSeq() throws Throwable {
		
		for (int i=1;i<=5;i++) {
			for (int[] combination: combination(i)) {
				int[] sorted = combination.clone();
				Arrays.sort(sorted);
				insertAndTest(combination, sorted);
			}
		}
	}
	
	@Test
	public void testBuildHeap() throws Throwable {
		BinaryHeap heap = new BinaryHeap();
		heap.buildHeap();
		assertTrue(heap.wellFormed());
		
		heap.insert(1);
		heap.insert(4);
		heap.buildHeap();
		heap.insert(-1);
		heap.insert(-2);
		heap.insert(-3);
		heap.buildHeap();
		
	}
	
	@Test
	public void testPercolateDown() throws Throwable {
		BinaryHeap heap = new BinaryHeap();

		
	}

}
