
package com.example.lotrcharacters.models;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

@Parcel
public class Doc {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("race")
    @Expose
    public String race;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("birth")
    @Expose
    public String birth;
    @SerializedName("spouse")
    @Expose
    public String spouse;
    @SerializedName("death")
    @Expose
    public String death;
    @SerializedName("realm")
    @Expose
    public String realm;
    @SerializedName("hair")
    @Expose
    public String hair;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("wikiUrl")
    @Expose
    public String wikiUrl;
    //pushId
    private String pushId;
    //index
    String index;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Doc() {
    }

    /**
     * 
     * @param hair
     * @param race
     * @param gender
     * @param death
     * @param name
     * @param wikiUrl
     * @param birth
     * @param realm
     * @param id
     * @param spouse
     * @param height
     */
    public Doc(String id, String height, String race, String gender, String birth, String spouse, String death, String realm, String hair, String name, String wikiUrl) {
        super();
        this.id = id;
        this.height = height;
        this.race = race;
        this.gender = gender;
        this.birth = birth;
        this.spouse = spouse;
        this.death = death;
        this.realm = realm;
        this.hair = hair;
        this.name = name;
        this.wikiUrl = wikiUrl;
        //
        this.index = "not_specified";
        //
    }

    final String unknown = "Unknown";
    final String nonYet = "No wiki fan page yet";

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeight() {
        if(height==null|| height.equals("")){
            return unknown;
        }else {
            return height;
        }
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRace() {
        if(race==null|| race.equals("")){
            return unknown;
        }else {
            return race;
        }
//        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        if(gender==null|| gender.equals("")){
            return unknown;
        }else {
            return gender;
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        if(birth==null|| birth.equals("")){
            return unknown;
        }else {
            return birth;
        }
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSpouse() {
        if(spouse==null|| spouse.equals("")){
            return unknown;
        }else {
            return spouse;
        }
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getDeath() {
        if(death==null|| death.equals("")){
            return unknown;
        }else {
            return death;
        }
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRealm() {
        if(realm==null|| realm.equals("")){
            return unknown;
        }else {
            return realm;
        }
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getHair() {
        if(hair==null|| hair.equals("")){
            return unknown;
        }else {
            return hair;
        }
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getName() {
        if(name==null|| name.equals("")){
            return unknown;
        }else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiUrl() {
        if(wikiUrl==null|| wikiUrl.equals("")){
            return nonYet;
        }else {
            return wikiUrl;
        }
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("birth", birth);
        result.put("death", death);
        result.put("gender", gender);
        result.put("hair", hair);
        result.put("height", height);
        result.put("id", id);
        result.put("name", name);
        result.put("pushId", pushId);
        result.put("race", race);
        result.put("realm", realm);
        result.put("spouse", spouse);
        result.put("wikiUrl", wikiUrl);

        return result;
    }

}
