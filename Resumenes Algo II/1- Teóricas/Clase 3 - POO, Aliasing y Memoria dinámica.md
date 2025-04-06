# <u>POO y Aliasing</u>
## <u>Programación Orientada a Objetos</u>
Es un paradigma de programación basado en objetos, donde un objeto es una _entidad_ que tiene:
- _**Estado**_, que son los atributos que describen al objeto
- _**Comportamiento**,_ que son operaciones (métodos) que el objeto puede hacer

Los objetos **modifican su estado interno** a través de sus **métodos públicos**
### <u>Conceptos para usar en la POO</u>
- ***Abstracción***: "Qué hace" más que "cómo lo hace"
- ***Encapsulamiento***: Enfoque en la necesidad del usuario en una función, ocultando el funcionamiento interno
- ***Modularización***: Divido el problema en partes más pequeñas

## <u>Clases</u>
Se define en varias etapas:
- ***Métodos públicos***: Sus "operaciones", son la **interfaz de la clase**, las funciones que actúan en el programa
- ***Atributos privados***: Su "estado", que queda oculto al usuario y puede ser modificado
- Implementar los métodos públicos usando los métodos privados, permite definir el **comportamiento de la clase**
- _**Constructor** de la clase_: Permite **inicializar** una **instancia** de la clase (un objeto)

Se puede programar con clases, pero **no modularmente**

## <u>Implementar el comportamiento de una Clase</u>
La **implementación** se da por:
- La **representación interna**: Conjunto de atributos (variables) que determina el **estado interno de la instancia**
- Un **conjunto de algoritmos** que implementan cada uno de los **métodos públicos**, llamando y modificando las variables de la representación interna

El estado interno **no es accesible** desde afuera del objeto (encapsulamiento)
Un objeto debe ser usado mediante su interfaz (los métodos públicos)


## <u>Comportamiento</u>
El **comportamiento** para las instancias se define por **métodos públicos** que **acceden** a los **atributos privados**

Los **métodos** pueden usar **métodos privdos** $\to$ métodos **"internos"** que solo son accesibles desde métodos de la clase

## <u>Constructores</u>
- Son **funciones especiales** para inicializar una **nueva instancia de una clase** 
- Se deben **definir** los valores iniciales de los **atributos privados**
- Se escriben con el **nombre de la clase**

## <u>Constructor por copia</u>
- Permite crear una **nueva instancia** de la clase que **sea una copia de otra instancia ya creada** $\to$ _que tenga los mismos valores en su representación interna_ 
- Hay que buscar que **no haya _aliasing_** $\to$ que no haya dos o más objetos apuntando a la **misma representación interna**, ya que uno modificaría al otro

#### <u>Ejemplo</u>:
```public CTD (CTD otro){```
```}``` 
me permite **copiar el CTD en "cascada"**
	
Y, para **crear una nueva instancia** que sea **copia de la otra**:
		``` {```
			```CTD c1 = new CTD(c2)```
		```}```

## <u>Aliasing</u>
- Se genera cuando **dos variables referencian al mismo valor**
- Es un problema cuando se trabaja con objetos mutables (modificables)

## <u>Igualdad de tipos</u>
- Si el tipo es **primitivo**, a == b $\to$ si "a" y "b" son **valores iguales**
- Si el tipo es un **objeto/arreglo**, a == b $\to$ si "a" y "b" **apuntan a la misma dirección de memoria**
- Si el tipo es un **objeto**, a.equals(b) $\to$ si "a" y "b" son **iguales o equivalentes**

## <u>Método equals</u>
- Permite definir **cómo se comparan dos instancias de una misma clase**
- Se define un método **equals** dentro de nuestra clase

```
@Override
public boolean equals(Object otro) {
	boolean otroEsNull = (otro == null);
	boolean claseDistinta = otro.getClass() != this.getClass();
	
	if (otroEsNull || claseDistinta) {
	return false;
	}
	
	// casting -> cambiar el tipo
	CTD otroCTD = (CTD) otro;
	
	return gastosPersona1 == otroCTD.gastosPersona1 && gastosPersona2 == otroCTD.gastosPersona2; // Si todos cumplen, son del mismo tipo -> True
}
```



# <u>Memoria Dinámica</u>
