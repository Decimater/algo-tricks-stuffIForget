import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SelectionSort {

    public static void main(String[] args) {
	    List<Integer> toSort =
                new Random()
                .ints(1, 100)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(toSort);
        System.out.println(selectionSort(toSort));
    }

    /**
     * steps
     * 1. Find the smallest value in input
     * 2. append smallest value to output
     * 3. remove smallest value from input
     * 4. Stop when input is empty as all values have now been appended
     */
    public static List<Integer> selectionSort(List<Integer> input) {
        List<Integer> myInput = new LinkedList<>(input);
        List<Integer> output = new LinkedList<>();

        while (!myInput.isEmpty()) {
            Integer smallest = myInput.stream()
                    .min(Integer::compareTo)
                    .orElseThrow(IllegalArgumentException::new);

            myInput.remove(smallest);
            output.add(smallest);
        }

        return output;
    }
}
