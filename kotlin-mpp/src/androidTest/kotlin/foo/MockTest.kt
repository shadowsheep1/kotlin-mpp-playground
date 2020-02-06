package foo

import org.junit.Test
import sample.Platform
import sample.Sample
import sample.myClass2

class MockTest {
    @Test
    fun foo() {
        assert(true)
    }

    @Test
    fun giveMeTheAnswer() {
        println("The ultimate answer is: ${Sample().checkMe()}")
    }

    @Test
    fun testMyClass2() {
        println("MyClass2 global val test: ${myClass2.printHerName()}")
    }

    @Test
    fun testPlatform() {
        println("Platform test: ${Platform.name}")
    }
}