# E-Commerce Modular Android App

A production-oriented **modular Android e-commerce application** built with Kotlin, Jetpack Compose, Hilt, Coroutines, Flow, Retrofit, and a layered/feature-based architecture.

The project is designed to demonstrate how a large Android application can be divided into independent modules while keeping:

* Feature boundaries clear
* Dependencies controlled
* Navigation centralized
* Business logic testable
* UI reusable
* Networking and persistence isolated
* Dynamic features independently deliverable
* CI/CD automation ready
* The architecture scalable as the application grows

---

# Project Goal

The goal of this project is not only to build an e-commerce application, but to demonstrate how to build an Android application that can grow from a small application into a large modular system.

The architecture emphasizes:

```text
Modularity
    +
Separation of concerns
    +
Dependency inversion
    +
Testability
    +
Reusable infrastructure
    +
Feature isolation
    +
Scalable navigation
    +
Automated CI/CD
```

The application should remain maintainable as new features, developers, APIs, and infrastructure are added.

---

## Table of Contents

* [Architecture Overview](#architecture-overview)
* [Project Structure](#project-structure)
* [Module Responsibilities](#module-responsibilities)
* [Dependency Architecture](#dependency-architecture)
* [Application Layer](#application-layer)
* [Core Layer](#core-layer)
* [Feature Layer](#feature-layer)
* [Dynamic Features](#dynamic-features)
* [Feature Internal Architecture](#feature-internal-architecture)
* [Navigation Architecture](#navigation-architecture)
* [Product Navigation Flow](#product-navigation-flow)
* [Bottom Navigation](#bottom-navigation)
* [Data Flow](#data-flow)
* [Networking](#networking)
* [Repository Pattern](#repository-pattern)
* [Use Cases](#use-cases)
* [ViewModel and UI State](#viewmodel-and-ui-state)
* [Dependency Injection](#dependency-injection)
* [Build Logic](#build-logic)
* [Build Variants](#build-variants)
* [Environment Configuration](#environment-configuration)
* [Firebase Configuration](#firebase-configuration)
* [Testing](#testing)
* [CI/CD](#cicd)
* [Building Locally](#building-locally)
* [Git Workflow](#git-workflow)
* [Architecture Principles](#architecture-principles)
* [Future Extensions](#future-extensions)

---

# Architecture Overview

The application follows a **modular, feature-oriented architecture**.

At a high level:

```text
                         ┌──────────────────────┐
                         │      app-mobile      │
                         │ Application / Entry  │
                         └──────────┬───────────┘
                                    │
                    ┌───────────────┼────────────────┐
                    │               │                │
                    ▼               ▼                ▼
              ┌───────────┐   ┌────────────┐   ┌──────────────┐
              │  Feature  │   │ Dynamic    │   │    Core      │
              │  Modules  │   │ Features   │   │   Modules    │
              └─────┬─────┘   └────────────┘   └──────┬───────┘
                    │                                  │
                    └────────────────┬─────────────────┘
                                     │
                                     ▼
                              ┌─────────────┐
                              │ Infrastructure│
                              │ Network/DB/UI │
                              └─────────────┘
```

The main architectural idea is:

> **Features contain business functionality. Core contains reusable infrastructure. The app module composes everything together.**

---

# Project Structure

Current repository structure:

```text
E-Commerce-Modular-Android-App/
│
├── app-mobile/
│
├── build-logic/
│
├── core/
│   ├── analytics-api/
│   ├── analytics-firebase/
│   ├── common/
│   ├── database/
│   ├── model/
│   ├── navigation/
│   ├── network/
│   └── ui/
│
├── feature/
│   ├── auth/
│   ├── cart/
│   │   ├── cart-domain/
│   │   └── cart-mobile/
│   │
│   ├── checkout/
│   ├── common-product/
│   ├── common-product-ui/
│   ├── home/
│   ├── order/
│   ├── product/
│   └── profile/
│
├── dynamicfeature/
│   └── chat/
│
├── gradle/
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── local.properties
```

---

# Module Responsibilities

The project is divided into four major areas:

```text
app-mobile
    │
    ├── core
    │
    ├── feature
    │
    └── dynamicfeature
```

Each area has a different responsibility.

| Layer            | Responsibility                      |
| ---------------- | ----------------------------------- |
| `app-mobile`     | Application composition and startup |
| `core`           | Shared infrastructure               |
| `feature`        | Business features                   |
| `dynamicfeature` | On-demand functionality             |
| `build-logic`    | Centralized Gradle conventions      |

---

# Application Layer

## `app-mobile`

Location:

```text
app-mobile/
```

This is the **main Android application module**.

It is responsible for composing the application.

Typical responsibilities:

* Application class
* Main Activity
* Root Compose UI
* Application-level navigation
* Feature registration
* Bottom navigation
* App-wide dependency injection
* Build variants
* Firebase configuration
* Dynamic feature configuration

The application module should **orchestrate** features rather than contain their business logic.

Conceptually:

```text
app-mobile
     │
     ├── Application
     ├── MainActivity
     ├── MainScreen
     ├── MainNavHost
     ├── Bottom Navigation
     │
     └── Feature Navigation
```

---

# Core Layer

The `core` directory contains functionality that can be shared by multiple features.

```text
core/
├── analytics-api
├── analytics-firebase
├── common
├── database
├── model
├── navigation
├── network
└── ui
```

The core layer should not contain feature-specific business rules.

---

# Core Analytics

## `core/analytics-api`

Contains the abstraction for analytics.

Example:

```text
Analytics
   │
   ├── trackEvent()
   ├── trackScreen()
   └── setUserProperty()
```

Features depend on the abstraction rather than directly depending on Firebase.

This follows the dependency inversion principle.

```text
Feature
   │
   ▼
Analytics API
   │
   ▼
Firebase implementation
```

---

## `core/analytics-firebase`

Contains the Firebase implementation of the analytics API.

```text
analytics-api
      ▲
      │
      │ implements
      │
analytics-firebase
```

This allows Firebase to be replaced later without modifying feature modules.

For example:

```text
analytics-api
      │
      ├── analytics-firebase
      │
      ├── analytics-aws
      │
      └── analytics-test
```

---

# Core Common

## `core/common`

Contains small reusable utilities that don't belong to a specific feature.

Examples:

```text
Extensions
Constants
Result helpers
Coroutine helpers
Date utilities
String utilities
```

This module should remain lightweight.

Avoid turning `common` into a dumping ground for unrelated functionality.

---

# Core Database

## `core/database`

Contains local persistence infrastructure.

Possible responsibilities:

```text
Room
DAOs
Entities
Database
Migrations
Local data sources
```

Architecture:

```text
Feature
   │
   ▼
Repository
   │
   ▼
Local Data Source
   │
   ▼
Room Database
```

The database implementation should not leak into the UI.

---

# Core Model

## `core/model`

Contains shared domain/data models.

For example:

```kotlin
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)
```

The purpose of this module is to provide shared models without forcing features to depend on implementation details of another feature.

---

# Core Navigation

## `core/navigation`

Contains navigation abstractions.

The navigation module should not contain individual feature UI implementations.

Instead, it defines contracts such as:

```text
FeatureNavigation
AppRoutes
Navigation helpers
```

A feature can expose its navigation contract while `app-mobile` owns the application-level navigation graph.

This keeps navigation responsibilities separated.

---

# Core Network

## `core/network`

Contains networking infrastructure.

Typical components:

```text
Retrofit
OkHttp
Interceptors
NetworkResult
API configuration
Network error handling
Serialization
```

Example:

```text
Feature
   │
   ▼
Repository
   │
   ▼
ProductApi
   │
   ▼
Retrofit
   │
   ▼
HTTP API
```

Features should not create Retrofit instances themselves.

---

# Core UI

## `core/ui`

Contains reusable Compose UI components.

Examples:

```text
LoadingIndicator
ErrorView
CommonButton
CommonTextField
ProductCard
CommonTopBar
```

Feature-specific UI should remain inside the feature module.

---

# Feature Layer

The feature layer contains business functionality.

Current features:

```text
feature/
├── auth
├── cart
├── checkout
├── common-product
├── common-product-ui
├── home
├── order
├── product
└── profile
```

Each feature owns its own functionality.

---

# Feature: Authentication

```text
feature/auth/
```

Responsible for:

* Login
* Registration
* Logout
* Authentication state
* Session handling
* Authentication UI

---

# Feature: Cart

```text
feature/cart/
├── cart-domain/
└── cart-mobile/
```

The cart feature demonstrates separation between business logic and Android/UI code.

```text
cart-domain
     │
     ├── Use Cases
     ├── Repository interfaces
     └── Domain logic
     
cart-mobile
     │
     ├── Compose UI
     ├── ViewModels
     └── Navigation
```

The dependency direction is:

```text
cart-mobile
     │
     ▼
cart-domain
```

Not:

```text
cart-domain
     │
     ▼
cart-mobile
```

This allows domain logic to remain independent from Android UI.

---

# Feature: Checkout

```text
feature/checkout/
```

Responsible for:

* Checkout UI
* Address selection
* Payment selection
* Order confirmation
* Checkout business logic

Possible future structure:

```text
checkout/
├── checkout-domain/
├── checkout-data/
└── checkout-mobile/
```

---

# Feature: Product

```text
feature/product/
```

Responsible for product-related functionality.

Examples:

```text
Product List
Product Details
Product Search
Product Categories
```

Product details flow:

```text
Product List
     │
     │ product.id
     ▼
Product Details Route
     │
     ▼
ProductDetailsViewModel
     │
     ▼
GetProductDetailsUseCase
     │
     ▼
ProductRepository
     │
     ▼
ProductApi
     │
     ▼
Backend
```

---

# Common Product

## `feature/common-product`

Contains reusable product-related business components.

This prevents multiple features from duplicating product logic.

For example:

```text
Product
ProductRepository
Product mapper
Product use cases
Product contracts
```

---

# Common Product UI

## `feature/common-product-ui`

Contains reusable product UI components.

For example:

```text
ProductCard
ProductImage
PriceView
RatingView
ProductListItem
```

This allows:

```text
Home
Product
Search
Cart
Recommendations
```

to reuse the same product UI components.

---

# Feature: Home

```text
feature/home/
```

Home is responsible for the application's main product discovery experience.

Typical structure:

```text
home/
├── home-domain/
└── home-mobile/
```

The home UI can display:

```text
Banner
Categories
Products
Recommendations
```

Home should not directly own application-level navigation.

Instead it exposes navigation events:

```kotlin
onProductClick(product)
onCartClick()
```

The app navigation layer decides what happens next.

---

# Feature: Order

```text
feature/order/
```

Responsible for:

```text
Order List
Order Details
Order status
Order history
```

---

# Feature: Profile

```text
feature/profile/
```

Responsible for:

```text
Profile
Addresses
Account settings
Preferences
```

---

# Dynamic Features

```text
dynamicfeature/
└── chat/
```

Dynamic features are functionality that can be delivered separately/on demand.

For example:

```text
Main App
   │
   ├── Home
   ├── Product
   ├── Cart
   └── Checkout
         
          │
          ▼
      Chat Feature
```

The chat feature can be installed when required rather than being bundled as normal application code.

The application module remains responsible for integrating the dynamic feature.

---

# Feature Internal Architecture

A feature should generally follow:

```text
             UI
              │
              ▼
          ViewModel
              │
              ▼
          Use Case
              │
              ▼
         Repository
              │
        ┌─────┴─────┐
        ▼           ▼
      Remote       Local
        │           │
        ▼           ▼
      API          DB
```

For example:

```text
ProductDetailsScreen
        │
        ▼
ProductDetailsViewModel
        │
        ▼
GetProductDetailsUseCase
        │
        ▼
ProductRepository
        │
        ▼
ProductRepositoryImpl
        │
        ▼
ProductApi
        │
        ▼
Backend API
```

---

# Navigation Architecture

Navigation is centralized at the application level.

The application owns:

```text
NavController
NavHost
Application Routes
Bottom Navigation
```

Features expose navigation events rather than directly controlling application navigation.

Example:

```kotlin
ProductListRoute(
    onProductClick = { product ->
        navController.navigate(
            productDetailsRoute(product.id)
        )
    }
)
```

The feature tells the application:

> "The user selected this product."

The application decides:

> "Navigate to product details."

This prevents feature modules from becoming tightly coupled to the application's navigation graph.

---

# Product Navigation Flow

The complete flow is:

```text
Home
 │
 │ User clicks product
 ▼
ProductList/ProductCard
 │
 │ product.id
 ▼
productDetailsRoute(product.id)
 │
 ▼
NavHost
 │
 ▼
ProductDetailsRoute
 │
 ▼
ProductDetailsViewModel
 │
 │ productId
 ▼
GetProductDetailsUseCase
 │
 ▼
ProductRepository
 │
 ▼
ProductApi
 │
 ▼
GET /products/{id}
 │
 ▼
Product
 │
 ▼
ProductDetailsUiState
 │
 ▼
ProductDetailsScreen
```

The important principle is that the screen does **not** fetch the API directly.

---

# Bottom Navigation

The application contains the global bottom navigation.

Typical destinations:

```text
Home
Cart
Orders
Profile
```

Conceptually:

```text
┌──────────────────────────────────────────────┐
│                                              │
│                 NavHost                      │
│                                              │
│          Current Screen Content              │
│                                              │
├──────────────────────────────────────────────┤
│ Home │ Cart │ Orders │ Profile               │
└──────────────────────────────────────────────┘
```

The bottom bar belongs to the application shell because it is global navigation.

---

# Full-Screen Destinations

Some destinations should not display the bottom bar.

Examples:

```text
Product Details
Checkout
Order Details
Profile Details
Payment
```

The application determines whether the current route requires the bottom bar.

Conceptually:

```text
currentRoute
     │
     ├── HOME
     │     └── Bottom Bar
     │
     ├── CART
     │     └── Bottom Bar
     │
     ├── ORDERS
     │     └── Bottom Bar
     │
     ├── PROFILE
     │     └── Bottom Bar
     │
     └── PRODUCT_DETAILS
           └── No Bottom Bar
```

The important point is that the `Scaffold` should not reserve bottom-bar space when the bottom bar is not required.

---

# Data Flow

The application follows unidirectional data flow.

```text
User Action
    │
    ▼
Composable
    │
    ▼
ViewModel
    │
    ▼
Use Case
    │
    ▼
Repository
    │
    ▼
Data Source
    │
    ▼
Backend / Database
    │
    ▼
Repository
    │
    ▼
Use Case
    │
    ▼
ViewModel
    │
    ▼
UiState
    │
    ▼
Composable
```

Example:

```text
Click "Add to Cart"
        │
        ▼
ViewModel
        │
        ▼
AddToCartUseCase
        │
        ▼
CartRepository
        │
        ▼
Database/API
        │
        ▼
CartUiState
        │
        ▼
Compose UI
```

---

# Networking

The network stack is centralized in:

```text
core/network
```

A typical request flow is:

```text
ProductRepositoryImpl
        │
        ▼
ProductApi
        │
        ▼
Retrofit
        │
        ▼
OkHttp
        │
        ▼
Backend
```

The API interface remains simple:

```kotlin
@GET("products/{id}")
suspend fun getProductDetail(
    @Path("id") id: Int
): Product
```

---

# NetworkResult

Network operations can expose a common result abstraction:

```kotlin
sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ) : NetworkResult<T>

    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : NetworkResult<Nothing>

    data object Loading : NetworkResult<Nothing>
}
```

This allows the UI layer to represent:

```text
Loading
Success
Error
```

without knowing Retrofit implementation details.

---

# Repository Pattern

The repository separates business logic from data sources.

Example:

```text
ProductRepository
       ▲
       │
       │ implements
       │
ProductRepositoryImpl
       │
       ▼
ProductApi
```

The interface can live at the appropriate abstraction/domain boundary:

```kotlin
interface ProductRepository {

    suspend fun getProductById(
        productId: Int
    ): NetworkResult<Product>
}
```

Implementation:

```kotlin
class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository
```

This allows the implementation to change without changing consumers.

For example:

```text
ProductRepository
       │
       ├── ProductRepositoryImpl
       ├── FakeProductRepository
       └── CachedProductRepository
```

---

# Use Cases

Use cases represent business operations.

Example:

```kotlin
class GetProductDetailsUseCase(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(
        productId: Int
    ): NetworkResult<Product> {

        if (productId == 0) {
            return NetworkResult.Error(
                "Product ID cannot be empty"
            )
        }

        return productRepository.getProductById(productId)
    }
}
```

The ViewModel does not need to know how the product is retrieved.

It only knows:

```text
Get product details
```

---

# ViewModel and UI State

The ViewModel owns screen state.

Example:

```text
ProductDetailsViewModel
        │
        ▼
ProductDetailsUiState
```

A typical state could be:

```kotlin
data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)
```

The screen observes the state:

```text
ViewModel
    │
    ▼
StateFlow<ProductDetailsUiState>
    │
    ▼
Compose
```

The UI should render the state instead of performing business operations itself.

---

# Dependency Injection

The project uses **Hilt** for dependency injection.

Typical dependency chain:

```text
ProductDetailsViewModel
        │
        ▼
GetProductDetailsUseCase
        │
        ▼
ProductRepository
        │
        ▼
ProductRepositoryImpl
        │
        ▼
ProductApi
        │
        ▼
Retrofit
```

Hilt constructs these dependencies.

The application therefore avoids manual object creation such as:

```kotlin
val retrofit = Retrofit.Builder()...
val api = retrofit.create(ProductApi::class.java)
val repository = ProductRepositoryImpl(api)
```

Instead, dependencies are provided through Hilt modules.

---

# Build Logic

```text
build-logic/
```

contains reusable Gradle conventions.

Instead of duplicating configuration in every module:

```kotlin
plugins {
    id(...)
}

android {
    ...
}
```

the project can define convention plugins.

For example:

```text
myapp.android.application
myapp.android.library
myapp.android.compose
myapp.android.feature
```

This gives the project centralized build configuration.

Benefits:

* Less duplication
* Consistent configuration
* Easier upgrades
* Easier module creation
* Centralized Android configuration

---

# Build Variants

The application currently has two flavors:

```text
dev
prod
```

and two build types:

```text
debug
release
```

Therefore the project produces:

```text
devDebug
devRelease
prodDebug
prodRelease
```

Application IDs can be configured as:

```text
Dev Debug
com.samir.ecommerceapp.dev.debug

Dev Release
com.samir.ecommerceapp.dev

Prod Debug
com.samir.ecommerceapp.debug

Prod Release
com.samir.ecommerceapp
```

The exact application IDs depend on the base application ID configured in the project.

---

# Environment Configuration

Environment-specific configuration should be separated from application logic.

Conceptually:

```text
dev
 │
 ├── Development API
 ├── Development Firebase
 └── Development configuration

prod
 │
 ├── Production API
 ├── Production Firebase
 └── Production configuration
```

This allows the same source code to run against different environments.

---

# Firebase Configuration

Because Firebase is configured per application ID, the project supports flavor-specific Firebase configuration.

Recommended structure:

```text
app-mobile/
└── src/
    ├── dev/
    │   └── google-services.json
    │
    ├── prod/
    │   └── google-services.json
    │
    └── main/
```

The Firebase Android application IDs must correspond to the application's actual variant IDs.

For example:

```text
devDebug
    ↓
com.samir.ecommerceapp.dev.debug
```

If Firebase configuration is only available for development, build:

```bash
./gradlew :app-mobile:assembleDevDebug
```

---

# Testing

Testing is divided into multiple levels.

## Unit Tests

Unit tests validate business logic:

```text
Use Cases
ViewModels
Repositories
Mappers
Utilities
```

Run:

```bash
./gradlew test
```

---

## Lint

Run:

```bash
./gradlew lint
```

Lint helps identify:

* Android API issues
* Resource problems
* Code quality issues
* Manifest issues
* Potential bugs

---

## Instrumentation Tests

Instrumentation tests run on an Android device/emulator.

Run:

```bash
./gradlew connectedCheck
```

Typical tests:

```text
Navigation
Compose UI
Database
Integration flows
```

---

# CI/CD

The project is designed to run automatically in CI.

A typical pipeline:

```text
              ┌─────────────┐
              │   Checkout  │
              └──────┬──────┘
                     │
          ┌──────────┼───────────┐
          ▼          ▼           ▼
      Unit Test     Lint   Instrumentation
          │          │           │
          └──────────┼───────────┘
                     ▼
                  Build APK
                     │
                     ▼
               Upload Artifact
```

For development CI, the explicit variant should be used:

```bash
./gradlew :app-mobile:assembleDevDebug
```

The resulting APK is located under:

```text
app-mobile/build/outputs/apk/dev/debug/
```

---

# Gradle Wrapper Verification

The project uses the Gradle Wrapper:

```text
gradlew
gradlew.bat
gradle/wrapper/
```

The wrapper distribution can be protected using:

```properties
distributionSha256Sum=...
```

This verifies that the downloaded Gradle distribution matches the expected checksum.

Never remove checksum verification simply to bypass a CI failure.

---

# Building Locally

## Clone the repository

```bash
git clone <repository-url>
cd E-Commerce-Modular-Android-App
```

---

## Make Gradle Wrapper executable

macOS/Linux:

```bash
chmod +x gradlew
```

---

## Run unit tests

```bash
./gradlew test
```

---

## Run lint

```bash
./gradlew lint
```

---

## Build development debug APK

```bash
./gradlew :app-mobile:assembleDevDebug
```

---

## Build development release APK

```bash
./gradlew :app-mobile:assembleDevRelease
```

---

## Build production debug APK

```bash
./gradlew :app-mobile:assembleProdDebug
```

---

## Build production release APK

```bash
./gradlew :app-mobile:assembleProdRelease
```

Production release builds may additionally require signing configuration.

---

# Git Workflow

Before committing changes:

```bash
git status
```

Review the changes:

```bash
git diff
```

Then validate:

```bash
./gradlew test
./gradlew lint
./gradlew :app-mobile:assembleDevDebug
```

If validation succeeds:

```bash
git add .
git commit -m "your commit message"
git push
```

---

# Architecture Principles

The project follows several important architectural principles.

## 1. Separation of Concerns

Each module has a clearly defined responsibility.

```text
UI
Business Logic
Data
Infrastructure
```

should not be unnecessarily mixed together.

---

## 2. Dependency Inversion

High-level business logic depends on abstractions.

```text
Use Case
   │
   ▼
Repository Interface
   ▲
   │
Repository Implementation
```

not:

```text
Use Case
   │
   ▼
Retrofit API
```

---

## 3. Feature Isolation

A feature should not directly depend on another feature's internal implementation.

Prefer:

```text
feature A
   │
   ▼
core/common abstraction
```

over:

```text
feature A
   │
   ▼
feature B internal implementation
```

---

## 4. Application Owns Composition

The application module is responsible for composing the system.

```text
app-mobile
     │
     ├── Navigation
     ├── DI
     ├── Feature registration
     └── Application startup
```

Features provide functionality.

The app decides how everything is assembled.

---

## 5. UI Does Not Own Business Logic

Avoid:

```text
Composable
   │
   └── Retrofit call
```

Prefer:

```text
Composable
   ↓
ViewModel
   ↓
Use Case
   ↓
Repository
   ↓
API
```

---

## 6. State-Driven UI

Compose UI should be driven by state:

```text
UiState
   ↓
Composable
```

rather than manually manipulating UI from business logic.

---

## 7. Reusable Infrastructure

Cross-cutting infrastructure belongs in `core`.

Examples:

```text
Network
Database
Analytics
UI
Navigation
Models
Common utilities
```

---

# Dependency Direction

A simplified dependency graph is:

```text
                    app-mobile
                       │
          ┌────────────┼─────────────┐
          │            │             │
          ▼            ▼             ▼
      features     dynamicfeature    core
          │            │             │
          └────────────┼─────────────┘
                       │
                       ▼
                 infrastructure
```

More specifically:

```text
app-mobile
    │
    ├── feature/home
    ├── feature/product
    ├── feature/cart
    ├── feature/order
    ├── feature/profile
    ├── feature/checkout
    ├── feature/auth
    │
    ├── dynamicfeature/chat
    │
    └── core/*
```

The goal is to prevent circular dependencies such as:

```text
feature/home
      ↓
feature/product
      ↓
feature/home
```

---

# Example Product Architecture

A complete product feature can be visualized as:

```text
┌────────────────────────────────────────────┐
│                Product UI                  │
│                                            │
│ ProductListScreen                           │
│ ProductDetailsScreen                       │
└──────────────────────┬─────────────────────┘
                       │
                       ▼
┌────────────────────────────────────────────┐
│                 ViewModel                  │
│                                            │
│ ProductDetailsViewModel                    │
└──────────────────────┬─────────────────────┘
                       │
                       ▼
┌────────────────────────────────────────────┐
│                 Use Case                   │
│                                            │
│ GetProductDetailsUseCase                   │
└──────────────────────┬─────────────────────┘
                       │
                       ▼
┌────────────────────────────────────────────┐
│               Repository                   │
│                                            │
│ ProductRepository                          │
└──────────────────────┬─────────────────────┘
                       │
                       ▼
┌────────────────────────────────────────────┐
│          Repository Implementation         │
│                                            │
│ ProductRepositoryImpl                      │
└──────────────────────┬─────────────────────┘
                       │
                       ▼
┌────────────────────────────────────────────┐
│                 Retrofit                  │
│                                            │
│ ProductApi                                 │
└──────────────────────┬─────────────────────┘
                       │
                       ▼
                  Backend API
```

---

# Example Product Details Request

When the user opens:

```text
/products/10
```

the flow is:

```text
User
 │
 ▼
Product Card
 │
 │ id = 10
 ▼
Navigation
 │
 ▼
ProductDetailsRoute
 │
 ▼
ProductDetailsViewModel
 │
 │ productId = 10
 ▼
GetProductDetailsUseCase
 │
 ▼
ProductRepository
 │
 ▼
ProductRepositoryImpl
 │
 ▼
ProductApi
 │
 ▼
GET /products/10
 │
 ▼
Product
 │
 ▼
NetworkResult.Success
 │
 ▼
ProductDetailsUiState
 │
 ▼
ProductDetailsScreen
```

---

# Scaling the Project

As the application grows, individual features can become more internally modular.

For example:

```text
feature/product/
├── product-domain/
├── product-data/
├── product-ui/
└── product-navigation/
```

Similarly:

```text
feature/order/
├── order-domain/
├── order-data/
├── order-ui/
└── order-navigation/
```

This allows teams to work on features independently.

---

# Future Architecture

The project can eventually evolve toward:

```text
                         app-mobile
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
          ▼                  ▼                  ▼
       Features        Dynamic Features       Core
          │                                      │
          │                              ┌───────┼────────┐
          │                              │       │        │
          │                              ▼       ▼        ▼
          │                           Network  DB     Analytics
          │
          ▼
       Domain
          │
          ▼
        Data
          │
       ┌──┴───┐
       ▼      ▼
    Remote   Local
```

Potential future modules:

```text
core/
├── security
├── logging
├── feature-flags
├── remote-config
├── crash-reporting
└── testing

feature/
├── search
├── wishlist
├── reviews
├── recommendations
└── payments
```

---

# Technology Stack

The project is built around modern Android technologies.

| Technology              | Purpose                                     |
| ----------------------- | ------------------------------------------- |
| Kotlin                  | Primary language                            |
| Jetpack Compose         | UI                                          |
| Coroutines              | Asynchronous programming                    |
| Flow                    | Reactive state/data streams                 |
| ViewModel               | UI state/business coordination              |
| Hilt                    | Dependency injection                        |
| Retrofit                | HTTP API                                    |
| OkHttp                  | HTTP client                                 |
| Room                    | Local persistence                           |
| Gradle Kotlin DSL       | Build configuration                         |
| Convention Plugins      | Centralized build logic                     |
| Firebase                | Analytics/backend services where configured |
| Android Dynamic Feature | On-demand features                          |
| GitHub Actions          | CI/CD                                       |

---

