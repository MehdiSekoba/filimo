# Advanced Android Development Techniques

A featurerich movie app built using the **MVVM architecture** and the **Filimo API**



## ScreenShots

splash online           | offline      | 
:-------------------------:|   :-------------------------:|
<img src="https://github.com/MehdiSekoba/filimo/blob/master/art/splash_online.jpg">| <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/splash_offline.jpg"> |

Home                    | Home |
:-------------------------:|:-------------------------:|
  <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/home.jpg">| <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/home_down.jpg">| 

Category                     | Explore |
:-------------------------:|:-------------------------:|
  <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/home_down.jpg">| <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/explore.jpg">| 

  SubCategory              | SubCategory |
:-------------------------:|:-------------------------:|
  <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/subcategoury.jpg">| <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/subcategoury2.jpg">| 
  
  Detail                   |  Detail |
:-------------------------:|:-------------------------:|
  <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/detail_down.jpg">| <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/detail_down2.jpg">            | 

  
  Setting                   |  Setting |
:-------------------------:|:-------------------------:|
  <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/setting.jpg">| <img src="https://github.com/MehdiSekoba/filimo/blob/master/art/setting_dark.jpg">            | 



### Key Features:

 **Dynamic Splash Screen**: 
   In **online mode**, data is fetched from the source and displayed in a custom RecyclerView with random content.
   In **offline mode**, a fallback splash screen is displayed.
  
 **Dynamic Carousel**: 
   Implemented with **Material Design 3 Carousel** to display movie slides dynamically.

 **Image Loading with Glide**: 
   Seamless image loading using the powerful **Glide library**.
   Customized image shapes with **ShapeableImageView** during the loading process.

 **Video Playback with Kohii**: 
   Videos are played within the **RecyclerView** using the **Kohii library**.
   Custom background integration for **ExoPlayer** for smoother video playback.

 **Day/Night Mode**: 
   Supports both light and dark themes, with appropriate styling adjustments using **Material 3 components**.

 **Auto Video Playback**: 
   As users scroll down, videos start playing automatically.

 **State Preservation**: 
   RecyclerView scroll state is preserved using **RecyclerView extensions**.

 **Nested RecyclerViews**: 
   Categories are displayed using nested RecyclerViews for an organized layout.

 **Image Corner Styling**: 
   Rounded image corners are achieved with **EdgeTreatment** for a polished UI.

 **Efficient Layouts**: 
   **ViewStub** is utilized instead of **include** for optimized layout inflation.

 **Themebased Tinting**: 
   Styles are applied dynamically to adjust tints for night and day modes.

 **Custom Views & Components**: 
   Includes custom views for enhanced flexibility.

 **Dependency Injection with Hilt**: 
   Simplified dependency management using **Hilt**.

 **Coroutines for Multithreading**: 
   Efficient **thread management** and CPU task handling via **Kotlin Coroutines**.

 **LiveData for ViewBinding**: 
   Reactive programming with **LiveData** for realtime updates.

 **Theme Persistence**: 
   **DataStore** is used to save theme preferences (day/night).

 ...and much more!
    Video
    
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/pLxWRVL3cNY/0.jpg)](https://www.youtube.com/embed/pLxWRVL3cNY?si=hxuwUbuGqPVmCjqZ)


## Support

**If you found this project helpful, you can support me and the project by giving it a star or making a donation.**


