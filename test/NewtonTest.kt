import org.junit.jupiter.api.Test
import ru.avk.polynoms.Lagrange
import ru.avk.polynoms.Newton
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.random.Random

class NewtonTest {

    val eps = 1e-7

    @Test
    fun `Newton Test`(){
        val num = 10
        val x = Random(Date().time)
        for (k in 0 until num) {
            val sz = x.nextInt(20, 21)
            val mm =
                mutableMapOf<Double, Double>()
            for (i in 0..sz){
                mm[x.nextDouble()*200-100] =
                    x.nextDouble()*200-100
            }
            val l = Lagrange(mm)
            val n = Newton(mm)
            println(l)
            println(n)
            mm.keys.forEach{
                print(it)
                print(" ")
                println(mm.getValue(it))
                println("Лагранж "+l.getValue(it))
                println("Ньютон "+n.getValue(it))
                println("-------------------------------------------")


            }
            for (i in 0 until l.coeffs.size) {
                assert(abs(l.coeffs[i]-n.coeffs[i])<eps)
            }
        }
    }
}