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

        return doCalculate(xNorm, eps)

    }

    private fun doCalculate(x: Double, eps: Double): Double {
        var calc = x - x.pow(3) / 6 + x.pow(5) / 120 - x.pow(7) / 5040
        var prev = 0.0
        var i = 4
        var fact = 5040
        while (abs(prev - calc) > eps) {
            prev = calc
            fact *= 2 * i * (2 * i + 1)
            val term = x.pow(2 * i + 1) * (-1.0).pow(i) / fact
            calc += term
            i++
        }
        return calc
    }

}
