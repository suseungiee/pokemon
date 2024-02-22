plugins {
    id("pokemon.android.library.plugin")
    id("pokemon.android.hilt.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.seungsu.pokemon.presentation"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.bundles.navigation)
    implementation(libs.coil)
    implementation(libs.paging)
    implementation(project(":domain"))
}
