package repository.Employees;

import config.DatabaseConnection;
import model.Customers;
import model.Employees;
import model.Toy;
import model.ToyType;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesRepositoryJDBCImpl implements Repository<Employees> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Employees> list() {
        List<Employees> employeesList =new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM Employees;
                        """
            ))
        {
            while (resultSet.next()){
                Employees employees = new Employees();
                employeesList.add(employees);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesList;
    }

    private Employees createEmployee(ResultSet resultSet) throws SQLException {
        Employees employees = new Employees();
        employees.setId(resultSet.getInt("id"));
        employees.setName(resultSet.getString("name"));
        employees.setPosition(resultSet.getString("position"));
        employees.setSalary(resultSet.getDouble("salary"));
        employees.setUser(resultSet.getString("user"));
        employees.setPassword(resultSet.getString("password"));
        employees.setEntry_date(resultSet.getDate("entry_date").toLocalDate());
        employees.setBirthdayDate(resultSet.getDate("birthday_date").toLocalDate());
        employees.setGender(resultSet.getString("gender"));
        return employees;
    }

    @Override
    public Employees byId(Integer id) {
        Employees employees = null;
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
                employees =createEmployee(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }


    @Override
    public void save(Employees employees) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO Employees(name,position,salary,user, password, entry_date, birthday_date, genre ) values (?,?,?,?,?,?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, employees.getName());
            preparedStatement.setString(2, employees.getPosition());
            preparedStatement.setDouble(3, employees.getSalary());
            preparedStatement.setString(4, employees.getUser());
            preparedStatement.setString(5, employees.getPassword());
            preparedStatement.setDate(6, Date.valueOf(employees.getEntry_date()));
            preparedStatement.setDate(7,Date.valueOf(employees.getBirthdayDate()));
            preparedStatement.setString(8,employees.getGender());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM Employees where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
