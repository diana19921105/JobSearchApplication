package com.dianaszanto.jobsearchapi.thirdparty;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class JobApiServiceImpl {
    private JobApiService jobApiService = JobApiServiceGenerator.createService(JobApiService.class);
    private String appKey = System.getenv("API_KEY");
    private String appId = System.getenv("API_ID");

    /**
     * @param ISOCountryCode is Alpha-2 code in lower case format. Check out https://www.iso.org/obp/ui/#search
     */
    public JobSearchResult showJobsByLocationAndKeyWord(String ISOCountryCode, Integer page, String keyWord,
                                                        String countryAbbreviation) throws IOException {
        Call<JobSearchResult> call = jobApiService.getJobsByLocationAndKeyWord(ISOCountryCode, page, appId,
                                                                               appKey, keyWord,
                                                                               countryAbbreviation);

        Response<JobSearchResult> response = call.execute();

        //if 3rd party is not available for any reason return new JobSearchResult to avoid NullPointerException
        if (response.body() == null) {
            return new JobSearchResult();
        }
        return response.body();
    }
}
