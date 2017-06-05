package net.lezhang.spring.mvcdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offerDao")
public class OfferDAO {
    
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        System.out.println("Autowired dataSource: " + ((dataSource == null) ? "null" : "ok"));
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Offer> getOffers() {
        return jdbcTemplate.query("select * from offers", new RowMapper<Offer>(){
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setOffer(rs.getString("text"));
                return offer;
            }
        });
    }
    
    public List<Offer> getOffers(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        
        return namedParameterJdbcTemplate.query("select * from offers where name = :name", params, new RowMapper<Offer>(){
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setOffer(rs.getString("text"));
                return offer;
            }
        });
    }

    /**
     * This returns a single object from DB.
     * @param id
     * @return
     */
    public Offer getOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        
        Offer result = null;
        try {
        result = namedParameterJdbcTemplate.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>(){
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setOffer(rs.getString("text"));
                return offer;
            }
        });
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }        
        return result;
    }
    
    public int deleteOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update("delete from offers where id = :id", params);

    }

    public int insertOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update("insert into offers (id, name, email, text) "
                + "values (:id, 'Le Zhang', 'newemail@email.com', 'revived from deletion...')", params);
    }
    
    public int insertOfferUsingObject(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
        return namedParameterJdbcTemplate.update("insert into offers (name, email, text)"
                + "values (:name, :email, :offer)", params);
        // note the param name need to match the POJO property getter name
    }

    /**
     * Insert in batch.
     * @param offers
     * @return the total number of records updated
     */
    public int insertOffers(List<Offer> offers) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
        int[] results = namedParameterJdbcTemplate.batchUpdate("insert into offers (name, email, text)"
                + "values (:name, :email, :offer)", params);
        int result = 0;
        for(int r : results) {
            result += r;
        }
        return result;
    }

    public int updateOffer(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
        return namedParameterJdbcTemplate.update("update offers set name = :name, email = :email, text = :offer "
                + "where id = :id", params);
    }

    @Transactional
    public int transactionalInsert() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", 3);
        int result1 = namedParameterJdbcTemplate.update("insert into offers (name, email, text)"
                + "values ('OK Name', 'test@email.com', 'OK offer')", params);
        System.out.println(result1); // will print 1, indicating this statement is ok
        int result2 = namedParameterJdbcTemplate.update("insert into offers (id, name, email, text)"
                + "values (:id, 'Error Name', 'test@email.com', 'Error offer')", params);
        System.out.println(result2); // will not reach
        return result1 + result2;
    }

}
