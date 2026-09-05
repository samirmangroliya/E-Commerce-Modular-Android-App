plugins {
    alias(libs.plugins.myapp.android.library)
    alias(libs.plugins.myapp.android.hilt)
}

android {
    namespace = "com.samir.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))

    implementation(libs.gson) // for @SerializedName on the DTOs
}