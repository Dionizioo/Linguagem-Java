import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class BancoArmazenamento implements Armazenamento {
    @Override
    public void adicionarVeiculo(Veiculo veiculo) {
        try (Connection connection = BancoDeDados.conectar()) {
            String tipo = (veiculo instanceof Carro) ? "carro" : "motocicleta";
            String sql = "INSERT INTO veiculos (marca, modelo, ano, preco, tipo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, veiculo.getMarca());
            statement.setString(2, veiculo.getModelo());
            statement.setInt(3, veiculo.getAno());
            statement.setDouble(4, veiculo.getPreco());
            statement.setString(5, tipo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Veiculo> recuperarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        try (Connection connection = BancoDeDados.conectar()) {
            String sql = "SELECT * FROM veiculos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                int ano = resultSet.getInt("ano");
                double preco = resultSet.getDouble("preco");
                String tipo = resultSet.getString("tipo");
                Veiculo veiculo;
                if ("carro".equals(tipo)) {
                    int numPortas = resultSet.getInt("numPortas");
                    veiculo = new Carro(marca, modelo, ano, preco, numPortas);
                } else if ("motocicleta".equals(tipo)) {
                    int cilindradas = resultSet.getInt("cilindradas");
                    veiculo = new Motocicleta(marca, modelo, ano, preco, cilindradas);
                } else {
                    // Tratar outros tipos de veículos, se necessário
                    veiculo = null;
                }
                if (veiculo != null) {
                    veiculos.add(veiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    @Override
    public void atualizarVeiculo(int indice, Veiculo novoVeiculo) {
        try (Connection connection = BancoDeDados.conectar()) {
            String tipo = (novoVeiculo instanceof Carro) ? "carro" : "motocicleta";
            String sql = "UPDATE veiculos SET marca=?, modelo=?, ano=?, preco=?, tipo=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, novoVeiculo.getMarca());
            statement.setString(2, novoVeiculo.getModelo());
            statement.setInt(3, novoVeiculo.getAno());
            statement.setDouble(4, novoVeiculo.getPreco());
            statement.setString(5, tipo);
            statement.setInt(6, indice + 1); // +1 porque os índices no banco começam em 1
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
