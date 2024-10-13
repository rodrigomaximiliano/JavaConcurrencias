import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PiEstimationMultiThread {
    private static final int NUM_THREADS = 4; 
    private static final int TRIALS_PER_THREAD = 250_000; 

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        AtomicInteger totalInCircleCount = new AtomicInteger(0);

        long startTime = System.nanoTime(); 

        
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.execute(() -> {
                int inCircleCount = 0;
                for (int j = 0; j < TRIALS_PER_THREAD; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (x * x + y * y < 1) {
                        inCircleCount++;
                    }
                }
                
                totalInCircleCount.addAndGet(inCircleCount);
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Esperar a que todos los hilos terminen
        }

        double totalTrials = NUM_THREADS * TRIALS_PER_THREAD;
        double estimateForPi = 4.0 * ((double) totalInCircleCount.get() / totalTrials); 
        long endTime = System.nanoTime();

        System.out.println("Estimación de Pi (multihilo): " + estimateForPi);
        System.out.println("Tiempo de ejecución (multihilo): " + (endTime - startTime) + " nanosegundos");
    }
}
