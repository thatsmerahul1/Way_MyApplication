package com.rahul.way_myapplication.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationAnnotation(val value: String)
