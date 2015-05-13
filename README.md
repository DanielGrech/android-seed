android-seed
========

A template/bootstrap Android app preconfigured with some common classes &amp; libraries

A seed for new Android projects with some nice structure/libraries already set up

Usage
-----

<pre>
  <code>
    grow.py [-h]
               COMPANY_NAME PACAKGE_PREFIX APPLICATION_ID PREFIX
               COMPILE_SDK_VERSION MIN_SDK_VERSION TARGET_SDK_VERSION

Grow a nice Android app from this beautiful seed

positional arguments:
  COMPANY_NAME         the name of your company. Used in the class package
                       names
  PACAKGE_PREFIX       The name of the application. Used in the class package
                       names
  APPLICATION_ID       the name of the generated package
  PREFIX               prefix for import classes. Eg 'Weather' for WeatherApp
                       and WeatherContentProvider
  COMPILE_SDK_VERSION  Sdk version to use to compile the app. Eg 19
  MIN_SDK_VERSION      Minimum sdk version the app targets. Eg 14
  TARGET_SDK_VERSION   Target sdk version the app targets. Eg 19

optional arguments:
  -h, --help           show this help message and exit
  </code>
</pre>

Example:

`./grow.py dgsd weather com.dgsd.android.weatherapp Weather 22 14 22`