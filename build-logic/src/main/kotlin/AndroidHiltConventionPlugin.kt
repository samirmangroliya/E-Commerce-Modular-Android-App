import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val catalog = target.versionCatalog()

        with(target) {
            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                "ksp"(catalog.findLibrary("hilt.compiler").get())
                "ksp"(catalog.findLibrary("kotlin.metadata").get())
            }

            // Add support for Jvm Module, base on org.jetbrains.kotlin.jvm
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    "implementation"(catalog.findLibrary("hilt.core").get())
                }
            }

            /** Add support for Android modules, based on [AndroidBasePlugin] */
            pluginManager.withPlugin("com.android.base") {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies {
                    "implementation"(catalog.findLibrary("hilt.android").get())
                }
            }
        }
    }


/*    override fun apply(target: Project) {
        val catalog = target.versionCatalog()

        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("org.jetbrains.kotlin.kapt")

            dependencies {
                add("implementation", catalog.findLibrary("hilt-android").get())
                add("kapt", catalog.findLibrary("hilt-compiler").get())
            }
        }
    }*/
}