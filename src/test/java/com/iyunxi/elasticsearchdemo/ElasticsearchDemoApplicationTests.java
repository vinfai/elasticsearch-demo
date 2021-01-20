package com.iyunxi.elasticsearchdemo;

import com.iyunxi.elasticsearchdemo.domain.Book;
import com.iyunxi.elasticsearchdemo.repository.BookRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class ElasticsearchDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void indexTest() {

        IndexOperations indexOperations = elasticsearchOperations.indexOps(Book.class);
        boolean b = indexOperations.create();
        System.out.println("create index:" + b);

        boolean exists = indexOperations.exists();
        System.out.println("is exists:" + exists);

    }


    @Test
    public void curdData() {
        Book book = new Book();
        book.setId("SN-1000003");
        book.setName("像火箭科学家一样思考2");
        book.setPrice(1000);
        book.setPublishTime(LocalDateTime.now());
        book.setSummary("一只特立独行的猪（有趣、独立、反对假正经，读王小波的“入坑之选”！依据王小波手稿、生前定稿修订多年讹误，SHOU度以读者视角排定蕞佳阅读顺序。王小波代表作！肖战朱一龙麦家李诞实锤推荐！独·家授·权，全新编排，李银河审定。首度收录《有关克隆人》。2021，读懂一只特立独行的猪，跳出人生的围栏！");
        book.setTags(Lists.newArrayList("肖战", "入坑之选", "有关克隆人"));
        //Book save = elasticsearchOperations.save(book);

        bookRepository.save(book);

    }

    @Test
    public void query() {
        Book book = new Book();
        book.setName("思考");
        String id = "SN-1000003";
        IndexQuery indexQuery = new IndexQueryBuilder()/*.withId(id)*/.withObject(book).build();
        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of("books"));
        System.out.println(documentId);

        Book book1 = elasticsearchOperations.get(id, Book.class);
        System.out.println(book1);


        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book2 = bookOptional.get();
        System.out.println(book2);

//        elasticsearchOperations.search(, Book.class);


    }


}
