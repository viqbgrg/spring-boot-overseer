package com.github.viqbgrg.springbootoverseer.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

public interface BaseService<T> extends IService<T> {
    boolean saveByException(T entity);
    boolean removeByIdMyException(Serializable id);
    T getByIdException(Serializable id);
}
