# GlideImageView

[![N|Solid](https://img.shields.io/badge/Android%20Arsenal-GlideImageView-brightgreen.svg)](https://android-arsenal.com/details/1/5719)

GlideImageView used to show loader(Progress bar while loading image from url)

### Latest Version [2.1]
- Added method to set application context via (setApplicationContext()), Use this method before loading url.
- Added Singleton request Manager for efficient loading.

  
### Download
Include the following dependency in your apps build.gradle file.
```
compile 'com.master.android:glideimageview:2.1'
```

### How to use
#### in xml
```
<com.master.glideimageview.GlideImageView
        android:id="@+id/glide_image_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="fitXY"
        android:src="@mipmap/ic_launcher"
        app:error_res="@drawable/no_image"
        app:placeholder_res="@mipmap/ic_launcher"
        style="?android:attr/progressBarStyleSmall"
        app:show_progress="true" />
```

You can hide/show progress while loading using 
```
app:show_progress="false|true"
```

#### in java
```
glideImageView = (GlideImageView) findViewById(R.id.glide_image_view);
glideImageView.loadImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Pizigani_1367_Chart_10MB.jpg/800px-Pizigani_1367_Chart_10MB.jpg");
```

### Libraries Used
Thanks to Glide image loading library
https://github.com/bumptech/glide


### License
```
Copyright 2017 Pankaj Sharma

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
