// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.Path.AndroidApplication) version(Plugins.Version.ANDROID) apply(false)
    id(Plugins.Path.AndroidLibrary) version(Plugins.Version.ANDROID) apply(false)
    id(Plugins.Path.JetBrainsKotlinAndroid) version(Plugins.Version.JETBRAINS_KOTLIN_ANDROID) apply(false)
    id(Plugins.Path.DaggerHiltAndroid) version(Plugins.Version.DAGGER_HILT_ANDROID) apply(false)
    id(Plugins.Path.NavigationSafeArgs) version(Plugins.Version.NAVIGATION_SAFE_ARGS) apply(false)
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
