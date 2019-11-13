package com.via.android_development.companion.persistence.api.response;

import com.via.android_development.companion.persistence.api.pojo.BriefSpell;

import java.util.List;

public class AllBriefSpellsResponse {
    private int count;
    private List<BriefSpell> results;

    public int getCount() {
        return count;
    }

    public List<BriefSpell> getResults() {
        return results;
    }

}
