# Proyecto: Estimación de Pi

Este proyecto estima el valor de Pi utilizando el método de Monte Carlo. Se implementa tanto en un solo hilo como en un entorno multihilo para comparar el rendimiento.

## Descripción del Problema

La estimación de Pi se basa en la probabilidad de que un punto aleatorio caiga dentro de un círculo de radio 1.


## Archivos

- **PiEstimationSingleThread.java**: Estimación de Pi usando un solo hilo.
- **PiEstimationMultiThread.java**: Estimación de Pi usando múltiples hilos.


## Resultados

- **Estimación de Pi (hilo único)**: 3.14362
- **Tiempo de ejecución (hilo único)**: 90016400 nanosegundos

- **Estimación de Pi (multihilo)**: 3.141408
- **Tiempo de ejecución (multihilo)**: 193322700 nanosegundos

### Aceleración

La aceleración se calculó de la siguiente manera:

Aceleración = (Tiempo de ejecución (hilo único)) / (Tiempo de ejecución (multihilo)) ≈ 0.465

## Conclusión

El método de Monte Carlo es una técnica efectiva para estimar \(\pi\), y el uso de múltiples hilos puede ser beneficioso en ciertos contextos. Sin embargo, en este caso específico, el programa de un solo hilo mostró un mejor rendimiento debido a la sobrecarga de la gestión de múltiples hilos.

## Notas

- Aumentar el número de pruebas por hilo podría mejorar la estimación y el rendimiento del enfoque multihilo.
- El método de Monte Carlo es una técnica de simulación que utiliza la aleatoriedad para resolver problemas matemáticos y estadísticos. Se basa en la generación de números aleatorios y el uso de estos para estimar resultados que pueden ser difíciles de calcular de manera exacta. 
