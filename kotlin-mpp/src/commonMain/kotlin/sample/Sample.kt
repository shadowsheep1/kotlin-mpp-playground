package sample

val myClass = MyClass()
val myClass2 = MyClass2(myClass = expectGlobalVal)

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"