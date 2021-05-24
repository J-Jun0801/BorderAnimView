# Kotlin-BorderAnimView

![introduce](https://user-images.githubusercontent.com/76588577/119303228-5ca46900-bca0-11eb-9bc5-9a8f92d74d7f.gif)<br>
<br>

Button 및 TextView 와 같은 Component에<br>
Outline 및 Border에 진행중인것을 알리고 싶을때 사용 <br>

<br>
<br>

```xml
    <com.jjg.borderanimview.BorderAnimView
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        app:move_icon="@drawable/watch_yellow_ic"
        app:stroke_width="3dp"
        app:stroke_color="#0083ff"
        app:text="TEXTVIEW 영역"
        app:min_height="150dp"
        app:text_size="10dp"
        app:text_color="#0083ff"
        />
```
`app:move_icon="@drawable/watch_yellow_ic"` 움직이는 icon<br>