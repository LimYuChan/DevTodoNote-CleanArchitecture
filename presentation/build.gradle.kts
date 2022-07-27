plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AppConfig.CompileSdkVersion

    defaultConfig {
        applicationId = AppConfig.ApplicationId
        minSdk = AppConfig.MinSdkVersion
        targetSdk = AppConfig.TargetSdkVersion
        versionCode = AppConfig.VersionCode
        versionName = AppConfig.VersionName

        testInstrumentationRunner = AppConfig.TestInstrumentationRunner
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

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependencies.AndroidX.CoreKtx)
    implementation(Dependencies.AndroidX.AppCompat)

    implementation(Dependencies.Google.AndroidMaterial)
    implementation(Dependencies.AndroidX.ConstraintLayout)

    testImplementation(Dependencies.Test.Junit)
    androidTestImplementation(Dependencies.Test.TestExtJunit)
    androidTestImplementation(Dependencies.Test.TestEspressoCore)
}