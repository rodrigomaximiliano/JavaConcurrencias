import java.io.File; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import java.util.concurrent.Callable; 

public class WordCountTask implements Callable<Map<String, Integer>> {
    private final File file;

    public WordCountTask(File file) {
        this.file = file;
    }

    @Override
    public Map<String, Integer> call() {
        Map<String, Integer> wordCount = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String line : lines) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word.toLowerCase(), wordCount.getOrDefault(word.toLowerCase(), 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }
}
