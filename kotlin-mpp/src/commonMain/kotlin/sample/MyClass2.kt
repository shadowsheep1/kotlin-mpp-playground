package sample

class MyClass2(val myClass: MyClass) {
    fun printHerName() = myClass.printMyName()
}