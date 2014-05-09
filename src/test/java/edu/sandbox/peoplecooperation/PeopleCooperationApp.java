package edu.sandbox.peoplecooperation;

import edu.clients.Citizen;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yurij.pyvovarenko on 21.04.14.
 */
public class PeopleCooperationApp {

    /*
    Testcase1
    Let Vasia find Petya who has same hobbies (renewables)
    */
    @Test
    public void MustFindPersonWithSameHobbie() {
        //given
        Citizen citizenVasia = new Citizen("Petrenko","Vasia","Ivanovych");
        Citizen citizenPetya = new Citizen("Oleskiv","Petro","Mykolayovych");
        Citizen citizenKolya = new Citizen("Golovach","Mykola","Lvovych");


        //when
        citizenVasia.setHobbies("renewables, painting, eco, екологія, мандрівки, футбол");
        citizenPetya.setHobbies("renewables, football, photography");
        citizenKolya.setHobbies("painting, skiing");
        List<Citizen> citizens =
                Collections.unmodifiableList(Arrays.asList(
                        citizenKolya,citizenPetya,citizenVasia));
        SearchEngine searchEngine = new HobbieSearchEngine(citizens);

        //then
        org.junit.Assert.assertTrue(searchEngine.findByHobbie(citizenVasia).contains(citizenPetya));
        System.out.println(searchEngine.findByHobbie(citizenVasia));
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
