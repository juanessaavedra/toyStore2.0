package repository.Sales;

import config.DatabaseConnection;
import model.Customers;
import model.Employees;
import model.Sales;
import repository.Customers.CustomersRepositoryJDBCImpl;
import repository.Employees.EmployeesRepositoryJDBCImpl;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesRepositoryJDBCImpl implements Repository<Sales> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Sales> list() {
        List<Sales> salesList =new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM Sales;
                        """
            ))
        {
            while (resultSet.next()){
                Sales sales = new Sales();
                salesList.add(sales);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    private Sales createSales(ResultSet resultSet) throws SQLException {
        Sales sales = new Sales();
        CustomersRepositoryJDBCImpl customersRepositoryJDBC = new CustomersRepositoryJDBCImpl();
        EmployeesRepositoryJDBCImpl employeesRepositoryJDBC = new EmployeesRepositoryJDBCImpl();
        sales.setId(resultSet.getInt("id"));
        sales.setDate(resultSet.getDate("date").toLocalDate());
        int idCustomer = resultSet.getInt("id_customer");
        Customers customers = customersRepositoryJDBC.byId(idCustomer);
        sales.setIdCustomer(customers);
        int idEmployee = resultSet.getInt("id_employee");
        Employees employees = employeesRepositoryJDBC.byId(idEmployee);
        sales.setIdEmployee(employees);
        return sales;
    }

    @Override
    public Sales byId(Integer id) {
        Sales sales = null;
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
                sales =createSales(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sales;
    }

    @Override
    public void save(Sales sales) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO Sales(date,id_customer,id_employee ) values (?,?,?)
                                       """)
        ){
            preparedStatement.setDate(1, Date.valueOf(sales.getDate()));
            preparedStatement.setInt(2, sales.getIdCustomer().getId());
            preparedStatement.setInt(3, sales.getIdEmployee().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM Sales where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }


