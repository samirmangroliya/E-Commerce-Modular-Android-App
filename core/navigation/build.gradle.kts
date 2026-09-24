plugins {
    alias(libs.plugins.myapp.android.library)
}

android {
    namespace = "com.samir.navigation"
}

dependencies {
    api(libs.androidx.navigation.compose)
}