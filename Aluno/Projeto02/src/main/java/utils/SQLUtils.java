package main.java.utils;

public class SQLUtils {

    public static String CREATE_SCHEMA_MYSQL = "CREATE DATABASE IF NOT EXISTS ESCOLA;";
    public static final String  USE_SCHEMA_BANCO = "use ESCOLA;";

    public static final String CREATE_TABLE_ESTUDANTES =
            "CREATE TABLE  IF NOT EXISTS ESTUDANTES  (" + 
            "ID INT PRIMARY KEY AUTO_INCREMENT," + 
            "NOME VARCHAR(255)," + 
            "CPF VARCHAR(11)," +
            "TIPO_CURSO VARCHAR(50)," +
            "DISCIPLINAS VARCHAR(255) DEFAULT ''," + // Adicione o valor padrão vazio aqui
            "APROVACAO BOOLEAN" + 
            ");";

    public static final String CREATE_TABLE_CERTIFICADOS =
            "CREATE TABLE  IF NOT EXISTS CERTIFICADOS (" + 
            "ID INT PRIMARY KEY AUTO_INCREMENT," + 
            "ESTUDANTE_ID INT," + 
            "DESCRICAO VARCHAR(255)," +
            "TIPO_CURSO VARCHAR(50)," +
            "DISCIPLINAS VARCHAR(255) DEFAULT ''," + // Adicione o valor padrão vazio aqui
            "FOREIGN KEY (ESTUDANTE_ID) REFERENCES ESTUDANTES(ID)" + 
            ");";

    public static String CREATE_DISCIPLINAS_TABLE =
            "CREATE TABLE  IF NOT EXISTS DISCIPLINAS (" + 
            "ID INT PRIMARY KEY AUTO_INCREMENT," + 
            "ESTUDANTE_ID INT," + 
            "NOME VARCHAR(255)," + 
            "PROFESSOR VARCHAR(255)," + 
            "CARGA_HORARIA INT," + 
            "TIPO_CURSO VARCHAR(50)," + 
            "NOTA_FINAL VARCHAR(255) NOT NULL DEFAULT ''," + // Adicione o valor padrão vazio aqui
            "APROVACAO BOOLEAN," + 
            "FOREIGN KEY (ESTUDANTE_ID) REFERENCES ESTUDANTES(ID)" + 
            ");";

    public static final String CREATE_ESTUDANTE_SQL = "INSERT INTO ESTUDANTES (NOME, CPF, TIPO_CURSO, DISCIPLINAS, APROVACAO) VALUES (?, ?, ?, ?, ?);";
    public static final String READ_ESTUDANTE_SQL = "SELECT * FROM ESTUDANTES";
    public static final String UPDATE_ESTUDANTE_SQL = "UPDATE ESTUDANTES SET NOME = ?, TIPO_CURSO = ?, APROVACAO = ?, DISCIPLINAS = ?  WHERE ID = ?;";
    public static final String DELETE_ESTUDANTE_SQL = "DELETE FROM ESTUDANTES WHERE ID = ?;";

    public static final String CREATE_CERTIFICADO_SQL = "INSERT INTO CERTIFICADOS (ESTUDANTE_ID, DESCRICAO, TIPO_CURSO) VALUES (?, ?, ?);";
    public static final String READ_CERTIFICADOS_SQL = "SELECT * FROM CERTIFICADOS WHERE ESTUDANTE_ID = ?;";
    public static final String UPDATE_CERTIFICADO_SQL = "UPDATE CERTIFICADOS SET DESCRICAO = ?, TIPO_CURSO = ? WHERE ID = ?;";
    public static final String DELETE_CERTIFICADO_SQL = "DELETE FROM CERTIFICADOS WHERE ID = ?;";
    public static final String DELETE_CERTIFICADO_ESTUDANTE_ID_SQL = "DELETE FROM CERTIFICADOS WHERE ESTUDANTE_ID = ?;";

    public static final String CREATE_DISCIPLINA_SQL =
            "INSERT INTO DISCIPLINAS (ESTUDANTE_ID, NOME, PROFESSOR, CARGA_HORARIA, TIPO_CURSO, NOTA_FINAL, APROVACAO) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String READ_DISCIPLINAS_SQL = "SELECT * FROM DISCIPLINAS WHERE ESTUDANTE_ID = ?;";
    public static final String UPDATE_DISCIPLINA_SQL =
            "UPDATE DISCIPLINAS SET NOME = ?, PROFESSOR = ?, CARGA_HORARIA = ?, TIPO_CURSO = ?, NOTA_FINAL = ?, APROVACAO = ? WHERE ID = ?;";

    public static final String DELETE_DISCIPLINA_SQL = "DELETE FROM DISCIPLINAS WHERE ID = ?;";
    public static final String DELETE_DISCIPLINA_ESTUDANTE_ID_SQL = "DELETE FROM DISCIPLINAS WHERE ESTUDANTE_ID = ?;";

    public static final String SELECT_ESTUDANTE_BY_ID = "SELECT * FROM ESTUDANTES WHERE ID = ?;";
    public static final String SELECT_DISCIPLINA_BY_ID = "SELECT * FROM DISCIPLINAS WHERE ID = ?;";
}
