package example.com.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String TAG;
    @BindView(R.id.tv_repo)
    TextView mTextViewRepo;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = this.getClass().getName();

        realm = Realm.getDefaultInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = service.groupList("jungjusung");

        ButterKnife.bind(this);
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {

                    List<Repo> repoList = response.body();
                    Log.d(TAG, repoList.size() + "");
                    StringBuffer result = new StringBuffer();

                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(repoList);
                    realm.commitTransaction();
                    List<Repo> realmList = realm.where(Repo.class).findAll();
                    for (Repo repo : realmList) {
                        result.append("full_name : " + repo.full_name + "\n");
                        result.append("name : " + repo.name + "\n");
                        result.append("id : " + repo.id + "\n\n");

                    }

                    mTextViewRepo.setText(result.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "error");
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null)
            realm.close();
    }
}
