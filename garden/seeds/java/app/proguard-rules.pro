
# Generic

-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature

# Support library

-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }