package me.wubc.model.mappers;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc 通用mapper
 **/
@Repository
public interface CommonMapper<T> {

    int save(T t);

    int deleteById(String id);

    int update(T t);

    T getById(String id);

    List<T> findList(T t);

}
