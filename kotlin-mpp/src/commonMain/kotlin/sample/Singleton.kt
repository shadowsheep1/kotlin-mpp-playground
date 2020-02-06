package sample

object Singleton {
    fun printMyName() = "I'm a singleton"

    val myClass = MyClass(Platform.name)

    val myClass2 = MyClass2(myClass)

    val platform = Platform.name
}