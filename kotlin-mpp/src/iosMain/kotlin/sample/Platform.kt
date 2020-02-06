package sample

import platform.UIKit.UIDevice

actual object Platform {
    actual val name: String = UIDevice.currentDevice.systemName() +
            " " +
            UIDevice.currentDevice.systemVersion
}

actual class Sample actual constructor() {
    actual fun checkMe(): Int = 42
}

actual val expectGlobalVal: MyClass  by lazy {
    MyClass(Platform.name)
}