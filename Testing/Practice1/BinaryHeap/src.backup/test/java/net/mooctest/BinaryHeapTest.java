package net.mooctest;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

	
	@Test
	public void testWellFormedBad() throws Throwable {
		BinaryHeap heap = new BinaryHeap();
		assertTrue(heap.wellFormed());
		
		Field arrayField = heap.getClass().getDeclaredField("array");
		Field sizeField = heap.getClass().getDeclaredField("currentSize");
		sizeField.setAccessible(true);
		arrayField.setAccessible(true);
		
		Object original = arrayField.get(heap);
		arrayField.set(heap, null);
		assertFalse(heap.wellFormed());
		arrayField.set(heap, original);
		

		original = sizeField.get(heap);
		sizeField.set(heap, -1);
		assertFalse(heap.wellFormed());
		sizeField.set(heap, ((int[])arrayField.get(heap)).length+2);
		assertFalse(heap.wellFormed());
		sizeField.set(heap, original);
		
		arrayField.set(heap, new int[] { 0, 1 });
		assertTrue(heap.wellFormed());
		
		arrayField.set(heap,  new int[] { 0, 1, 2 });
		sizeField.set(heap, 2);
		assertTrue(heap.wellFormed());
		
		arrayField.set(heap, new int[] { 0, 2, 1 });
		assertFalse(heap.wellFormed());
		
		arrayField.set(heap, new int[] { 0, 1, 2, 3});
		sizeField.set(heap, 3);
		assertTrue(heap.wellFormed());
		
		arrayField.set(heap, new int[] { 0, 1, 2, 0});
		assertFalse(heap.wellFormed());
		
		
		
	}

	
	@Test
	public void testBuildHeap() throws Throwable {
		BinaryHeap heap = new BinaryHeap();
		heap.buildHeap();
		
		Method method = heap.getClass().getDeclaredMethod("percolateDown", int.class);
		Field arrayField = heap.getClass().getDeclaredField("array");
		Field sizeField = heap.getClass().getDeclaredField("currentSize");
		
		arrayField.setAccessible(true);
		sizeField.setAccessible(true);
		method.setAccessible(true);
		
		arrayField.set(heap, new int[] { 0,5,4,3,2,1});
		sizeField.set(heap, 5);
		assertFalse(heap.wellFormed());
		
		heap.buildHeap();
		assertTrue(heap.wellFormed());
	}
	
	@Test
	public void testPercolateDown() throws Throwable {
		BinaryHeap heap = new BinaryHeap();
		Method method = heap.getClass().getDeclaredMethod("percolateDown", int.class);
		Field arrayField = heap.getClass().getDeclaredField("array");
		Field sizeField = heap.getClass().getDeclaredField("currentSize");
		
		arrayField.setAccessible(true);
		sizeField.setAccessible(true);
		method.setAccessible(true);
		
		arrayField.set(heap, new int[] { 0, 1,2});
		sizeField.set(heap, 2);
		method.invoke(heap, 1);
		assertTrue(heap.wellFormed());
		
		
		arrayField.set(heap, new int[] { 0, 5,2,3,4, 1});
		sizeField.set(heap, 5);
		method.invoke(heap, 2);
		method.invoke(heap, 1);
		
		assertTrue(heap.wellFormed());
		
		arrayField.set(heap, new int[] { 0, 5,4,3,2,1 });
		method.invoke(heap, 2);
		assertFalse(heap.wellFormed());
		method.invoke(heap, 1);
		assertTrue(heap.wellFormed());
		
	}

}
