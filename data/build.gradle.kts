plugins {
    id("pokemon.android.library.plugin")
    id("pokemon.android.hilt.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")

}

android {
    namespace = "com.seungsu.pokemon.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.okhttp3)
}
