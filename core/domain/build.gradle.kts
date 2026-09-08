plugins {
    alias(libs.plugins.myapp.android.library)
}

android {
    namespace = "com.samir.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.javax.inject)
}