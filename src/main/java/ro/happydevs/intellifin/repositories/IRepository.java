package ro.happydevs.intellifin.repositories;

import java.util.ArrayList;

public interface IRepository {
    ArrayList<?> getAll();

    ArrayList<?> getAllByStringColumn(String column, String value);

    ArrayList<?> getAllByNumericColumn(String column, int value);

    Object getById(int id);

    boolean delete(int id);

    boolean update(int id, Object newObject);

    boolean create(Object object);


}
