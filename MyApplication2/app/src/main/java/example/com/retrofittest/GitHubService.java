package example.com.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jusung on 2017. 1. 23..
 */


public interface GitHubService {
    @GET("users/{users_id}/repos")
    Call<List<Repo>> groupList(@Path("users_id") String groupId);

}
