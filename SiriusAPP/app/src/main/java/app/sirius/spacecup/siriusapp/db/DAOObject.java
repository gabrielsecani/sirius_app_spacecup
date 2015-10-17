package app.sirius.spacecup.siriusapp.db;

/**
 * Created by Gabriel on 17/10/2015.
 */
public abstract class DAOObject<T> implements DAO<T> {

    private T object;

    @Override
    public T getObject() {
        return this.object;
    }

}
