plugins {
    alias(libs.plugins.myapp.android.library)
    alias(libs.plugins.myapp.android.hilt)
    alias(libs.plugins.myapp.android.compose)
}

android {
    namespace = "com.samir.auth.mobile"
}

dependencies {
    implementation(project(":feature:auth:auth-domain"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))


    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.coroutines.android)

    implementation(libs.coil.compose)
    implementation(libs.androidx.compose.material.icons.extended)
}