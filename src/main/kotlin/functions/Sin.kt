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

        var calc = xNorm - xNorm.pow(3) / 6.0 + xNorm.pow(5) / 120.0
        var prev: Double
        var i = 3
        var fact = 120.0

        do {
            prev = calc
            fact *= 2*i * (2*i + 1)
            val coefficient = (-1.0).pow(i) / fact
            calc += coefficient * xNorm.pow(2*i + 1)
            i++
        } while (abs(calc - prev) > eps)

        if (abs(calc) < eps) return 0.0
        if (abs(calc - 1) < eps) return 1.0
        return calc

    }

}
