plugins {
    alias(libs.plugins.myapp.android.dynamic.feature)
    alias(libs.plugins.myapp.android.compose)
}
android {
    namespace = "com.samir.chat"
}

dependencies {
    implementation(project(":app-mobile"))   // mandatory link, stays here — never centralized
    implementation(project(":core:ui"))
    implementation(project(":core:common"))

    implementation(libs.androidx.compose.material.icons.extended)
}