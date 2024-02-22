import com.seungsu.pokemon.build_logic.convention.implementation
import com.seungsu.pokemon.build_logic.convention.kapt
import com.seungsu.pokemon.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class JvmHiltPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                implementation(libs.hilt.core)
                kapt(libs.hilt.compiler)
            }
        }
    }
}
