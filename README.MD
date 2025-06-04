## TP Nro 4

### Estructura de proyecto a nivel de app


```
src/
├── events/              → Clases de eventos (arribo, salida, etc.)
│   ├── Event.java
│   ├── ArrivalEvent.java
│   └── DepartureEvent.java
├── core/                → Núcleo de la simulación
│   ├── Simulator.java
│   ├── Clock.java
│   └── EventQueue.java
├── model/               → Elementos del sistema
│   ├── Server.java
│   ├── Queue.java
│   └── Customer.java
├── main/
│   └── Main.java        → Lanza la simulación
└── utils/
    └── RandomGenerator.java → Generador de tiempos (exponencial, uniforme, etc.)
```