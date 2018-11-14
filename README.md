# HKVideoLibrary
基于海康视频的开发，用于APP内视频监控模块的封装，避免写重复多余的代码

## Gradle
project
```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url "https://jitpack.io"
        }
    }
}
```
module
```

```

## 注意
1. 由于JNI库版本限制，targetSdkVersion 不能高于23，所以需要设置成 22 或者以下，即
    ```
    targetSdkVersion 22
    ```
2. So库过滤
    ```
    defaultConfig {
        ...
        ...
        ...
        ndk {
            //APP的build.gradle设置支持的SO库架构
            //abiFilters 'armeabi', 'armeabi-v7a', 'x86'
            abiFilters 'armeabi'
        }
    }
    ```
3. JDK版本，1.8+
    ```
    android {
        ...
        ...
        ...
        compileOptions {
            targetCompatibility 1.8
            sourceCompatibility 1.8
        }
    }
    ```
4. 需依赖其他必须库
    ```
    dependencies {
        ...
        ...
        ...
        implementation 'com.android.support:design:27.1.1'
        implementation 'com.github.HyfSunshine:SDRLibrary:1.0.0'
        implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.22'
    }
    ```

## 使用

- application初始化
    ```java
    HKVideoLibrary.getInstance().init(this, BuildConfig.DEBUG, BaseActivity.getHeaderBarDrawable(getApplicationContext()), R.layout.layout_public_toolbar_white);
    ```

- 打开HKMainActivity
    ```
    HKVideoLibrary.getInstance().start(getContext(), url, userName, passWord);
    ```
    url如：192.168.0.1:8086

