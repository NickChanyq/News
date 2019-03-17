package network;

import java.util.Map;

import bean.DataInfo;
import bean.Movie;
import bean.NewTop;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/15.
 */

public interface Api {
    //在主界面中直接采用retrofit网络请求
    //在新闻界面采用rxjava+retrofit进行网络请求；
    /**
     * 获取学习干货api
     *
     * @param pageCount
     * @param pageIndex
     * @return 示例：
     * //http://gank.io/api/data/Android/10/1
     */

    @GET("/api/data/Android/{pageCount}/{pageIndex}")
    Call<DataInfo> getDataLearn(@Path("pageCount") int pageCount,
                                @Path("pageIndex") int pageIndex);

    /**
     * 获取电影api
     *
     * @param qt
     * @param location
     * @param output
     * @param ak
     * @return 示例：
     * http://api.map.baidu.com/telematics/v3/movie?
     * qt=hot_movie&location=北京&output=json&ak=A72e372de05e63c8740b2622d0ed8ab1
     */
    @GET("/telematics/v3/movie/{qt}/{location}/{output}/{ak}")
    Call<Movie> getDataMovie(@Query("qt") String qt,
                             @Query("location") String location,
                             @Query("output") String output,
                             @Query("ak") String ak);

    @GET("telematics/v3/movie/")
    Call<Movie> getDataMovie2(@QueryMap Map<String, String> map);


    @GET("journalismApi")
    Call<ResponseBody> getString();
    @GET("in_theaters")
    Call<ResponseBody> getMovieString(@QueryMap Map<String,String> options);

//    @GET("in_theaters")
//    Call<ResponseBody> getString2();



//*************rxjava示例*****************************************************************
    /**
     * 获取新新闻头条api
     *
     * @param tableNum
     * @param pagesize
     * @return 示例
     * http://api.dagoogle.cn/news/get-news?tableNum=1&pagesize=10
     */
    @GET("news/get-news/")
    Observable<NewTop> getNewTop(@Query("tableNum") String tableNum,
                           @Query("pagesize") String pagesize);

    /**
     * 获取新闻娱乐api
     * @param tableNum
     * @param pagesize
     * @return
     */

    @GET("news/get-news/")
    Observable<NewTop> getNewFuns(@Query("tableNum") String tableNum,
                                                 @Query("pagesize") String pagesize);

}
