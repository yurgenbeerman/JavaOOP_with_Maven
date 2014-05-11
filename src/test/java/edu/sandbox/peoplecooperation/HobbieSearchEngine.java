package edu.sandbox.peoplecooperation;

import edu.clients.SearchableCitizen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Lena on 05.05.14.
 */
public class HobbieSearchEngine extends SearchEngine {
    private final String delimiters = " ,:;-.\n\t";
    List<SearchableCitizen> citizensToSearch;

    public HobbieSearchEngine(List<SearchableCitizen> citizensToSearch) {
        this.citizensToSearch = citizensToSearch;
    }

    public List<SearchableCitizen> findByHobbie(SearchableCitizen citizen) {
        List<SearchableCitizen> result = new ArrayList<SearchableCitizen>();
        StringTokenizer stringTokenizer = new StringTokenizer(citizen.getHobbies(), delimiters);
        while (stringTokenizer.hasMoreTokens()) {
            String hobbie = stringTokenizer.nextToken();
            for (SearchableCitizen aCitizen : citizensToSearch) {
                if (aCitizen.getHobbies().contains(hobbie)) {
                    result.add(aCitizen);
                }
            }
        }

        return Collections.unmodifiableList(result);
    }
}
