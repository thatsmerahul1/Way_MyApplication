package com.rahul.way_myapplication.interceptors

import com.rahul.way_myapplication.annotations.AuthenticationAnnotation
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader = invocation
            .method()
            .annotations
            .any { it.annotationClass == AuthenticationAnnotation::class }

        return if (shouldAttachAuthHeader) {
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("authtoken", "authtoken")
                    .build()
            )
        }
        else chain.proceed(chain.request())
    }
}