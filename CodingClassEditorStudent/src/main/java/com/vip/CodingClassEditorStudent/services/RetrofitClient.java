package com.vip.CodingClassEditorStudent.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {
	private static Retrofit retrofit = null;
	static OkHttpClient client;
	
	static CookieJar cookieJar = new CookieJar() {

	    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

	    @Override
	    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
	        // here you get the cookies from Response
	        cookieStore.put(url.host(), cookies);
	    }

	    @Override
	    public List<Cookie> loadForRequest(HttpUrl url) {
	        List<Cookie> cookies = cookieStore.get(url.host());
	        return cookies != null ? cookies : new ArrayList<Cookie>();
	    }

		
	};

    public static Retrofit getClient(String url){
    	
    	client = new OkHttpClient.Builder()
    			.cookieJar(cookieJar)
                .build();
    	
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static OkHttpClient getClient() {
		return client;
	}
}
