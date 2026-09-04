package common

import com.android.build.api.dsl.ApplicationExtension

/**
 * Standard debug/release build type setup. Kept separate from flavors on purpose —
 * flavors answer "which backend/environment", build types answer "how is it compiled".
 * Crossing dev+debug, dev+release, prod+debug, prod+release all fall out automatically
 * once both are configured — no manual variant wiring needed.
 */
fun ApplicationExtension.configureBuildTypes() {
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}