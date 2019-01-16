package com.lineng.mongodao;

import com.lineng.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher,String> {
    public Teacher getById(String id);

    public List<Teacher> getByUserName(String userName);

    Integer countByUserName(String userName);


    String getUserNameById(String id);

    Integer getAgeById(String id);
}
