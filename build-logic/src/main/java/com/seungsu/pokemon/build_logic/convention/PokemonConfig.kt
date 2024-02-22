package com.seungsu.pokemon.build_logic.convention

import org.gradle.api.JavaVersion

object PokemonConfig {
    const val applicationId = "com.seungsu.pokemon"
    const val minSdk = 28
    const val compileSdk = 34
    const val targetSdk = 34
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val jvmTarget = "1.8"
    val javaVersion = JavaVersion.VERSION_1_8
}
