Android ViewBinding Library
========================================================
[![Version](https://img.shields.io/badge/version-1.0.1-green)](https://github.com/yogacp/android-viewbinding/releases/tag/1.0.1)
----------------------------------------------------------

A Simple Library to simplify viewbinding delegation in your Android Application

### Adding dependencies
Add this to your build.gradle:
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

enable viewbinding in your app/build.gradle:
```groovy
android {
    ....
    viewBinding {
        enabled = true
    }
}
```

Add the dependencies in your app/build.gradle:
```groovy
dependencies {
    ....
    implementation 'com.github.yogacp:android-viewbinding:1.0.0'
}
```

### How to use the library
Create your regular XML layout. For example, create **activity_sample.xml**:
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/app_name"
        android:layout_marginBottom="16dp"/>

    <Button
        android:id="@+id/btnSample"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/app_name"
        android:textStyle="bold"/>

        ....
</LinearLayout>
```

And use it in your Activity, just extend an binding variable that extend to your generated binding view and add ***by viewBinding()*** at the end:
```kotlin
class SampleActivity : AppCompatActivity() {

    private val binding: ActivitySampleBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvTitle.text = getString(R.string.sample_title)

        binding.btnSample.setOnClickListener {
            Toast.makeText(this, "Sample Button Clicked", Toast.LENGTH_SHORT).show()
        }
        ....
    }
}
```

And here if you want to use it in your Fragment. For example, you create a xml layout with name ***fragment_sample.xml***:
```kotlin
class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val binding: FragmentSampleBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFragmentTitle.text = getString(R.string.sample_fragment_title)
        binding.btnFragmentSample.setOnClickListener {
            Toast.makeText(context, "Sample Fragment Button Clicked", Toast.LENGTH_SHORT).show()
        }

        ....
    }
}
```

### TODO:
- Add DialogFragment viewbinding delegation
- Add ViewGroup viewbinding delegation to use it in Custom View

##### Happy coding :)
