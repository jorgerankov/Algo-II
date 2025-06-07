## <u>TAD (Tipo Abstracto de Datos)</u>
- Es un **tipo de datos** que define un **conjunto de valores** y las operaciones realizables sobre ellos
- Es **abstracto** porque no se necesita conocer los **detalles** de cómo está hecha la representación ni las operaciones 
- Describe el "qué" y no el "cómo"
- Es una forma de **modularizar** a nivel de los datos
- Ejemplos de TADs: Diccionario, Pila, Cola
## <u>Instancias</u>:
- Que pertenecen a su conjunto de valores
## <u>Observadores</u>
- Son **variables o funciones** que **describen** el estado de una instancia de un TAD
- Permite usar **todo tipo de datos** de lenguaje de especificación
- El **estado de una instancia** del TAD va a ser dado por el **estado de todos sus observadores** en un instante de tiempo

- El conjunto de observadores tiene que ser **completo**. Nos debe permitir ver **todas las características que queremos** de las instancias
- Nos debe permitir distinguir si dos instancias son $\neq$
- **$\forall$ las operaciones** se tienen que poder **describir** a partir de los observadores
## <u>Operaciones</u>
- Sirven para **crear** una nueva instancia,**calcular valores** a partir de una instancia, o **modificar** las mismas
- Indican **qué se puede hacer** con una **instancia** de un TAD
- Explicadas con nuestro lenguaje de especificación
- Se usan requiere y asegura (pre y postcondiciones)