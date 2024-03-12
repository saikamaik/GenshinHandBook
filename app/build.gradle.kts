plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.genshinhandbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.genshinhandbook"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    val daggerVer = "2.50"
    val rxJavaVer = "3.1.8"
    val retrofitVer = "2.9.0"
    val navigationComponentVer = "2.7.7"
    val coroutinesKotlinVer = "1.7.3"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // ViewModel LifeData
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationComponentVer")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationComponentVer")

    // Retrofit + RxJava Adapter + Retrofit Gson Converter
    implementation("com.squareup.retrofit2:retrofit:$retrofitVer")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVer")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVer")

    // RxJava
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVer")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesKotlinVer")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesKotlinVer")

//    // Hilt
//    implementation("com.google.dagger:hilt-android:2.51")
//    kapt("com.google.dagger:hilt-android-compiler:2.51")

    // Dagger
    implementation ("com.google.dagger:dagger:$daggerVer")
    implementation ("com.google.dagger:dagger-android:$daggerVer")
    implementation ("com.google.dagger:dagger-android-support:$daggerVer")
    kapt ("com.google.dagger:dagger-compiler:$daggerVer")
    kapt ("com.google.dagger:dagger-android-processor:$daggerVer")

    // Coil
    implementation("io.coil-kt:coil:2.5.0")

}