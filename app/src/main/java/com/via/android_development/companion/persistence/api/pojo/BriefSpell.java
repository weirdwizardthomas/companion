package com.via.android_development.companion.persistence.api.pojo;

public class BriefSpell {

    private String name;
    private String url;

    public BriefSpell(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
