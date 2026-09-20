plugins {
    alias(libs.plugins.myapp.android.library)
}

android {
    namespace = "com.samir.database"
}

dependencies {
    implementation(project(":core:model"))
}