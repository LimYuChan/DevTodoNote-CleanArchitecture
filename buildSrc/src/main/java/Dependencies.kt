object Dependencies {
    object AndroidX{
        const val CoreKtx = "androidx.core:core-ktx:1.8.0"
        const val AppCompat = "androidx.appcompat:appcompat:1.4.2"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    }
    object Kotlin{
        const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    }
    object Google{
        const val AndroidMaterial = "com.google.android.material:material:1.6.1"
    }

    object Test{
        private const val JUNIT_VERSION = "4.13.2"
        private const val TEST_EXT_JUNIT_VERSION = "1.1.3"
        private const val TEST_ESPRESSO_CORE_VERSION = "3.4.0"

        const val Junit = "junit:junit:$JUNIT_VERSION"
        const val TestExtJunit = "androidx.test.ext:junit:$TEST_EXT_JUNIT_VERSION"
        const val TestEspressoCore = "androidx.test.espresso:espresso-core:$TEST_ESPRESSO_CORE_VERSION"
    }

    object DaggerHilt{
        private const val HILT_VERSION= "2.42"
        const val HiltAndroid = "com.google.dagger:hilt-android:$HILT_VERSION"
        const val HiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$HILT_VERSION"
    }

    object RoomDataBase{
        private const val ROOM_VERSION = "2.4.2"
        const val Room = "androidx.room:room-ktx:$ROOM_VERSION"
        const val RoomCompiler = "androidx.room:room-compiler:$ROOM_VERSION"
    }

    object Retrofit{
        private const val RETROFIT_VERSION = "2.9.0"
        const val Retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val RetrofitGsonConverter = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    }

    object Okhttp{
        const val Okhttp = "com.squareup.okhttp3:okhttp:4.9.3"
        const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.5.0"
        const val UrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:3.14.9"
    }

}