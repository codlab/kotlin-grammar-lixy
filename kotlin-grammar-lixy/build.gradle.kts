plugins {
    alias(additionals.plugins.kotlin.multiplatform)
    alias(additionals.plugins.android.library)
    alias(additionals.plugins.kotlin.serialization)
    id("publication")
    id("jvmCompat")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    js(IR) {
        browser()
        nodejs()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosArm64()
    macosX64()
    watchosArm32()
    watchosArm64()
    watchosX64()

    mingwX64()

    linuxArm64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                api(additionals.kotlinx.coroutines.test)
            }
        }

        val androidMain by getting
        val jvmMain by getting
        val jsMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val macosArm64Main by getting
        val macosX64Main by getting
        val watchosArm32Main by getting
        val watchosArm64Main by getting
        val watchosX64Main by getting
        val mingwX64Main by getting
        val linuxArm64Main by getting
        val linuxX64Main by getting


        val commonJvmMain by creating {
            dependsOn(commonMain)
        }

        val nonJvmMain by creating {
            dependsOn(commonMain)
        }

        listOf(
            androidMain,
            jvmMain
        ).forEach { it.dependsOn(commonJvmMain) }

        listOf(
            jsMain,
            iosX64Main,
            iosArm64Main,
            iosSimulatorArm64Main,
            macosArm64Main,
            macosX64Main,
            watchosArm32Main,
            watchosArm64Main,
            watchosX64Main,
            mingwX64Main,
            linuxArm64Main,
            linuxX64Main,
        ).forEach { it.dependsOn(nonJvmMain) }
    }
}

android {
    namespace = "guru.zoroark.lixy"
}
