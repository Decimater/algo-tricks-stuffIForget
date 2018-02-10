import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
* Iterates over iterable until break. Null or empty throw
*/
public class InfiniteIterable<T> implements Iterable<T> {
	public static void main(String[] args) {
		List<Integer> example = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
		
		int counter = 0;
		
		InfiniteIterable<Integer> infiniteIterable = new InfiniteIterable<>(example);
		
		for	(Integer value : infiniteIterable) {
			System.out.println(value);
			counter++;
			if (counter >= 10) break;
		}
	}
	
    private Iterable <T> myIterable;

    public InfiniteIterable(Iterable<T> iterateOverInfinitely) {
        if(iterateOverInfinitely == null)
            throw new IllegalArgumentException("Nulls are bad mmmkay");
        if (!iterateOverInfinitely.iterator().hasNext())
            throw new IllegalArgumentException("Empty iterable: infinite nothing it nothing");

        myIterable = iterateOverInfinitely;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Iterator<T> oldIterator = myIterable.iterator();

            @Override
            public boolean hasNext() {
                if (!oldIterator.hasNext()) {
                    oldIterator = myIterable.iterator();
                }

                return true;
            }

            @Override
            public T next() {
                return oldIterator.next();
            }
        };
    }
}
