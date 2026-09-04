import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val catalog = target.versionCatalog()

        with(target) {
            // Applies the Compose compiler plugin (Kotlin 2.0+)
            pluginManager.apply(
                catalog.findPlugin("kotlin-compose").get().get().pluginId
            )

            // Resolve and configure properties on the explicit extension instance
            val applicationExtension = extensions.findByType(ApplicationExtension::class.java)
            val libraryExtension = extensions.findByType(LibraryExtension::class.java)

            applicationExtension?.apply {
                buildFeatures { compose = true }
            }

            libraryExtension?.apply {
                buildFeatures { compose = true }
            }

            dependencies {
                add("implementation", platform(catalog.findLibrary("androidx-compose-bom").get()))
                add("implementation", catalog.findBundle("compose").get())
                add("implementation", catalog.findLibrary("coil-compose").get())
                add("implementation", catalog.findLibrary("androidx-compose-ui-tooling-preview").get())
            }
        }
    }
}
