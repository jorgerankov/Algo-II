## <u>Diseño de TADs</u>
+ Estructura de datos y serie de algoritmos
+ Indica **cómo se representa y codifica** una posible implementación del TAD
* Se elige una **estructura** de representación con **tipos de datos**
* Hay que **escribir algoritmos** para $\forall$ las operaciones que deben **respetar la especificación del TAD**

## <u>Ocultamiento - Abstracción - Encapsualmiento</u>
* Se puede **cambiar** la implementación **sin afectar su uso**
* Ayuda a **modularizar** y facilita la **comprensión**
* Favorece el **reuso**
* Facilidad de **entender y programar los módulos**
* Sistema **más resistente** a los cambios

## <u>Invariante de Representación</u>
#### Predicado que nos indica qué conjuntos de valores son instancias válidas de la implementación

+ Se tiene que cumplir **siempre** al *entrar y salir* de *cada proc*
+ Se denota como **InvRep**
+ Permite que la estructura de representación **no se rompa**
+ p/ cualquier proc se debe **verificar la tripla de Hoare**:
	+ *{InvRep(p)} procx(p, ...) {InvRep(p)}*

## <u>Correctitud</u>
+ Si la operación se puede *invocar en el estado abstracto*, también **es ejecutable** (inicia y termina) en el *estado concreto* (de implementación)
## <u>Función de Abstracción</u>
+ Indica (dada una instancia de implementación) **a qué instancia del TAD corresponde** $\to$ de qué instancia del TAD *es su "abstracción"*
+ **predAbs**: toma como parámetro una instancia del módulo y del TAD $\to$ *evalúa si son iguales*
+ predAbs nos permite *conectar* con el TAD
