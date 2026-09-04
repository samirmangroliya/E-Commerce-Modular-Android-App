import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val catalog = target.versionCatalog()

        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                compileSdk {
                    version = release(catalog.intVersion("sdkCompile"))
                }

                defaultConfig {
                    minSdk = catalog.intVersion("sdkMin")
                    targetSdk = catalog.intVersion("sdkTarget")
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.toVersion(catalog.intVersion("javaVersion"))
                    targetCompatibility = JavaVersion.toVersion(catalog.intVersion("javaVersion"))

                }
            }

            dependencies {
                add("androidTestImplementation", catalog.findLibrary("androidx-test-runner").get())
            }
        }
    }
}