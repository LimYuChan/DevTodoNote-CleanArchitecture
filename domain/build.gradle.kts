plugins {
    id(Plugins.Path.AndroidLibrary)
    id(Plugins.Path.JetBrainsKotlinAndroid)
    id(Plugins.NameTag.KotlinKAPT)
    id(Plugins.NameTag.DaggerHiltAndroid)
    id(Plugins.NameTag.KotlinParcelize)
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

    implementation(Dependencies.DaggerHilt.HiltAndroid)
    kapt(Dependencies.DaggerHilt.HiltAndroidCompiler)

    implementation(Dependencies.HtmlParser.JSoup)
}