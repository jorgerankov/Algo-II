## <u>Especificación</u>
* Escrita en lenguaje formal
* Describe las propiedades de los datos de entrada y las propiedades de la solución
* Contrato que define qué se debe resolver y de qué manera
* Incluye parámetros (datos de entrada)
* Sirve para testing, verificación de corrección

## <u>Contratos</u>
* La especificación es un contrato entre el programador de una función y el usuario de la misma
* Asegura qué cosas se pueden hacer, qué cosas no y qué debería suceder dentro de la función
* Compuesto por un **Encabezado**, una **Precondicón (requiere)**(Condición que se da por cierta para que la función corra), y una **Postcondición (asegura)** (Condición que debe ser cumplida por el programador sii el usuario cumple la precondición)
## <u>Algoritmo</u>
* Descripción de la solución escrita para humanos (yo)

## <u>Programa</u>
* Solución descrita en lenguaje de programación (para ser leído por la compu)

## <u>Tipos de datos</u>
* Conjunto de valores salidos de una serie de operaciones que involucran a esos valores
* Puede ser variable de tipo T (x,y,z), constante de tipo T (-1,1,"a") o funciones aplicadas a otros términos del tipo T u otro tipo

## <u>Predicados auxiliares</u>
* Expresiones que se usan dentro de las especificaciones como **reeplazos sintácticos**
* No se permiten definiciones recursivas

## <u>Expresiones condicionales</u>
* Función que elige entre 2 elementos del mismo tipo según una fórmula lógica, llamada **guarda**
* Si la guarda es verdadera, elige el primero. Sino, el segundo

## <u>Sobre-especificación</u>
* Postcondición más restrictiva
* Limita posibles algoritmos que resuelven el problema porque impone más condiciones para el output / amplía los inputs

## <u>Sub-especificación</u>
* Precondición más restrictiva / Postcondición más débil
* Deja afuera inputs o ignora condiciones necesarias para el output
* Permite soluciones no deseadas

