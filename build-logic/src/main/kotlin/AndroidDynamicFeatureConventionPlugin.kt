import com.android.build.api.dsl.DynamicFeatureExtension
import common.configureFlavors
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidDynamicFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.libs()

        with(target) {

            //1. Apply Application Plugin
            val dynamicFeaturePlugin = libs.findPlugin("android-dynamic-feature").orElseThrow {
                NoSuchElementException("Missing plugin entry 'android-dynamic-feature' in gradle/libs.versions.toml")
            }
            pluginManager.apply(dynamicFeaturePlugin.get().pluginId)

            extensions.configure<DynamicFeatureExtension> {
                compileSdk {
                    version = release(libs.getRequiredVersionInt("sdkCompile"))
                }

                defaultConfig {
                    minSdk = libs.getRequiredVersionInt("sdkMin")
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                compileOptions {
                    val javaVersionInt =
                        JavaVersion.toVersion(libs.getRequiredVersionInt("javaVersion"))
                    sourceCompatibility = javaVersionInt
                    targetCompatibility = javaVersionInt
                }

                configureFlavors()
            }

            dependencies {
                val androidxCoreKtx = libs.findLibrary("androidx-core-ktx").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-core-ktx' in gradle/libs.versions.toml")
                }
                add("implementation", androidxCoreKtx)

                val jUnit = libs.findLibrary("junit").orElseThrow {
                    NoSuchElementException("Missing library entry 'junit' in gradle/libs.versions.toml")
                }
                add("testImplementation", jUnit)

                val androidEspressoCore = libs.findLibrary("androidx-espresso-core").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-espresso-core' in gradle/libs.versions.toml")
                }
                add("androidTestImplementation", androidEspressoCore)

                val androidJunit = libs.findLibrary("androidx-junit").orElseThrow {
                    NoSuchElementException("Missing library entry 'androidx-junit' in gradle/libs.versions.toml")
                }
                add("androidTestImplementation", androidJunit)
            }
        }
    }
}