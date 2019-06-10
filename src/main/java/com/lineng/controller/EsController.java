package com.lineng.controller;

import com.google.common.collect.Lists;
import com.lineng.esdao.NoteBookRepository;
import com.lineng.esmodel.Gift;
import com.lineng.esmodel.NoteBook;
import com.lineng.service.EsdemoService;
import org.apache.lucene.search.BooleanQuery;
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
import java.util.Random;


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
            noteBook.setCreateTime(new Date());
            Random random = new Random();
            int num = random.nextInt(100);
            noteBook.setInventory(num);
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
        noteBook.setCreateTime(new Date());
        noteBook.setInventory(199);
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

    //barnd = 华硕 and (（size in 15.6 , 13.4 ）or color = "white"））
    @RequestMapping("/condictiontwo")
    @ResponseBody
    public List<NoteBook> condictiontwo() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        QueryBuilder brand =  QueryBuilders.termQuery("brand", "华硕");

        boolQueryBuilder =  boolQueryBuilder.must(brand);

        BoolQueryBuilder boolQueryColorBuilder = QueryBuilders.boolQuery();
        QueryBuilder sizesQuery =  QueryBuilders.termsQuery("size", "15.6","13.4");

        QueryBuilder colorQuery =  QueryBuilders.termQuery("color", "white");


        boolQueryBuilder = boolQueryBuilder.must(boolQueryColorBuilder.should(sizesQuery).should(colorQuery));
        Iterable<NoteBook> noteBookIterable = noteBookRepository.search(boolQueryBuilder);
        List<NoteBook> noteBookList = Lists.newArrayList(noteBookIterable);
        return  noteBookList;
    }

    //brand ="华硕" and color like 'b%'
    @RequestMapping("/likeDemo")
    @ResponseBody
    public List<NoteBook> likeDemo() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        QueryBuilder brandQuery = QueryBuilders.termQuery("brand","华硕");
        boolQueryBuilder =  boolQueryBuilder.must(brandQuery);

        QueryBuilder  colorQuery = QueryBuilders.wildcardQuery("color", "b*");
        boolQueryBuilder = boolQueryBuilder.must(colorQuery);

        Iterable<NoteBook> noteBookIterable = noteBookRepository.search(boolQueryBuilder);
        List<NoteBook> noteBookList = Lists.newArrayList(noteBookIterable);
        return  noteBookList;
    }


    //新增字段测试
    @RequestMapping("/testAddMapping")
    @ResponseBody
    public String testAddMapping() {
        NoteBook noteBook = new NoteBook();
        noteBook.setBrand("戴尔");
        noteBook.setColor("gray");
        noteBook.setSize("15.6");
        noteBook.setInventory(100);
        noteBookRepository.save(noteBook);
        return "成功";
    }

    @RequestMapping("/test")
    @ResponseBody
    public List<NoteBook> test() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder colorQuery =  QueryBuilders.wildcardQuery("color", "g*");
        boolQueryBuilder = boolQueryBuilder.must(colorQuery);
        Iterable<NoteBook> noteBookIterable = noteBookRepository.search(boolQueryBuilder);
        List<NoteBook> noteBookList = Lists.newArrayList(noteBookIterable);
        return  noteBookList;
    }

    /**
     * 时间和日期范围demo(注意日期在实体上做了注释，因为有8个小时的时区差的坑)
     */
    @RequestMapping("/range")
    @ResponseBody
    public List<NoteBook> range() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder inventoryRangeBuilder = QueryBuilders.rangeQuery("inventory").from(80).to(100);

        QueryBuilder createTimeRangeBuilder = QueryBuilders.rangeQuery("createTime").from("2019-05-21 16:29:50").to("2019-05-21 16:31:50");
        boolQueryBuilder = boolQueryBuilder.must(inventoryRangeBuilder).must(createTimeRangeBuilder);
        Iterable<NoteBook> noteBookIterable = noteBookRepository.search(boolQueryBuilder);
        List<NoteBook> noteBookList = Lists.newArrayList(noteBookIterable);
        return  noteBookList;
    }


    @RequestMapping("/testLogstashError")
    @ResponseBody
    public Integer testLogstashError(Integer num) {
        NoteBook noteBook = null;
        if(num ==0){

        }else{
            System.out.println(noteBook.getBrand());
        }
       // int k = 20/num;
        return  null;
    }
}
