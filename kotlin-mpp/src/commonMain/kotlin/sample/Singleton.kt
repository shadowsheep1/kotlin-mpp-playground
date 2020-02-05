package sample

object Singleton {
    fun printMyName() = "I'm a singleton"

    val myClass = MyClass()

    val platform = Platform.name
}