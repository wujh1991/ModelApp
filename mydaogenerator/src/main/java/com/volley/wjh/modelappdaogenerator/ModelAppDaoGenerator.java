package com.volley.wjh.modelappdaogenerator;


import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ModelAppDaoGenerator {

    public static void main(String[] args) {

        Schema schema = new Schema(1, "com.volley.wjh.bean");
        schema.setDefaultJavaPackageDao("com.volley.wjh.dao");

        //实体操作
        addUser(schema);

        addNote(schema);

        //cs  数据库升级
//        addPerson(schema);

        //自动生成代码
        try {
            DaoGenerator daoGenerator = new DaoGenerator();
            daoGenerator.generateAll(schema, "E:\\AndroidStudioWorkSpace\\ModelApp\\app\\src\\main\\java-gen");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text");
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addUser(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty();
        user.addStringProperty("name").notNull();
        user.addIntProperty("age");
//        user.addDateProperty("birthday");
        //新增
        user.addStringProperty("sex");

    }

    private static void addPerson(Schema schema){
        Entity person = schema.addEntity("Person");
        person.addIdProperty();
        person.addStringProperty("name");
        person.addStringProperty("sex");
        person.addIntProperty("age");
    }


}
