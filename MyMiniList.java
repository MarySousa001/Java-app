import java.util.Arrays;
import java.util.Objects;

public class MyMiniList <T> implements MiniList {


    T [] objectStore = (T[]) new Object[10];
    int size = 0;


    @Override
    public void add(Object element) {
        if (size < objectStore.length) {
            for ( int i=size; i< objectStore.length; i++) {
                if (objectStore[i] == null) {
                    objectStore[i]= (T) element;
                    size++;
                    break;
                }
            }
        }else {
            T[] newObject = (T[]) new Object[objectStore.length * 2];
            System.arraycopy(objectStore, 0, newObject, 0, objectStore.length);
            objectStore = newObject;
            add(element);
        }
    }


    @Override
    public Object get(int index) {
         Objects.checkIndex(index, objectStore.length);
                return objectStore[index];
    }

    @Override
    public int getIndex(Object element) {
        for ( int i=0; i< objectStore.length; i++){
            if (objectStore[i] == element) {
                return i;
            }
        }  return -1;
    }

    @Override
    public void set(int index, Object element) {
        // in case to set a null element
        if (index < size) {
             if(element == null){
                 remove(index);
             }
             else
                 objectStore[index] = (T) element;
            }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object remove(int index) {
        Object temp = null;
        if(index< size) {
            temp = objectStore[index];
            objectStore[index] = null;
            for (int i = index; i < size-1; i++) {
                set(i, objectStore[i + 1]);
            }
            objectStore[size] = null;
            size--;
        }else{
            throw new IndexOutOfBoundsException();
        }
        return temp;
    }

    @Override
    public boolean remove(Object element) {
        int a = getIndex(element);
        if(a != -1){
                remove(a);
                return true;
            }
        return false;
    }

    @Override
    public void clear() {
        for(int i=0; i<=size; i++){
            objectStore[i] = null;
        }
        size = 0;
    }

}