package common

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.kotlin.dsl.invoke

/**
 * Single source of truth for flavor dimension + flavor definitions across every module.
 * Add a new flavor here once — every module (app, and any flavor-aware library module)
 * picks it up automatically.
 */
enum class FlavorDimension {
    Environment
}

enum class AppFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null,
) {
    Dev(
        dimension = FlavorDimension.Environment,
        applicationIdSuffix = ".dev",
        versionNameSuffix = "-dev",
    ),
    Prod(
        dimension = FlavorDimension.Environment,
    ),
}

/**
 * Applies the dev/prod flavor dimension to any module (application OR library).
 * Application-only fields (applicationIdSuffix) are only set when the receiving
 * extension is actually an ApplicationExtension — safe to call from a library
 * convention plugin too, where those fields simply get skipped.
 */
fun CommonExtension.configureFlavors() {
    flavorDimensions += FlavorDimension.Environment.name

    productFlavors {
        AppFlavor.entries.forEach { flavor ->
            register(flavor.name.lowercase()) {
                dimension = flavor.dimension.name

                if (this@configureFlavors is ApplicationExtension && this is ApplicationProductFlavor) {
                    flavor.applicationIdSuffix?.let { applicationIdSuffix = it }
                    flavor.versionNameSuffix?.let { versionNameSuffix = it }
                }
            }
        }
    }
}