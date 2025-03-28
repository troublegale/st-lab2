package functions

import functions.interfaces.TrigonometricalFunction
import kotlin.math.pow
import kotlin.math.abs

class Sin : TrigonometricalFunction {

    override fun calculate(x: Double, eps: Double): Double {

        if (eps <= 0.0) throw IllegalArgumentException("eps has to be > 0")

        if (x.isNaN() || x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            return Double.NaN
        }

        val xNorm = normalize(x)

        var calc = xNorm - xNorm.pow(3) / 6 + xNorm.pow(5) / 120 - xNorm.pow(7) / 5040
        var prev = 0.0
        var i = 4
        var fact = 5040
        while (abs(prev - calc) > eps) {
            prev = calc
            fact *= 2 * i * (2 * i + 1)
            val term = xNorm.pow(2 * i + 1) * (-1.0).pow(i) / fact
            calc += term
            i++
        }
        return calc
    }

}
