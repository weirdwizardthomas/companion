package com.via.android_development.companion.persistence.api.response;

import com.via.android_development.companion.persistence.api.pojo.BriefSpell;

import java.util.List;

public class AllBriefSpellsResponse {
    private List<BriefSpell> results;

    public AllBriefSpellsResponse() {
    }

    public List<BriefSpell> getResults() {
        return results;
    }

}
