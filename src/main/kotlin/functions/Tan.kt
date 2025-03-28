package functions

import functions.interfaces.TrigonometricalFunction
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Tan(private val sin: Sin) : TrigonometricalFunction {

    override fun calculate(x: Double, eps: Double): Double {

        if (eps <= 0.0) throw IllegalArgumentException("eps has to be > 0")

        if (x.isNaN() || x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            return Double.NaN
        }

        val xNorm = normalize(x)

        if (xNorm == PI / 2.0) return Double.POSITIVE_INFINITY
        if (xNorm == -PI / 2.0) return Double.NEGATIVE_INFINITY

        val sinVal = sin.calculate(xNorm, eps)
        var cosVal = sqrt(1 - sinVal.pow(2))
        if (abs(xNorm) > PI / 2.0 && abs(xNorm) < 3.0 * PI / 2.0) cosVal *= -1

        return sinVal / cosVal

    }

}
