plugins {
    id("org.jetbrains.kotlin.jvm")
    id("pokemon.jvm.hilt.plugin")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.paging.common)
}
