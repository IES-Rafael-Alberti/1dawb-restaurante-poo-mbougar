
/**
 * Clase que representa el sistema de gestión de un restaurante, que incluye métodos para realizar diversas operaciones
 * como realizar un pedido, cerrar un pedido, cerrar una mesa, buscar platos, contar platos y buscar el plato más pedido.
 * @param mesas La lista de mesas del restaurante.
 */
class SistemaGestionRestaurante(private val mesas: List<Mesa>) {

    /**
     * Realiza un pedido en una mesa específica.
     * @param numeroMesa El número de la mesa en la que se realiza el pedido.
     * @param pedido El pedido que se va a realizar.
     */
    fun realizarPedido(numeroMesa: Int, pedido: Pedido) {

        if (mesas[numeroMesa - 1].estado = EstadoMesa.OCUPADA.name.lowercase()) {
            mesas[numeroMesa - 1].agregarPedido(pedido)
        }
    }

    /**
     * Cierra un pedido en una mesa específica.
     * @param numeroMesa El número de la mesa en la que se cierra el pedido.
     * @param numeroPedido El número del pedido que se va a cerrar (opcional).
     */
    fun cerrarPedido(numeroMesa: Int, numeroPedido: Int? = null) {

        if (numeroPedido == null) {
            mesas[numeroMesa - 1].cerrarPedido(mesas[numeroMesa - 1].pedidos.size - 1)
        } else  {
            mesas[numeroMesa - 1].cerrarPedido(numeroPedido)
        }
    }

    /**
     * Cierra una mesa si todos los pedidos asociados a ella han sido servidos.
     * @param numeroMesa El número de la mesa que se va a cerrar.
     */
    fun cerrarMesa(numeroMesa: Int) {

        if (mesas[numeroMesa - 1].pedidos.all { it.estado == EstadoPedido.SERVIDO.name.lowercase() }) {
            mesas[numeroMesa - 1].estado = EstadoMesa.LIBRE.name.lowercase()
        }
    }

    /**
     * Busca todos los platos pedidos en todas las mesas.
     * @return Una lista de nombres de platos pedidos.
     */
    fun buscarPlatos(): List<String?>? {
        val platos = mesas.flatMap { it.pedidos }.flatMap { it.platos }.map { it.nombre }
        return platos.ifEmpty { null }
    }

    /**
     * Busca todos los platos pedidos en todas las mesas (alternativa).
     * @return Una lista de nombres de platos pedidos.
     */
    fun buscarPlatosAlternativo(): List<String> {
        val platos = mutableListOf<String>()
        mesas.forEach { mesa -> mesa.pedidos.forEach { pedido -> pedido.platos.forEach { plato -> platos.add(plato.nombre!!) } } } //Podemos usar el !! porque si el programa ha llegado hasta aquí es seguro que el nombre no es nulo
        return platos
    }

    /**
     * Cuenta la cantidad de veces que se ha pedido un plato específico en todas las mesas.
     * @param nombre El nombre del plato a contar.
     * @return La cantidad de veces que se ha pedido el plato.
     */
    fun contarPlato(nombre: String): Int? {
        val count = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .count { it.nombre == nombre }
        return if (count > 0) count else null
    }

    /**
     * Cuenta la cantidad de veces que se ha pedido un plato específico en todas las mesas (alternativo).
     * @param nombre El nombre del plato a contar.
     * @return La cantidad de veces que se ha pedido el plato.
     */
    fun contarPlatoAlternativo(nombre: String): Int? {

        val count = buscarPlatosAlternativo().count { it == nombre }
        return if (count > 0) count else null
    }

    /**
     * Busca el plato más pedido en todas las mesas.
     * @return Una lista de nombres de los platos más pedidos.
     */
    fun buscarPlatoMasPedido(): List<String?>? {
        val platoCounts = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .groupingBy { it.nombre }
            .eachCount()

        val maxCount = platoCounts.maxByOrNull { it.value }?.value
        return maxCount?.let { max -> platoCounts.filter { it.value == max }.keys.toList() }
    }

    /**
     * Busca el plato más pedido en todas las mesas (alternativo).
     * @return Un conjunto de nombres de los platos más pedidos.
     */
    fun buscarPlatoMasPedidoAlternativo(): Set<String> {

        val platosMasPedidos = mutableSetOf<String>()
        val platos = buscarPlatosAlternativo()
        var numPlatos: Int?
        var platoMasRepetido = 0

        platos.forEach {
            numPlatos = contarPlatoAlternativo(it)
            if (numPlatos!! > platoMasRepetido) {
                platoMasRepetido = numPlatos!!
                platosMasPedidos.clear()
                platosMasPedidos.add(it)
            } else if (numPlatos!! == platoMasRepetido) {
                platosMasPedidos.add(it)
            }
        }
        return platosMasPedidos
    }
}
