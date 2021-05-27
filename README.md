# Kotlin-BorderAnimView

![introduce](https://user-images.githubusercontent.com/76588577/119303228-5ca46900-bca0-11eb-9bc5-9a8f92d74d7f.gif)<br>
<br>

Button 및 TextView 와 같은 Component에<br>
Outline 및 Border에 진행중인것을 알리고 싶을때 사용 <br>

```xml
    <com.jjg.borderanimview.BorderAnimView
           android:layout_width="300dp"
           android:layout_height="wrap_content"
           android:layout_gravity="center"
           app:velocity="10dp"
           app:isLoop="true"
           app:min_height="150dp"
           app:move_icon="@drawable/watch_yellow_ic"
           app:stroke_color="#0083ff"
           app:stroke_width="3dp"
           app:text="TEXTVIEW 영역"
           app:text_color="#0083ff"
           app:text_size="10dp" />
```
`app:move_icon="@drawable/watch_yellow_ic"`움직이는 icon(drawable)<br>
`app:stroke_width="3dp"` 외곽 선 넓이<br>
`app:stroke_color="#0083ff"` 외곽 선 색상<br>
`app:text="TEXTVIEW 영역"` 보여주고자 하는 텍스트<br>
`app:min_height="150dp"` 해당 Component의 최소 높이<br>
`app:text_size="10dp"` 텍스트 size<br>
`app:text_color="#0083ff"` 텍스트 색상<br>
`app:velocity="10dp"` move_icon 움직이는 속도<br>
`app:isLoop="true"` Loop의 유무<br>
`android:layout_gravity"` TextView의 Gravity<br>
`android:fontFamily"` TextView의 font<br>
<hr>
사용법<br>
Project : Project Name<br>
build.gradle<br>

`
dependencies {
    implementation 'com.github.J-Jun0801:BorderAnimView:v0.0.3'
}
`
<br>
<br>

Module : Project Name<br>
build.gradle<br>

`
allprojects {
     repositories {
         maven { url "https://jitpack.io" }
     }
 }
 `