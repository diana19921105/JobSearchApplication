package com.dianaszanto.jobsearchapi.thirdparty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JobApiService {
    @GET("jobs/{country}/search/{page}")
    Call<JobSearchResult> getJobsByLocationAndKeyWord(@Path("country") String ISOCountryCode,
                                                       @Path("page") Integer page,
                                                       @Query("app_id") String appId,
                                                       @Query("app_key") String appKey,
                                                       @Query("what") String keyWord,
                                                       @Query("location0") String country);

}
