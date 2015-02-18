# Instagram Photo Viewer

This is an Android app displaying current popular photos from Instagram.  Please
 see *CodePath* Android Observer Group [Week 1](http://courses.codepath.com/courses/intro_to_android/week/1#!module) material, covering app fundamentals, views and layouts.









![GIF Walkthrough](https://cloud.githubusercontent.com/assets/929507/6256089/19167de6-b76a-11e4-89dc-28556f8e4f8a.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Logistics
 * How many hours did it take to complete?
	* 8 hours for the initial commit, including the documentation and logistics
 * Which required and optional stories have you completed?
	* Required: User can scroll through current polular photos from Instagram
	* Required: For each photo displayed, user can see the following details: Graphic, Caption, Username
 * GIF walkthrough of all required and optional stories (using LiceCap)
	* Provided


## Libraries

This app leverages two third-party libraries:

 * [Android AsyncHTTPClient](http://loopj.com/android-async-http/) - For asynchronous network requests
 * [Picasso](http://square.github.io/picasso/) - For remote image loading

## Development Environment

 * Android Studio 1.0.1

 * App tested with BlueStacks (4.4.2 support) on Windows 7

## Before cloning this repository

First, please get an Instagram Developer client ID.  Sign in to Instagram developer site and click [Register Your Application](http://instagram.com/developer/clients/manage/#).  Once you have the client id, replace the CLIENT_ID constant in "InstagramClient / app / src / main / java / com / example / pleasure / instagramclient / PhotosActivity.java" file.

## Learning

1. Fetch Instagram popular photos in JSON format, decode and display utilizing list/image/text view.  Please see [Instagram API details](http://instagram.com/developer/endpoints/media/#).

2. PhotosActivity initializes the adapter and binds it to the ListView, then fetchs the data via fetchPopularPhotos().

3. fetchPolularPhotos(), with async http client library, sends network request, stores the response data in aPhotos array, and notifies the adapter that the data has changed.

4. getView() is called when an item in the list is drawn.  It returns a list view item filled with data.  The image is inserted using Picasso.  
	* LayoutInflater() takes in XML tags/parameters and returns the actual object, which later can be referenced via findViewById().  getView() inflates the view object or reuse if possible.
	* [TODO] Use ViewHolder for efficiency






