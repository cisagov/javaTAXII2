package xor.bcmc.taxii2;

public interface Identifiable<E> {
    E getId();
    void setId (E id);
}
