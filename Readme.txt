Leonardo Rafael Guerrero Ortega
Francisco Ignacio Fuentealba Rubilar

Intruducción

Este informe documenta el desarrollo de un sistema de gestión de torneos orientado a facilitar la organización 
de competiciones deportivas y de videojuegos, como fútbol, e-sports y otras disciplinas. El sistema integra una
 interfaz gráfica (paquete org.Interfaz) con la lógica del negocio (paquete org.Logica), aplicando principios de 
diseño orientado a objetos, patrones de diseño reconocidos y principios SOLID.

El objetivo es presentar una visión integral de la arquitectura, funcionalidades clave, decisiones técnicas y posibles
 líneas de mejora para asegurar su evolución.

Objetivos

El sistema se desarrolló con los siguientes objetivos principales:

Definición de torneos: Permitir a los organizadores configurar torneos con características como nombre, disciplina,
fechas y formato (eliminación simple o liga).
Gestión de participantes: Facilitar la inscripción de jugadores individuales o equipos, almacenando información básica
 como nombres y datos de contacto.
Generación de estructuras: Crear automáticamente calendarios de enfrentamientos o brackets iniciales según el formato
 del torneo y los participantes inscritos.
Seguimiento de resultados: Registrar los resultados de los enfrentamientos, actualizando automáticamente el estado
 del torneo, incluyendo posiciones, avances en el bracket o tablas de clasificación.
Visualización del estado: Proporcionar a los usuarios una interfaz para visualizar el estado actual del torneo, los próximos
 encuentros y estadísticas generales.


Metodología

El desarrollo del sistema se llevó a cabo utilizando Java como lenguaje principal, siguiendo un enfoque orientado
 a objetos y aplicando patrones de diseño para garantizar modularidad, escalabilidad y mantenibilidad. La arquitectura
 se dividió en dos paquetes principales:

Paquete org.Interfaz: Contiene las clases responsables de la interfaz gráfica, desarrolladas con Java Swing, 
para permitir la interacción del usuario con el sistema.
Paquete org.Logica: Implementa la lógica del negocio, incluyendo la gestión de torneos, participantes, enfrentamientos y eventos.
Se emplearon patrones de diseño como Singleton, Factory Method, Observer y Template Method para estructurar el código de
 manera eficiente. Además, se siguieron los principios SOLID para garantizar un diseño robusto y extensible. Las pruebas 
se realizaron mediante clases principales (Main, MainEliminacionSimple, MainLiga) que simulan torneos de ejemplo.


Singleton: su clase es GestorDeInstanciaCreadora, este asegura una única instancia para la gestión de creadores de torneos.

Factory Method: entre sus clases están Creador, EliminacionSimpleCreador, LigaCreador, esto facilita la extensión para nuevos
 formatos de torneo desacoplando la creación de instancias.

Observer: con clases SujetoTorneo, ObservadorTorneo, RegistradorTorneo, gestiona notificaciones ante eventos del torneo, permitiendo
 la reacción de componentes externos.

Template Method representando por la clase TorneoAbstracto, definiendo un flujo base para la gestión de torneos, delegando en
 subclases las operaciones específicas.

Desarrollo

Estructura del Sistema
El sistema se organiza en dos capas principales:

En la capa de la interfaz las clases principales: Incluyen Boton, BotonPanel, Interfaz, PanelBotones, PanelTorneo y Ventana.
Funcionalidad: Proporciona una interfaz gráfica basada en Java Swing que permite al usuario interactuar con el sistema. La clase 
Ventana actúa como la ventana principal, gestionando la navegación entre paneles (PanelBotones, PanelTorneo) mediante un CardLayout.
 Cada panel contiene botones personalizados (Boton) que disparan acciones específicas, como la creación de torneos, la inscripción de
 participantes o la visualización del estado del torneo.
Diseño: La interfaz es modular, con paneles que se alternan dinámicamente según las necesidades del usuario. Los botones son configurables
 y reutilizables, siguiendo un diseño orientado a componentes.

Y la capa lógica que como su nombre lo indica maneja la lógica del sistema, incluyendo la creación de torneos, la gestión
 de participantes, la generación de enfrentamientos y calendarios, y el seguimiento de resultados. Soporta dos formatos principales: eliminación simple
 (donde los perdedores son eliminados) y liga (todos contra todos con tablas de clasificación), y cuyas clases clave son: Gestión
 de torneos: Torneo (interfaz), TorneoAbstracto (clase abstracta), TorneoEliminacionSimple, TorneoLiga.

Participantes: Participante (clase abstracta), IndividuoParticipante, Equipo. Enfrentamientos: Enfrentamiento, Calendario.
Creadores: Creador (clase abstracta), EliminacionSimpleCreador, LigaCreador, GestorDeInstanciaCreadora (Singleton).
 Patrón Observer: SujetoTorneo (interfaz), ObservadorTorneo (interfaz), RegistradorTorneo.
Excepciones y enums: TorneoException, Disciplina, FormatoTorneo, TipoEvento.


Principios SOLID Aplicados

Cada clase tiene una responsabilidad única. Por ejemplo:
Enfrentamiento gestiona la simulación y resultados de un encuentro. Calendario maneja la programación de enfrentamientos.
 RegistradorTorneo se encarga de registrar eventos en consola. La interfaz gráfica (Ventana, PanelTorneo) se centra en la 
presentación, mientras que TorneoAbstracto y sus derivados manejan la lógica del torneo.
Beneficio: Facilita el mantenimiento, ya que los cambios en una funcionalidad afectan solo a la clase correspondiente.

El sistema está abierto a la extensión (por ejemplo, añadiendo nuevos formatos de torneo mediante nuevas clases que extiendan 
Creador o TorneoAbstracto) pero cerrado a la modificación (no se necesita alterar el código existente para incorporar nuevos formatos).
Ejemplo: El uso del patrón Factory Method permite añadir nuevos tipos de torneos sin modificar GestorDeInstanciaCreadora.
Beneficio: Mejora la escalabilidad y reduce el riesgo de introducir errores al añadir funcionalidades.

Las clases derivadas (TorneoEliminacionSimple, TorneoLiga) pueden sustituir a su clase base (TorneoAbstracto) sin alterar el 
comportamiento esperado. Por ejemplo, cualquier implementación de Torneo puede usarse con GestorDeInstanciaCreadora.
Beneficio: Garantiza que las nuevas implementaciones de torneos sean compatibles con el sistema existente.

Las interfaces (Torneo, SujetoTorneo, ObservadorTorneo) son específicas y no obligan a las clases a implementar métodos innecesarios.
 Por ejemplo, SujetoTorneo solo define métodos relacionados con la notificación de eventos.
Beneficio: Reduce la complejidad y mejora la cohesión de las clases.

Las clases de alto nivel (como MainEliminacionSimple o MainLiga) dependen de abstracciones (Torneo, Creador) en lugar de implementaciones
 concretas. Esto se logra mediante el uso de interfaces y clases abstractas.
Ejemplo: GestorDeInstanciaCreadora interactúa con Creador en lugar de clases concretas como EliminacionSimpleCreador.
Beneficio: Facilita la sustitución de implementaciones y mejora la flexibilidad.


Análisis de la Solución

Cumplimiento de los Objetivos

Definición de torneos:

Cumplido: La interfaz gráfica permite configurar torneos mediante PanelTorneo, y la lógica (Creador, TorneoAbstracto) valida y
 almacena parámetros como nombre, disciplina y número máximo de participantes. El enum Disciplina asegura que solo se usen disciplinas válidas.
Implementación: TorneoEliminacionSimple y TorneoLiga permiten definir torneos con formatos específicos.

Gestión de participantes:
Cumplido: Las clases IndividuoParticipante y Equipo manejan la inscripción de participantes, con validaciones como edad (
18-100 años) y contacto. La interfaz gráfica incluye formularios para agregar participantes.
Implementación: TorneoAbstracto proporciona métodos para agregar y eliminar participantes, con notificaciones a observadores.

Generación de estructuras:
Cumplido: El sistema genera brackets para torneos de eliminación simple (TorneoEliminacionSimple) y calendarios
 para ambos formatos mediante Calendario. Los enfrentamientos se crean automáticamente con generarEnfrentamientos.
Implementación: Enfrentamiento simula partidos con puntajes basados en la disciplina, y Calendario asigna fechas basadas en índices.

Seguimiento de resultados:
Cumplido: Los resultados de los enfrentamientos se actualizan automáticamente (Enfrentamiento.iniciarEncuentro), y 
el patrón Observer notifica a los observadores (RegistradorTorneo) sobre cambios en los resultados.
Implementación: TorneoEliminacionSimple avanza rondas y determina ganadores, mientras que TorneoLiga actualiza tablas de clasificación.

Visualización del estado:
Parcialmente cumplido: La lógica permite visualizar el estado del torneo (verEstado, mostrarBracket, verCalendario) en consola,
 y la interfaz gráfica muestra información básica. Sin embargo, la visualización de estadísticas generales (como tablas 
de clasificación detalladas) está limitada en la interfaz.

Implementación: PanelTorneo muestra información del torneo, pero la integración con estadísticas completas requiere mejoras.


Limitaciones y Autocrítica

Falta de Persistencia:

El sistema no implementa almacenamiento persistente de datos (por ejemplo, en una base de datos o archivos). Esto limita su uso en 
escenarios reales donde los datos deben guardarse entre sesiones. Sería una mejora incorporar un sistema de persistencia para almacenar
 torneos, participantes y resultados.

Interfaz Gráfica Limitada:
La interfaz gráfica (org.Interfaz) es funcional pero básica. No muestra tablas de clasificación detalladas ni estadísticas avanzadas, 
lo que reduce la experiencia del usuario. Una opción para mejorar la interfaz sería con componentes visuales más ricos, como tablas dinámicas y gráficos.

Simulación Simplista:
La simulación de enfrentamientos en Enfrentamiento es básica, basada en puntajes aleatorios. No considera reglas específicas 
de cada disciplina (por ejemplo, sets en tenis o rondas en Valorant). Sería bueno Implementar simulaciones más realistas que respeten
 las reglas de cada disciplina.

Soporte Limitado de Formatos:
Solo se implementan eliminación simple y liga. Formatos como eliminación doble o torneos por grupos no están soportados. La idea sería 
añadir más formatos mediante nuevas clases que extiendan TorneoAbstracto o que dependan de más abstracciones para que los códigos no estén tan cargados

Falta de consistencia y observer algo incompleto:
Si bien el observer está bien implementado, solo se limita a avisar sobre cambios, por lo que un uso real solo sería para depurar,
por lo que una mejora sería ajustar el observer para que maneje correctamente los datos y sea más fácil comunicarle actualizaciones 
a un suscriptor o más fácil pasar datos de un sujeto 

Gestión de Errores en la Interfaz:
Los errores (TorneoException) se manejan principalmente en consola, con retroalimentación limitada en la interfaz gráfica.
Mejora futura: Mostrar mensajes de error en la interfaz mediante diálogos o notificaciones.

Implementaciones muy complicadas:
Códigos como torneo eliminación simple y enfrentamiento son innecesariamente complicados. Sería mejor los métodos entre
 los actores, dejándole menos trabajo a una sola clase

Conclusión

El sistema de gestión de torneos cumple con la mayoría de los objetivos establecidos, proporcionando una solución robusta 
para la creación, gestión y seguimiento de torneos deportivos y de juegos. La implementación utiliza patrones de diseño 
como Singleton, Factory Method, Observer y Template Method para garantizar modularidad y escalabilidad, y aplica los
 principios SOLID para mantener un diseño limpio y mantenible. La separación entre la lógica (org.Logica) y la interfaz (org.Interfaz)
 permite una clara división de responsabilidades, facilitando futuras mejoras.

Sin embargo, la solución presenta limitaciones, como la falta de persistencia, una interfaz gráfica básica y simulaciones simplistas.
 Estas áreas ofrecen oportunidades para mejoras futuras, como la integración con bases de datos, una interfaz más rica y simulaciones 
más realistas. A pesar de estas limitaciones, el sistema es un punto de partida sólido que cumple con los requisitos
 básicos y puede evolucionar para satisfacer necesidades más complejas en el futuro.






