# <u>***Heaps***</u>
#### Es un Arbol binario que:
1) Está **perfectamente balanceado**
2) La clave de cada Nodo debe ser mayor o igual a la de sus hijos
	+ No se distingue entre hijo izq y der, solo que sean menores que el padre
3) Es **izquierdista** = Se va completando de izquierda a derecha

#### Pueden ser:
+ ***Max*** **Heaps**: Tiene en la raiz al Nodo mayor
+ ***Min*** **Heaps**: Tiene en la raiz al Nodo menor

#### Eliminar Elemento (Desencolar):
1) **Reemplazo** la Raiz con el ultimo Nodo (la hoja que está más a la derecha)
2) **Elimino** el ultimo Nodo y lo pongo en la Raiz
3) **Reacomodo** el Arbol con la nueva Raiz para que cumpla la regla de los heaps
	+ Evaluo los 2 hijos de la Raiz y me quedo con el mas grande
	+ Evaluo el hijo mayor con la Raiz y, si hijo > Raiz, reemplazo la Raiz
	+ Repito proceso hasta que todos los Nodos tengan hijos mejores al Padre

#### Insertar Elemento (Encolar)
1) **Agrego** el elemento al fondo
2) Si el arbol está lleno, lo agrego como un Nodo izquierdo del Nodo sin hijos de más a la izquierda
3) Lo **comparo con su padre** para "corregir" su posición
	+ En el peor de los casos, corrijo hasta la altura del arbol (el Nodo es mas grande que la Raiz) = O(*log n*)

<br><br>

## <u>Complejidades de Estructuras</u>

|                    | Pertenece  |  Insertar  |   Borrar   |   Mínimo   |   Máximo   |
| :----------------: | :--------: | :--------: | :--------: | :--------: | :--------: |
| **Lista Ordenada** | O(*log n*) | O(*log n*) |   O(*n*)   |   O(*1*)   |   O(*1*)   |
|      **ABB**       |   O(*n*)   |   O(*n*)   |   O(*n*)   |   O(*n*)   |   O(*n*)   |
|      **AVL**       | O(*log n*) | O(*log n*) | O(*log n*) | O(*log n*) | O(*log n*) |
|      **Heap**      |   O(*n*)   | O(*log n*) |   O(*n*)   |   O(*1*)   | O(*log n*) |