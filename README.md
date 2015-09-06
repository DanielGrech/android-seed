android-seed
========

A seed for new Android projects with some nice structure/libraries already set up.

The seed is generated using a json configuration file, or by interfacing with the web interface (running at http://android-seed.dgrech.com/).

Repo Structure
--------------

The repository is structured as such:

- `greenhouse`:

	> Houses a python ([flask](http://flask.pocoo.org/)) web app which wraps the generation of the seed projects with a simple interface
	> Uses Google's [Material Design Lite](https://github.com/google/material-design-lite) for some nice styling

	> Running a local server is as simple as `python greenhouse.py`

- `garden`:

	> Contains a python script (`grow.py`) which is responsible for generating a seed project. This script can be used via the web interface as is also available as a standalone script on the command line:

	> `cat garden/sample_config.json | garden/grow.py`
	
	> The `seeds/` directory contains the template project for each different language. This is where you can modify the output of the generation. The seed generation utilises the [Pystache templating engine](https://github.com/defunkt/pystache) 	

Seed projects
-------------

As of writing, there are seed projects available in both Java and Kotlin. Each seed takes advantage of language-specific features where possible, though the overall architecture remains the same.

The seed projects use the gradle build system and are split across 3 gradle modules:

- `model`:
	
	> Java-only module which holds simple model objects. Classes in this module should hold no Android-specific logic (and cant - as the whole module isn't compiled against the Android SDK). As much business logic as possible should be pushed to this layer, as these simple classes are often simpler and faster to test. The seed projects encourage the use of the [Immutables](http://immutables.github.io/) library. Immutable models lead to less shared state and this library can use code generation to reduce boilerplate code directly in the repository.

	There is no reason that this module cannot acheive 100% unit-test coverage of any custom logic.

- `network`:

	> Java-only module which holds all logic to mask access to any data-resources. In simple apps, the function of this module is to retrieve data from a server, convert it into a model found in the `models` module (a dependency of this module) and pass it back to it's caller. In the seed projects, this is a java-only module to encourage a clean separation between application logic and that which manages network communication/parsing/transforming. This module makes use of the [Retrofit](http://square.github.io/retrofit/) library for networking and [GSON](https://github.com/google/gson) for json transformations. It often contains API-specific model code of its own, but these should be seen as an implementation detail of the module and not exposed to downstream users. Only classes defined in the `models` module should be exposed.

	Note that if a database caching layer is required in the app, this module would be a good candidate for housing it. This would involve transforming the module into an `android-library` project in order to get access to database resources (Be it SQLite, Realm, Sprinkes etc). No database calls should be exposed outside of this module - all should be masked behind the consistent `DataStore` interface.

	This module can be tested using traditional JUnit tests. The [OkHttp MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) provides useful functionality for testing end-to-end functionality in conjunction with canned json responses.

- `app`:

	> Android module, with a dependency on the previous two modules which houses the actual application code. The seed contains some utilities and base classes which should all be straightforward in use. The module uses the Model-View-Presenter pattern described in detail [here](http://hannesdorfmann.com/android/mosby/). The basis of this pattern is to separate as much business logic as possible from its Android-specific implementation. Each Activity/Fragment implements in an interface in the package `.mvp.view.*` is is manipulated through this interface by a `Presenter` subclass in `.mvp.presenter.*`. Presenters should be responsible for all network calls, business logic and other view-agnositic functionality. This does not mean that presenters should be monolithic classes, with proper delegation of responsibilities still adhered too. 

	Activity/Fragment subclasses should become simple shells which implement simple functionality as defined in its associated MVP interface.

	Following this pattern greatly reduces the burden of testing business-logic. Simple JUnit tests can be used on Presenters with mock implementations of the view interface. Robolectric, whilst available as a depencency, should be used a sparingly as possible.

Popular libraries used in the seed projects include:

- RxJava (For asynchronous modelling/inter-app communication. Includes a range of companion RxAndroid dependencies)
- Dagger 2 (For dependency injection)
- Immutables (For immutable model generation)
- Calligraphy (For custom font management)
- EasyDataStore (For declarative SharedPreferences interactions)
- Timber (For logging)
- Picasso (For image fetching)
- YearClass (For device capability assessment)
- Stetho/LeakCanary (For debuggability)
- Retrofit/OkHttp (For networking)
- Butterknife (For view binding)
- Anko (For view utilities - Kotlin Only)
- Mockito/Robolectric/Espresso/Spoon/Jacoco (For testing)
- Crashlytics (For crash reporting)


