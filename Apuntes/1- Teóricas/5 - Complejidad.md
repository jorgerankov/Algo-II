# <u>Análisis de Complejidad de Algoritmos</u>
+ #### Empírica o experimental
	Mide tiempo de ejecución para un input y computadora específicos
+ #### Teórica
	Medida del comportamiento de un algoritmo


# <u>Análisis teórico</u>
+ Se puede hacer hasta antes de codear
+ Vale para todo momento del problema
+ No depende del lenguaje de programación
+ No depende de la máquina donde se corra
+ No depende del conocimiento del programador

#### Se utiliza mediante un modelo de cómputo que trabaja bajo:
+ **Medida del tiempo**: Número de pasos que corren en la máquina "ideal" para determinado input
+ **Medida del espacio**: Número de posiciones de memoria en esa máquina que se usan para determinado input
<br>
1) #### <u>Operaciones Elementales (OE):</u>
	+ Operaciones que el procesador hace en tiempo acotado por una constante (no depende el tamaño del input)
	+ **Ejemplo**: operaciones aritméticas, comparaciones lógicas
	+ **t(I)**: Función que cuenta el número de operaciones elementales requeridas por el algoritmo para cumplir I
	+ **El tiempo de una OE es 1** (por definición)
	+ El tiempo de ejecución de una secuencia de instrucciones se calcula **sumando los tiempos de cada una de ellas**
		<br>
2) #### <u>Tiempos de Ejecución:</u>
	+ **if C then S1 else S2 =** T (C) + max{T(S1), T(S2)}
	+ **while C do S =**  = T(C) + (n-iteraciones) ∗ (T(S) + T(C))
	+ **Llamada a funcion F(P1, P2,..., Pn) =** 1 + T(P1) + T(P2) + ... + T(Pn) + T(F)<br><br>
	
3) #### <u>Tamaño de la entrada:</u>
	+ Se trabaja de forma abstracta y no en función de cada input
	+ **T(n): Complejidad temporal** para una entrada de tamaño n
	+ **S(n): Complejidad espacial** para una entrada de tamaño n
	<br>
	
4) #### <u>Casos:</u>
	* Instancias con el *mismo tamaño* pueden hacer que el algoritmo *se comporte de distintas formas*, y tomar distinto tiempo, o requerir distinta cantidad de memoria:
	+ **Caso peor:** Implica el *mayor* tiempo de ejecución (entre los inputs de tamaño n), y da garantía sobre cómo funciona el algoritmo
	+ **Caso mejor:** Implica el *menor* tiempo de ejecución (entre los inputs de tamaño n)
	+ **Caso medio:** Es el *tiempo “promedio” de ejecución*, el tiempo “esperado” sobre instancias “típicas” de un programa
	<br>
5) #### <u>Análisis Asintótico:</u>
	+ Calcula el **orden de magnitud** que tiene el tiempo de ejecución de cada algoritmo
	+ Ve el comportamiento para valores de entrada *suficientemente grandes*
	+ Cuando el tamaño de los datos es grande, se observa que los *costos* de los diferentes algoritmos *pueden variar de manera significativa*
	<br>
	
6) #### <u>Tipos de mediciones (Cotas Asintóticas):</u>
	+ ***Big O <u>(Cota Superior):</u>*** 
		+ Expresa el límite del T de ejecución de un algoritmo
		+ **f $\in$ O(g)** expresa que *la función f no crece más que g* $\to$ g se llama *cota superior de f*
		* <u>Funciones de Complejidad Temporal</u>:
			* **O(1) Complejidad constante:** No depende de los datos de entrada
			* **O(log n) Complejidad logarítimica:** Usado en  algoritmos con iteración o recursión
			* **O(n) Complejidad lineal:** Usado en bucles simples cuando las operaciones internas son constantes
			<br>
	+ ***$\Omega$ <u>(Cota Inferior):</u>***
		+ Expresa el límite (cota inferior) del T de ejecución de un algoritmo
		+ **f $\in \Omega$(g)** expresa que *la función f crece al menos como g* $\to$ g se llama *cota inferior de f*  
		+ Siempre existe una cota inferior trivial $\to$ La primer cota inferior sería la que escribamos luego de leer los datos
		<br>
	+ ***$\Theta$ <u>(Orden Exacto):</u>*** 
		+ *Crece* asintóticamente *de la misma forma*: $\Theta$(f) = O(f) $\cap$ $\Omega(f)$
		+ **t $\in$ $\Theta$(f)** expresa que *f es igual que g* $\to$ t está acotada por f *tanto superior como inferiormente*


