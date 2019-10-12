import ru.avk.polynoms.Lagrange
import ru.avk.polynoms.Newton
import ru.avk.polynoms.Polynom

fun main() {
    val l1 = Lagrange(arrayOf(-1.0, 0.0, 1.0), arrayOf(1.0, 0.0, 1.0))
    println(l1)
    val l2 = Lagrange(arrayOf(-1.0, 0.0, 2.0), arrayOf(-1.0, 0.0, 8.0, 1.0))
    println(l2)
    val l3 = Lagrange(arrayOf(-1.0, 0.0, 1.0, 2.0), arrayOf(0.0, 1.0, 2.0, 9.0))
    println(l3)
    val l4 = Newton(arrayOf(-1.0, 0.0, 1.0,2.0), arrayOf(1.0, 0.0, 1.0,100.0))
    println(l4)
    val l5 = Newton(arrayOf(-1.0, 0.0, 2.0), arrayOf(-1.0, 0.0, 8.0, 1.0))
    println(l5)
    val l6 = Newton(arrayOf(-1.0, 0.0, 1.0, 2.0), arrayOf(0.0, 1.0, 2.0, 9.0))
    println(l6)
}