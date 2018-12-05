import org.junit.jupiter.api.Test

internal class Day1Test {
    private val data1 = "input/input_1.txt".readFileAsInt()
    private val test1 = listOf(+1, -2, +3, +1)

    @Test
    fun `1,2,test,3`() {
        report {
            Day1.day1_2_take3(test1) to 2
        }
    }

    @Test
    fun `1,2,live,3`() {
        report {
            Day1.day1_2_take3(data1) to 83130
        }
    }

    @Test
    fun `1,2,test,4`() {
        report {
            Day1.day1_2_take4(test1) to 2
        }
    }

    @Test
    fun `1,2,live,4`() {
        report {
            Day1.day1_2_take4(data1) to 83130
        }
    }
}