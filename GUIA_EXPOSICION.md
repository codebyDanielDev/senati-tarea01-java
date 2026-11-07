# Tarea 01: guía para exponer

## Cómo ejecutar

```bash
javac Caso01_PasajeAereo.java
java Caso01_PasajeAereo
```

Todos los casos tienen la misma forma: **entrada → proceso → salida**.

Dos líneas se repiten en todos:

- `Scanner lector = new Scanner(System.in);` lee lo que se escribe en el teclado.
- `Locale.setDefault(Locale.US);` hace que los decimales usen punto (`24.60`) en cualquier computadora.

`return;` dentro de `main` termina el programa. Se usa cuando un dato inválido obliga a "mostrar y terminar".

## Parte A: condicionales

### Caso 1: Pasaje aéreo
**Prueba:** `2 E 30 45 20` → Total S/ 215.90

| Estructura | Dónde | Por qué |
|---|---|---|
| `switch` | Destino | Es un valor exacto: 1, 2, 3 o 4. `default` atiende el destino inválido. |
| `if - else if - else` | Clase | Dos valores válidos y un inválido. Comparo texto con `equals`. |
| `else if` | Edad y anticipación | Son rangos, y `switch` no sirve para rangos. |
| `if` | Exceso de equipaje | Es una sola acción opcional. |

**Orden:** tarifa base → clase → edad → anticipación. Los descuentos se multiplican uno sobre otro. Un 15 % de descuento es multiplicar por 0.85, y un 8 % es multiplicar por 0.92. El equipaje y la tasa se suman al final porque no tienen descuento.

**Pregunta probable:** ¿por qué en edad no hay `else`? Porque las demás edades pagan el 100 % y la tarifa no cambia.

### Caso 2: Taxi por aplicativo
**Prueba:** `8 0 23 S 2` → S/ 36.08

| Estructura | Dónde | Por qué |
|---|---|---|
| `switch` | Demanda | Valor exacto: 1, 2 o 3. |
| `if-else` | Distancia | Siempre pasa una de dos cosas: hasta 5 km o más de 5 km. |
| `if` | Espera, recargo, aeropuerto | Acciones opcionales. |
| `\|\|` | Recargo nocturno | La hora es nocturna si es `>= 22` **o** `< 5`. Con `&&` nunca se cumpliría. |

**Orden:** subtotal → recargo nocturno → demanda → aeropuerto → tarifa mínima. El aeropuerto va después de la demanda porque esos S/ 8 no se multiplican.

**Cálculo de la prueba:** 4.50 + 9 + 4.50 = 18; × 1.2 = 21.60; × 1.3 = 28.08; + 8 = 36.08.

### Caso 3: Control de calidad
**Prueba:** `1000 100 4 0 15 S` → Lote ACEPTADO CON DESCUENTO, S/ 13500.00

| Estructura | Dónde | Por qué |
|---|---|---|
| `if` con `return` | Muestra y críticos | Si se cumplen, el programa termina ahí. |
| `if-else` | Máximo aceptable | Certificado 3 %, no certificado 2 %. |
| `else if` | Decisión | Tres rangos del porcentaje. |

- `Math.ceil(lote / 10.0)` redondea hacia arriba. Uso `10.0` porque `lote / 10` sería división entera y perdería el decimal.
- `menores * 100.0 / revisadas` lleva `100.0` por la misma razón.
- `%%` en `printf` imprime el signo `%`.

### Caso 4: Tarjeta de crédito
**Prueba:** `800 20 15 G` → Nuevo saldo S/ 813.54, En mora

| Estructura | Dónde | Por qué |
|---|---|---|
| `else if` | Tarjeta | Dos valores válidos y un inválido. |
| `if` con `return` | Deuda cancelada | Termina el programa. |
| `if` seguidos | Pago mínimo | Se aplican reglas en orden: 5 %, luego mínimo S/ 50, luego deuda menor a S/ 50. |
| `if` anidado | Mora | El tope solo importa si ya hay mora: la segunda decisión depende de la primera. |

**Regla de la mora:** `saldo × 0.001 × días`, con un máximo de `saldo × 0.10`.

**Dato añadido:** el enunciado no dice qué hacer con otra tarjeta, así que el programa muestra "Tipo de tarjeta no válido".

### Caso 5: Seguro de salud
**Prueba:** `P E 800` → Aseguradora S/ 675.00, paciente S/ 125.00

| Estructura | Dónde | Por qué |
|---|---|---|
| `switch` (String) | Plan | Valor exacto. Guarda los 3 porcentajes y el tope. |
| `switch` (String) | Atención | Valor exacto. Elige el porcentaje y el deducible. |
| `if` anidado | Pago | El tope solo se revisa si la aseguradora paga algo. |

**Cálculo de la prueba:** (800 − 50) × 90 / 100 = 675; el paciente paga 800 − 675 = 125.

**Pregunta probable:** ¿por qué se valida primero el plan? Lo pide el enunciado, y por eso lo leo y valido antes de pedir la atención.

## Parte B: repetitivas

### Caso 6: Venta de entradas
**Prueba:** `G 5 V 2 G 4 V 3 G 3 X 2 G 9 F`

- **`while (true)` con `break`:** no sé cuántas compras habrá; termina con el valor de salida `F`.
- **`continue`:** rechaza la compra y pasa a la siguiente sin ejecutar el resto de la vuelta.
- **Orden de validación:** tipo → cantidad → disponibles, como pide el enunciado.
- **Detalle:** la cantidad se lee antes de validar el tipo porque siempre se ingresa, excepto con `F`.
- **Ocupación:** vendidas × 100.0 / 16.

### Caso 7: Depreciación
**Prueba:** `10000` → 9 años, S/ 9000.00

- **`do-while`:** no sé cuántos años habrá y siempre se deprecia al menos un año.
- **Último año:** si restar la depreciación deja el valor por debajo del residual, la depreciación pasa a ser `valorLibros - valorResidual` y el valor queda exacto en el residual. Así el bucle termina.
- **Decimales:** se trabaja con `double` completo. Solo `printf("%.2f")` redondea al mostrar.

### Caso 8: Notas de un aula
**Prueba:** `5`, luego `Ana 15 14 16`, `Luis 8 10 9`, `Marta 20 18 19`, `Pedro 12 25 10`, `Rosa 11 11 11`

- **`for` anidado:** sé cuántas vueltas son: N estudiantes y 3 notas. El enunciado lo exige.
- **`boolean notasValidas`:** empieza en `true` y pasa a `false` si una nota está fuera de rango.
- **Las 3 notas se leen siempre:** si cortara antes, la nota siguiente se tomaría como el nombre del próximo estudiante.
- **`continue`:** salta al siguiente estudiante sin contarlo.
- **Empate:** uso `>` y no `>=` para que se quede el primero.
- **`mejorPromedio` empieza en −1:** así el primer promedio válido siempre lo supera, incluso si es 0.

### Caso 9: Llamadas
**Prueba:** `L 3000 L 1200 N 150 I 61 F` → Total S/ 3.60

- **`while (true)`:** termina con `F` o cuando se supera el límite de S/ 50.
- **`switch` dentro del bucle:** elige el nombre y la tarifa según el tipo.
- **`continue` en `default`:** salta a la siguiente llamada. Dentro de un `switch`, `break` solo saldría del `switch`; `continue` actúa sobre el bucle.
- **Último `break`:** está fuera del `switch`, por eso sí termina el `while`.
- **Minutos:** `Math.ceil(segundos / 60.0)`, para que 61 segundos sean 2 minutos.
- **Minutos gratis:** `minutosGratisUsados` se acumula entre llamadas; solo se cobra lo que pasa de 60.

### Caso 10: Carga de un camión
**Prueba:** `80 50 0`

- **`while (true)`:** termina con peso `0` o cuando el camión llega a 950 kg.
- **`continue`:** rechaza el paquete y sigue con el siguiente.
- **`masPesado`:** empieza en 0 y se actualiza solo con paquetes cargados.
- **`cargados`:** es el número N que se muestra; los rechazados no lo aumentan.

## Preguntas de teoría

**¿Qué es la programación estructurada?** Resolver un problema combinando solo secuencia, selección y repetición.

**¿`if` o `switch`?** `switch` compara valores exactos (`int`, `char`, `String`). Para rangos se usa `else if`.

**¿Qué pasa si falta `break` en un `switch`?** El programa sigue ejecutando los casos siguientes.

**¿Para qué sirve `default`?** Atiende los valores no previstos.

**¿`while`, `do-while` o `for`?**

| Estructura | Mínimo de vueltas | Cuándo |
|---|---|---|
| `for` | 0 | Sé cuántas vueltas hacer |
| `while` | 0 | No sé cuántas vueltas habrá |
| `do-while` | 1 | Debe ejecutarse al menos una vez |

**¿`break` o `continue`?** `break` termina el bucle (o el `case`). `continue` salta el resto de la vuelta y pasa a la siguiente.

**¿Qué necesita todo bucle?** Inicio, condición y actualización. Sin actualización no termina.

**¿Por qué `7 / 2` da 3?** Porque `int / int` es división entera. Con `7 / 2.0` da 3.5.

**¿Qué hace `(int) 3.99`?** Da 3: trunca, no redondea.

**¿Cómo se comparan los String?** Con `equals()`, no con `==`.

**¿`&&` o `||`?** `&&` exige que se cumplan ambas; `||`, al menos una. Ambos usan cortocircuito: no evalúan lo que no hace falta.

**¿`i++` o `++i`?** `i++` usa el valor y luego incrementa. `++i` incrementa y luego usa.

**¿`while (true)` es un bucle infinito?** Solo si nada lo detiene. Aquí el `break` lo termina al leer el valor de salida.
