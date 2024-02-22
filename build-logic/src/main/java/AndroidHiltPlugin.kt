import com.seungsu.pokemon.build_logic.convention.implementation
import com.seungsu.pokemon.build_logic.convention.kapt
import com.seungsu.pokemon.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("org.jetbrains.kotlin.kapt")
            }
            dependencies {
                implementation(libs.hilt.android)
                kapt(libs.hilt.compiler)
            }
        }
    }
}
