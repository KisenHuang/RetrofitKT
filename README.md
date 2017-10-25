# RetrofitKT
A Demo about simplify use of  the Retrofit.

##说明:
简单封装了Retrofit，实现链式变成，兼容其他插件（例如rxjava，gson等）。
例如：

    interface MainService {
        @GET("s?wd=/{str}")
        Call<String> searchRetrofit(@Path("str") String str);
    }

    ------------
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
            .obtain(new BaseCallback<>(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String body = response.body();
                    webView.loadDataWithBaseURL("", body, "text/html", "UTF-8", "");

                @Override
                public void onFailure(Call<String> call, Throwable t)
                }
            }));

方式二：

    //发送请求
    RetrofitKT.newBuilder()
            .addConverterFactory(RetrofitConverter.RetrofitFactory.create())
            .build("http://wwww.baidu.com/")
            .service(MainService.class)
            .method("searchRetrofit", "Retrofit")
            .obtain(new BaseCallback<>(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String body = response.body();
                    webView.loadDataWithBaseURL("", body, "text/html", "UTF-8", "");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                }
            }));
