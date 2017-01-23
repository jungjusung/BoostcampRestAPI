package example.com.retrofittest;

import io.realm.RealmObject;

/**
 * Created by Jusung on 2017. 1. 23..
 */

public class Owner extends RealmObject{
    String login;
    String avatar_url;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String following_url;
    String gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String events_url;
    String received_events_url;
    String type;
    Boolean site_admin;
}
