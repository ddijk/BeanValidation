package nl.dijkrosoft.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    String groupOne = "nl.dijkrosoft.validation.groups.GroupOne";
    String subOfGroupOne = "nl.dijkrosoft.validation.groups.SubOfGroupOne";


    @Test
    public void verifyAssignToSuper() throws ClassNotFoundException {

        assertTrue("groupOne is not assignable from SubGroupOne", Class.forName(groupOne).isAssignableFrom(Class.forName(subOfGroupOne)));
    }

    @Test
    public void verifyAssignToSub() throws ClassNotFoundException {

        assertFalse("subOfGroupOne is not assignable from groupOne", Class.forName(subOfGroupOne).isAssignableFrom(Class.forName(groupOne)));
    }

    @Test
    public void verifyFindAncestors() throws ClassNotFoundException {


        String annotationOnField = "nl.dijkrosoft.validation.groups.SubSubOfGroupOne";

        String annotationFromValidator = "nl.dijkrosoft.validation.groups.GroupOne";

        assertTrue("SubClass does not match group from validator", match(annotationOnField, annotationFromValidator));
    }

    @Test
    public void verifyFindAncestorsFails() throws ClassNotFoundException {


        String annotationOnField = "nl.dijkrosoft.validation.groups.GroupOne";

        String annotationFromValidator = "nl.dijkrosoft.validation.groups.SubSubOfGroupOne";

        assertFalse("validator Group super should not match field with subclass", match(annotationOnField, annotationFromValidator));
    }

    private boolean match(String annotationOnField, String annotationFromValidator) throws ClassNotFoundException {

        return Class.forName(annotationFromValidator).isAssignableFrom(Class.forName(annotationOnField));
    }
}