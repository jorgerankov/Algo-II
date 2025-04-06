## Estados
Un **estado de un programa** son todos los valores de todas las variables en un punto de ejecución:
* Antes de la primer instrucción, entre 2 instrucciones, y después de ejecutar la última instrucción

La ejecución de un programa puede ser una **sucesión de estados**

## Corrección de un programa
Un programa S es correcto **respecto de una especificación** (dada por una precondición P y una post Q) si el programa siempre empieza en un estado que cumple P, **termina su ejecución** y en el estado final **se cumple Q**

Cuando S es correcto, se denota como {P} S {Q} == tripla de Hoare

Para **formalizar** estos razonamientos, se puede hacer:
**"$\alpha$ es verdadera sii {P} S {Q} es verdadera"**
Lo que nos permite automatizar la demostración con un **verificador automático**

## Precondición más débil
La **precondición más débil** de un programa S respecto a una postcondición Q es el predicado P más débil posible tal que {P}S{Q}

* Notación: **wp(S,Q)**
* Teorema: **{P}S{Q} es válido sii P-> wp(S,Q)

Si S comienza en un estado que **satisface P**, entonces **termina** y lo hace en un estado que **satisface Q** 

Basta con obtener wp(S,Q) para demostrar la validez de {P}**S**{Q}.

Entonces, mediante un conjunto de **axiomas** podemos encontrar la _wp_

## Predicados def(E) y QxE
### def(E)
Dada una expresión _E_, def(_E_) son las **condiciones necesarias** para que _E_ esté **definida**
Ejemplos:
* def(x + y) $\equiv$ def(x) $\wedge$ def(y)
* def(x/y) $\equiv$ def(x) $\wedge$ (def(y) $\wedge L$ y $\neq$ 0)

### QxE
Se reemplaza en Q todas las apariciones **libres** de la variable x por _E_

## Axiomas
#### Axioma 1: Asignación
_wp_(**x := E**,Q)  $\equiv$ def(_E_) $\land L$ QxE 

Ejemplo:
	x := x + 1
	{Q : x $\geq$ 7}

Hay que:
	wp(x := x+1, Q) $\equiv$ def(x+1) $\land L$ $Q^{x}$x+1
				 $\equiv$ True $\land L$ (x+1) $\geq$ 7
				 $\equiv$ x $\geq$ 6

#### Axioma 2:
_wp_(**skip**, Q) $\equiv$ Q

#### Axioma 3:
_wp_(**S1; S2**, Q) $\equiv$ _wp_(**S1**, _wp_(**S2**, Q))

## Propiedades

#### Monotonía
- Si Q $\to$ R entonces _wp_(S,Q) $\to$ _wp_(S,R)

#### Distributividad
- _wp_(S, Q) ∧ _wp_(S, R) $\to$ _wp_(S, Q ∧ R)
- _wp_(S, Q) ∨ _wp_(S, R) $\to$ _wp_(S, Q ∨ R)
#### "Excluded Miracle"
- _wp_(S, false) $\equiv$ false

