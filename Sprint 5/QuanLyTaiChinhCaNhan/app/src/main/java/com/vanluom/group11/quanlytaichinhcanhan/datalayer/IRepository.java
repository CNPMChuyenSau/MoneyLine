
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

/**
 * Repository interface
 */
public interface IRepository {
//    Select selectAll();
//    Select select();

    //<T extends EntityBase> boolean insert(Class<T> entity);
//    boolean insert(EntityBase entity);
//    int insert(T entity);
    boolean delete(IEntity entity);
}
