package edu.sandbox.peoplecooperation;

import edu.clients.SearchableCitizen;

import java.util.List;

/**
 * Created by Lena on 05.05.14.
 */
public abstract class SearchEngine {
    public abstract List<SearchableCitizen> findByHobbie(SearchableCitizen citizen);
}
