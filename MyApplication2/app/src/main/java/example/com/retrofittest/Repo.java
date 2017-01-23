package example.com.retrofittest;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Jusung on 2017. 1. 23..
 */

public class Repo extends RealmObject{
     String id;
     String name;
     String full_name;
     Owner owner;
}