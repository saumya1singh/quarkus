package io.quarkus.registry.union;

import java.util.Collection;

public interface ElementCatalog {

    /**
     * All elements of the catalog
     *
     * @return elements of the catalog
     */
    Collection<Element> elements();

    /**
     * All element keys
     *
     * @return all element keys
     */
    Collection<Object> elementKeys();

    /**
     * Returns an element for a given key.
     *
     * @param elementKey element key
     * @return element associated with the key or null
     */
    Element get(Object elementKey);

    /**
     * Checks whether the catalog contains any elements.
     *
     * @return true if the catalog does not contain any elements, otherwise - false
     */
    boolean isEmpty();
}
