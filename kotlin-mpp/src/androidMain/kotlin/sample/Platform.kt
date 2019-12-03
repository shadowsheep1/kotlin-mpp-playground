package sample

import android.os.Build

actual object Platform {
    actual val name: String = "Android - ${Build.VERSION.CODENAME}"
}

actual class Sample actual constructor() {
    actual fun checkMe(): Int = 42
}