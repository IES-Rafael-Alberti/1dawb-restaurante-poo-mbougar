
/**
 * Clase que representa un plato de un restaurante, con nombre, precio, tiempo de preparación y lista de ingredientes.
 * @param nombre El nombre del plato.
 * @param precio El precio del plato.
 * @param tiempoPreparacion El tiempo de preparación del plato en minutos.
 * @param ingredientes La lista mutable de ingredientes del plato.
 */
class Plato(nombre: String?, precio: Double, tiempoPreparacion: Int, private val ingredientes: MutableList<String>) {

    var nombre = nombre
        set(value) {
            require(!value.isNullOrBlank()) {"Error, todos los platos deben tener nombre"}
            field = value
        }

    var precio = precio
        set(value) {
            require(value > 0) {"Error, un plato no puede tener un precio negativo"}
            field = value
        }

    var tiempoPreparacion = tiempoPreparacion
        set(value) {
            require(value > 1) {"Error, el tiempo de preparación no podrá ser igual o inferior a 1"}
            field = value
        }

    init {
        require(ingredientes.isNotEmpty()) {"Error, el plato debe tener al menos 1 ingrediente."}
        require(!nombre.isNullOrBlank()) {"Error, todos los platos deben tener nombre"}
        require(precio > 0) {"Error, un plato no puede tener un precio negativo"}
        require(tiempoPreparacion > 1) {"Error, el tiempo de preparación no podrá ser igual o inferior a 1"}
    }

    /**
     * Agrega un ingrediente a la lista de ingredientes del plato.
     * @param ingrediente El ingrediente a agregar.
     * @throws Exception si el ingrediente es nulo o vacío.
     */
    fun agregarIngrediente(ingrediente: String?) {
        if (ingrediente.isNullOrBlank()) {
            throw Exception("Error, un ingrediente no puede ser vacío.")
        }
        ingredientes.add(ingrediente)
    }

    /**
     * Genera una representación modificada del plato, incluyendo la lista de ingredientes.
     * @return Una cadena de caracteres que representa el plato con la lista de ingredientes.
     */
    private fun toStringModified(): String {

        var string = ""
        ingredientes.forEachIndexed { index, ingrediente -> string += (if (index == ingredientes.size - 1) " y " else if (index == 0) "" else ", ") + ingrediente }

        return string
    }

    /**
     * Genera una representación en cadena del plato, incluyendo el nombre, tiempo de preparación, precio y lista de ingredientes.
     * @return Una cadena de caracteres que representa el plato con todos sus atributos.
     */
    override fun toString(): String {
        return "$nombre ($tiempoPreparacion min.) -> %.2f€ (${toStringModified()})".format(precio)
    }
}