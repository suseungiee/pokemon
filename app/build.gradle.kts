plugins {
    id("pokemon.android.application.plugin")
    id("pokemon.android.hilt.plugin")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.seungsu.pokemon"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.bundles.navigation)

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
}
