package com.nkl.shirodemo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/10 16:17
 */
@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable> extends PagingAndSortingRepository<T,I>,
        JpaSpecificationExecutor<T> {

}
