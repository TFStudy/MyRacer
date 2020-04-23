package com.example.net;

import android.text.TextUtils;
import android.util.Log;

import com.example.net.common.Config;
import com.example.net.model.api.TokenApi;
import com.example.net.model.protocol.TokenRespEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static volatile RetrofitFactory instance=null;
    private Retrofit retrofit;
    private OkHttpClient.Builder builder;

    private RetrofitFactory(){
        initRetrofit();
    }

    /**
     * Double Check单例  解决多线程引起的并发问题
     * @return
     */
    public static RetrofitFactory getInstance(){
        if (null==instance){
            synchronized (RetrofitFactory.class){
                if (null==instance){
                    instance=new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 创建/初始化Retrofit
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkhttpClient())
                .build();
    }

    /**
     *
     * @return
     */
    private OkHttpClient createOkhttpClient() {

        return getBuilder()
                .addNetworkInterceptor(createLogInterceptor())
                .addInterceptor(createRequestInterceptor())
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    /**
     * 创建OkHttp的请求日志拦截器
     * @return
     */
    private Interceptor createLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * 创建OkHttpClient.Builder
     * @return
     */
    private OkHttpClient.Builder getBuilder(){
        if (null==builder){
            builder = new OkHttpClient.Builder();
        }

        return builder;
    }




    /**
     * 创建服务
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return  retrofit.create(service);
    }


    /*******************************Token*************************************/

    private Interceptor createRequestInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                //如果是401 同步请求Token然后加载到新请求的Header里，重新发起业务请求
                if (checkHttpCode401(response)){
                    String token=requestToken();
                    if (TextUtils.isEmpty(token)){
                        Log.e("123","Error : token is null...");
                        return response;
                    }
                    //Request newRequest=chain.request();
                    Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer " + token);

                    Request newRequest=newBuilder.build();
                    return chain.proceed(newRequest);
                }
                return response;
            }
        };

        return interceptor;
    }

    /**
     * 判断HTTP CODE 是否401 —— TOKEN失效
     * @param response
     * @return
     */
    private boolean checkHttpCode401(Response response) {
        if (null==response){
            return false;
        }

        if (response.code()==401){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 获取Token的同步网络请求
     * @return
     */
    private String requestToken() {

        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTHCODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result!=null&&result.body()!=null){
                return  result.body().getAccess_token();
            }
        } catch (IOException e) {
            Log.e("123",e.getMessage());
        }
        return "";
    }
}
