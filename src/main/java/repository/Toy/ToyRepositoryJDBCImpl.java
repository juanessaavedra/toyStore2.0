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


public class ToyRepositoryJDBCImpl implements ToyRepository {


    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Toy createToy(ResultSet resultSet) throws SQLException {
        Toy toy = new Toy();
        toy.setId(resultSet.getInt("id"));
        toy.setName(resultSet.getString("name"));
        toy.setPrice(resultSet.getDouble("price"));
        toy.setPrice(resultSet.getDouble("quantity"));
        toy.setType(new ToyType(
                resultSet.getInt("category_id"),
                resultSet.getString("category_name")

        ));
        return toy;
    }



    @Override
    public List<Toy> listToys() {
        List<Toy>productosList = new ArrayList<>();
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
                Toy toy =createToy(resultSet);
                productosList.add(toy);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }

    @Override
    public void save(Toy t) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO Toys(name,price,quantity,id_category) values (?,?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, t.getName());
            preparedStatement.setDouble(2, t.getPrice());
            preparedStatement.setInt(3,t.getQuantity());
            preparedStatement.setInt(4,t.getType().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM Toys where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verifyToyExists(int id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      SELECT * FROM Toys where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Integer totalToys() throws Exception {
        int totalToys = 0;

        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""

                        SELECT SUM(quantity) as total_toys FROM Toys""")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalToys = resultSet.getInt("total_toys");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return totalToys;
    }


    @Override
    public double totalPrices() throws Exception {
            double totalPrice = 0.0;

            try (PreparedStatement preparedStatement = getConnection()
                    .prepareStatement("""
                SELECT SUM(price) as total_price FROM Toys""")) {

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    totalPrice = resultSet.getDouble("total_price");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return totalPrice;
        }

    @Override
    public List<Toy> increase(Toy toy, int quantity) throws Exception {
        List<Toy> toyList = null;
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE Toys SET quantity = quantity + ? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setInt (1, quantity);
            preparedStatement.setDouble(2, toy.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Toy toy1 = createToy(resultSet);
                toyList.add(toy1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toyList;
    }

    @Override
    public List<Toy> decrease(Toy toy, int quantity) throws Exception {
        List<Toy> toyList = null;
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE Toys SET quantity = quantity - ? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setInt (1, quantity);
            preparedStatement.setDouble(2, toy.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Toy toy1 = createToy(resultSet);
                toyList.add(toy1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toyList;
    }


    @Override
    public Toy findById(int id) {
        Toy toy = null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT t.*, c.name as category_name, c.id as category_id
                                    FROM toys AS t
                                    INNER JOIN categories AS c ON t.id_category = c.id
                                    WHERE t.id=?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                toy =createToy(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toy;
    }

    @Override
    public List<Toy> showByType() {
        return null;
    }

    @Override
    public void update(Toy toy  ) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE Toys SET name = ?, price = ?, quantity = ?, id_category=? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setString(1, toy.getName());
            preparedStatement.setDouble(2, toy.getPrice());
            preparedStatement.setDouble(3, toy.getQuantity());
            preparedStatement.setInt(4,toy.getType().getId());
            preparedStatement.setInt(5,toy.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}



