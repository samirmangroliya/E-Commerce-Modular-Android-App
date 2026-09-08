// build-logic/src/main/kotlin/CatalogAccessor.kt
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.libs(): VersionCatalog =
    extensions.getByType<VersionCatalogsExtension>().named("libs")

// Safe version helper with clear custom exception throwing
fun VersionCatalog.getRequiredVersionInt(key: String): Int {
    return findVersion(key)
        .orElseThrow { NoSuchElementException("Missing version entry '$key' in gradle/libs.versions.toml") }
        .requiredVersion
        .toInt()
}