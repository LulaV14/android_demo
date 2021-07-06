# android_demo
This is an example
This is a demo for a simple Android app.

The app has two views, a Main Activity and WebView for details.

#### MainActivity
The Main Activity has a List of posts/stories from this API (http://hn.algolia.com/api/v1/search_by_date?query=android)

I'm using Retrofit Library (http://square.github.io/retrofit/) to fetch HTTP requests.

And for the ListView I'm using a RecyclerView to add the functionality of the swipe down to refresh and swipe left to delete a post.

The stories are also downloaded to the local SQLite Database and refresh every time the activity starts or swipe down refresh.
In the local database I keep track of the deleted posts with a boolean property called "deleted" in order to show only the undeleted stories.


#### WebViewActivity
The WebViewActivity is pretty simple, it has a webview, and I added a progressbar to show when the webpage is waiting to load and it hides when finished loading.


#### Extended Features

I'm planning to add tests to all functionality of the app, right now given the time I couldn't complete that.


#### Video Walkthrough 

Here's a little walkthrough of the implemented work:

<img src='https://s3-us-west-1.amazonaws.com/examplelulav14/android_demo_app2.gif' title='Video Walkthrough' width='200' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).


Will keep updating.


PS: You don't need anything special to run the app.



