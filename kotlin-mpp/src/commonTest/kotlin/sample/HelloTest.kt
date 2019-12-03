package sample

import kotlin.test.Test
import kotlin.test.assertTrue

class HelloTest {
    @Test
    fun testHello() {
        assertTrue { hello().contains("Hello") }
    }
}