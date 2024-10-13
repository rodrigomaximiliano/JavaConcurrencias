
import java.io.File; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import java.util.*; 
import java.util.concurrent.*; 
import java.util.Scanner; 

@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
       
        System.out.print("Introduce la ruta del directorio donde están los archivos de texto: ");
        String directoryPath = scanner.nextLine(); 

        
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files != null && files.length > 0) {
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            List<Future<Map<String, Integer>>> futures = new ArrayList<>();

            // Enviar tareas de conteo de palabras a la cola
            for (File file : files) {
                Future<Map<String, Integer>> future = executor.submit(new WordCountTask(file));
                futures.add(future);
            }

            // Acumular resultados
            Map<String, Integer> wordCountMap = new HashMap<>();
            for (Future<Map<String, Integer>> future : futures) {
                try {
                    Map<String, Integer> result = future.get();
                    result.forEach((word, count) -> 
                        wordCountMap.merge(word, count, Integer::sum)
                    );
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            
            if (!wordCountMap.isEmpty()) {
                System.out.println("Resultados del conteo de palabras:");
                wordCountMap.forEach((word, count) -> 
                    System.out.println(word + ": " + count)
                );
            } else {
                System.out.println("No se encontraron palabras en los archivos.");
            }

            executor.shutdown();
        } else {
            System.out.println("No hay archivos de texto en el directorio especificado.");
        }

        
        scanner.close();
    }
}
