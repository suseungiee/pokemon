import com.android.build.gradle.LibraryExtension
import com.seungsu.pokemon.build_logic.convention.configBlock
import com.seungsu.pokemon.build_logic.convention.implementation
import com.seungsu.pokemon.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> { configBlock(this) }

            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}
