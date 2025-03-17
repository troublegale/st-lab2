package functions

import functions.interfaces.Function

class Log2(private val ln: Ln) : Function {

    override fun calculate(x: Double, eps: Double): Double {

        if (eps <= 0.0) throw IllegalArgumentException("eps has to be > 0")

        if (x.isNaN()) return Double.NaN
        if (x < 0.0) throw IllegalArgumentException("log2(x) is undefined on x < 0")
        if (x == 0.0) return Double.NEGATIVE_INFINITY
        if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY
        if (x == 1.0) return 0.0

        return ln.calculate(x, eps) / ln.calculate(2.0, eps)

    }

}
