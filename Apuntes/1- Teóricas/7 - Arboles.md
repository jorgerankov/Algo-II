# <u>***Arboles***</u>
##### Son estructuras que consisten de Nodos (elementos) y conexiones (flechas que los unen)
+ **Rama**: Es cualquier Nodo con algun padre que tenga (por lo menos) un hijo
+ **Hoja**: Son Nodos que no tienen hijos
+ **Raiz**: Es el primer Nodo, el "principio" del arbol
+ **Profundidad del arbol**: Es la cantidad de Nodos que hay entre la raiz y la hoja mas lejana

## <u>***Arboles Binarios de Busqueda***</u>
##### $\forall$ Nodo va a tener a su izquierda numeros *menores* a ese Nodo, y a su derecha *mayores*

#### Buscar elemento:
+ Si **elem = Raiz**, ya lo encontré
+ Sino, consulto si **elem > Raiz o elem < Raiz**:
	+ Si es **menor**, **bajo por la rama izquierda** y sigo consultando
	+ Si es **mayor**, **por la rama derecha**
+ Si sigo bajando, comparando, y **no encuentro a elem**, **no está en el Arbol**

#### Insertar elemento:
+ Hago el mismo recorrido que al buscar un elem
+ Cuando encuentro un *espacio disponible*, **meto el elem ahí**
+ Asigno de **padre** al **ultimo Nodo** que haya encontrado **mayor** (a la der) o **menor** (a la izq) **que el nuevo elem**
+ El nuevo elem **no debe pertenecer antes** en el Arbol, ya que **no deben haber repetidos**
#### Eliminar elemento:
+ Existen 3 casos:
	+ Que el Nodo eliminado sea una hoja (no tenga hijos)
	+ Que tenga un solo hijo
	+ Que tenga dos hijos

## <u>***Balanceo de Arboles Binarios***</u>
#### <u>Arbol completo</u>
+ Son Arboles que todos sus Nodos tienen 2 hijos, menos los del ultimo nivel (hojas), que tienen 0
+ **Problema**: Insertar / **mantenerlo completo** (mucho costo de espacio y tiempo)

#### <u>Arbol balanceado</u>
+ Busca que todas las ramas de un Arbol tengan *casi* la misma longitud
+ Que la diferencia de longitud entre sus ramas izquierda y derecha difiera en, a lo sumo, una unidad
	+ ##### Factor de balanceo
		+ $A_D - A_I$ ($A_x$ es la altura del subsarbol x), puede dar **1, 0 ó -1**
		+ Si **cumple** el Factor de balanceo, se dice que el Arbol **está balanceado en altura, o que es un AVL**


