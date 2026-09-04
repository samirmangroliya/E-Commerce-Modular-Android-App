import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val catalog = target.versionCatalog()

        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("org.jetbrains.kotlin.kapt")

            dependencies {
                add("implementation", catalog.findLibrary("hilt-android").get())
                add("kapt", catalog.findLibrary("hilt-compiler").get())
            }
        }
    }
}