object Dependencies {
    object AndroidX{
        private const val CORE_KTX_VERSION = "1.8.0"
        private const val APPCOMPAT_VERSION = "1.4.2"
        private const val CONSTRAINT_LAYOUT_VERSION = "2.1.4"
        const val CoreKtx = "androidx.core:core-ktx:$CORE_KTX_VERSION"
        const val AppCompat = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
    }
    object Google{
        private const val ANDROID_MATERIAL_VERSION = "1.6.1"
        const val AndroidMaterial = "com.google.android.material:material:$ANDROID_MATERIAL_VERSION"
    }

    object Test{
        private const val JUNIT_VERSION = "4.13.2"
        private const val TEST_EXT_JUNIT_VERSION = "1.1.3"
        private const val TEST_ESPRESSO_CORE_VERSION = "3.4.0"

        const val Junit = "junit:junit:$JUNIT_VERSION"
        const val TestExtJunit = "androidx.test.ext:junit:$TEST_EXT_JUNIT_VERSION"
        const val TestEspressoCore = "androidx.test.espresso:espresso-core:$TEST_ESPRESSO_CORE_VERSION"
    }
}