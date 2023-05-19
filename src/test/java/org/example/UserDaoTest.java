package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {

    //@BeforeEach : 테스트 코드가 실행되기 전에 실행되는 코드
    @BeforeEach
    void setUp() {
        //테이블 생성, 혹은 초기 데이터, 쿼리를 수행하기 위해서 작업을 수행해줌
        //이 때, springboot-jdbc와 hikariCP 의존성 추가
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("eongyu", "password", "name", "email"));

        User user = userDao.findByUserId("eongyu");
        assertThat(user).isEqualTo(new User("eongyu", "password", "name", "email"));
    }
}
