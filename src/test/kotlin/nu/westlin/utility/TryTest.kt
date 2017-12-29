package nu.westlin.utility

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TryTest {

    @Test
    fun `should return a Success type on clean execution`() {
        val tryResult: Try<Int> = Try {
            4 / 2
        }

        assertThat(tryResult.isSuccess()).isTrue()

        if (tryResult is Success) {
            assertThat(tryResult.value).isEqualTo(2)
        } else {
            assertThat(false).describedAs("Not expected!").isTrue()
        }
    }

    @Test
    fun `should return success`() {
        val result = Try {
            8 / 2
        }

        assertThat(result.isSuccess()).isTrue()
        assertThat(result.get()).isEqualTo(4)

        if (result is Success) assertThat(result.value).isEqualTo(4)
    }

    @Test
    fun `should return failure`() {
        val result = Try {
            8 / 0
        }

        assertThat(result.isFailure()).isTrue()
    }

    @Test
    fun `should return 0`() {
        val result = Try {
            8 / 0
        }.getOrElse(0)

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `map should return 8`() {
        val result = Try {
            8 / 2
        }.map { it * 2 }

        assertThat(result.isSuccess()).isTrue()
        assertThat(result.get()).isEqualTo(8)
    }

    @Test
    fun `map should return "8"`() {
        val result = Try {
            8 / 2
        }.map { it * 2 }.map { it.toString() }

        assertThat(result.isSuccess()).isTrue()
        assertThat(result.get()).isEqualTo("8")
    }

    @Test
    fun `map should return Failure`() {
        val result = Try {
            8 / 2
        }.map { it / 0 }.map { it.toString() }

        assertThat(result.isFailure()).isTrue()
    }

    @Test
    fun `flatMap should return "8"`() {
        val result = Try {
            8 / 2
        }.flatMap { Try { it * 2 } }.flatMap { Try { it.toString() } }

        assertThat(result.isSuccess()).isTrue()
        assertThat(result.get()).isEqualTo("8")
    }
}