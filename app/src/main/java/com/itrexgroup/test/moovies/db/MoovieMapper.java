package com.itrexgroup.test.moovies.db;


import com.itrexgroup.test.moovies.model.Moovie;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

public class MoovieMapper {

    public static Moovie map(MoovieRealm moovieRealm) {
        Moovie moovie = new Moovie();
        moovie.setName(moovieRealm.getName());
        moovie.setNameEng(moovieRealm.getNameEng());
        moovie.setImage(moovieRealm.getImage());
        moovie.setDescription(moovieRealm.getDescription());
        moovie.setPremiere(moovieRealm.getPremiere());
        return moovie;
    }

    public static ArrayList<Moovie> map(RealmResults<MoovieRealm> results) {
        ArrayList<Moovie> moovies = new ArrayList<>();
        for (MoovieRealm moovieRealm : results) {
            moovies.add(map(moovieRealm));
        }
        return moovies;
    }

    public static RealmList<MoovieRealm> map(ArrayList<Moovie> moovies) {
        RealmList<MoovieRealm> results = new RealmList<>();
        for (Moovie moovie : moovies) {
            results.add(map(moovie));
        }
        return results;
    }

    public static MoovieRealm map(Moovie moovie) {
        MoovieRealm moovieRealm = new MoovieRealm();
        moovieRealm.setName(moovie.getName());
        moovieRealm.setNameEng(moovie.getNameEng());
        moovieRealm.setImage(moovie.getImage());
        moovieRealm.setDescription(moovie.getDescription());
        moovieRealm.setPremiere(moovie.getPremiere());
        return moovieRealm;
    }
}
