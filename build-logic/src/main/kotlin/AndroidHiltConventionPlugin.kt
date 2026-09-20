import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// AndroidHiltConventionPlugin.kt — applies BOTH plugins internally, invisibly
class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.libs()

        with(target) {
            //Adding KSP Plugin
            val kspPlugin = libs.findPlugin("ksp").orElseThrow {
                NoSuchElementException("Missing plugin entry 'ksp' in gradle/libs.versions.toml")
            }
            pluginManager.apply(kspPlugin.get().pluginId)

            //Adding Hilt Plugin
            val hiltPlugin = libs.findPlugin("hilt-android").orElseThrow {
                NoSuchElementException("Missing plugin entry 'hilt' in gradle/libs.versions.toml")
            }
            pluginManager.apply(hiltPlugin.get().pluginId)

            //Adding Dependencies for Hilt-KSP
            dependencies {
                val hiltAndroid = libs.findLibrary("hilt-android").orElseThrow {
                    NoSuchElementException("Missing library entry 'hilt-android' in gradle/libs.versions.toml")
                }
                val hiltCompiler = libs.findLibrary("hilt-compiler").orElseThrow {
                    NoSuchElementException("Missing library entry 'hilt-compiler' in gradle/libs.versions.toml")
                }

                add("implementation", hiltAndroid)
                add("ksp", hiltCompiler)
            }
        }
    }
}