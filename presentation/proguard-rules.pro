# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



# ====================== [class and google] ========================
-keep class com.google.android.** { *; }
-keep class google.** { *; }
-keep class android.** { *; }
-keep class androidx.** { *; }
-keep class com.android.** { *; }
-keep class com.google.** { *; }
-keep class lambda* { *; }
-keepclassmembers enum * {
    values(...);
    valueOf(...);
}
-keepclasseswithmembers class * {
    native <methods>;
}

-keep public class * extends android.app.Activity { *; }
-keep public class * extends androidx.activity.** { *; }
-keep public class * extends androidx.appcompat.view.** { *; }
-keep public class * extends android.app.Application { *; }
-keep public class * extends androidx.appcompat.** {*; }
-keep public class * extends android.app.Service { *; }
-keep public class * extends android.content.BroadcastReceiver { *; }
-keep public class * extends android.content.ContentProvider { *; }
-keep public class * extends android.app.backup.BackupAgentHelper { *; }
-keep public class * extends android.preference.Preference { *; }
-keep public class * extends android.widget.TextView { *; }
-keep public class * extends android.widget.Button { *; }
-keep public class * extends androidx.fragment.app.** { *; }
# ==================================================================

# ==================== [ok http and retrofit] ======================
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keep class retrofit2.** { *; }
# ==================================================================


# ===================== [webview javascript] =======================
-keepclassmembers class * {
	@android.webkit.JavascriptInterface <methods>;
}
# ==================================================================


# ========================= [tedpermission] =========================
-dontwarn com.gun0912.tedpermission.**
-keep class com.gun0912.tedpermission.** { *; }
-keep interface com.gun0912.tedpermission.** { *; }
# ==================================================================


# ========================== [glide image] =========================
-dontwarn com.bumptech.glide.**
-keepclassmembers class com.bumptech.glide.** { *; }
-keep interface com.bumptech.glide.** { *; }
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# ==================================================================

# ========================== [data store] =========================
# DataStore 패키지에 대한 보존 규칙
-keep class androidx.datastore.** { *; }

# DataStore 패키지를 제외한 다른 패키지들에 대한 축소화와 난독화 적용
-keep class !androidx.datastore.** {
    <methods>;          # 클래스 내의 메서드 보존
    <fields>;           # 클래스 내의 필드 보존
}
# ==================================================================


-keep class **.R$*

# kakao login
-keep class com.kakao.sdk.**.model.* { <fields>; }

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

-dontwarn org.xmlpull.v1.**
-dontwarn android.content.res.XmlResourceParser

-keep class java.sql.** { *; }
-keep class org.sqlite.** { *; }

-dontwarn javax.**
-dontwarn java.awt.**
-dontwarn com.sun.**
-dontwarn android.databinding.**
-dontwarn org.antlr.stringtemplate.**
-dontwarn java.lang.reflect.**
