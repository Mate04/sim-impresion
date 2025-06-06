# Simulación de Impresión - Trabajo Práctico 4

Este repositorio contiene la implementación de un pequeño simulador de un centro de impresión. El proyecto se divide en dos módulos principales:

- **app/** – Aplicación Java (Spring Boot) que expone la lógica de simulación.
- **app-frontend/** – Aplicación React que consume la API del simulador.
- **assets/** – Diagramas utilizados durante el modelado.
- **modelado.eapx** – Archivo del modelo para Enterprise Architect.

## Requisitos

- JDK **21** y Maven
- Node.js y npm (para la interfaz web)

## Compilación y ejecución

### Backend Java

```bash
cd app
mvn package           # descarga dependencias y compila el proyecto
mvn spring-boot:run   # levanta la API REST en http://localhost:8080
```

También es posible ejecutar la versión por consola con:

```bash
mvn exec:java -Dexec.mainClass=org.utn.sim.Main
```

### Frontend React

```bash
cd app-frontend
npm install
npm run dev           # servidor en modo desarrollo
```

La interfaz suele estar disponible en `http://localhost:5173` por defecto.

Para generar la versión de producción:

```bash
npm run build
npm run preview       # revisión local de la build
```

## Estructura del código

Dentro de `app/src/main/java/org/utn/sim` encontrarás los paquetes:

- **core** – Implementación del motor de eventos de la simulación.
- **events** – Eventos como `LlegadaCliente` y `FinImpresion`.
- **model** – Clases que representan asistentes, impresoras y técnico.
- **utils** – Generadores de tiempos aleatorios y utilidades varias.
- **Main** – Versión de consola del simulador.

---

## Diagramas

En `assets/` se incluyen los diagramas que ayudaron a modelar el problema:

- `analisis.png`
- `diagrama de estado asistente.png`
- `diagrama de estado impresora.png`
- `diagrama de estado tecnico.png`

Cada imagen ilustra los estados y transiciones de los actores principales.



