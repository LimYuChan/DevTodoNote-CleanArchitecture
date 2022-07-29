plugins {
    id(Plugins.Path.AndroidLibrary)
    id(Plugins.Path.JetBrainsKotlinAndroid)
    id(Plugins.NameTag.KotlinKAPT)
    id(Plugins.NameTag.KotlinParcelize)
    id(Plugins.NameTag.DaggerHiltAndroid)
}

android {
    compileSdk = AppConfig.CompileSdkVersion

    defaultConfig {
        minSdk = AppConfig.MinSdkVersion
        targetSdk = AppConfig.TargetSdkVersion

        testInstrumentationRunner = AppConfig.TestInstrumentationRunner
        consumerProguardFiles(AppConfig.ConsumerProguardRules)

        buildConfigField("String", "GITHUB_CLIENT_ID", "\"${getLocalProperty("GITHUB_CLIENT_ID")}\"")
        buildConfigField("String", "GITHUB_SECRET_KEY", "\"${getLocalProperty("GITHUB_SECRET_KEY")}\"")
        buildConfigField("String", "GITHUB_REDIRECT_URL", "\"${getLocalProperty("GITHUB_REDIRECT_URL")}\"")

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

    implementation(Dependencies.AndroidX.CoreKtx)
    testImplementation(Dependencies.Test.Junit)
    androidTestImplementation(Dependencies.Test.TestExtJunit)
    androidTestImplementation(Dependencies.Test.TestEspressoCore)

    implementation(Dependencies.DaggerHilt.HiltAndroid)
    kapt(Dependencies.DaggerHilt.HiltAndroidCompiler)

    implementation(Dependencies.RoomDataBase.Room)
    kapt(Dependencies.RoomDataBase.RoomCompiler)

    implementation(Dependencies.Retrofit.Retrofit)
    implementation(Dependencies.Retrofit.RetrofitGsonConverter)

    implementation(Dependencies.Okhttp.Okhttp)
    implementation(Dependencies.Okhttp.LoggingInterceptor)
    implementation(Dependencies.Okhttp.UrlConnection)
}
fun getLocalProperty(key: String): String{
    return com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty(key)
}