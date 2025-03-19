/**
 * Defines a class with a static method that generates 
 * a twin prime number within a lower and upper bound
 * 
 * @author James Stringham
 */
public class TwinPrimeGenerator {
    /**
     * Calculates and returns twin prime
     * @param min lower bound
     * @param max upper bound
     * @return twin prime
     */
    public static int generateTwinPrimes(int min, int max)
    { 
        int twinPrime = 0;
        for (int i = min; i <= max; i++) {
            if (isPrime(i) && isPrime(i - 2)) {
                    twinPrime = i;
                    break; 
                }
            }
            return twinPrime;
    }

    /**
     * Helper method that checks if a number is prime
     * @param n number to check
     * @return boolean if it is prime
     */
    private static boolean isPrime(int n) 
    {
        if (n < 2) return false;
        for (int j = 2; j <= Math.sqrt(n); j++) 
        {
            if (n % j == 0) return false;
        }
        return true;
    }
}
