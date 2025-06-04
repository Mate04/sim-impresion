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
├── core/            -> Núcleo de la simulacion
├── model/           -> Elementos del sistema
├── main/            -> Contendra Main.java
└── utils/           -> Utilidades (generadores de tiempos)
```

Por el momento solo esta implementada la clase `Main` a modo de ejemplo.

## Compilacion y ejecucion

1. Instalar JDK 21 y Maven.
2. Desde la carpeta `app`, ejecutar `mvn package` para compilar el proyecto.
3. Ejecutar la aplicacion con `mvn exec:java -Dexec.mainClass=org.utn.sim.Main` o bien con `java -cp target/classes org.utn.sim.Main` despues de la compilacion.

## Diagramas

En la carpeta `assets` se encuentran los principales diagramas utilizados durante el modelado:

- `analisis.png`
- `diagrama de estado asistente.png`
- `diagrama de estado impresora.png`
- `diagrama de estado tecnico.png`

