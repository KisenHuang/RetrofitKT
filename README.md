# RetrofitKT
A Demo about simplify use of  the Retrofit.

## 说明:
简单封装了Retrofit，实现链式编程，兼容其他插件（例如rxjava，gson等）。
Retrofit创建的Service对象之后，通过使用回调的方式调用方法返回对应方法的返回对象。
没有注入代码，没有使用反射。不增加程序负担。
例如：

    interface MainService {
        @GET("s?wd=/{str}")
        Call<String> searchRetrofit(@Path("str") String str);
    }

    Callback<String> callback = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                webView.loadDataWithBaseURL("", body, "text/html", "UTF-8", "");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            }
        };

方式一：

    //全局配置
    RKTConfig config = new RKTConfig()
            .addConverterFactory(RetrofitConverter.RetrofitFactory.create());
    RetrofitKT.config(config);
    //发送请求
    RetrofitKT
            .build("http://wwww.baidu.com/")
            .service(MainService.class)
            .method("searchRetrofit", "Retrofit")
            .obtain(new BaseCallback<>(callback));

方式二：

    //发送请求
    RetrofitKT.newBuilder()
            .addConverterFactory(RetrofitConverter.RetrofitFactory.create())
            .build("http://wwww.baidu.com/")
            .service(MainService.class)
            .method("searchRetrofit", "Retrofit")
            .obtain(new BaseCallback<>(callback));
