package xor.bcmc.taxii2;

public interface Identifiable<E> {
    E getId();

    // Warning: the Authorization json uses "_abilities" as
    // a reserved word, therefore, no object id of string
    // "_abilities" should be created.
    void setId (E id);

    Identifiable<E> withId(E id);
}
