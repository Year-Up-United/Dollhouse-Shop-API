package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.yearup.models.Profile;
import org.yearup.data.ProfileDao;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao
{
    public MySqlProfileDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public Profile create(Profile profile)
    {
        String sql = "INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getFirstName());
            ps.setString(3, profile.getLastName());
            ps.setString(4, profile.getPhone());
            ps.setString(5, profile.getEmail());
            ps.setString(6, profile.getAddress());
            ps.setString(7, profile.getCity());
            ps.setString(8, profile.getState());
            ps.setString(9, profile.getZip());

            ps.executeUpdate();

            return profile;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Profile getProfileById(int id){
        Profile profile = new Profile();

        try(Connection connection = getConnection()){

            String sql = "SELECT * FROM profiles WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                profile.setUserId(rs.getInt(1));
                profile.setFirstName(rs.getString(2));
            }

        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }

        return profile;
    }



// make update method

    public Profile update(int id, Profile profile) {

        try(Connection connection = getConnection()) {

            String sql = "UPDATE profiles" +
                    " SET  first_name = ? " +
                //    "   , las = ? " +
                 //   "   , category_id = ? " +
                //    "   , description = ? " +
                 //   "   , color = ? " +
                 //   "   , image_url = ? " +
                 //   "   , stock = ? " +
                 //   "   , featured = ? " +
                    " WHERE user_id = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,profile.getFirstName());
            pstmt.setInt(2,id);
            pstmt.executeUpdate();

        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return profile;
    }


}
