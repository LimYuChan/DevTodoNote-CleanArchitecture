plugins {
    id(Plugins.Path.AndroidApplication)
    id(Plugins.Path.JetBrainsKotlinAndroid)
    id(Plugins.Path.NavigationSafeArgs)
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
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    implementation(Dependencies.AndroidX.Browser)
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

    testImplementation(Dependencies.Test.Junit)
    androidTestImplementation(Dependencies.Test.TestExtJunit)
    androidTestImplementation(Dependencies.Test.TestEspressoCore)

    implementation(Dependencies.Lottie.Lottie)

    implementation(Dependencies.DaggerHilt.HiltAndroid)
    kapt(Dependencies.DaggerHilt.HiltAndroidCompiler)

    implementation(Dependencies.Glide.Glide)
    annotationProcessor(Dependencies.Glide.GlideCompiler)

    implementation(Dependencies.Permission.TedPermission)

    implementation(Dependencies.ImagePicker.EsafirmPicker)

    implementation(Dependencies.Gson.Gson)
}