package edu.sandbox.peoplecooperation;

import edu.clients.Citizen;
import edu.clients.SearchableCitizen;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yurij.pyvovarenko on 21.04.14.
 */
public class PeopleCooperationAppTests {

    /*
    Testcase1
    Let Vasia find Petya who has same hobbies (renewables)
    */
    @Test
    public void MustFindPersonWithSameHobbie() {
        //given
        SearchableCitizen citizenVasia = new SearchableCitizen("Petrenko","Vasia","Ivanovych");
        SearchableCitizen citizenPetya = new SearchableCitizen("Oleskiv","Petro","Mykolayovych");
        SearchableCitizen citizenKolya = new SearchableCitizen("Golovach","Mykola","Lvovych");


        //when
        citizenVasia.setHobbies("renewables");//, painting, eco, екологія, мандрівки, футбол");
        citizenPetya.setHobbies("renewables");//, football, photography");
        citizenKolya.setHobbies("painting, skiing");
        List<SearchableCitizen> citizens =
                Collections.unmodifiableList(Arrays.asList(
                        citizenKolya,citizenPetya,citizenVasia));
        SearchEngine searchEngine = new HobbieSearchEngine(citizens);

        //then
        org.junit.Assert.assertTrue(searchEngine.findByHobbie(citizenVasia).containsCitizen(citizenPetya));

    }

    @Test
    public void MustFindPersonWithSameOneOfSeveralHobbies() {
        //given
        SearchableCitizen citizenVasia = new SearchableCitizen("Petrenko","Vasia","Ivanovych");
        SearchableCitizen citizenPetya = new SearchableCitizen("Oleskiv","Petro","Mykolayovych");
        SearchableCitizen citizenKolya = new SearchableCitizen("Golovach","Mykola","Lvovych");


        //when
        citizenVasia.setHobbies("renewables, painting, eco, екологія, мандрівки, футбол");
        citizenPetya.setHobbies("renewables, football, photography");
        citizenKolya.setHobbies("painting, skiing");
        List<SearchableCitizen> citizens =
                Collections.unmodifiableList(Arrays.asList(
                        citizenKolya,citizenPetya,citizenVasia));
        SearchEngine searchEngine = new HobbieSearchEngine(citizens);

        //then
        org.junit.Assert.assertTrue(searchEngine.findByHobbie(citizenVasia).containsCitizen(citizenPetya));
    }

    @Test
    public void MustNotFindPersonWithNoSameOneOfSeveralHobbies() {
        //given
        SearchableCitizen citizenVasia = new SearchableCitizen("Petrenko","Vasia","Ivanovych");
        SearchableCitizen citizenPetya = new SearchableCitizen("Oleskiv","Petro","Mykolayovych");
        SearchableCitizen citizenKolya = new SearchableCitizen("Golovach","Mykola","Lvovych");


        //when
        citizenVasia.setHobbies("renewables, eco, екологія, мандрівки, футбол"); //painting,
        citizenPetya.setHobbies("renewables, football, photography");
        citizenKolya.setHobbies("painting, skiing");
        List<SearchableCitizen> citizens =
                Collections.unmodifiableList(Arrays.asList(
                        citizenKolya,citizenPetya,citizenVasia));
        SearchEngine searchEngine = new HobbieSearchEngine(citizens);

        //then
        org.junit.Assert.assertFalse(searchEngine.findByHobbie(citizenVasia).containsCitizen(citizenKolya));
    }

    //TODO: add rating to search results: more same hobbies make higher rating for result
    @Test
    public void MustRateSearchResultsInRightWay() {
        //given
        SearchableCitizen citizenVasia = new SearchableCitizen("Petrenko","Vasia","Ivanovych");
        SearchableCitizen citizenPetya = new SearchableCitizen("Oleskiv","Petro","Mykolayovych");
        SearchableCitizen citizenKolya = new SearchableCitizen("Golovach","Mykola","Lvovych");


        //when
        citizenVasia.setHobbies("renewables, photography, painting, eco, екологія, мандрівки, футбол"); //
        citizenPetya.setHobbies("renewables, football, photography");
        citizenKolya.setHobbies("painting, skiing");
        List<SearchableCitizen> citizens =
                Collections.unmodifiableList(Arrays.asList(
                        citizenKolya,citizenPetya,citizenVasia));
        SearchEngine searchEngine = new HobbieSearchEngine(citizens);

        //then
        System.out.println(searchEngine.findByHobbie(citizenVasia));
        org.junit.Assert.assertEquals(searchEngine.findByHobbie(citizenVasia).get(0).getSameHobbiesCount(), 2);
        org.junit.Assert.assertEquals(searchEngine.findByHobbie(citizenVasia).get(1).getSameHobbiesCount(), 1);
    }

    @Test
    public void SearchResultsMustExcludeTheSearchedCitizen() {
        //given
        SearchableCitizen citizenVasia = new SearchableCitizen("Petrenko","Vasia","Ivanovych");
        SearchableCitizen citizenPetya = new SearchableCitizen("Oleskiv","Petro","Mykolayovych");
        SearchableCitizen citizenKolya = new SearchableCitizen("Golovach","Mykola","Lvovych");


        //when
        citizenVasia.setHobbies("renewables, eco, екологія, мандрівки, футбол"); //painting,
        citizenPetya.setHobbies("renewables, football, photography");
        citizenKolya.setHobbies("painting, skiing");
        List<SearchableCitizen> citizens =
                Collections.unmodifiableList(Arrays.asList(
                        citizenKolya,citizenPetya,citizenVasia));
        SearchEngine searchEngine = new HobbieSearchEngine(citizens);

        //then
        org.junit.Assert.assertFalse(searchEngine.findByHobbie(citizenVasia).containsCitizen(citizenVasia));
    }

    /*
    Testcase1
        Let Vasia find Petya who has same hobbies (renewables)
    *** Methods ***
    FindPotentialCooperators
        All
        By Place
            Nearest

        By Info
            _MeTheyFilter_

        By Activity
            _MeTheyFilter_

        By resources
            _MeTheyFilter_

        MeTheyFilter
            Who can help to me
            Who needs same as me

    *** Tools ***
    Give tools to start and maintain cooperation
        Doodle
        Project management
        Google tools
        BestPractices

    *** Entities ***
    Human
        _SocialInfo_
        wants to help with
            _CooperetionElements_
        looks for
            _CooperetionElements_

    SocialInfo
        Name
        Second Name
        FacebookProfileURL
        VKontakteProfileURL
        Skype
        Email

    Place
        anywhere (presence is not required)
        coordinates
        Address
        description of place (i.e. for flash mob)

    CooperetionElements
        info
            topic
            professional level
        active help (actions)
            _Place_
            type of action
            time of acting
            periodicity of acting
        resources (things)
            _Place_
            tools
            equipment
            materials
                solid
                liquid

     */
}
