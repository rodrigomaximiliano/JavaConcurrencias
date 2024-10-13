public class PiEstimationSingleThread {
    public static void main(String[] args) {
        int trialCount = 1_000_000; 
        int inCircleCount = 0; 

        long startTime = System.nanoTime();

        for (int i = 0; i < trialCount; i++) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y < 1) {
                inCircleCount++;
            }
        }

        double estimateForPi = 4.0 * ((double) inCircleCount / trialCount);
        long endTime = System.nanoTime(); 

        System.out.println("Estimación de Pi (hilo único): " + estimateForPi);
        System.out.println("Tiempo de ejecución (hilo único): " + (endTime - startTime) + " nanosegundos");
    }
}
