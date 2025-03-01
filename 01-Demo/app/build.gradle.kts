
plugins {
    id("com.android.application")
    // com.android.library means this is a lib module.
    // difference between them is that the application
    // module can run independently while lib module 
    // will need to be loaded in other apps so that 
    // it can run
    
    
    id("kotlin-android")
}

android {
    namespace = "com.example.demo"
    compileSdk = 33
    
    defaultConfig {
        applicationId = "com.example.demo"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        //Todo: https://developer.android.com/topic/libraries/view-binding
        //https://github.com/android/architecture-components-samples/tree/main/ViewBindingSample
    }
    
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

dependencies {


    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
}
