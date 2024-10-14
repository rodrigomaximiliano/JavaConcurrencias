### Simulación Bancaria -

Este programa implementa una simulación de un banco donde varios clientes realizan operaciones de depósito o extracción sobre una bóveda compartida. Las operaciones de los clientes se ejecutan de manera concurrente utilizando hilos en Java. Para garantizar la consistencia de los datos, las operaciones de depósito y extracción sobre la bóveda están sincronizadas.

### Componentes principales:
1. Cada cliente puede realizar una operación (depositar o extraer) con un monto específico.
2. La clase que gestiona el estado del dinero en el banco. Usa métodos sincronizados para proteger el acceso concurrente.
3. Los clientes son ejecutados en hilos paralelos y realizan sus operaciones en la bóveda de manera concurrente.

Ejecución:
- El programa crea varios clientes aleatorios.
- Cada cliente realiza su operación sobre la bóveda.
- Al final del día, se muestra un resumen de las operaciones y el estado final de la bóveda.

Cómo ejecutar el programa:
1. Asegúrate de tener el archivo `Banco.java` y sus clases asociadas en tu entorno de trabajo.

2. Ejecuta el programa con el siguiente comando:
   
   java Banco
  

### Salida esperada (ejemplo):

Cliente llegó: Cliente{operacion=EXTRAER, monto=93}  
Cliente extrajo: 93  
Cliente llegó: Cliente{operacion=DEPOSITAR, monto=986}  
Cliente depositó: 986  
Cliente llegó: Cliente{operacion=EXTRAER, monto=528}  
Cliente extrajo: 528  
Cliente llegó: Cliente{operacion=DEPOSITAR, monto=603}  
Cliente depositó: 603  
Cliente llegó: Cliente{operacion=EXTRAER, monto=127}  
Registros del día:  
EXTRAER: 93  
DEPOSITAR: 986  
EXTRAER: 528  
DEPOSITAR: 603  
EXTRAER: 127  
Estado final de la bóveda: 10841  
Total depositado: 1589  
Total extraído: 748  
La bóveda tiene más dinero que al principio del día.