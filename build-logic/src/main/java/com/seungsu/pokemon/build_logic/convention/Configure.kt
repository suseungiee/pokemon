package com.seungsu.pokemon.build_logic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


internal fun Project.configBlock(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        defaultConfig {
            compileSdk = PokemonConfig.compileSdk
            minSdk = PokemonConfig.minSdk
        }

        compileOptions {
            sourceCompatibility = PokemonConfig.javaVersion
            targetCompatibility = PokemonConfig.javaVersion
        }

        buildFeatures {
            viewBinding = true
        }
    }
    configureKotlin()
}

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = PokemonConfig.jvmTarget
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
                "-opt-in=kotlinx.coroutines.DelicateCoroutinesApi"
            )
        }
    }
}
