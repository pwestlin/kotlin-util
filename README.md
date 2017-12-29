# kotlin-util
Try is very much inspired by [this article](https://www.javacodegeeks.com/2017/12/kotlin-try-type-functional-exception-handling.html). :)

``` kotlin
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
fun `flatMap should return "8"`() {
    val result = Try {
        8 / 2
    }.flatMap { Try { it * 2 } }.flatMap { Try { it.toString() } }

    assertThat(result.isSuccess()).isTrue()
    assertThat(result.get()).isEqualTo("8")
}
```