package com.lineng.esdao;

import com.lineng.esmodel.NoteBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoteBookRepository extends ElasticsearchRepository<NoteBook,String> {

}