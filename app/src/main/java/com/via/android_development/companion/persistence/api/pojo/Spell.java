package com.via.android_development.companion.persistence.api.pojo;

import java.util.List;

public class Spell {
    private String name;
    private List<String> description;
    private List<String> higherLevel;
    private String range;
    private List<String> components;
    private String duration;
    private String concentration;
    private String casting_time;
    private Integer level;
    private String url;

    public Spell(String name, List<String> description, List<String> higherLevel, String range, List<String> components, String duration, String concentration, String casting_time, Integer level, String url) {
        this.name = name;
        this.description = description;
        this.higherLevel = higherLevel;
        this.range = range;
        this.components = components;
        this.duration = duration;
        this.concentration = concentration;
        this.casting_time = casting_time;
        this.level = level;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getHigherLevel() {
        return higherLevel;
    }

    public String getRange() {
        return range;
    }

    public List<String> getComponentsAsList() {
        return components;
    }

    public String getComponentsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String component : components)
            stringBuilder.append(component).append(" ");
        return stringBuilder.toString().trim();
    }

    public String getDuration() {
        return duration;
    }

    public String getConcentration() {
        return concentration;
    }

    public String getCasting_time() {
        return casting_time;
    }

    public Integer getLevel() {
        return level;
    }

    public String getUrl() {
        return url;
    }
}
