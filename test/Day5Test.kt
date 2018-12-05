import org.junit.jupiter.api.Test

@DisableSlow
internal class Day5Test {

    val data5 = "input/input_5.txt".readFile()[0]
    val test5 = "dabAcCaCBAcCcaDA"

    @Test
    fun `5,1,test`() {
        report {
            Day5.partOne(test5) to 10
        }
    }

    @Test
    @Slow(500)
    fun `5,1,live`() {
        report {
            Day5.partOne(data5) to 10450
        }
    }

    @Test
    @Slow(2000)
    fun `5,2,live`() {
        report {
            Day5.partTwo(data5) to 4624
        }
    }

    @Test
    fun `5,2,test`() {
        report {
            Day5.partTwo(test5) to 4
        }
    }
}

