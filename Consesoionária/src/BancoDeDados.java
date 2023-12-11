import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoDeDados {
    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/projeto";
    private static final String USUARIO = "root";
    private static final String SENHA = "1243";

    private static Connection connection;

    public static Connection conectar() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return connection;
    }

    public static void fecharConexao(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void criarTabela() {
        try (Connection connection = conectar()) {
            String sql = "CREATE TABLE IF NOT EXISTS veiculos (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "marca VARCHAR(255) NOT NULL," +
            "modelo VARCHAR(255) NOT NULL," +
            "ano INT NOT NULL," +
            "preco DOUBLE NOT NULL," +
            "numPortas INT," +
            "cilindradas INT," +
            "tipo VARCHAR(50) NOT NULL)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
