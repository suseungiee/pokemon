## Tech stack & Open-source libraries
- Minimum SDK level 28
- Version Catalog, Build-logic
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Compose - Modern toolkit for building native Android UI
- Architecture
  - Presentation: MVI (Model - View - Intent)
  - Repository pattern
  - Clean Architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - A modern JSON library for Kotlin
- [Coil](https://github.com/coil-kt/coil) - loading images.
  
## Architecture
Based on MVI, Clean Architecture and a repository pattern.

<img width="570" alt="image" src="https://github.com/suseungiee/pokemon/assets/52225690/5832cbc0-5fa1-435d-bfc0-f863ee0c0053">


| record |
:-:|
<video src="https://github.com/suseungiee/pokemon/assets/52225690/f15d8fa4-0846-4e52-9115-ebe0c165147e" width="250"/>|
