plugins {
    id("pokemon.android.library.plugin")
    id("pokemon.android.hilt.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.seungsu.pokemon.presentation"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
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
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.coil.compose)
    api(libs.androidx.compose.material3)
    api(libs.androidx.lifecycle.viewModel.compose)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    androidTestApi(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}
