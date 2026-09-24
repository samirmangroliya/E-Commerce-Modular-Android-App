plugins {
    alias(libs.plugins.myapp.android.application)
    alias(libs.plugins.myapp.android.hilt)
    alias(libs.plugins.myapp.android.compose)
}

android {
    namespace = "com.samir.ecommerceapp"

    defaultConfig {
        applicationId = "com.samir.ecommerceapp"
        versionCode = 1
        versionName = "1.0"
    }
    dynamicFeatures += setOf(":dynamicfeature:chat")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.play.feature.delivery.ktx)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(project(":feature:home:home-mobile"))
    implementation(project(":feature:product:product-mobile"))
    implementation(project(":feature:cart:cart-mobile"))
    implementation(project(":feature:order:order-mobile"))
    implementation(project(":feature:profile:profile-mobile"))
    implementation(project(":feature:checkout:checkout-mobile"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
}