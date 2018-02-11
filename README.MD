## Github Arch

This sample code that implement MVVM architecture using Android architecture component intensively and some RxJava

I create this repo for a few reasons:
1. Help others to understand architecture component in sample way
2. Understand paging library with RxJava
3. To use as starting point for future projects
4. Get feedback from other developers to improve my code

## Libraries and tools used
* [Android Support Libraries](https://developer.android.com/topic/libraries/support-library/index.html)
* [Paging](https://developer.android.com/topic/libraries/architecture/paging.html)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
* [RxJava2](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Dagger](https://google.github.io/dagger/)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [AutoValue](https://github.com/google/auto/tree/master/value)
* [AutoValueGson](https://github.com/rharter/auto-value-gson)
* [Timber](https://github.com/JakeWharton/timber)

## Requirements
* JDK 1.8
* Android SDK
* Latest Android SDK Tools and build tools.

## Architecture
The sample app when run will show you a simple list of Github users

![Imgur](https://i.imgur.com/Yo1W4bv.png?2)

So the app using Paging library from architecture component at it's core to handle
loading and displaying the users list. like this image from official docs

![Imgur](https://i.imgur.com/rcdD01z.gif)

At this sample the data source get it's data from [Retrofit Service](https://github.com/Ahmed-Abdelmeged/GithubArch/blob/master/app/src/main/java/com/ahmedabdelmeged/githubarch/api/GithubService.java)

and the data source handle loading the new data from the API  

The Repository is responsible for mange he connection between view model and the data source
so the view model doesn't know any thing about the data source
it's only consume data from the repository whatever that data source is. also create the PagedList
to bass it to the PagedListAdapter to consume and display it. The Activity is only consume
the data so it's pretty lightweight and change can handled easily configuration

Databinding is used to bind the data to views to avoid boilerplate code and add more layer of abstraction  

RxJava2 is used to get the data from API and do some mapping from Raw data to safe Databinding
and give the final result to the data source. Your case maybe more complex or simpler
so i prefer using RxJava because it provide powerful thread and error handing and go with you
from simplest solution to complex one

Dagger is used for dependency injection of course to make the code more testable and robust


## Conclusion
This architecture is similar to what google used in [Guide to App Architecture](https://developer.android.com/topic/libraries/architecture/guide.html)
<a href="https://developer.android.com/topic/libraries/architecture/guide.html#the_final_architecture">
<img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png">
</a>

But enhanced  with paging , RxJava2 and Databinding

contribution to improve this architecture is open just make a PR :D

## TODO
* Enable caching
* Add unit test
* Add instrumentation test

## Thanks
A special thanks to the authors involved with these two repositories, they were a great resource during our learning!

* [https://github.com/n26/N26AndroidSamples](https://github.com/n26/N26AndroidSamples)
* [https://github.com/googlesamples/android-architecture-components](https://github.com/googlesamples/android-architecture-components)
