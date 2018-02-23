import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
* Use known primes to filter a list of numbers
* Pick the first prime, two
* Filter the list for numbers which are not divisable by two
* Next prime is the first in the list
*/
public class SieveOfEratosthenes {
	
    public static void main(String[] args) {
	    Integer x = 100;
        
        List<Integer> values = IntStream.rangeClosed(2, x).boxed().collect(Collectors.toList());

        List<Integer> primes = new LinkedList<>();

        while (!values.isEmpty()) {
            int aPrime = values.get(0);
            primes.add(aPrime);
            values.removeIf(i -> i % aPrime == 0);
        }

        System.out.println(primes);
        System.out.println(primes.size());
    }
}
