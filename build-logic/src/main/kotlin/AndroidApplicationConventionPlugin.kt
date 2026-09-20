import com.android.build.api.dsl.ApplicationExtension
import common.configureBuildTypes
import common.configureFlavors
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.libs()

        with(target) {

            //1. Apply Application Plugin
            val applicationPlugin = libs.findPlugin("android-application").orElseThrow {
                NoSuchElementException("Missing plugin entry 'android-application' in gradle/libs.versions.toml")
            }
            pluginManager.apply(applicationPlugin.get().pluginId)

            //2. Apply Google Service Plugin
            val googleServicesPlugin =  libs.findPlugin("google-services").orElseThrow {
                NoSuchElementException(
                    "Missing plugin entry 'google-services' in libs.versions.toml"
                )
            }
            pluginManager.apply(googleServicesPlugin.get().pluginId)

            //3. SDK, compileOptions, flavors, build type
            extensions.configure<ApplicationExtension> {
                compileSdk {
                    version = release(libs.getRequiredVersionInt("sdkCompile"))
                }

                defaultConfig {
                    minSdk = libs.getRequiredVersionInt("sdkMin")
                    targetSdk = libs.getRequiredVersionInt("sdkTarget")
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                compileOptions {
                    val javaVersionInt = JavaVersion.toVersion(libs.getRequiredVersionInt("javaVersion"))
                    sourceCompatibility = javaVersionInt
                    targetCompatibility = javaVersionInt
                }

                buildFeatures {
                    buildConfig = true   // ← add here so every app module gets it automatically
                }

                configureFlavors()      // ← dev/prod, single source of truth
                configureBuildTypes()   // ← debug/release, single source of truth
            }

            //4 dependencies
            dependencies {
                val androidxTestRunner = libs.findLibrary("androidx-test-runner").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-test-runner' in gradle/libs.versions.toml")
                }
                add("androidTestImplementation", androidxTestRunner)
            }
        }
    }
}