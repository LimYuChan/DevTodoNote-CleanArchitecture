import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id(Plugins.NameTag.KotlinKAPT)
    id(Plugins.NameTag.KotlinParcelize)
    id(Plugins.NameTag.DaggerHiltAndroid)
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
    implementation(project(":data"))

    implementation(Dependencies.AndroidX.CoreKtx)
    implementation(Dependencies.AndroidX.AppCompat)
    //Ui
    implementation(Dependencies.AndroidX.ViewPager)
    implementation(Dependencies.AndroidX.ConstraintLayout)
    implementation(Dependencies.AndroidX.SwipeRefreshLayout)
    //view
    implementation(Dependencies.AndroidX.ActivityKtx)
    implementation(Dependencies.AndroidX.FragmentKtx)
    //Navigation
    implementation(Dependencies.AndroidX.NavigationFragmentKtx)
    implementation(Dependencies.AndroidX.NavigationUiKtx)
    //ViewModel
    implementation(Dependencies.AndroidX.LifecycleViewModelKtx)
    implementation(Dependencies.AndroidX.LifecycleViewModelSaveState)

    implementation(Dependencies.Kotlin.Coroutine)

    implementation(Dependencies.Google.AndroidMaterial)
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation(Dependencies.Test.Junit)
    androidTestImplementation(Dependencies.Test.TestExtJunit)
    androidTestImplementation(Dependencies.Test.TestEspressoCore)

    implementation(Dependencies.Lottie.Lottie)

    implementation(Dependencies.DaggerHilt.HiltAndroid)
    kapt(Dependencies.DaggerHilt.HiltAndroidCompiler)

    implementation(Dependencies.Glide.Glide)
    annotationProcessor(Dependencies.Glide.GlideCompiler)

    implementation(Dependencies.Permission.TedPermission)
}

fun getLocalProperty(key: String): String{
    return gradleLocalProperties(rootDir).getProperty(key)
}