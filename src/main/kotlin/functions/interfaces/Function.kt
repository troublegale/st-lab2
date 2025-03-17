package functions.interfaces

import org.apache.commons.csv.CSVFormat
import java.io.IOException
import java.io.Writer

interface Function {

    fun calculate(x: Double, eps: Double): Double

    fun writeToCSV(x: Double, eps: Double, out: Writer) {
        val result = calculate(x, eps)
        try {
            val printer = CSVFormat.DEFAULT.print(out)
            printer.printRecord(x, result)
        } catch (e: IOException) {
            println("File error")
        }
    }

}
