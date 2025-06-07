# <u>Diseño de TADs</u>
## <u>***Módulo e Implementa:***</u>

+ **Módulo**: Indica el nombre que le pongo al diseño que quiero crear
+ **Implementa**: Explica de qué TAD proviene el módulo que creemos
<br>

Para verificar que el **diseño es correcto** (identifica y representa al TAD que estoy analizando), uso:
## <u>***Invariante de representación:***</u>
* **Predicado** / conjunto de predicados que, al correr el programa, **no cambia** $\to$ No es afectado
* Tiene que **decir algo** sobre el TAD que **queremos evaluar**
* Entre sus parámetros, **está la instancia de un TAD implementado**
* Los predicados **nunca deberían ser falsos**
* Queremos que **se cumpla**: **{$P \land I_{rep}$} S {$Q \land I_{rep}$}** 

## <u>***Función de Abstracción***</u>
* Es una función $T_{impl} \to T$, donde:
	* $T_{impl}$ es el *tipo del TAD implementado*
	* $T$ es el *tipo del TAD a representar*
	
* Se requiere que **los observadores sean suficientes** para distinguir una instancia de otra


