package repository.SalesDetails;

import config.DatabaseConnection;
import mapping.dtos.SalesDTO;
import model.Employees;
import model.Sales;
import model.SalesDetails;
import model.Toy;
import repository.Repository;
import repository.Sales.SalesRepositoryJDBCImpl;
import repository.Toy.ToyRepositoryJDBCImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDetailsRepositoryJDBCImpl implements Repository<SalesDetails> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<SalesDetails> list() {
        List<SalesDetails> employeesList =new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM SalesDetails;
                        """
            ))
        {
            while (resultSet.next()){
                SalesDetails salesDetails = new SalesDetails();
                employeesList.add(salesDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesList;
    }

    private SalesDetails createSalesDetails(ResultSet resultSet) throws SQLException {
        SalesDetails salesDetails = new SalesDetails();
        SalesRepositoryJDBCImpl salesRepositoryJDBC = new SalesRepositoryJDBCImpl();
        ToyRepositoryJDBCImpl toyRepositoryJDBC = new ToyRepositoryJDBCImpl();
        salesDetails.setId(resultSet.getInt("id"));
        int idSale = resultSet.getInt("id_sales");
        Sales sales = salesRepositoryJDBC.byId(idSale);
        salesDetails.setIdSales(sales);
        int idToy = resultSet.getInt("id_toys");
        Toy toy = toyRepositoryJDBC.findById(idToy);
        salesDetails.setIdToys(toy);
        salesDetails.setId(resultSet.getInt("quantity"));
        return salesDetails;
    }

    @Override
    public SalesDetails byId(Integer id) {
        SalesDetails salesDetails = null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT * FROM SalesDetails;
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                salesDetails =createSalesDetails(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesDetails;
    }

    @Override
    public void save(SalesDetails salesDetails) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO SalesDetails(id_sales,id_toys,quantity ) values (?,?,?)
                                       """)
        ){
            preparedStatement.setInt(1, salesDetails.getIdSales().getId());
            preparedStatement.setInt(2, salesDetails.getIdToys().getId());
            preparedStatement.setInt(3, salesDetails.getQuantity());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM SalesDetails where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
