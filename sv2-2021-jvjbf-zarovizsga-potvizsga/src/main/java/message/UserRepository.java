package message;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class UserRepository {
    private MariaDbDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UserRepository(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertUser(String username){
        jdbcTemplate.update("insert into users(username) values(?)",username);
    }

    public Optional<User> findUserByName(String username){
        try {
            return Optional.of(new User(jdbcTemplate.queryForObject("select * from users where username like ?", (rs, rowNum) -> Optional.of(rs.getLong("id")).orElse(null), username), username));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


}
