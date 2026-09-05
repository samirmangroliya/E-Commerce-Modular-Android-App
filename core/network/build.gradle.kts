plugins {
    alias(libs.plugins.myapp.android.library)
    alias(libs.plugins.myapp.android.hilt)
}

android {
    namespace = "com.samir.network"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.bundles.network)
}