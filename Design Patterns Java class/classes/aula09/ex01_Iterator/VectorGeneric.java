/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex01_Iterator;

/**
 *
 * @author diogo
 */
public class VectorGeneric<T> {

    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC;

    @SuppressWarnings("unchecked")
    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null) {
            return false;
        }
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if (nElem >= dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem);
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for (int i = 0; i < nElem; i++) {
            if (vec[i].equals(elem)) {
                if (nElem - i - 1 > 0) // not last element
                {
                    System.arraycopy(vec, i + 1, vec, i, nElem - i - 1);
                }
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    public java.util.Iterator<T> Iterator() {
        return new VectorIterator<T>();
    }

    public java.util.ListIterator<T> listIterator() {
        return new VectorListIterator<>();
    }

//    public java.util.ListIterator<T> listIterator(index) {
//        
//        
//    }

    private class VectorListIterator<T> implements java.util.ListIterator<T> {

        private int index;

        public VectorListIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < nElem);
        }

        @Override
        public T next() {
            if (hasNext()) {
                return (T) VectorGeneric.this.vec[index++];
            } else {
                throw new IndexOutOfBoundsException("only " + nElem + " elements");
            }
        }

        @Override
        public boolean hasPrevious() {
            return (index > 0);
        }

        @Override
        public T previous() {
            if (hasNext()) {
                return (T) VectorGeneric.this.vec[index--];
            } else {
                throw new IndexOutOfBoundsException("index on position " + index);
            }
        }

        @Override
        public int nextIndex() {
            if (hasNext()) {
                return index++;
            } else {
                return nElem;
            }
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()) {
                return index--;
            } else {
                return -1;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class VectorIterator<T> implements java.util.Iterator<T> {

        private int index;

        public VectorIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < nElem);
        }

        @Override
        public T next() {
            if (hasNext()) {
                return (T) VectorGeneric.this.vec[index++];
            } else {
                throw new IndexOutOfBoundsException("only " + nElem + " elements");
            }
        }
    }
}
