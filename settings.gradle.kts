pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "E-Commerce Modular App"
include(":app-mobile")

//Core modules
include(":core:analytics-api")
include(":core:analytics-firebase")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:model")
include(":core:navigation")
include(":core:network")
include(":core:ui")

//Features
include(":feature:home:home-domain")
include(":feature:home:home-mobile")
include(":feature:auth:auth-domain")
include(":feature:auth:auth-mobile")
include(":feature:cart:cart-domain")
include(":feature:cart:cart-mobile")
include(":feature:checkout:checkout-domain")
include(":feature:checkout:checkout-mobile")
include(":feature:order:order-domain")
include(":feature:order:order-mobile")
include(":feature:product:product-domain")
include(":feature:product:product-mobile")
include(":feature:profile:profile-domain")
include(":feature:profile:profile-mobile")

//Dynamic Features
 