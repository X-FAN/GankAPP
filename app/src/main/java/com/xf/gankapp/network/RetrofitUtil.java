package com.xf.gankapp.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.xf.gankapp.BuildConfig;
import com.xf.gankapp.GankAPI;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by X-FAN on 2016/6/21.
 */
public class RetrofitUtil {
    private RetrofitUtil() {

    }

    public static volatile Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 确保该方法在Application类中调用一次
     *
     * @param
     * @return
     */
    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder().baseUrl(GankAPI.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getClient())
                            .build();
                }

            }
        }
        return retrofit;
    }


    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.networkInterceptors().add(new StethoInterceptor());
        }
        return builder.build();
    }

    private static OkHttpClient getUnsafeOkHttpClient() throws NoSuchAlgorithmException {

        final SSLContext sslContext;
        sslContext = SSLContext.getInstance("SSL");
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        return new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }).hostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;

            }
        }).build();
    }

}
