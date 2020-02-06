package sample

val myClass = MyClass(Platform.name)
//val myClass2 = MyClass2(myClass = expectGlobalVal) // <- Thread 1: EXC_BAD_ACCESS (code=1, address=0x8)

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"