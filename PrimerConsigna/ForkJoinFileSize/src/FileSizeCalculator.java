import java.io.File;
import java.util.concurrent.RecursiveTask;

public class FileSizeCalculator extends RecursiveTask<Long> {
    private final File directory;

    public FileSizeCalculator(File directory) {
        this.directory = directory;
    }

    @Override
    protected Long compute() {
        long size = 0;
        File[] files = directory.listFiles();  

        if (files != null) {
            
            for (File file : files) {
                if (file.isDirectory()) {
                    
                    FileSizeCalculator subTask = new FileSizeCalculator(file);
                    subTask.fork();  
                    size += subTask.join();  
                } else {
                    size += file.length(); 
                }
            }
        }

        return size;
    }
}
