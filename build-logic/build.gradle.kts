plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("androidApplicationPlugin") {
            id = "pokemon.android.application.plugin"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidLibraryPlugin") {
            id = "pokemon.android.library.plugin"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidHiltPlugin") {
            id = "pokemon.android.hilt.plugin"
            implementationClass = "AndroidHiltPlugin"
        }

        register("jvmHiltPlugin") {
            id = "pokemon.jvm.hilt.plugin"
            implementationClass = "JvmHiltPlugin"
        }
    }
}
