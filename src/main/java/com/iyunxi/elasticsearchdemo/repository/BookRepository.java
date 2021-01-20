package com.iyunxi.elasticsearchdemo.repository;

import com.iyunxi.elasticsearchdemo.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRespository：
 *
 * @author fangwh
 * @date 2021/1/20 17:50
 */

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {



}
