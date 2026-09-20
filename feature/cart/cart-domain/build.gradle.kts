plugins {
    alias(libs.plugins.myapp.android.library)
    alias(libs.plugins.myapp.android.hilt)
}

android {
    namespace = "com.samir.home.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.coroutines.android)
}