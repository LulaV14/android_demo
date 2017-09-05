# android_demo
This is a demo for a simple Android app.
The app has to views, a Main Activity and WebView for details.

The Main Activity has a List of posts/stories from this API (http://hn.algolia.com/api/v1/search_by_date?query=android)

I'm using Retrofit Library (http://square.github.io/retrofit/) to fetch HTTP requests.

And for the ListView I'm using a RecyclerView to add the functionality of the swipe down to refresh and swipe left to delete a post.

The stories are also downloaded to the local SQLite Database and refresh every time the activity starts or swipe down refresh.

I'm planning to add tests to all functionality of the app, right now given the time I couldn't complete that.


Will keep updating.



