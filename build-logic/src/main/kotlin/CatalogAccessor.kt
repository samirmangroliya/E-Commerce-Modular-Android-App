// build-logic/src/main/kotlin/CatalogAccessor.kt
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.versionCatalog(): VersionCatalog =
    extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.intVersion(alias: String): Int {
    val version = findVersion(alias)
    require(version.isPresent) {
        "Version alias '$alias' not found in libs.versions.toml [versions] block. " +
                "Check for a typo or a missing entry."
    }
    return version.get().requiredVersion.toInt()
}