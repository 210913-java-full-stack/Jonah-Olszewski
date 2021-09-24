package utils;

/**
 * Custom list interface we will be used to implement our own list data structure
 *     //add stuff
 *         //add one item - add(E e)
 *         //add one item in a specific index - add(E e, int index)
 *     //get stuff
 *         //get by index - get(int index)
 *     //delete stuff
 *         //remove at index - remove(int index)
 *         //remove all - clear()
 *     //size()
 *     //contains(E e) //find?
 * */
public interface MyListInterface<E> {
    //size()
    int size();

    //add(E e)
    void add(E e);

    //add(E e, int index)
    void add(E e, int index);

    //get(int index)
    E get(int index);

    //remove(int index)
    void remove(int index);

    //clear()
    void clear();

    //contains(E e)
    int contains(E e);


}
