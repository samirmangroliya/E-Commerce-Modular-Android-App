import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// AndroidLibraryConventionPlugin.kt — if you still have this from the multi-module version
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.libs()

        with(target) {
            //Adding library Plugin
            val libraryPlugin = libs.findPlugin("android-library").orElseThrow {
                NoSuchElementException("Missing plugin entry 'android-library' in gradle/libs.versions.toml")
            }

            pluginManager.apply(libraryPlugin.get().pluginId)

            extensions.configure<LibraryExtension> {
                compileSdk = libs.getRequiredVersionInt("sdkCompile")
                defaultConfig { minSdk = libs.getRequiredVersionInt("sdkMin") }
                compileOptions {
                    val javaVersionInt = JavaVersion.toVersion(libs.getRequiredVersionInt("javaVersion"))
                    sourceCompatibility = javaVersionInt
                    targetCompatibility = javaVersionInt
                }
                buildFeatures {
                    buildConfig = true
                }
            }

            dependencies {
                val coroutinesAndroid = libs.findLibrary("coroutines-android").orElseThrow {
                    NoSuchElementException("Missing library entry 'coroutines-android' in gradle/libs.versions.toml")
                }
                add("implementation", coroutinesAndroid)
            }
        }
    }
}