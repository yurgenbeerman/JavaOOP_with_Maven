package edu.sandbox.peoplecooperation;

import edu.clients.SearchableCitizen;

/**
 * Created by Lena on 11.05.14.
 */
public class SearchResultItem {
    private SearchableCitizen foundCitizen;
    private Integer sameHobbiesCount;

    public SearchableCitizen getFoundCitizen() {
        return foundCitizen;
    }

    public void setFoundCitizen(SearchableCitizen foundCitizen) {
        this.foundCitizen = foundCitizen;
    }

    public Integer getSameHobbiesCount() {
        return sameHobbiesCount;
    }

    public void setSameHobbiesCount(Integer sameHobbiesCount) {
        this.sameHobbiesCount = sameHobbiesCount;
    }

    @Override
    public String toString() {
        return "SearchResultItem{" +
                "foundCitizen=" + foundCitizen +
                ", sameHobbiesCount=" + sameHobbiesCount +
                '}';
    }
}
