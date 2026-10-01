# fitzgerald-post1-u4
Post-contenido - Patrones de Comportamiento aplicados al backend de ComprasUDES
# Necesidad 1
- Patrón de comportamiento aplicado: Chain of Responsibility (Cadena de Responsabilidad).
  Alternativa más cercana considerada y descartada: Command (Comando). Se descarta debido al problema central radica en encaminar y delegar una solicitud a través de una cadena de decisores o evaluadores reconfigurables, donde cada manejador decide autónomamente si procesa o transfiere la petición al siguiente nivel sin acoplar dicha lógica al controlador. Command fue descartado porque su objetivo es encapsular una acción discreta dentro de un objeto para diferirla o deshabilitar/revertir ejecuciones, y no resolver la evaluación condicional delegada entre múltiples receptores potenciales.

# Necesidad 2
- Patrón de comportamiento aplicado: Command (Comando)
  En esta necesidad no existen condiciones evaluadas a lo largo de una secuencia para decidir si se transfiere o resuelve una petición. Se trata de encapsular operaciones discretas ejecutadas por un actor en objetos independientes que comparten una interfaz uniforme ("execute()" / "undo()"), desacoplando quien solicita la acción de quien la ejecuta.

  Para conservar todas las operaciones ejecutadas y permitir su inspección o reversión posterior (en lugar de limitar el alcance a la última acción), la solución utiliza un invocador (Invoker) que administra una estructura de pila o colección de historial (como Deque<Comando>). Cada vez que se ejecuta un comando, este se apila ("push"), permitiendo auditar la secuencia entera y deshacer las operaciones en orden inverso ("pop().undo()").