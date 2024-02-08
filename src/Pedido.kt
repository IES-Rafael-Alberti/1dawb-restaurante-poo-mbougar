
/**
 * Clase que representa un pedido en un restaurante, con un número único, una lista de platos, y un estado.
 */
class Pedido() {


    var numero = ++contPedido
    var platos: MutableList<Plato> = mutableListOf()
    var estado: String = EstadoPedido.PENDIENTE.name.lowercase()

    /**
     * Agrega un plato al pedido.
     * @param plato El plato a agregar al pedido.
     */
    fun agregarPlato(plato: Plato) {

        platos.add(plato)
    }

    /**
     * Elimina un plato del pedido por su nombre.
     * @param nombrePlato El nombre del plato a eliminar del pedido.
     */
    fun eliminarPlato(nombrePlato: String) {

        platos.forEach { if (it.nombre == nombrePlato) platos.remove(it) }
    }

    /**
     * Calcula el precio total del pedido sumando los precios de todos los platos.
     * @return El precio total del pedido.
     */
    fun calcularPrecio(): Double {

        var totalPrecio = 0.0
        platos.forEach { totalPrecio += it.precio }

        return totalPrecio
    }

    /**
     * Calcula el tiempo total de preparación del pedido sumando los tiempos de preparación de todos los platos.
     * @return El tiempo total de preparación del pedido en minutos.
     */
    fun calcularTiempo(): Int {

        var totalTiempo = 0
        platos.forEach { totalTiempo += it.tiempoPreparacion }

        return totalTiempo
    }

    /**
     * Cambia el estado del pedido a "servido".
     */
    fun cerrarPedido() {

        estado = EstadoPedido.SERVIDO.name.lowercase()
    }

    /**
     * Genera una representación en cadena de los platos del pedido.
     * @return Una cadena de caracteres que representa los platos del pedido.
     */
    private fun platosToString(): String {

        var string = ""
        platos.forEachIndexed { index, plato -> string += (if (index == 0) "" else "\n") + plato.toString() }

        return string
    }

    /**
     * Genera una representación en cadena del pedido, incluyendo los platos y su estado.
     * @return Una cadena de caracteres que representa el pedido con sus platos y estado.
     */
    override fun toString(): String {
        return platosToString() + "\nEstado: $estado"
    }

    companion object {
        var contPedido: Int = 0
    }
}