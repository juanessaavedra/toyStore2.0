package repository.Toy;

import config.DatabaseConnection;

import model.Toy;
import repository.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
public class ToyRepositoryImpl implements Repository<Toy> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Toy> list() {
        List<Toy> productList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
            ResultSet resultset = statement.executeQuery ("""
                                                             SELECT p.*, c.nombre AS categoryName, c.id AS categoryId
                                                             FROM productos AS p 
                                                             INNER JOIN categorias AS c ON p.categoryId = c.id""")
) {
           while(resultset.next()) {
               Toy toy  = createProduct(resultset);
               productList.add(toy);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }



    @Override
    public Toy byId(Integer id) {
        Toy toy = null;

        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      
                        SELECT p.*, c.nombre as categoryName, c.id as categoryId
                                      FROM productos AS p 
                                      INNER JOIN categorias AS c ON p.categoryId = c.id
                                      WHERE p.id = ?
    
                             """)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                toy = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toy;
    }

    @Override
    public void save(Toy toy) {
        String sql;
        if (toy.getId() != null && toy.getId() > 0) {
            sql = "UPDATE products SET name=?, price=?, date_register=?, categoryId=?  WHERE id = ?";
        } else {
            sql = "INSERT INTO products(name,price,, categoryId) VALUES (?,?,?,?)";
        }


        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1,toy.getName());
            preparedStatement.setDouble(2, toy.getPrice());
            if(toy.getRegistrationDate()!=null){
                preparedStatement.setDate(3, Date.valueOf(product.getRegistrationDate().toLocalDate()) );
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            preparedStatement.setInt(4, toy.getCategoryId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                "DELETE FROM products WHERE id=?")
        ) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Toy createProduct (ResultSet resultSet) throws SQLException {
        Toy product = new Toy();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("nombre"));
        product.setPrice(resultSet.getDouble("price"));
        Date dbSqlDate = resultSet.getDate("fechaRegistro");
        if (dbSqlDate != null) {
            LocalDate fechaRegistro = dbSqlDate.toLocalDate();
            product.setRegistrationDate(fechaRegistro.atStartOfDay());
        } else {
            product.setRegistrationDate(null);
        }
        product.setCategoryId(resultSet.getInt("categoryId"));
        return product;
    }






}

**/
