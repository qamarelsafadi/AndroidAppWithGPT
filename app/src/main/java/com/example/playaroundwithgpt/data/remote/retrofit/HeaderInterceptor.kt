package com.example.playaroundwithgpt.data.remote.retrofit

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        try {
            request = request.newBuilder()
                .addHeader("accept-encoding", "gzip, deflate, br")
                .addHeader("accept", "text/event-stream")
                .addHeader("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
                .addHeader("content-type", "application/json")
                .addHeader("referer", "https://chat.openai.com/chat")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .apply {  addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiJxYW1hci5lbHNhZmFkaTk4QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJnZW9pcF9jb3VudHJ5IjoiVVMifSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InVzZXJfaWQiOiJ1c2VyLXFSRzNhSkZTNFAwQUxPak9ETDB1c1M5YiJ9LCJpc3MiOiJodHRwczovL2F1dGgwLm9wZW5haS5jb20vIiwic3ViIjoiYXV0aDB8NjM5NjEyOGZjMzNiYTRmMjIwN2RiNmU3IiwiYXVkIjpbImh0dHBzOi8vYXBpLm9wZW5haS5jb20vdjEiLCJodHRwczovL29wZW5haS5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjcwNzc5NzE5LCJleHAiOjE2NzA4MjI5MTksImF6cCI6IlRkSkljYmUxNldvVEh0Tjk1bnl5d2g1RTR5T282SXRHIiwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSBtb2RlbC5yZWFkIG1vZGVsLnJlcXVlc3Qgb3JnYW5pemF0aW9uLnJlYWQgb2ZmbGluZV9hY2Nlc3MifQ.Y_sVyJu7Izwo5cATJnVJLLSZfgIet4rTBmKuAcZtZ3QJtXOXniEsAoBuZ-rwu79NJEFzTJAU0IDWIbSzo0qprQbZXUp2TRlNd5psGs42T7rt0HrSkKbWZNoXTOrGnnY0R-DSbKOcM9Ig8EYr_Za3scGOjB7vNP4JbZFWTdhI6Gjg4zIX_5Mvo0nBDQpwotOj9SPFj0snu7azt7aZAy_NQNGQgkSvwQOrKr3bqphEaJTIs-1EilecodN1jUpp2oz4CYsmQ0ZVeejnx3Lo-CJiiFJiJYEqqI-zqcMxeWIwdYGBAosodh0VOsrgQmG22fnPEBEVxXgopWAbVrfapYAQcA")  }
                .build()

        }catch (e:Exception){
            e.printStackTrace()
        }
        return chain.proceed(request)

    }
}