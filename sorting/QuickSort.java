import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class QuickSort {

    public static void main(String[] args) {
	    int[] toSort = new Random()
                .ints(1, 100)
                .limit(10)
                .toArray();

        System.out.println(Arrays.toString(toSort));
        System.out.println(Arrays.toString(quickSort(toSort)));
    }

    public static int[] quickSort(int[] input) {
        // base case, empty arrays and arrays with a single value are already sorted
        if (input.length < 2) {
            return input;
        } else {
            int pivot = input[0];

            // less than or equal to pivot,
            // less than removes duplicates,
            // if pivot is not removed cannot simplify to base case
            int[] less = Arrays.stream(Arrays.copyOfRange(input,1, input.length)).filter(i -> i <= pivot).toArray();
            int[] more = Arrays.stream(input).filter(i -> i > pivot).toArray();
            int[] pivotArray = {pivot};

            // concat and return pivot with the now sorted less more arrays
            return Stream.of(quickSort(less), pivotArray, quickSort(more))
                    .flatMapToInt(Arrays::stream)
                    .toArray();
        }
    }
}
