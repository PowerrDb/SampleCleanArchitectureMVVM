# SampleCleanArchitectureMVVM
This is a sample project and using best practies 
For this app Using[The Movie Db API](https://rickandmortyapi.com/) to display Movies it has been built with clean architecture principles and MVVM pattern as well as Architecture Components.
## Libraries:

- MVVM
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) 
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) 
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/) 
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [OkHttp](https://github.com/square/okhttp)
- [Glide](https://github.com/bumptech/glide) 
- [Retrofit 2](https://github.com/square/retrofit)
- [Gson](https://github.com/google/gson)
- [Material Design](https://material.io/develop/)
- 
## Screenshots:

![List](https://user-images.githubusercontent.com/37610029/163682704-f3990e18-1125-4b1b-ae95-c4a206e46729.png)
![Detail](https://user-images.githubusercontent.com/37610029/163682707-4d6110ac-8dfe-441d-9429-1118d0b99dcf.png)

## Package Structure
    
    com.bestpractises.razisample    # Root Package
    .
    ├── base                   
    |
    ├── di                   
    │
    ├── network              
    │
    ├── ui                        
    │   ├── movieDetail        
    |   │   ├── data            
    |   │   ├── domain          
    |   │   └── peresentation  
    |   │  
    │   ├── movieList      
    |       ├── data        
    |       ├── domain      
    |       └── peresentation   
    |
    └── util               

