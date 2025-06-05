# TP Nro 4 - Simulacion de impresion

Este repositorio contiene el codigo fuente y los diagramas asociados al trabajo practico numero 4. La aplicacion esta escrita en Java y utiliza Maven como sistema de construccion.

## Estructura general

```
.
├── app/        -> Proyecto Java (Maven)
├── assets/     -> Imagenes y diagramas de modelo
├── modelado.eapx
└── modelado.ldb
```

### Proyecto Java

Dentro de `app` se espera la siguiente organizacion del codigo:

```
src/
├── events/          -> Clases de eventos (arribo, salida, etc.)
├── core/            -> Núcleo de la simulación
├── model/           -> Elementos del sistema
├── main/            -> Contendrá Main.java
└── utils/           -> Utilidades (generadores de tiempos)
```

A continuación se describen brevemente los componentes más importantes del
directorio `src`:

- **core/Simulador**: gestiona el ciclo de eventos, la cola de asistentes y
  mantiene la lista de impresoras disponibles.
- **events**: contiene los eventos `LlegadaCliente` y `FinImpresion`, que
  extienden de la clase abstracta `Event` y definen la lógica de la
  simulación.
- **model**: incluye las clases `Asistente`, `Impresora`, `Tecnico` y sus
  enumeraciones de estado, modelando a los participantes del sistema.
- **utils/Utils**: ofrece métodos para generar tiempos aleatorios (variables
  exponencial y uniforme) usados en los eventos.
- **main/Main**: punto de entrada que solicita por consola los parámetros de la
  simulación y ejecuta el `Simulador`.

## Compilacion y ejecucion

1. Instalar JDK 21 y Maven.
2. Desde la carpeta `app`, ejecutar `mvn package` para compilar el proyecto.
3. Ejecutar la aplicacion con `mvn exec:java -Dexec.mainClass=org.utn.sim.Main` o bien con `java -cp target/classes org.utn.sim.Main` despues de la compilacion. //si no les funcion el de maven pongan entre comillas el -> "-Dexec..Main"

## Diagramas

En la carpeta `assets` se encuentran los principales diagramas utilizados durante el modelado:

- `analisis.png`
- `diagrama de estado asistente.png`
- `diagrama de estado impresora.png`
- `diagrama de estado tecnico.png`
