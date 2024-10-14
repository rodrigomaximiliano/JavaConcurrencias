### Barbería
# Descripción del Proyecto

Este proyecto simula el funcionamiento de una barbería utilizando dos enfoques de sincronización en Java: monitores y locks con condiciones. La barbería tiene una sala de espera con un número limitado de sillas, y los clientes pueden entrar, esperar su turno y recibir un corte de cabello del barbero.
### Implementación con Monitores
En la implementación con monitores, utilizamos el mecanismo de sincronización proporcionado por los bloques synchronized de Java para coordinar las interacciones entre el barbero y los clientes. El barbero duerme cuando no hay clientes y se despierta cuando un cliente entra.
Salida del Programa

Al ejecutar el programa utilizando monitores, la salida fue la siguiente:

Barbero se duerme.
Cliente 1 se sienta en una silla.
Cliente 1 despierta al barbero.
Barbero corta el cabello al cliente 1.
Cliente 2 se sienta en una silla.
Cliente 3 se sienta en una silla.
Cliente 4 se sienta en una silla.
Barbero ha terminado de cortar el cabello.
Barbero corta el cabello al cliente 2.
Cliente 5 se sienta en una silla.
Cliente 6 se va porque no hay sillas disponibles.
Cliente 7 se va porque no hay sillas disponibles.
Barbero ha terminado de cortar el cabello.
Barbero corta el cabello al cliente 3.
...

### Implementación con Locks y Conditions


En la implementación con locks y condiciones, utilizamos ReentrantLock y las condiciones asociadas para gestionar la sincronización entre el barbero y los clientes. Este enfoque permite un control más fino sobre la sincronización, así como evitar problemas de inanición y garantizar la prioridad de los clientes.
Salida del Programa

Al ejecutar el programa utilizando locks y condiciones, la salida fue la siguiente:


Barbero se duerme.
Cliente 1 se sienta en una silla.
Cliente 1 despierta al barbero.
Barbero corta el cabello al cliente 1.
Cliente 2 se sienta en una silla.
Cliente 3 se sienta en una silla.
Cliente 4 se sienta en una silla.
Barbero ha terminado de cortar el cabello.
Barbero corta el cabello al cliente 2.
Cliente 5 se sienta en una silla.
Cliente 6 se va porque no hay sillas disponibles.
Cliente 7 se va porque no hay sillas disponibles.
Barbero ha terminado de cortar el cabello.
Barbero corta el cabello al cliente 3.
...

## Conclusiones

Ambas implementaciones logran coordinar eficazmente las interacciones entre el barbero y los clientes, asegurando que los clientes sean atendidos de manera ordenada y que el barbero trabaje solo cuando hay clientes en espera. La implementación con locks y condiciones proporciona un mayor control sobre el comportamiento concurrente, lo que puede ser útil en aplicaciones más complejas.