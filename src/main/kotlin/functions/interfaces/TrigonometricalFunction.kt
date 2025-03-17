package functions.interfaces

import kotlin.math.abs
import kotlin.math.PI

interface TrigonometricalFunction : Function {

    fun normalize(x: Double): Double {
        var xNorm = x
        while (abs(xNorm) > 2*PI) {
            if (xNorm < 0) xNorm += 2*PI
            else xNorm -= 2*PI
        }
        return xNorm
    }

}
