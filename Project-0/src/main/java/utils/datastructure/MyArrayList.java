////////////////////////////////// THIS STUFF IS HINTS ABOUT IMPLEMENTING ARRAYLIST ////////////////////////////////////
//    At the core of your arraylist implementation there WILL BE A primitive array.
//    this works - our hack we avoid generics, instead using an array of Objects,
//    because all objects inherit eventually from Object class.
//    Object[] o = new Object[2];
//
//    this doesn't work, can't directly build an array of generics
//    E[] w = new E[2];
//
//    When we want to return our array, we would need to "cast" it like this "(Type) thing" we turn the thing into type.
//    public E[] getArray() {
//        return (E[]) o;
//    }
////////////////////////////////// THIS STUFF IS HINTS ABOUT IMPLEMENTING ARRAYLIST ////////////////////////////////////
package utils.datastructure;

public class MyArrayList<E> implements MyListInterface<E>{
    private int size;                                       // keeps track of the number of elements in the array
    private int max_size;                                  // keeps track of the current max size, in case we exceed and need to grow the array
    private Object[] array;             // stores the elements in an array

    public MyArrayList(){
        max_size = 10;
        size = 0;
        array = new Object[max_size];             // MyArrayList = stores 10 elements
    }

    @Override
    // Returns the number of elements in this collection.
    // If this collection contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
    public int size() {
        return size;
    }

    @Override
    // Appends the specified element to the end of this list.
    public void add(E e) {
//        if(size == 0) {
//            //if array is empty we add something to the beginning
//        }
//        else if(size == max_size) {
//        }
//        else {
//            //array not empty, we need to add to the end.
//        }
        //we need to grow the array before we can add an element
        if(size == max_size){
            growArray();
        }
        array[size] = e;
        size++;
    }

    private void growArray() {
        Object[] newArray = new Object[max_size * 2]; //once full it will multiply the max size by 2
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        max_size = max_size * 2;

        //max_size *= 2;
        //array = Arrays.copyOf(array, max_size * 2)
        //simplified version impimenting java.utils.Arrays
    }



    @Override
    // Inserts the specified element at the specified position in this list.
    // Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
    public void add(E e, int index) {
        //we will need to shift anything that comes after requested index to the right in order to make a space for the new element
        //rangeCheck
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size + " is out of bounds.");
        }
        // add the element to get the resizing
        add(e);

        // shift the elements
        for (int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        // put the new one in the right place
        array[index] = e;
        size++;
    }

    @Override
    // Returns the element at the specified position in this list.
    public E get(int index) {
        //rangeCheck
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size + " is out of bounds.");
        }
        else {
            return (E) array[index]; //cast E into array
        }
    }

    @Override
    // Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices).
    public void remove(int index) {
        //we will need to shift everything after the removed element to the left to close the empty space.
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size + " is out of bounds.");
        }
        else {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
        }
        size--;
    }

    // Removes all of the elements from this list. The list will be empty after this call returns.
    public void clear() {
        size = 0;
    }

    @Override
    // Returns true if this collection contains the specified element.
    // More formally, returns true if and only if this collection contains at least one element e such that (o==null ? e==null : o.equals(e)).
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (e.equals(array[i]))
                    return i;
        }
        return -1;
    }
}
