package com.itrexgroup.test.moovies.db;


import com.itrexgroup.test.moovies.model.Moovie;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBHelper {
    public static void saveMooviesToRealm(final ArrayList<Moovie> moovies) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
                realm.delete(MoovieRealm.class);
                realm.insert(MoovieMapper.map(moovies));
            }
        });
    }

    public static ArrayList<Moovie> retrieveMooviesFromRealm() {
        RealmResults<MoovieRealm> realmResults = Realm.getDefaultInstance().where(MoovieRealm.class).findAll();
        return MoovieMapper.map(realmResults);
    }
}
