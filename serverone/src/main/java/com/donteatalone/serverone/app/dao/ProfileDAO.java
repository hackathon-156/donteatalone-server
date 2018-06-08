package com.donteatalone.serverone.app.dao;

import com.donteatalone.serverone.app.entity.ProfileEntity;
import com.donteatalone.serverone.app.entity.SigninEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class ProfileDAO implements IProfileDAO{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDAO (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean setupProfile(ProfileEntity userProfile){
        if (getUserByEmail(userProfile.getEmailID()) != null) {
            String sql = "INSERT INTO Profile(emailID, gender, geoLat, geoLong, breakS, breakE values (?, ?, ?, ?, ?)";
            StringBuilder sb = new StringBuilder();
            try{
                jdbcTemplate.update(sql, userProfile.getEmailID(), sb.toString());
            }

            catch (Exception e) {
                throw new IllegalArgumentException("Unable to update SQL");
            }
            sql = "SELECT id FROM Login where email = ?";
            int user_id = jdbcTemplate.queryForObject(sql, Integer.class, userProfile.getEmailID());
            userProfile.setUser_id(user_id);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public SigninEntity getUserByEmail(String email) {
        String sql = "SELECT id, email FROM Login where email = ?";
        List<SigninEntity> user = null;
        user = jdbcTemplate.query(sql, new RowMapper<SigninEntity>() {
            @Override
            public SigninEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                SigninEntity s = new SigninEntity();
                s.setUser_id(rs.getInt("id"));
                s.setEmailId(rs.getString("email"));
                return s;
            }

        }, email);
        return user.size() == 0?null:user.get(0);
    }


}
