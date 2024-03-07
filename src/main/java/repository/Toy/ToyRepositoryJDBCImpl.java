package repository.Toy;

import config.DatabaseConnection;

import model.Toy;
import model.ToyType;
import repository.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ToyRepositoryJDBCImpl implements Repository<Toy> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Toy createProduct(ResultSet resultSet) throws SQLException {
        Toy toy = new Toy();
        toy.setId(resultSet.getInt("id"));
        toy.setName(resultSet.getString("nombre"));
        toy.setPrice(resultSet.getDouble("precio"));
        java.sql.Date dbSqlDate = resultSet.getDate("fecha_registro");
        if (dbSqlDate != null) {
            LocalDate fechaRegistro = dbSqlDate.toLocalDate();
            producto.setRegistrationDate(fechaRegistro.atStartOfDay()); // Convierte LocalDate a LocalDateTime al inicio del día
        } else {
            producto.setRegistrationDate(null);
        }
        toy.setType(new ToyType(
                resultSet.getInt("category_id"),
                resultSet.getString("category_name")

        ));
        return toy;
    }

   /* @Override
    public List<Product> list() {
        List<Product> productoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from productos")) {
            while (resultSet.next()) {
                Product product = createProduct(resultSet);
                productoList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productoList;
    }*/

    @Override
    public List<Toy> list() {
        List<Toy>productosList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT p.*, c.name as category_name, c.id as category_id
                        FROM productos AS p
                        INNER JOIN categories AS c ON p.categoria_id = c.id;
                        """
            ))
        {
            while (resultSet.next()){
                Toy producto=createProduct(resultSet);
                productosList.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }

    @Override
    public Toy byId(Integer id) {
        Toy producto=null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT p.*, c.name as category_name, c.id as category_id
                                    FROM productos AS p
                                    INNER JOIN categories AS c ON p.categoria_id = c.id
                                    WHERE p.id=?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                producto=createProduct(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }



/*    @Override
    public Product byId(int id) {
        Product product = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }*/


    @Override
    public void save(Toy toy    ) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO Productos(nombre,precio,fecha_registro,categoria_id) values (?,?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, toy.getName());
            preparedStatement.setDouble(2, toy.getPrice());
            LocalDateTime fechaRegistro = toy.getRegistrationDate();
            preparedStatement.setDate(3, Date.valueOf(fechaRegistro.toLocalDate()));
            preparedStatement.setInt(4,toy.getType().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM Productos where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Toy toy  ) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE Productos SET nombre = ?, precio = ?, fecha_registro = ? , categoria_id=? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setString(1, toy.getName());
            preparedStatement.setDouble(2, toy.getPrice());
            LocalDateTime fechaRegistro = toy.getRegistrationDate();
            preparedStatement.setDate(3, Date.valueOf(fechaRegistro.toLocalDate()));
            preparedStatement.setInt(4,toy.getType().getId());
            preparedStatement.setInt(5,toy.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}


