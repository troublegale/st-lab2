package functions

import functions.interfaces.TrigonometricalFunction
import kotlin.math.PI
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

        return sinVal / sqrt(1 - sinVal.pow(2))

    }

}
