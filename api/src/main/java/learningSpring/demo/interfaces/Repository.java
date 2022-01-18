package learningSpring.demo.interfaces;

import java.util.Collection;
import java.util.function.Predicate;

public interface Repository<E extends Updatable<E>, K> {
    E getItem(Predicate<E> predicate);
    E getItemById(K id);
    Collection<E> getAllItems();
    E replaceItem(K id, E newItem);
    E addItem(E newItem);
    void deleteItem(Predicate<E> predicate);
    void deleteItemById(K id);
}
