package projectgui_1;

import java.util.*;

public class ProblematicTenantException extends Exception {

    public ProblematicTenantException(Person p) {
        super("Person: " + p.getName() + " " + p.getSurname() + " had already renting rooms: " + p.getEstate_num());
    }
}
