package main.java.gerenciamento;

import java.util.ArrayList;
import java.util.List;

import main.java.banco.BancoDeDados;
import main.java.certificado.Certificado;
import main.java.certificado.CertificadoCursoBacharelado;
import main.java.certificado.CertificadoCursoTecnico;
import main.java.decorator.CertificadoDecorator;
import main.java.disciplina.Disciplina;
import main.java.disciplina.Nota;
import main.java.enums.TipoBanco;
import main.java.enums.TipoCurso;
import main.java.estudante.Estudante;
import main.java.fabrica.db.BancoFactory;
import main.java.fabrica.db.MariaDBBancoFactory;
import main.java.fabrica.db.MySQLBancoFactory;
import main.java.fabrica.disciplina.DisciplinaFactory;
import main.java.fabrica.estudante.EstudanteFactory;
import main.java.service.DisciplinaService;
import main.java.service.EstudanteService;

public class Gerenciador {
    private BancoDeDados bancoDeDados;
    private TipoBanco tipoBanco;

    private final EstudanteService estudanteService;
    private final DisciplinaService disciplinaService;

    public Gerenciador(TipoBanco tipoBanco) {
        this.tipoBanco = tipoBanco;
        criarBanco();

        this.estudanteService = new EstudanteService(bancoDeDados);
        this.disciplinaService = new DisciplinaService(bancoDeDados);
    }

    public void trocarBanco(TipoBanco novoTipoBanco) {
        if (tipoBanco != novoTipoBanco) {
            desconectar();
            tipoBanco = novoTipoBanco;
            criarBanco();
        }
    }

    public Estudante criarEstudante(String nome, String cpf, TipoCurso tipoCurso) {
        Estudante aluno = EstudanteFactory.criarEstudante(nome, cpf, tipoCurso);

        estudanteService.insert(aluno);

        return aluno;
    }

    public void criarDisciplina(Estudante estudante, String nome, String professor, int cargaHoraria, List<Nota> notas) {
        Disciplina disciplina = DisciplinaFactory.criarDisciplina(estudante.getId(), nome, cargaHoraria, professor, estudante.getTipoCurso(), notas);
        disciplinaService.insert(disciplina);

        estudante.adicionarDisciplina(disciplina);
        estudanteService.update(estudante);
    }

    public void updateEstudante(Estudante estudante) {
        estudanteService.update(estudante);
    }

    public void updateDisciplina(Disciplina disciplina) {
        disciplinaService.update(disciplina);
    }

    public void deletarEstudante(Estudante estudante) {
        estudanteService.delete(estudante);
    }

    public void deletarDisciplina(Estudante estudante, Disciplina disciplina) {
        if (estudante.getDisciplinas().contains(disciplina)) {
            estudante.getDisciplinas().remove(disciplina);
            estudanteService.update(estudante);
            disciplinaService.delete(disciplina);

            System.out.println("Disciplina excluída com sucesso para o estudante com ID: " + estudante.getId());
        } else {
            System.out.println("O estudante não possui essa disciplina.");
        }
    }

    public Estudante selectEstudante(int estudanteId) {
        Estudante estudante = (Estudante) estudanteService.select(estudanteId);

        String disciplinasIdString = estudante.getDisciplinasIdToString();
        estudante.setDisciplinasIdFromString(disciplinasIdString);

        estudante.setDisciplinas(new ArrayList<>());

        for (Integer disciplinaId : estudante.getDisciplinasId()) {
            Disciplina disciplina = (Disciplina) disciplinaService.select(disciplinaId);

            if (disciplina != null) {
                estudante.adicionarDisciplina(disciplina);
            }
        }
        return estudante;
    }

    public Disciplina selectDisciplina(int disciplinaId) {
        return (Disciplina) disciplinaService.select(disciplinaId);
    }

    public void exibirTodosEstudantes() {
       List<Estudante> listaDeEstudantes = estudanteService.selectAllSEstudantes();
    

        if (!listaDeEstudantes.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            listaDeEstudantes.forEach(estudante -> stringBuilder.append(estudante.toString()).append("\n"));

            System.out.println(stringBuilder.toString().trim());
        } else {
            System.out.println("Nenhum objeto cadastrado nessa tabela!");
        }
    }

    public void exibirTodasDisciplinas() {
        List<Object> listaDeObjetos = disciplinaService.selectAll();
        List<Disciplina> listaDeDisciplinas = new ArrayList<>();

        for (Object objeto : listaDeObjetos) {
            if (objeto instanceof Disciplina) {
                listaDeDisciplinas.add((Disciplina) objeto);
            }
        }

        if (!listaDeDisciplinas.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            listaDeDisciplinas.forEach(disciplina -> stringBuilder.append(disciplina.toString()).append("\n"));

            System.out.println(stringBuilder.toString().trim());
        } else {
            System.out.println("Nenhum objeto cadastrado nessa tabela!");
        }
    }

    public void conectar() {
        if (this.bancoDeDados != null) {
            this.bancoDeDados.conectar();
        }
    }

    public void desconectar() {
        if (this.bancoDeDados != null) {
            this.bancoDeDados.desconectar();
        }
    }

    private void criarBanco() {
        BancoFactory bancoFactory = switch (tipoBanco) {
            case MYSQL -> new MySQLBancoFactory();
            case MARIADB -> new MariaDBBancoFactory();
        };

        this.bancoDeDados = bancoFactory.criarBanco();
        bancoDeDados.conectar();
        bancoDeDados.criarSchemaETabelas();
    }
    // precisa implementar o certificado 
    public Estudante decorarEstudanteComCertificado(Estudante estudante, Certificado certificado) {
        if (estudante.getTipoCurso() == TipoCurso.BACHARELADO) {
            if (certificado instanceof CertificadoCursoBacharelado) {
                return new CertificadoDecorator(estudante, ((CertificadoCursoBacharelado) certificado).getDescricao());
            } else {
                System.out.println("Certificado incompatível com o tipo de curso do estudante.");
            }
        } else if (estudante.getTipoCurso() == TipoCurso.TECNICO) {
            if (certificado instanceof CertificadoCursoTecnico) {
                return new CertificadoDecorator(estudante, ((CertificadoCursoTecnico) certificado).getDescricao());
            } else {
                System.out.println("Certificado incompatível com o tipo de curso do estudante.");
            }
        } else {
            System.out.println("Tipo de curso não suportado para a criação de certificado.");
        }
        return estudante;
    }
}
