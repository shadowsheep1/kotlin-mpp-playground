// https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin/
// https://plugins.gradle.org/plugin/org.jetbrains.kotlin.multiplatform
// https://kotlinlang.org/docs/reference/using-gradle.html
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val compileJvm = false
val compileMacOS = false

val rootProjectExt = rootProject.extra

plugins {
    id ("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    // https://github.com/JetBrains/kotlin-native/blob/master/COCOAPODS.md
    id("org.jetbrains.kotlin.native.cocoapods")
}

println("compileSdkVersion: ${rootProjectExt["compileSdkVersion"]}")

// https://github.com/gradle/kotlin-dsl-samples/issues/163
android {
    compileSdkVersion(rootProjectExt["compileSdkVersion"] as Int)
    defaultConfig {
        minSdkVersion(rootProjectExt["minSdkVersion"] as Int)
        targetSdkVersion(rootProjectExt["targetSdkVersion"] as Int)
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    //implementation ("'com.android.support:appcompat-v7:28.0.0")
    //implementation ("'com.android.support.constraint:constraint-layout:1.1.3")
    //androidTestImplementation ("com.android.support.test:runner:1.0.2")
}

group =  "it.shadowsheep.kotlin.mpp"
version = "0.0.1"

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "MBB Mobile and More Kotlin/Native module"
        homepage = "https://www.shadowsheep.it"
    }

    iOSTarget("ios") {
        // Changeing name is still not allowed
        //https://youtrack.jetbrains.com/issue/KT-31542?_ga=2.135420210.2093573096.1575016813-841171207.1573547292
        // Add iOS framework setup here
        binaries {

        }
        // https://github.com/cashapp/sqldelight/issues/1442
        compilations.forEach {
            it.extraOpts("-linker-options", "-lsqlite3")
        }
    }


    android()
    //jvm("android")

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
    }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-annotations-common"))
    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib"))
    }

    sourceSets["androidTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
        implementation("com.android.support.test:runner:1.0.2")
    }

    sourceSets["iosMain"].dependencies {

    }

    if (compileJvm) {
        jvm()

        sourceSets["jvmMain"].dependencies {
            implementation(kotlin("tstdlib-jdk8"))
        }

        sourceSets["jvmTest"].dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit"))
        }
    }

    if (compileMacOS) {
        // For ARM, should be changed to iosArm32 or iosArm64
        // For Linux, should be changed to e.g. linuxX64
        // For MacOS, should be changed to e.g. macosX64
        // For Windows, should be changed to e.g. mingwX64
        macosX64("macos")

        sourceSets["macosMain"].dependencies {

        }

        sourceSets["macosTest"].dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit"))
        }
    }
}


task("iosTest") {
    dependsOn("linkDebugTestIos")
    doLast {
        val testBinaryPath =
            (kotlin.targets["ios"] as KotlinNativeTarget).binaries.getTest("DEBUG").outputFile.absolutePath
        exec {
            commandLine("xcrun", "simctl", "spawn", "--standalone", "iPhone Xʀ", testBinaryPath)
        }
    }
}