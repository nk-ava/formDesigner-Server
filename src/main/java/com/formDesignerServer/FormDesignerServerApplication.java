package com.formDesignerServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

@SpringBootApplication
public class FormDesignerServerApplication {

    public static void main(String[] args) throws Exception {
        Map<String,Object> mp = getProperty();
        String dbname = (String)mp.get("dbname");
        try{
            Class.forName(mp.get("driver-class-name")+"");
            Connection connection = DriverManager.getConnection(((String)mp.get("url")).replace(String.format("/%s",dbname),""),mp.get("username")+"",mp.get("password")+"");
            Statement statement = connection.createStatement();
            statement.execute(String.format("create database if not exists %s;",dbname));
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        SpringApplication.run(FormDesignerServerApplication.class, args);
    }

    public static Map<String, Object> getProperty() throws Exception {
        String path = ResourceUtils.getURL("classpath:application.yml").toString().replace("file:/","");
        InputStream inputStream = new FileInputStream(path);
        Map<String,Object> mp = new Yaml().load(inputStream);
        mp = ((Map<String, Map<String,Object>>)mp.get("spring")).get("datasource");
        inputStream.close();
        return mp;
    }
}
