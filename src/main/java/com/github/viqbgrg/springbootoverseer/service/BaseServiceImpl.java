package com.github.viqbgrg.springbootoverseer.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.viqbgrg.springbootoverseer.exception.NotDelException;
import com.github.viqbgrg.springbootoverseer.exception.NotFoundException;
import com.github.viqbgrg.springbootoverseer.exception.NotSaveException;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author viqbg
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    public boolean saveByException(T entity) {
        if (!super.save(entity)) {
            throw new NotSaveException();
        }
        return true;
    }

    public boolean removeByIdMyException(Serializable id) {
        this.getByIdException(id);
        if (!super.removeById(id)) {
            throw new NotDelException();
        }
        return true;
    }

    public T getByIdException(Serializable id) {
        T t = getBaseMapper().selectById(id);
        if (ObjectUtils.isEmpty(t)) {
            throw new NotFoundException("账号不存在");
        }
        return t;
    }
}
