
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        
       
        System.out.print("Introduce la ruta del directorio: ");
        String dirPath = scanner.nextLine();
        
        File directory = new File(dirPath);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("El directorio no existe o no es un directorio válido.");
            return;
        }

        try (
        ForkJoinPool pool = new ForkJoinPool()) {
            FileSizeCalculator calculator = new FileSizeCalculator(directory);

           
            long totalSize = pool.invoke(calculator);

           
            System.out.println(directory.getAbsolutePath() + " (" + formatSize(totalSize) + ")");
        }
       
        printDirectoryDetails(directory, "");
    }

    
    private static void printDirectoryDetails(File directory, String indent) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    long dirSize = getDirectorySize(file);
                    System.out.println(indent + file.getAbsolutePath() + " (" + formatSize(dirSize) + ")");
                    printDirectoryDetails(file, indent + "  ");
                } else {
                    System.out.println(indent + file.getAbsolutePath() + " (" + formatSize(file.length()) + ")");
                }
            }
        }
    }

    
    private static long getDirectorySize(File directory) {
        long size = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    size += getDirectorySize(file);
                } else {
                    size += file.length();
                }
            }
        }
        return size;
    }

    
    private static String formatSize(long size) {
        if (size >= 1024 * 1024) {
            return (size / (1024 * 1024)) + " Mb";
        } else if (size >= 1024) {
            return (size / 1024) + " Kb";
        } else {
            return size + " bytes";
        }
    }
}
