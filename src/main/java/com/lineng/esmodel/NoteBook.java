package com.lineng.esmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "lineng2", type = "notebook")
public class NoteBook {
    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String brand;
    @Field(type = FieldType.Keyword)
    private String color;
    @Field(type = FieldType.Keyword)
    private String size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
