plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.leo.viewpager2demo"
    compileSdk = 31

    defaultConfig {
        applicationId = "com.leo.viewpager2demo"
        minSdk = 29
        targetSdk = 31
        versionCode = 301
        versionName = "3.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    //签名 (需要签名文件)
    signingConfigs {

        create("testKey") {
            storeFile = file("atman.jks")
            storePassword = "5888062"
            keyAlias = "fastMedical"
            keyPassword = "5888062"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("testKey")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.blankj:utilcodex:1.31.1")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")
    implementation("com.github.JavaNoober.BackgroundLibrary:libraryx:1.7.6")
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
//    implementation("com.github.lihangleo2:SmartViewPager2Adapter:2.0.1")
    implementation(project(mapOf("path" to ":smartVpageAdapterlibrary")))
}