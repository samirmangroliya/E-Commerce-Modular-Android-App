import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// AndroidLibraryConventionPlugin.kt — if you still have this from the multi-module version
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val catalog = target.versionCatalog()

        with(target) {
            pluginManager.apply("com.android.library")
            // REMOVED here too: pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                compileSdk = catalog.intVersion("sdkCompile")
                defaultConfig { minSdk = catalog.intVersion("sdkMin") }
                compileOptions {
                    sourceCompatibility = JavaVersion.toVersion(catalog.intVersion("javaVersion"))
                    targetCompatibility = JavaVersion.toVersion(catalog.intVersion("javaVersion"))
                }
            }

            dependencies {
                add("implementation", catalog.findLibrary("coroutines-android").get())
            }
        }
    }
}