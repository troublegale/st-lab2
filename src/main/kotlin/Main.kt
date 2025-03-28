import functions.*
import java.io.FileWriter

fun main() {
    writeAllResultsToCSV(-4.0, -2.0, 0.1, 0.0001)
    writeAllResultsToCSV(0.5, 2.5, 0.1, 0.0001)
}

fun writeAllResultsToCSV(from: Double, to: Double, step: Double, eps: Double) {
    val sin = Sin()
    val sinWriter = FileWriter("src/main/resources/sin.csv", true)

    val tan = Tan(sin)
    val tanWriter = FileWriter("src/main/resources/tan.csv", true)

    val ln = Ln()
    val lnWriter = FileWriter("src/main/resources/ln.csv", true)

    val log2 = Log2(ln)
    val log2Writer = FileWriter("src/main/resources/log2.csv", true)

    val log3 = Log3(ln)
    val log3Writer = FileWriter("src/main/resources/log3.csv", true)

    val target = Target(sin, tan, ln, log2, log3)
    val targetWriter = FileWriter("src/main/resources/target.csv", true)

    var i = 0
    while (from + i * step <= to) {
        val x = from + i * step

        sin.writeToCSV(x, eps, sinWriter)

        tan.writeToCSV(x, eps, tanWriter)

        try {
            ln.writeToCSV(x, eps, lnWriter)
        } catch (e: Exception) {
            println(e.message)
        }

        try {
            log2.writeToCSV(x, eps, log2Writer)
        } catch (e: Exception) {
            println(e.message)
        }

        try {
            log3.writeToCSV(x, eps, log3Writer)
        } catch (e: Exception) {
            println(e.message)
        }

        try {
            target.writeToCSV(x, eps, targetWriter)
        } catch (e: Exception) {
            println(e.message)
        }

        i++
    }
    sinWriter.close()
    tanWriter.close()
    lnWriter.close()
    log2Writer.close()
    log3Writer.close()
    targetWriter.close()
}
