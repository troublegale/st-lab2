package functions

import kotlin.math.pow
import functions.interfaces.Function

class Target(
    private val sin: Sin,
    private val tan: Tan,
    private val ln: Ln,
    private val log2: Log2,
    private val log3: Log3,
) : Function {

    override fun calculate(x: Double, eps: Double): Double {

        if (eps <= 0.0) throw IllegalArgumentException("eps has to be > 0")
        if (x == 0.0) throw IllegalArgumentException("target function is undefined on x = 0")

        return doCalculate(x, eps)

    }

    private fun doCalculate(x: Double, eps: Double): Double {
        var result: Double

        if (x <= 0) {

            val sinVal = sin.calculate(x, eps)
            val tanVal = tan.calculate(x, eps)

            result = tanVal + sinVal
            result /= sinVal
            result = result.pow(2)
            result = result.pow(3)
            result /= sinVal

        } else {

            val lnVal = ln.calculate(x, eps)
            val log2Val = log2.calculate(x, eps)
            val log3Val = log3.calculate(x, eps)

            result = lnVal * log2Val
            result = result.pow(3)
            result += log3Val
            result *= log2Val
            result = result.pow(3)

        }

        return result
    }

}
