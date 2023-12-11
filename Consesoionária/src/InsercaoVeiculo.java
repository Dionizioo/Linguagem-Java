import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsercaoVeiculo {
    private Connection connection;

    public InsercaoVeiculo(Connection connection) {
        this.connection = connection;
    }

    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        String tipo = (veiculo instanceof Carro) ? "carro" : "motocicleta";
        String sql = "INSERT INTO veiculos (marca, modelo, ano, preco, tipo";
        if (veiculo instanceof Carro) {
            sql += ", numPortas) VALUES (?, ?, ?, ?, ?, ?)";
        } else if (veiculo instanceof Motocicleta) {
            sql += ", cilindradas) VALUES (?, ?, ?, ?, ?)";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, veiculo.getMarca());
        preparedStatement.setString(2, veiculo.getModelo());
        preparedStatement.setInt(3, veiculo.getAno());
        preparedStatement.setDouble(4, veiculo.getPreco());
        preparedStatement.setString(5, tipo);
        if (veiculo instanceof Carro) {
            preparedStatement.setInt(6, ((Carro) veiculo).getNumPortas());
        } else if (veiculo instanceof Motocicleta) {
            preparedStatement.setInt(6, ((Motocicleta) veiculo).getCilindradas());
        }
        preparedStatement.executeUpdate();
    }
}
