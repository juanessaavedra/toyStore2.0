package repository.Customers;

import config.DatabaseConnection;
import model.Customers;
import model.Toy;
import model.ToyType;
import repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomersRepositoryJDBCImpl implements Repository<Customers> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Customers> list() {
        List<Customers> customersList =new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * from Customers;
                        """
            ))
        {
            while (resultSet.next()){
                Customers customer = createCustomer(resultSet);
                customersList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    private Customers createCustomer(ResultSet resultSet) throws SQLException {
        Customers customers = new Customers();
        customers.setId(resultSet.getInt("id"));
        customers.setName(resultSet.getString("name"));
        customers.setUser(resultSet.getString("user"));
        customers.setPassword(resultSet.getString("password"));
        customers.setBirthdayDate(resultSet.getDate("birthday_date").toLocalDate());
        customers.setGender(resultSet.getString("gender"));
        return customers;
    }

    @Override
    public Customers byId(Integer id) {

        Customers customers = null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT p.*, c.name as category_name, c.id as category_id
                                    FROM toys AS p
                                    INNER JOIN categories AS c ON p.categoria_id = c.id
                                    WHERE p.id=?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                customers =createCustomer(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void save(Customers customers) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO Customers(name,user,password,birthday_date,gender) values (?,?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, customers.getName());
            preparedStatement.setString(2, customers.getUser());
            preparedStatement.setString(3, customers.getPassword());
            preparedStatement.setDate(4, Date.valueOf(customers.getBirthdayDate()));
            preparedStatement.setString(5, customers.getGender());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM Customers where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
