
## Descripción
Este proyecto implementa una aplicación en Java que utiliza la API Fork/Join para calcular y reportar el espacio ocupado por un directorio y todos sus subdirectorios y archivos. La aplicación permite al usuario ingresar la ruta de un directorio, y luego calcula el tamaño total del directorio, así como el tamaño de cada archivo y subdirectorio dentro de él.



## Ejecución
Para ejecutar la aplicación, sigue estos pasos:

1. Abre una terminal.
2. Navega al directorio `ForkJoinFileSize`:
   
   cd C:\Users\Usuario\Desktop\JavaConcurrencia\PrimerConsigna\ForkJoinFileSize

    Ejecuta el programa:
           
           java -cp . Main

Cuando se te solicite, introduce la ruta del directorio que deseas analizar por Ejemplo :

    Introduce la ruta del directorio: "C:...\PrimerConsigna\TestDirectory"

Resultados Esperados

Al introducir la ruta del directorio, la salida debería ser similar a la siguiente:


        C:\Users\Usuario\Desktop\JavaConcurrencia\PrimerConsigna\TestDirectory (1 Mb)
        C:\Users\Usuario\Desktop\JavaConcurrencia\PrimerConsigna\TestDirectory\arch1.txt (512 Kb)
        C:\Users\Usuario\Desktop\JavaConcurrencia\PrimerConsigna\TestDirectory\dir1 (512 Kb)
        C:\Users\Usuario\Desktop\JavaConcurrencia\PrimerConsigna\TestDirectory\dir1\arch1.txt (256 Kb)
        C:\Users\Usuario\Desktop\JavaConcurrencia\PrimerConsigna\TestDirectory\dir1\arch2.txt (256 Kb)

Uso de Concurrencia

La aplicación utiliza la API Fork/Join de Java para dividir la tarea de calcular el tamaño de archivos y directorios en sub-tareas más pequeñas. Esto permite que múltiples hilos de ejecución se encarguen de diferentes partes de la tarea simultáneamente, lo que mejora la eficiencia y reduce el tiempo total de ejecución.

La clase FileSizeCalculator es responsable de la lógica de división de tareas y de la combinación de resultados. Al utilizar la API Fork/Join, la aplicación puede escalar y aprovechar mejor los recursos del sistema, especialmente cuando se trabaja con directorios grandes o estructuras de archivos complejas.
Conclusiones

Este proyecto demuestra el uso eficaz de la API Fork/Join en Java para el procesamiento concurrente de tareas, mostrando cómo puede mejorar la eficiencia en la gestión de archivos y directorios en sistemas de archivos.