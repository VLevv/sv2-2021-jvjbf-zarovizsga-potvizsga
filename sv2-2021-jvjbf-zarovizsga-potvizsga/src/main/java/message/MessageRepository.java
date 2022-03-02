package message;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MessageRepository {
    private MariaDbDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public MessageRepository(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public void insertMessage(long senderId,long receiverId,String massage){
        jdbcTemplate.update("insert into messages(sender_id,receiver_id,message) values(?,?,?)",senderId,receiverId,massage);
    }

    public List<String> findMessagesBySenderId(long senderId){
        return jdbcTemplate.query("select message from messages where sender_id = ?",(rs, rowNum) -> rs.getString("message"),senderId);
    }


}
