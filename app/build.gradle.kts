plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.cocktailsproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cocktailsproject"
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    //Material
    implementation ("com.google.android.material:material:1.11.0")

    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")


    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Coil
    implementation ("io.coil-kt:coil:1.4.0")

    //Gif
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    //SwipeToRefresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    //ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")



    testImplementation ("org.hamcrest:hamcrest-all:1.3")

    // AndroidX Test - JVM testing
    testImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation ("androidx.test:core-ktx:1.5.0")
    testImplementation ("org.robolectric:robolectric:4.12.2")

    testImplementation ("androidx.arch.core:core-testing:2.2.0")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")


    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")


    debugImplementation ("androidx.fragment:fragment-testing:1.7.1")

    implementation ("androidx.test:core-ktx:1.5.0")

    // Dependencies for Android instrumented unit tests
    androidTestImplementation ("org.mockito:mockito-core:2.8.9")

    androidTestImplementation ("com.linkedin.dexmaker:dexmaker-mockito:2.28.1")

    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1")
    implementation("androidx.paging:paging-runtime-ktx:3.3.0")

    androidTestImplementation ("androidx.navigation:navigation-testing:2.7.7")

    androidTestImplementation ("com.google.truth:truth:1.4.2")

    //Dagger
    implementation ("com.google.dagger:dagger:2.48")
    ksp("com.google.dagger:dagger-compiler:2.48")











}