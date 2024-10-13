# Consigna 
Dada una lista de archivos de texto, analice cada archivo de texto contando el uso de cada palabra. Una vez analizado cada archivo, acumule los resultados en un resultado general. Considere utilizar Executors y Callable.

Este proyecto utiliza la programación concurrente en Java para analizar el conteo de palabras en múltiples archivos de texto. A continuación, se detalla el flujo de trabajo y cómo funcionan las clases principales:

### 1. **Entrada del Usuario**
   - El programa comienza solicitando al usuario que ingrese la ruta del directorio que contiene los archivos de texto; en este caso, se creó una carpeta `testdirectory` en `SegConsigna/testdirectory`.
   Esto se hace a través de la clase `Scanner`, que permite capturar la entrada desde la consola.

### 2. **Listar Archivos de Texto**
   - Una vez que se recibe la ruta del directorio, el programa utiliza la clase `File` para obtener una lista de archivos en ese directorio. Solo se consideran los archivos que tienen la extensión `.txt`.

### 3. **Creación de Tareas Concurrentes**
   - Para cada archivo de texto encontrado, se crea una instancia de `WordCountTask`, que implementa la interfaz `Callable<Map<String, Integer>>`. Esto permite que cada tarea se ejecute de manera independiente y devuelva un mapa que contiene las palabras y su respectivo conteo.

### 4. **Uso de Executors**
   - Se utiliza un `ExecutorService` con un grupo de hilos, creado a través de `Executors.newFixedThreadPool()`, que permite manejar múltiples tareas de manera concurrente. El número de hilos se establece en función del número de procesadores disponibles en el sistema.
   - Las tareas son enviadas al `ExecutorService`, y las instancias de `Future<Map<String, Integer>>` se almacenan en una lista. Cada `Future` representa el resultado futuro de una tarea que se está ejecutando.

### 5. **Acumulación de Resultados**
   - Después de enviar todas las tareas, el programa espera a que se completen todas las tareas utilizando el método `get()` en cada `Future`. Esto permite obtener el mapa de conteo de palabras de cada archivo.
   - Los resultados de cada archivo se acumulan en un único `Map<String, Integer>`, que mantiene el conteo total de palabras en todos los archivos analizados. Se utiliza el método `merge()` para sumar los conteos de palabras iguales.

### 6. **Salida de Resultados**
   - Una vez que se han acumulado todos los resultados, el programa imprime el conteo de cada palabra en la consola. Esto proporciona una visión general del uso de las palabras en todos los archivos de texto analizados.

### Ejemplo de Funcionamiento
Se tiene el siguiente contenido en dos archivos de texto:

- **file1.txt**
  Hello world
  This is a test
  Hello again

- **file2.txt**
  Hello world
  this is a second test
  test are fun

Al ejecutar el programa y proporcionar la ruta del directorio que contiene estos archivos, la salida podría ser:

Resultados del conteo de palabras:
a: 2
world: 2
test: 3
are: 1
again: 1
this: 2
is: 2
hello: 3
fun: 1
second: 1

### Consideraciones Técnicas
- **Concurrente**: Utiliza la capacidad de múltiples hilos para procesar archivos simultáneamente, lo que mejora el rendimiento en comparación con un enfoque secuencial.
- **Manejo de Errores**: Se implementa el manejo de excepciones para capturar errores de entrada/salida al leer archivos, lo que asegura que el programa no se detenga ante un problema en un archivo específico.

Este diseño modular y concurrente permite analizar grandes volúmenes de datos de manera eficiente.