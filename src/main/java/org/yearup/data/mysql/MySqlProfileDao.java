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
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, profile.getUserId());
            preparedStatement.setString(2, profile.getFirstName());
            preparedStatement.setString(3, profile.getLastName());
            preparedStatement.setString(4, profile.getPhone());
            preparedStatement.setString(5, profile.getEmail());
            preparedStatement.setString(6, profile.getAddress());
            preparedStatement.setString(7, profile.getCity());
            preparedStatement.setString(8, profile.getState());
            preparedStatement.setString(9, profile.getZip());

            preparedStatement.executeUpdate();

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
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

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
                    "   , last_name = ? " +
                    "   , phone = ? " +
                    "   , email = ? " +
                    "   , address = ? " +
                    "   , city = ? " +
                    "   , state = ? " +
                    "   , zip = ? " +
                    " WHERE user_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,profile.getFirstName());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

        }catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return profile;
    }


}
