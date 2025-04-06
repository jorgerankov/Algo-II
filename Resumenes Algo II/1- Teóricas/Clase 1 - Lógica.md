## Valuaciones

* Es una **función** *v* : V $\to$ {T,F} que asigna valores de verdad a variables proposicionales
* Toma como entrada una fórmula y devuelve su valor de verdad
* La notación se leería como **v(p) = T**, que significa: "La valuación de la función p es True"
* Caso contrario, es esa misma notación, pero con el símbolo tachado
* Si una función p cumple toda valuación, es una **Tautología**
* Si no cumple ninguna, es una **Contradicción**
* Si existe por lo menos una valuación T, es ***Satisfactible***
* Cuando no sucede esto último, es ***Insatisfactible***

## Lógica Trivaluada secuencial

* Se utiliza para casos que no se pueden abarcar con T o F, en esos casos se usa el ***indefinido***
* Por ejemplo, P(n) es T sii n = 2 $\to$ n=2, n=4/2 es T, 3 es F, pero **1/0 es indefinido**

##### Se ve como *secuencial* el orden en que se ven las operaciones: estrictamente de izquierda a derecha, y se notan con una L pequeña (Luego) junto a los símbolos que ya conozco (y, o, tal que)

## Sistema deductivo

* Busca probar una fórmula en particular (**conclusión**) en base a otras fórmulas (**premisas**)
* Se llama **prueba** a una secuencia de reglas lógicas aplicadas sucesivamente para llegar a una **conclusión**
## Equivalencia entre fórmulas
![[Pasted image 20250319100352.png]]

## Términos y fórmulas
![[Pasted image 20250319103307.png]]
## Cuantificadores
![[Pasted image 20250319103935.png]]

## Cuantificadores tipados
![[Pasted image 20250319104225.png]]
## Lógica de Primer orden (LPO)
![[Pasted image 20250319120104.png]]

* Es un **conjunto numerable *C* de constantes**,
* Un **conjunto *F* de símbolos de función** con, por lo menos, 1 argumento -> ***aridad > 0***
* Un **conjunto *P* de símbolos de predicado** que puede tener (o no) argumentos -> ***aridad >= 0***

#### Aridad: Núm de argumentos que toman


## Términos de primer orden
* Toda constante de un LPO y toda variable **es un L-término**
* Si tengo L-términos y f es un símbolo de función con n cantidad de argumentos, entonces f(t1,...,tn) está entre los L-términos
## Fórmulas atómicas
* Se define como todo símbolo de predicado de aridad 0 -> L-fórmula atómica
* Si tengo n elementos entre L-términos y P es un símbolo de predicado de aridad n -> P(t1,...tn) es una L-fórmula atómica
## Fórmulas de primer orden
* Toda L-fórmula atómica es una L-fórmula
* Si *O* y *V* están dentro de L-fórmulas, entonces toda "tabla de verdad" (o lógico, y lógico, tal que, sii, negación) entre ellas son L-fórmulas



## Variables libres y ligadas
![[Pasted image 20250319121012.png]]
![[Pasted image 20250319121550.png]]
* Entiendo como **FV (Free)** a una variable que **no** depende de ninguna condición, más sí aparece en una fórmula
* "" "" **BV (Bounded)** a una variable que está, por lo menos, "atada" a una condición (Universo, Existencia, función)

## Validez
![[Pasted image 20250319122324.png]]