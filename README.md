# Post-contenido — Unidad 4: Patrones de Comportamiento en ComprasUDES

## Descripción
Repositorio del post-contenido de la Unidad 4 de Patrones de Diseño
de Software. Un único proyecto Spring Boot (compras-comportamiento)
que resuelve cuatro necesidades reales del backend de ComprasUDES,
el sistema interno de solicitudes de compra corporativas: aprobación
por niveles jerárquicos, ejecución reversible de solicitudes
aprobadas, notificaciones ante cambios de estado y reglas de
transición según el estado actual de la solicitud.

## Cómo ejecutar
```
$ mvn clean package
$ mvn spring-boot:run
$ mvn test
```

## Decisiones de diseño

### Necesidad 1 — Aprobación por niveles jerárquicos
Patrón de comportamiento aplicado: Chain of Responsibility (Cadena de Responsabilidad). Alternativa más cercana considerada y descartada: Command (Comando). Se descarta debido al problema central radica en encaminar y delegar una solicitud a través de una cadena de decisores o evaluadores reconfigurables, donde cada manejador decide autónomamente si procesa o transfiere la petición al siguiente nivel sin acoplar dicha lógica al controlador. Command fue descartado porque su objetivo es encapsular una acción discreta dentro de un objeto para diferirla o deshabilitar/revertir ejecuciones, y no resolver la evaluación condicional delegada entre múltiples receptores potenciales.

### Necesidad 2 — Ejecución reversible de solicitudes
Patrón de comportamiento aplicado: Command (Comando)
En esta necesidad no existen condiciones evaluadas a lo largo de una secuencia para decidir si se transfiere o resuelve una petición. Se trata de encapsular operaciones discretas ejecutadas por un actor en objetos independientes que comparten una interfaz uniforme ("execute()" / "undo()"), desacoplando quien solicita la acción de quien la ejecuta.

Para conservar todas las operaciones ejecutadas y permitir su inspección o reversión posterior (en lugar de limitar el alcance a la última acción), la solución utiliza un invocador (Invoker) que administra una estructura de pila o colección de historial (como Deque<Comando>). Cada vez que se ejecuta un comando, este se apila ("push"), permitiendo auditar la secuencia entera y deshacer las operaciones en orden inverso ("pop().undo()").

### Necesidad 3 — Notificaciones ante cambio de estado
Patrón aplicado: Observer (Observador). Se utiliza debido al problema central no reside en alterar  la lógica interna del objeto "Solicitud", sino en notificar a módulos independientes (como correo, contabilidad o auditoría) cuando ocurre una modificación de estado, sin que la "Solicitud" ni quien aplica el cambio conozcan directamente a dichas entidades. El patrón Observer establece una relación uno-a-muchos mediante un publicador central donde los observadores se suscriben y reciben notificaciones automáticas ante cada evento.

Mientras State se orienta a mutar la conducta del objeto principal y determinar qué métodos son ejecutables según su estado interno, Observer está diseñado para orquestar la reacción distribuida de entidades externas e ilimitadas que escuchan los eventos del sujeto sin acoplarse a él.

### Necesidad 4 — Reglas de transición según el estado
Patrón aplicado: State (Estado). Aunque ambos patrones comparten diagramas de clases prácticamente idénticos, en Strategy es un cliente externo el que selecciona e inyecta explícitamente el algoritmo a utilizar. En cambio, con State, la Solicitud (el Contexto) altera su comportamiento válido basándose en su estado actual e impulsa automáticamente las transiciones hacia nuevos estados a lo largo de su ciclo de vida.

El patrón encapsula las reglas de cada fase en clases concretas e independientes que implementan una interfaz común. Con esta abstracción, incorporar una nueva fase (como "EN_ESPERA_PROVEEDOR") únicamente implica crear una clase adicional que implemente el contrato de estado, evitando modificar el código existente y garantizando la extensibilidad bajo el principio Abierto/Cerrado.

### Reflexión — otros tres patrones (opcional)
Se comprende la diferencia entre los patrones State con Strategy apesar de que ambos patrones hagan casi lo mismo, pero de diferente forma. Tambien la diferencia entre Command y Chain Of Responsability, siendo lo mismo que los anteriores hacen casi lo mismo pero ciertos detalles relevan su verdadera funcion y diferencia.

## Herramientas utilizadas
- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- VS Code o IntelliJ IDEA, Git, GitHub

## Conclusiones
[Párrafo de 3-5 oraciones con los aprendizajes más relevantes de
ambas partes, incluyendo qué hizo difícil o fácil decidir entre
patrones cercanos.]
