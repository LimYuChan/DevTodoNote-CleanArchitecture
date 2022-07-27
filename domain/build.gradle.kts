plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AppConfig.CompileSdkVersion

    defaultConfig {
        minSdk = AppConfig.MinSdkVersion
        targetSdk  = AppConfig.TargetSdkVersion

        testInstrumentationRunner = AppConfig.TestInstrumentationRunner
        consumerProguardFiles(AppConfig.ConsumerProguardRules)
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(AppConfig.DefaultProguardFile), AppConfig.ProguardRules)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.JvmTarget
    }
}

dependencies {

    implementation(Dependencies.AndroidX.CoreKtx)
    testImplementation(Dependencies.Test.Junit)
    androidTestImplementation(Dependencies.Test.TestExtJunit)
    androidTestImplementation(Dependencies.Test.TestEspressoCore)

    implementation(Dependencies.Kotlin.Coroutine)
}