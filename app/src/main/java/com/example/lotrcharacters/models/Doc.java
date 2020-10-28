
package com.example.lotrcharacters.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

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
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

}
