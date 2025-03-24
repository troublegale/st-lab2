import functions.*
import org.apache.commons.csv.CSVFormat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito
import java.io.FileReader

class FunctionTests {

    private val funEps = 0.00001
    private val testEps = 0.5

    companion object {

        private const val EPS = 0.01

        private var lnMock: Ln = Mockito.mock(Ln::class.java)
        private var log2Mock: Log2 = Mockito.mock(Log2::class.java)
        private var log3Mock: Log3 = Mockito.mock(Log3::class.java)
        private var sinMock: Sin = Mockito.mock(Sin::class.java)
        private var tanMock: Tan = Mockito.mock(Tan::class.java)

        @JvmStatic
        @BeforeAll
        fun init(): Unit {
            val lnReader = FileReader("src/test/resources/mocks/LnMock.csv")
            val log2Reader = FileReader("src/test/resources/mocks/LnMock.csv")
            val log3Reader = FileReader("src/test/resources/mocks/LnMock.csv")
            val sinReader = FileReader("src/test/resources/mocks/LnMock.csv")
            val tanReader = FileReader("src/test/resources/mocks/LnMock.csv")

            var records = CSVFormat.DEFAULT.parse(lnReader)
            for (record in records) {
                Mockito.`when`(lnMock.calculate(record.get(0).toDouble(), EPS)).thenReturn(record.get(1).toDouble())
            }

            records = CSVFormat.DEFAULT.parse(log2Reader)
            for (record in records) {
                Mockito.`when`(log2Mock.calculate(record.get(0).toDouble(), EPS)).thenReturn(record.get(1).toDouble())
            }

            records = CSVFormat.DEFAULT.parse(log3Reader)
            for (record in records) {
                Mockito.`when`(log3Mock.calculate(record.get(0).toDouble(), EPS)).thenReturn(record.get(1).toDouble())
            }

            records = CSVFormat.DEFAULT.parse(sinReader)
            for (record in records) {
                Mockito.`when`(sinMock.calculate(record.get(0).toDouble(), EPS)).thenReturn(record.get(1).toDouble())
            }

            records = CSVFormat.DEFAULT.parse(tanReader)
            for (record in records) {
                Mockito.`when`(tanMock.calculate(record.get(0).toDouble(), EPS)).thenReturn(record.get(1).toDouble())
            }
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with mocks`(input: Double, expected: Double) {
        val target = Target(sinMock, tanMock, lnMock, log2Mock, log3Mock)
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with tan`(input: Double, expected: Double) {
        val target = Target(sinMock, Tan(sinMock), lnMock, log2Mock, log3Mock)
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with sin`(input: Double, expected: Double) {
        val target = Target(Sin(), Tan(Sin()), lnMock, log2Mock, log3Mock)
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with log2`(input: Double, expected: Double) {
        val target = Target(sinMock, tanMock, lnMock, Log2(lnMock), log3Mock)
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with log3`(input: Double, expected: Double) {
        val target = Target(sinMock, tanMock, lnMock, log2Mock, Log3(lnMock))
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with log2 and log3`(input: Double, expected: Double) {
        val target = Target(sinMock, tanMock, lnMock, Log2(lnMock), Log3(lnMock))
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with ln`(input: Double, expected: Double) {
        val target = Target(sinMock, tanMock, Ln(), Log2(Ln()), Log3(Ln()))
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/TargetIn.csv"])
    fun `Target with everything`(input: Double, expected: Double) {
        val target = Target(Sin(), Tan(Sin()), Ln(), Log2(Ln()), Log3(Ln()))
        assertEquals(expected, target.calculate(input, funEps), testEps)
    }

}
