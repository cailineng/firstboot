package com.lineng.controller;

import com.google.common.collect.Lists;
import com.lineng.esdao.NoteBookRepository;
import com.lineng.esmodel.Gift;
import com.lineng.esmodel.NoteBook;
import com.lineng.service.EsdemoService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/es")
public class EsController {
   
    @Resource
    private EsdemoService esdemoService;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private NoteBookRepository noteBookRepository;
    @RequestMapping("/createIndex")
    @ResponseBody
    public String createIndex() {
        esdemoService.createIndex();
        return "createIndex";
    }


    @RequestMapping("/initData")
    @ResponseBody
    public String initData() {
        List<NoteBook> list = new ArrayList<>();
        String[] brands = new String[]{"华硕","小米","苹果","华为"};
        String[] colors = new String[]{"white","red","blank","blue","green"};
        String[] sizes = new String[]{"13.4","15.6","17.8"};
        for(int i=0;i<100;i++) {
            NoteBook noteBook = new NoteBook();

            noteBook.setBrand(brands[i%brands.length]);
            noteBook.setColor(colors[i%colors.length]);
            noteBook.setSize(sizes[i%sizes.length]);
            list.add(noteBook);
        }
        noteBookRepository.saveAll(list);
        return "成功";
    }

    @RequestMapping("/testGenerateId")
    @ResponseBody
    public NoteBook testGenerateId() {
        NoteBook noteBook = new NoteBook();
        noteBook.setBrand("戴尔");
        noteBook.setColor("blank");
        noteBook.setSize("15.6");
        noteBook = noteBookRepository.save(noteBook);
        return noteBook;
    }

      @RequestMapping("/findNotebookById")
    @ResponseBody
    public String findNotebookById() {
        String id = "QtMdfmoBNdWG3c3axXoX";
        NoteBook noteBook = noteBookRepository.findById(id).get();
        return noteBook.toString();
    }


    @RequestMapping("/findAllNoteBook")
    @ResponseBody
    public String findAllNoteBook() {
        List<NoteBook> list = Lists.newArrayList(noteBookRepository.findAll());
        return list.toString();
    }
    

    @RequestMapping("/testQueryBuilder")
    @ResponseBody
    public List<NoteBook> tessQueryBuilder() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        QueryBuilder brand =  QueryBuilders.termQuery("brand", "华硕");

        boolQueryBuilder =  boolQueryBuilder.must(brand);

        QueryBuilder colorDetail =  QueryBuilders.termQuery("color", "blank");
        boolQueryBuilder = boolQueryBuilder.must(colorDetail);
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("size").order(SortOrder.DESC);
        NativeSearchQueryBuilder builder =  new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
                                                                          .withSort(fieldSortBuilder);
                                                                         /* .withPageable(PageRequest.of(1,1));*/
        ///这样返回的列表如果不指定每页条数只会返回10条，所以嘛如有需要最好用testQueryBuilder2来查全部
        Page<NoteBook>  pageList = noteBookRepository.search(builder.build());
        return  pageList.getContent();
    }

    //barnd = 华硕 and （size = 15.6 or 13.4）
    @RequestMapping("/condictionone")
    @ResponseBody
    public List<NoteBook> condictionone() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        QueryBuilder brand =  QueryBuilders.termQuery("brand", "华硕");

        boolQueryBuilder =  boolQueryBuilder.must(brand);

        BoolQueryBuilder boolQueryColorBuilder = QueryBuilders.boolQuery();
        QueryBuilder colorDetail =  QueryBuilders.termQuery("size", "15.6");

        QueryBuilder colorDetail2 =  QueryBuilders.termQuery("size", "13.4");


        boolQueryBuilder = boolQueryBuilder.must(boolQueryColorBuilder.should(colorDetail).should(colorDetail2));
        Iterable<NoteBook> noteBookIterable = noteBookRepository.search(boolQueryBuilder);
        List<NoteBook> noteBookList = Lists.newArrayList(noteBookIterable);
        return  noteBookList;
    }

    //barnd = 华硕 and （size = 15.6 or 13.4）
    @RequestMapping("/condictiontwo")
    @ResponseBody
    public List<NoteBook> condictiontwo() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        QueryBuilder brand =  QueryBuilders.termQuery("brand", "华硕");

        boolQueryBuilder =  boolQueryBuilder.must(brand);

        BoolQueryBuilder boolQueryColorBuilder = QueryBuilders.boolQuery();
        QueryBuilder colorDetail =  QueryBuilders.termQuery("size", "15.6");

        QueryBuilder colorDetail2 =  QueryBuilders.termQuery("size", "13.4");


        boolQueryBuilder = boolQueryBuilder.must(boolQueryColorBuilder.should(colorDetail).should(colorDetail2));
        Iterable<NoteBook> noteBookIterable = noteBookRepository.search(boolQueryBuilder);
        List<NoteBook> noteBookList = Lists.newArrayList(noteBookIterable);
        return  noteBookList;
    }




}
