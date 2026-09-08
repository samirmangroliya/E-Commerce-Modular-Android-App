plugins {
    alias(libs.plugins.myapp.android.library)
    alias(libs.plugins.myapp.android.hilt)
}

android {
    namespace = "com.samir.firebase"
}

dependencies {
    implementation(project(":core:analytics-api"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}