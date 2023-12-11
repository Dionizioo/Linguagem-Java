package main.java.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.certificado.Certificado;
import main.java.disciplina.Disciplina;
import main.java.enums.TipoCurso;
import main.java.estudante.Estudante;
import main.java.fabrica.disciplina.DisciplinaFactory;
import main.java.fabrica.estudante.EstudanteFactory;
import main.java.utils.SQLUtils;

public class BancoDeDados {

    private Connection conexao;

    protected String getUrl() {
        return null;
    }

    protected String getUsuario() {
        return null;
    }

    protected String getSenha() {
        return null;
    }

    public void criarSchemaETabelas() {
        executarQuery(SQLUtils.CREATE_SCHEMA_MYSQL);
        executarQuery(SQLUtils.USE_SCHEMA_BANCO);
        executarQuery(SQLUtils.CREATE_TABLE_ESTUDANTES);
        executarQuery(SQLUtils.CREATE_TABLE_CERTIFICADOS);
        executarQuery(SQLUtils.CREATE_DISCIPLINAS_TABLE); 
    }

    public void conectar() {
        try {
            conexao = DriverManager.getConnection(getUrl(), getUsuario(), getSenha());
            System.out.println("Conexão bem-sucedida com o banco de dados MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao conectar ao banco de dados MySQL: " + e.getMessage());
        }
    }

    public void executarQuery(String query) {
        if (conexao != null) {
            try {
                PreparedStatement statement = conexao.prepareStatement(query);
                statement.executeUpdate();
                System.out.println("Consulta executada com sucesso no banco MySQL.");
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao executar a consulta no banco MySQL: " + e.getMessage());
            }
        } else {
            System.err.println("Não é possível executar a consulta. A conexão com o banco de dados MySQL não está estabelecida.");
        }
    }

    public void desconectar() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados MySQL foi fechada.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao fechar a conexão com o banco de dados MySQL: " + e.getMessage());
            }
        }
    }

    // MÉTODOS DE INSERÇÃO
    public int inserirEstudante(String nome, String cpf, String tipoCurso, boolean aprovacao) {
        int estudanteId = -1; // Valor padrão, indicando falha na inserção

        try {
            String insertEstudanteSQL = SQLUtils.CREATE_ESTUDANTE_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(insertEstudanteSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, tipoCurso);
            preparedStatement.setString(4, ""); 
            preparedStatement.setBoolean(5, aprovacao);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                estudanteId = generatedKeys.getInt(1);
            }

            System.out.println("Estudante adicionado com sucesso (ID: " + estudanteId + "): " + nome);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao adicionar estudante: " + e.getMessage());
        }

        return estudanteId;
    }

    public int inserirDisciplina(int estudanteId, String nome, String professor, int cargaHoraria, String notaFinal, boolean aprovacao) {
        int idDaNovaDisciplina = -1;
       

        try {
            String insertDisciplinaSQL = SQLUtils.CREATE_DISCIPLINA_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(insertDisciplinaSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, estudanteId);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, professor);
            preparedStatement.setInt(4, cargaHoraria);
            preparedStatement.setString(5, ""); // Alteração 2: Incluído valor vazio para a coluna 'DISCIPLINAS'
            preparedStatement.setString(6,notaFinal);
            preparedStatement.setBoolean(7, aprovacao);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idDaNovaDisciplina = generatedKeys.getInt(1);
            }

            System.out.println("Disciplina adicionada com sucesso para o estudante com ID: " + estudanteId + " (ID da disciplina: " + idDaNovaDisciplina + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao adicionar disciplina: " + e.getMessage());
        }

        return idDaNovaDisciplina;
    }

    public void inserirCertificado(int estudanteId, String descricao, String tipoCurso, String disciplinas) {
        try {
            String insertCertificadoSQL = SQLUtils.CREATE_CERTIFICADO_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(insertCertificadoSQL);
            preparedStatement.setInt(1, estudanteId);
            preparedStatement.setString(2, descricao);
            preparedStatement.setString(3, tipoCurso);
            preparedStatement.setString(4, disciplinas);
            preparedStatement.executeUpdate();

            System.out.println("Certificado adicionado com sucesso para o estudante com ID: " + estudanteId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao adicionar certificado: " + e.getMessage());
        }
    }

    public void atualizarEstudante(int id, String nome, String tipoCurso, boolean aprovacao, String disciplinasId) {
        System.out.println("BLABLABLA" + disciplinasId);
    
        try {
            String updateEstudanteSQL = SQLUtils.UPDATE_ESTUDANTE_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(updateEstudanteSQL);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, tipoCurso);
            preparedStatement.setBoolean(3, aprovacao);  // Atualize o campo "aprovacao" de acordo com o valor de aprovacao no objeto Estudante
            preparedStatement.setString(4, disciplinasId);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
    
            System.out.println("Estudante atualizado com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao atualizar estudante: " + e.getMessage());
        }
    }
    

    public void atualizarCertificado(int id, String descricao, String tipoCurso, String disciplinas) {
        try {
            String updateCertificadoSQL = SQLUtils.UPDATE_CERTIFICADO_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(updateCertificadoSQL);
            preparedStatement.setString(1, descricao);
            preparedStatement.setString(2, tipoCurso);
            preparedStatement.setString(3, disciplinas);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

            System.out.println("Certificado atualizado com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao atualizar certificado: " + e.getMessage());
        }
    }

    public void atualizarDisciplina(int id, String nome, String professor, int cargaHoraria, String string, String notaFinal,
                                    boolean aprovacao) {
        try {
            String updateDisciplinaSQL = SQLUtils.UPDATE_DISCIPLINA_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(updateDisciplinaSQL);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, professor);
            preparedStatement.setInt(3, cargaHoraria);
            preparedStatement.setString(5, String.valueOf(string));
            preparedStatement.setString(6, notaFinal);
            preparedStatement.setBoolean(7, aprovacao);
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();

            System.out.println("Disciplina atualizada com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    // MÉTODOS DE DELETE

    public void excluirEstudante(int id) {
        try {
            // Desativar verificação de chave estrangeira
            Statement statement = conexao.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
    
            // Excluir o estudante
            String deleteEstudanteSQL = SQLUtils.DELETE_ESTUDANTE_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(deleteEstudanteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
    
            System.out.println("Estudante excluído com sucesso (ID: " + id + ")");
    
            // Reativar verificação de chave estrangeira
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao excluir estudante: " + e.getMessage());
        }
    }
    

    public void excluirCertificado(int id) {
        try {
            String deleteCertificadoSQL = SQLUtils.DELETE_CERTIFICADO_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(deleteCertificadoSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Certificado excluído com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao excluir certificado: " + e.getMessage());
        }
    }

    public void excluirCertificadoEstudanteId(int id) {
        try {
            String deleteCertificadoSQL = SQLUtils.DELETE_CERTIFICADO_ESTUDANTE_ID_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(deleteCertificadoSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Certificado excluído com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao excluir certificado: " + e.getMessage());
        }
    }

    public void excluirDisciplina(int id) {
        try {
            String deleteDisciplinaSQL = SQLUtils.DELETE_DISCIPLINA_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(deleteDisciplinaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Disciplina excluída com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao excluir disciplina: " + e.getMessage());
        }
    }

    public void excluirDisciplinaEstudanteId(int id) {
        try {
            String deleteDisciplinaSQL = SQLUtils.DELETE_DISCIPLINA_ESTUDANTE_ID_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(deleteDisciplinaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Disciplina excluída com sucesso (ID: " + id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao excluir disciplina: " + e.getMessage());
        }
    }

    // MÉTODOS DE SELECT

    public List<Estudante> selectAllEstudantes() {
        List<Estudante> estudantes = new ArrayList<>();
        try {
            String selectEstudanteSQL = SQLUtils.READ_ESTUDANTE_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(selectEstudanteSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                String cpf = resultSet.getString("CPF");
                String tipoCurso = resultSet.getString("TIPO_CURSO");
                boolean aprovacao = resultSet.getBoolean("APROVACAO");
    
                Estudante estudante = EstudanteFactory.criarEstudante(id, nome, cpf, TipoCurso.valueOf(tipoCurso), aprovacao);
                estudantes.add(estudante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar estudantes: " + e.getMessage());
        }
        return estudantes;
    }
    

    public List<Certificado> selecionarCertificados(int estudanteId) {
        List<Certificado> certificados = new ArrayList<>();
        try {
            String selectCertificadosSQL = SQLUtils.READ_CERTIFICADOS_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(selectCertificadosSQL);
            preparedStatement.setInt(1, estudanteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String descricao = resultSet.getString("DESCRICAO");
                String tipoCurso = resultSet.getString("TIPO_CURSO");
                String disciplinas = resultSet.getString("DISCIPLINAS");

                List<Integer> disciplinaIds = extrairIdsDeDisciplinas(disciplinas);

                Certificado certificado = new Certificado(id, estudanteId, descricao, TipoCurso.valueOf(tipoCurso), disciplinaIds);
                certificados.add(certificado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar certificados: " + e.getMessage());
        }
        return certificados;
    }

    public List<Disciplina> selecionarDisciplinasByEstudanteId(int estudanteId) {
        List<Disciplina> disciplinas = new ArrayList<>();
        try {
            String selectDisciplinasSQL = SQLUtils.READ_DISCIPLINAS_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(selectDisciplinasSQL);
            preparedStatement.setInt(1, estudanteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                String professor = resultSet.getString("PROFESSOR");
                int cargaHoraria = resultSet.getInt("CARGA_HORARIA");
                String tipoCurso = resultSet.getString("TIPO_CURSO");
                String notaFinal = resultSet.getString("NOTA_FINAL");
                boolean aprovacao = resultSet.getBoolean("APROVACAO");

                Disciplina disciplina =
                        DisciplinaFactory.criarDisciplina(id, estudanteId, nome, cargaHoraria, professor, TipoCurso.valueOf(tipoCurso), notaFinal,
                                aprovacao);
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }

    public List<Disciplina> selectDisciplinas(int estudanteId) {
        List<Disciplina> disciplinas = new ArrayList<>();
    
        try {
            String selectDisciplinasSQL = SQLUtils.READ_DISCIPLINAS_SQL;
            PreparedStatement preparedStatement = conexao.prepareStatement(selectDisciplinasSQL);
            preparedStatement.setInt(1, estudanteId); // Define o valor do placeholder para ESTUDANTE_ID
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                String professor = resultSet.getString("PROFESSOR");
                int cargaHoraria = resultSet.getInt("CARGA_HORARIA");
                String tipoCurso = resultSet.getString("TIPO_CURSO");
                String notaFinal = resultSet.getString("NOTA_FINAL");
                boolean aprovacao = resultSet.getBoolean("APROVACAO");
    
                Disciplina disciplina = DisciplinaFactory.criarDisciplina(id, estudanteId, nome, cargaHoraria, professor, TipoCurso.valueOf(tipoCurso), notaFinal, aprovacao);
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar disciplinas: " + e.getMessage());
        }
    
        return disciplinas;
    }
    

    public Disciplina selecionarDisciplinaPorId(int disciplinaId) {
        Disciplina disciplina = null;
    
        try {
            String selectDisciplinaByIdSQL = SQLUtils.SELECT_DISCIPLINA_BY_ID;
            PreparedStatement preparedStatement = conexao.prepareStatement(selectDisciplinaByIdSQL);
            preparedStatement.setInt(1, disciplinaId);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                int estudanteId = resultSet.getInt("ESTUDANTE_ID");
                String nome = resultSet.getString("NOME");
                String professor = resultSet.getString("PROFESSOR");
                int cargaHoraria = resultSet.getInt("CARGA_HORARIA");
                String tipoCurso = resultSet.getString("TIPO_CURSO");
                String notaFinal = resultSet.getString("NOTA_FINAL");
                boolean aprovacao = resultSet.getBoolean("APROVACAO");
    
                TipoCurso tipoCursoEnum = null;
    
                // Verifique se o valor do banco de dados corresponde a um valor válido do enum
                try {
                    tipoCursoEnum = TipoCurso.valueOf(tipoCurso);
                } catch (IllegalArgumentException e) {
                    // Trate o caso em que o valor não corresponde a nenhum enum válido
                    System.err.println("Valor inválido para TIPO_CURSO: " + tipoCurso);
                }
    
                if (tipoCursoEnum != null) {
                    disciplina = DisciplinaFactory.criarDisciplina(disciplinaId, estudanteId, nome, cargaHoraria, professor, tipoCursoEnum, notaFinal, aprovacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar disciplina: " + e.getMessage());
        }
    
        return disciplina;
    }
    
    

    public List<Integer> extrairIdsDeDisciplinas(String disciplinasString) {
        List<Integer> disciplinaIds = new ArrayList<>();
    
        if (disciplinasString != null && !disciplinasString.isEmpty()) {
            String[] idsArray = disciplinasString.split(",");
            for (String id : idsArray) {
                try {
                    int disciplinaId = Integer.parseInt(id);
                    disciplinaIds.add(disciplinaId);
                } catch (NumberFormatException e) {
                    // Não faz nada se encontrar IDs inválidos
                }
            }
        }
    
        return disciplinaIds;
    }
    
    

    public Estudante selecionarEstudantePorId(int estudanteId) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Estudante estudanteSelecionado = null;

        try {
            String selectEstudanteSQL = SQLUtils.SELECT_ESTUDANTE_BY_ID;
            preparedStatement = conexao.prepareStatement(selectEstudanteSQL);
            preparedStatement.setInt(1, estudanteId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                estudanteSelecionado = construirEstudanteAPartirDoResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar estudante: " + e.getMessage());
        }

        return estudanteSelecionado;
    }

    private Estudante construirEstudanteAPartirDoResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String cpf = resultSet.getString("cpf");
        String tipoCurso = resultSet.getString("tipo_curso");
        boolean aprovacao = resultSet.getBoolean("aprovacao");

        return EstudanteFactory.criarEstudante(id, nome, cpf, TipoCurso.valueOf(tipoCurso), aprovacao);
    }

    public Certificado selecionarCertificadoPorId(int certificadoId) {
        Certificado certificado = null;
        try {
            String selectCertificadoSQL = "SELECT * FROM certificados WHERE ID = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(selectCertificadoSQL);
            preparedStatement.setInt(1, certificadoId);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                int estudanteId = resultSet.getInt("ESTUDANTE_ID");
                String descricao = resultSet.getString("DESCRICAO");
                String tipoCurso = resultSet.getString("TIPO_CURSO");
                String disciplinas = resultSet.getString("DISCIPLINAS");
    
                List<Integer> disciplinaIds = extrairIdsDeDisciplinas(disciplinas);
    
                certificado = new Certificado(certificadoId, estudanteId, descricao, TipoCurso.valueOf(tipoCurso), disciplinaIds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao selecionar certificado: " + e.getMessage());
        }
        return certificado;
    }
    
}