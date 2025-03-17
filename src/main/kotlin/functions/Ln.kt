package functions

import functions.interfaces.Function
import kotlin.math.pow

class Ln : Function {

    override fun calculate(x: Double, eps: Double): Double {

        if (eps <= 0.0) throw IllegalArgumentException("eps has to be > 0")

        if (x.isNaN()) return Double.NaN
        if (x < 0.0) throw IllegalArgumentException("ln(x) is undefined on x < 0")
        if (x == 0.0) return Double.NEGATIVE_INFINITY
        if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY

        val term = (x - 1) / (x + 1)

        var calc = term + 1.0/3.0 * term.pow(3) + 1.0/5.0 * term.pow(5)
        var prev: Double
        var i = 7.0

        do {
            prev = calc
            calc += 1.0/i * term.pow(i)
            i += 2
        } while (calc - prev > eps)

        return 2 * calc

    }

}
