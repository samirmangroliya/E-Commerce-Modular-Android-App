import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.libs()

        with(target) {

            //Adding Compose compiler plugin (Kotlin 2.0+)
            val kotlinComposePlugin = libs.findPlugin("kotlin-compose").orElseThrow {
                NoSuchElementException("Missing plugin entry 'kotlin-compose' in gradle/libs.versions.toml")
            }
            pluginManager.apply(kotlinComposePlugin.get().pluginId)

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
                // 1. Compose BOM (Platform)
                val composeBom = libs.findLibrary("androidx-compose-bom").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-compose-bom' in gradle/libs.versions.toml")
                }
                add("implementation", platform(composeBom))

                // 2. Compose Bundle
                val composeBundle = libs.findBundle("compose").orElseThrow {
                    NoSuchElementException("Missing bundle entry 'compose' in gradle/libs.versions.toml")
                }
                add("implementation", composeBundle)

                // 3. Coil Compose
                val coilCompose = libs.findLibrary("coil-compose").orElseThrow {
                    NoSuchElementException("Missing library entry 'coil-compose' in gradle/libs.versions.toml")
                }
                add("implementation", coilCompose)

                // 4. Compose UI Tooling Preview
                val composeToolingPreview = libs.findLibrary("androidx-compose-ui-tooling-preview").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-compose-ui-tooling-preview' in gradle/libs.versions.toml")
                }
                add("implementation", composeToolingPreview)

                // 5. Compose UI Tooling (Required for Previews)
                val composeTooling = libs.findLibrary("androidx-compose-ui-tooling").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-compose-ui-tooling' in gradle/libs.versions.toml")
                }
                add("debugImplementation", composeTooling)
            }
        }
    }
}
