// Top-level build file where you can add configuration options common to all sub-projects/modules.

// https://developer.android.com/studio/build/gradle-tips
// This block encapsulates custom properties and makes them available to all
// modules in the project.
buildscript {
    extra.also {
        it["kotlin_version"] = "1.3.50" // To Build With Xcode < 11
        //it["kotlin_version"] = "1.3.60" // To see ios platform specific code you need to use the same version of kotlin corresponding to the IDE plugin
        it["compileSdkVersion"] = 29
        it["minSdkVersion"] = 23
        it["targetSdkVersion"] = it["compileSdkVersion"]
        // You can also use this to specify versions for dependencies. Having consistent
        // versions between modules can avoid behavior conflicts.
    }

    repositories {
        mavenCentral()
        google()
        jcenter()
        
    }

    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}