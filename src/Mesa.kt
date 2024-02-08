
/**
 * Clase que representa una mesa en un restaurante, con un número único y una capacidad máxima de comensales.
 * @param numero El número único de la mesa.
 * @param capacidad La capacidad máxima de comensales que puede albergar la mesa.
 */
class Mesa(val numero: Int, val capacidad: Int) {

    init {
        require(capacidad in 1..6) {"Una mesa no puede tener menos de 1 comensal o más de 6 comensales"}
    }
    var estado: String = EstadoMesa.LIBRE.name.lowercase()
    val pedidos: MutableList<Pedido> = mutableListOf()

    /**
     * Ocupa la mesa cambiando su estado a "ocupada".
     */
    fun ocuparMesa() {

        if (estado == EstadoMesa.LIBRE.name.lowercase()) {
            estado = EstadoMesa.OCUPADA.name.lowercase()
        }
    }

    /**
     * Ocupa la mesa con una reserva cambiando su estado a "ocupada".
     */
    fun ocuparReserva() {

        if (estado == EstadoMesa.RESERVADA.name.lowercase()) {
            estado = EstadoMesa.OCUPADA.name.lowercase()
        }
    }

    /**
     * Cierra un pedido asociado a la mesa por su número.
     * @param numPedido El número del pedido a cerrar.
     */
    fun cerrarPedido(numPedido: Int) {

        val pedido = pedidos.find { it.numero == numPedido }
        pedido?.cerrarPedido()
    }

    /**
     * Libera la mesa cambiando su estado a "libre".
     */
    fun liberarMesa() {

        estado = EstadoMesa.LIBRE.name.lowercase()
    }

    /**
     * Agrega un pedido a la lista de pedidos asociados a la mesa.
     * @param pedido El pedido a agregar.
     */
    fun agregarPedido(pedido: Pedido) {

        pedidos.add(pedido)
    }

    /**
     * Genera una representación en cadena de los pedidos asociados a la mesa.
     * @return Una cadena de caracteres que representa los pedidos asociados a la mesa.
     */
    fun pedidosToString(): String {

        var string = ""
        pedidos.forEachIndexed { index, pedido -> string += (if (index == 0) "" else "\n") + pedido.toString() }

        return string
    }

    /**
     * Genera una representación en cadena de la mesa, incluyendo su número, estado y los pedidos asociados.
     * @return Una cadena de caracteres que representa la mesa con su estado y pedidos asociados.
     */
    override fun toString(): String {

        return "Mesa $numero: $estado\n" + pedidosToString()
    }
}