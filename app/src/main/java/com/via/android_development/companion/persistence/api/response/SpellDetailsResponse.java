package com.via.android_development.companion.persistence.api.response;

import com.via.android_development.companion.persistence.api.pojo.Spell;

import java.util.List;

public class SpellDetailsResponse {

    private String name;
    private List<String> desc = null;
    private List<String> higher_level = null;
    private String range;
    private List<String> components = null;
    private String duration;
    private String concentration;
    private String casting_time;
    private Integer level;
    private String url;

    public Spell getSpell() {
        return new Spell(name, desc, higher_level, range, components, duration, concentration, casting_time, level, url);
    }
}
