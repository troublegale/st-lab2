import org.apache.commons.csv.CSVFormat
import java.io.FileReader
import kotlin.math.*

fun main() {
    val reader = FileReader("src/test/resources/TargetIn.csv")
    val records = CSVFormat.DEFAULT.parse(reader)
    for (record in records) {
        val y = target(record.get(0).toDouble())
        println("${record.get(0).toDouble()}, $y")
    }
}

fun target(x: Double): Double {
    if (x <= 0) {
        var result = (tan(x) + sin(x)) / sin(x)
        result = result.pow(2)
        result = result.pow(3)
        result /= sin(x)
        return result
    }
    var result = ln(x) * log2(x)
    result = result.pow(3)
    result += ln(x) / ln(3.0)
    result *= log2(x)
    result = result.pow(3)
    return result
}