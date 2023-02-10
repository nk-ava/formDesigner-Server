package formDesigner.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ComponentScan
@Configuration
public class AutoConfigureDataSource {
    @Autowired
    private Environment env;
    @Bean
    public DataSource getDataSource(){
        Pattern pattern = Pattern.compile("/[^/]+\\?");
        String str = env.getProperty("spring.datasource.url");
        String dbname = "";
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            dbname = str.substring(matcher.start()+1,matcher.end()-1);
            break;
        }
        try{
            Class.forName(env.getProperty("spring.datasource.driver-class-name"));
            Connection connection = DriverManager.getConnection(str.replaceFirst("/"+dbname,""),
                    env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
            Statement statement = connection.createStatement();
            statement.execute(String.format("create database if not exists %s;",dbname));
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }
}
