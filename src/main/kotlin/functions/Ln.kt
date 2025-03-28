package functions

import functions.interfaces.Function
import kotlin.math.abs
import kotlin.math.pow

class Ln : Function {

    override fun calculate(x: Double, eps: Double): Double {

        if (eps <= 0.0) throw IllegalArgumentException("eps has to be > 0")

        if (x.isNaN()) return Double.NaN
        if (x < 0.0) throw IllegalArgumentException("ln(x) is undefined on x < 0")
        if (x == 0.0) return Double.NEGATIVE_INFINITY
        if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY

        var invert = false
        var xVal = x
        if (x > 2.0) {
            xVal = 1.0 / x
            invert = true
        }

        var res = doCalculate(xVal, eps)
        if (invert) res *= -1
        return res

    }

    private fun doCalculate(x: Double, eps: Double): Double {
        val term = x - 1
        var calc = term - term.pow(2) / 2 + term.pow(3) / 3 - term.pow(4) / 4
        var prev = 0.0
        var i = 5
        while (abs(calc - prev) > eps) {
            prev = calc
            calc += (-1.0).pow(i + 1) * term.pow(i) / i
            i++
        }
        return calc
    }

}
