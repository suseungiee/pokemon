import com.android.build.api.dsl.ApplicationExtension
import com.seungsu.pokemon.build_logic.convention.PokemonConfig
import com.seungsu.pokemon.build_logic.convention.configBlock
import com.seungsu.pokemon.build_logic.convention.implementation
import com.seungsu.pokemon.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configBlock(this)

                defaultConfig.apply {
                    applicationId = PokemonConfig.applicationId
                    targetSdk = PokemonConfig.targetSdk
                    versionCode = PokemonConfig.versionCode
                    versionName = PokemonConfig.versionName
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
            }

            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}
