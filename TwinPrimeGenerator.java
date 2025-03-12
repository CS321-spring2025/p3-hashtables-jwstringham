/**
 * 
 */
public class TwinPrimeGenerator {
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
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int j = 2; j <= Math.sqrt(n); j++) {
            if (n % j == 0) return false;
        }
        return true;
    }
    
    /*
    public static void main(String[] args) {
        int twinPrime = generateTwinPrimes(95500, 96000);
        System.out.println("First twin prime found: " + twinPrime);
    }
    */
}
