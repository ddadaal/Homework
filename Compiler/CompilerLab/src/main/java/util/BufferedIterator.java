package util;

import java.util.*;

public class BufferedIterator<T> implements ListIterator<T> {



    private ArrayList<T> readContents = new ArrayList<>();

    private int readIndex = 0;

    private Iterator<T> originalIterator;

    public BufferedIterator(Iterator<T> originalIterator) {
        this.originalIterator = originalIterator;
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words,
     * returns {@code true} if {@link #next} would return an element rather
     * than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the forward direction
     */
    @Override
    public boolean hasNext() {
        return readIndex < readContents.size() || originalIterator.hasNext();
    }

    /**
     * Returns the next element in the list and advances the cursor position.
     * This method may be called repeatedly to iterate through the list,
     * or intermixed with calls to {@link #previous} to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same element repeatedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iteration has no next element
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }



        if (readIndex < readContents.size()) {
            return readContents.get(readIndex++);
        }

        T content = originalIterator.next();
        readContents.add(content);
        readIndex++;
        return content;

    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the reverse direction
     */
    @Override
    public boolean hasPrevious() {
        return readIndex != 0;
    }

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *                                element
     */
    @Override
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return readContents.get(--readIndex);
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #next}. (Returns list size if the list
     * iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a
     * subsequent call to {@code next}, or list size if the list
     * iterator is at the end of the list
     */
    @Override
    public int nextIndex() {
        if (!hasNext()) {
            return readContents.size();
        }
        return readIndex;
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}. (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     * subsequent call to {@code previous}, or -1 if the list
     * iterator is at the beginning of the list
     */
    @Override
    public int previousIndex() {
        if (!hasPrevious()) {
            return -1;
        }
        return readIndex-1;
    }

    /**
     * Removes from the list the last element that was returned by {@link
     * #next} or {@link #previous} (optional operation).  This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this list iterator
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}
     */
    @Override
    public void remove() {

    }

    /**
     * Replaces the last element returned by {@link #next} or
     * {@link #previous} with the specified element (optional operation).
     * This call can be made only if neither {@link #remove} nor {@link
     * #add} have been called after the last call to {@code next} or
     * {@code previous}.
     *
     * @param t the element with which to replace the last element returned by
     *          {@code next} or {@code previous}
     * @throws UnsupportedOperationException if the {@code set} operation
     *                                       is not supported by this list iterator
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws IllegalArgumentException      if some aspect of the specified
     *                                       element prevents it from being added to this list
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}
     */
    @Override
    public void set(T t) {

    }

    /**
     * Inserts the specified element into the list (optional operation).
     * The element is inserted immediately before the element that
     * would be returned by {@link #next}, if any, and after the element
     * that would be returned by {@link #previous}, if any.  (If the
     * list contains no elements, the new element becomes the sole element
     * on the list.)  The new element is inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new element.
     * (This call increases by one the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param t the element to insert
     * @throws UnsupportedOperationException if the {@code add} method is
     *                                       not supported by this list iterator
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws IllegalArgumentException      if some aspect of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public void add(T t) {

    }
}
