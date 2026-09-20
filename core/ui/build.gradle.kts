plugins {
    alias(libs.plugins.myapp.android.library)
    alias(libs.plugins.myapp.android.compose)
}

android {
    namespace = "com.samir.ui"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.compose.material.icons.extended)
}
