# Getting Started with Reactive Programming with Kotlin

## What is Async
 Asynchronous programming is a technique that allow us to run a processes or application outside the main/UI thread of execution.
## What is Reactive Programming
A tehchnique used to work with a high level of abstractions with data streams in a asynchronous way.
## Callback
 - A function that takes value parameters and a result handler function
 - Usually used in asynchronous computations
 - Can lead to callback hell
## Kotlin History
 - 2011 - Kotlin is announced by JetBrains
 - 2016 - Kotlin 1.0 is released
 - 2007 - Google announces Kotlin first class support for the Android platform
## Kotlin Key Features
 - Null-safety
 - String templates
 - Data classes
 - Extension functions and much more
## RxJava
 - A library for composing asynchronous and event-based programs
 - The most popular reactive library for the JVM
 - Originally developed at Netflix
 - Support Java 6+
 - Work 100% with kotlin
## Flowable
 - Main reactive type used in RxJava2 together with Observables
 - Complies with reactive streams specs and is backpressure ready
 - Used to declare streams of 0..N items
 - Used mainly for streams of high output (10k+ items)
 - The difference with Observable, is that Observable is not backpressure ready
 - **Flowable should be used when you are going to interact from 0..N items**
## Single
 - Used to refresent a stream of events  of one element
 - Formula - (onSucess(T value) || onError)
 - **Single should be used when the stream should have only one item**
## Completable
 - Represent a deferred computation that do not returns any value
 - It is used mainly for signaling completion or error of an deferred event
 - Formula - (onComplete || onError)
## Maybe
 - Combination between Single and Completable
 - Used to refresent a deferred computation that *Maybe* returns an element and complete or completes or returns an error
 - Formula - (onSuccess(T value) && onComplete || onComplete || onError)
## API Guidelines
 - If your public API can return more than one item or error use **Flowable**
 - If your public API can return only one item or error use **Single**
 - If your public API can return one item o no item or error use **Maybe**
 - If your public API won't return any item use **Completable**
## Why do We Need Schedulers?
 - Add multithreading to our RxJava operators
 - Allows us to have control on the threads where work is processed and where is delivered
 - Very useful for GUI and demanding web applications
 - RxJava is synchronous by default
## Schedulers
 - Schedulers allows us to have control over where subscriptions and or operator computations should be executed
 - *Where* meaning which thread
 - Thanks to RxJava and the scheduler interface, it is really straighforward to include concurrency in our streams
## Scheduler - newThread
 - Create a new thread for execution for each unit of work
 - Useful if you need to run your operation in a new fresh thread
## Scheduler - computation
 - The scheduler provides a thread pool of size == to the total number of processors available on the host
 - Not a good option for IO work
## Scheduler - io
 - Provides a scheduler used IO operations computation
 - It is backed with a single thread pool that tries at its best to reuse threads for best performance
## Scheduler - single
 - Create a new single thread for the whole flow of items
 - Useful if you need strongly order of items of the stream
## Scheduler - trampoline
 - Create a scheduler that execute receiving tasks/elements in FIFO manner
 - FIFO = First in First Out (queue)
## Scheduler - from(e: Executor)
 - Wraps the specified executor into an scheduler to use with RxJava
 - This option is ideal when you need the behavior of a specific executor to handle the concurenncy of your operators
