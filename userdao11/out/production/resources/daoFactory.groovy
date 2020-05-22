import kr.ac.jejunu.user.UserDao
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource

beans {
    dataSource(SimpleDriverDataSource) {
        driverClass = System.getenv("DB_CLASSNAME")
        url = System.getenv("DB_URL")
        username = System.getenv("DB_USERNAME")
        password = System.getenv("DB_PASSWORD")
    }

    jdbcTemplate(JdbcTemplate, dataSource) {

    }

    userDao(UserDao, jdbcTemplate) {

    }

}


// dsl로 정의되어 있음
// spring bean 관리 용도로 groovy를 만듬
