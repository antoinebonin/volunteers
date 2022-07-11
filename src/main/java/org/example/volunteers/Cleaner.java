package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cleaner {
    public static boolean handleDuplicates(List<Volunteer> volunteers) {
        // supprimer tous les doublons identiques
        // assigner un familly id à ceux qui sont identique ( par mail ou phone similaire mais nom différent )
        // Autre fonction pour formater par famille

        List<Volunteer> singles = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        Integer groupIndex = 0;

        for ( int i = 0; i < volunteers.size(); i++ ) {
            Volunteer item = volunteers.get(i);
            boolean sameMail = volunteers.stream().filter(o -> o.email.equals(item.email)).count() > 1;
            boolean samePhone = volunteers.stream().filter(o -> o.phone.equals(item.phone)).count() > 1;

            if ( item.phone == "+33045558312") {
                System.out.println(samePhone);
            }
            if ((sameMail) || ( samePhone )) {
                List<Group> currentGroup = null;
                if ( sameMail && item.email != "" ) {
                     currentGroup = groups.stream().filter(o -> o.volunteers.stream().anyMatch(v -> v.email.equals(item.email)) ).collect(Collectors.toList());
                } else if ( samePhone && item.phone != "" ) {
                    currentGroup = groups.stream().filter(o -> o.volunteers.stream().anyMatch(v -> v.phone.equals(item.phone)) ).collect(Collectors.toList());
                }

                if ( currentGroup != null  && currentGroup.size() > 0) {
                    currentGroup.get(0).volunteers.add(item);
                }
                else {
                    List<Volunteer> list = new ArrayList<Volunteer>();
                    list.add(item);
                    Group group = new Group(groupIndex, list);
                    groupIndex ++;
                    groups.add(group);
                }
            /*}
            else if ( item.email == "" || item.phone == "" ) {
                List<Volunteer> list = new ArrayList<Volunteer>();
                list.add(item);
                Group group = new Group(groupIndex, list);
                groupIndex ++;
                groups.add(group);*/
            } else singles.add(item);
        }
        System.out.println(singles);
        System.out.println(groups);

        /*for ( int i = 0; i < hasDuplicates.size(); i++ ) {
            Volunteer item = hasDuplicates.get(i);

        }*/

        // vérifier si mail ou tel similaire si le nom correspond aussi ou si les infos sont complémentaires ( donc à regrouper )
        // Lequel garder entre les deux doublons ? ( celui qui a le plus d'infos ? )
        // assigner à une famille si plusieurs personnes avec le même nom de famille mais pas même prénom

        return false;
    }

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        List<Volunteer> finalVolunteers = new ArrayList<Volunteer>();

        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer volunteer = volunteers.get(i);
            boolean hasValidEmail = volunteer.hasValidMail();
            boolean hasValidPhone = volunteer.hasValidPhoneNumber();
            boolean hasFullName = volunteer.hasFullName();
            boolean hasPseudo = (volunteer.nickName == null || volunteer.nickName == "");

            //volunteer.format();

            if (hasFullName && hasValidEmail && hasValidPhone) {
                volunteer.level = 1;
            } else if (hasFullName && (hasValidEmail || hasValidPhone)) {
                volunteer.level = 2;
            } else if (!hasFullName && hasPseudo && (hasValidEmail || hasValidPhone)) {
                volunteer.level = 3;
            } else if (!hasFullName && !hasPseudo && (hasValidEmail || hasValidPhone)) {
                volunteer.level = 4;
            } else {
                volunteer.level = 5;
            }

            finalVolunteers.add(volunteer);
        }

        Cleaner.handleDuplicates(finalVolunteers);

        return new ArrayList<>(finalVolunteers);
    }
}


/*
*
- Vérifier si il n’y a pas de doublon
    - Par mail
    - Par numéro
- Formater les numéros
* */