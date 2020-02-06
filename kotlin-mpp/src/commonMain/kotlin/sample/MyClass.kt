package sample

class MyClass(private val platformName: String) {
    fun printMyName() = "($platformName) I'm a MyClass instance $this"
}