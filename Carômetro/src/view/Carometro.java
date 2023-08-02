package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DAO;
import utils.Validador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Carometro extends JFrame {

    DAO dao = new DAO();
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private FileInputStream fis;
    private int tamanho;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblStatus;
    private JLabel lblData;
    private JLabel lblNewLabel;
    private JTextField txtRA;
    private JLabel lblNewLabel_1;
    private JLabel lblFoto;
    private JTextField txtNome;
    private JButton btnReset;
    private JButton btnBuscar;
    private JList<String> listNomes;
    private JScrollPane scrollPaneLista;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Carometro frame = new Carometro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Carometro() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                status();
                setarData();
            }
        });
        setTitle("carometro");
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Carometro.class.getResource("/img/instagram.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 634, 329);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        scrollPaneLista = new JScrollPane();
        scrollPaneLista.setBorder(null);
        scrollPaneLista.setVisible(false);
        scrollPaneLista.setBounds(76, 82, 220, 64);
        contentPane.add(scrollPaneLista);

        listNomes = new JList<>();
        listNomes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarNome();
            }
        });
        listNomes.setBorder(null);
        scrollPaneLista.setViewportView(listNomes);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.textHighlight);
        panel.setBounds(10, 227, 600, 54);
        contentPane.add(panel);
        panel.setLayout(null);

        lblStatus = new JLabel("");
        lblStatus.setIcon(new ImageIcon(Carometro.class.getResource("/img/dboff.png")));
        lblStatus.setBounds(545, 11, 32, 32);
        panel.add(lblStatus);

        lblData = new JLabel("");
        lblData.setForeground(SystemColor.text);
        lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblData.setBounds(10, 11, 503, 32);
        panel.add(lblData);

        lblNewLabel = new JLabel("RA");
        lblNewLabel.setBounds(27, 32, 61, 14);
        contentPane.add(lblNewLabel);

        txtRA = new JTextField();
        txtRA.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String caracteres = "0123456789";
                if (!caracteres.contains(e.getKeyChar() + "")) {
                    e.consume();
                }
            }
        });
        txtRA.setBounds(76, 29, 96, 20);
        contentPane.add(txtRA);
        txtRA.setColumns(10);
        txtRA.setDocument(new Validador(6));

        lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setBounds(27, 69, 49, 14);
        contentPane.add(lblNewLabel_1);

        txtNome = new JTextField();
        txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        txtNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                listarNomes();
            }
        });
        txtNome.setBounds(76, 63, 220, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);
        txtNome.setDocument(new Validador(30));

        lblFoto = new JLabel("");
        lblFoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblFoto.setIcon(new ImageIcon(Carometro.class.getResource("/img/camera.png")));
        lblFoto.setBounds(354, 0, 256, 216);
        contentPane.add(lblFoto);

        JButton btnCarregar = new JButton("Carregar Foto");
        btnCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarFoto();
            }
        });
        btnCarregar.setForeground(SystemColor.textHighlight);
        btnCarregar.setBounds(211, 94, 133, 23);
        contentPane.add(btnCarregar);

        JButton btnAdicionar = new JButton("");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionar();
            }
        });
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setIcon(new ImageIcon(Carometro.class.getResource("/img/create.png")));
        btnAdicionar.setBounds(10, 152, 64, 64);
        contentPane.add(btnAdicionar);

        btnReset = new JButton("");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnReset.setIcon(new ImageIcon(Carometro.class.getResource("/img/eraser.png")));
        btnReset.setToolTipText("Limpar Campos");
        btnReset.setBounds(280, 152, 64, 64);
        contentPane.add(btnReset);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarRA();
            }
        });
        btnBuscar.setForeground(SystemColor.textHighlight);
        btnBuscar.setBounds(182, 28, 133, 23);
        contentPane.add(btnBuscar);
    }

    private void status() {
        try {
            con = dao.conectar();
            if (con == null) {
                lblStatus.setIcon(new ImageIcon(Carometro.class.getResource("/img/dboff.png")));
            } else {
                lblStatus.setIcon(new ImageIcon(Carometro.class.getResource("/img/dbon.png")));
            }
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setarData() {
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
        lblData.setText(formatador.format(data));
    }

    private void carregarFoto() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Selecionar arquivo");
        jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de Imagens(*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
        int resultado = jfc.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                fis = new FileInputStream(jfc.getSelectedFile());
                tamanho = (int) jfc.getSelectedFile().length();
                Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
                        lblFoto.getHeight(), Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(foto));
                lblFoto.updateUI();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void adicionar() {
        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o nome");
            txtNome.requestFocus();
        } else {
            String insert = "insert into alunos(nome,foto) values (?,?)";
            try {
                con = dao.conectar();
                pst = con.prepareStatement(insert);
                pst.setString(1, txtNome.getText());
                pst.setBlob(2, fis, tamanho);
                int confirma = pst.executeUpdate();
                if (confirma == 1) {
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! Aluno não cadastrado ");
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void buscarRA() {
        if (txtRA.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite o RA");
            txtRA.requestFocus();
        } else {
            String readRA = "select * from alunos where ra = ?";
            try {
                con = dao.conectar();
                pst = con.prepareStatement(readRA);
                pst.setString(1, txtRA.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtNome.setText(rs.getString(2));
                    Blob blob = (Blob) rs.getBlob(3);
                    byte[] img = blob.getBytes(1, (int) blob.length());
                    BufferedImage imagem = null;
                    try {
                        imagem = ImageIO.read(new ByteArrayInputStream(img));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    ImageIcon icone = new ImageIcon(imagem);
                    Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(),
                            lblFoto.getHeight(), Image.SCALE_SMOOTH));
                    lblFoto.setIcon(foto);
                } else {
                    JOptionPane.showMessageDialog(null, "Aluno não cadastrado");
                }
                con.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void listarNomes() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listNomes.setModel(modelo);
        String readList = "select * from alunos where nome like ? order by nome";
        try {
            con = dao.conectar();
            pst = con.prepareStatement(readList);
            pst.setString(1, txtNome.getText() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                scrollPaneLista.setVisible(true);
                modelo.addElement(rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buscarNome() {
        int linha = listNomes.getSelectedIndex();
        if (linha >= 0) {
            String readeNome = "select * from alunos where nome like ? order by nome limit ?, 1";
            try {
                con = dao.conectar();
                pst = con.prepareStatement(readeNome);
                pst.setString(1, txtNome.getText() + "%");
                pst.setInt(2, linha);
                rs = pst.executeQuery();
                if (rs.next()) {
                    scrollPaneLista.setVisible(false);
                    txtRA.setText(rs.getString(1));
                    txtNome.setText(rs.getString(2));
                    Blob blob = (Blob) rs.getBlob(3);
                    byte[] img = blob.getBytes(1, (int) blob.length());
                    BufferedImage imagem = null;
                    try {
                        imagem = ImageIO.read(new ByteArrayInputStream(img));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    ImageIcon icone = new ImageIcon(imagem);
                    Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(),
                            lblFoto.getHeight(), Image.SCALE_SMOOTH));
                    lblFoto.setIcon(foto);
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            scrollPaneLista.setVisible(false);
        }
    }

    private void reset() {
        scrollPaneLista.setVisible(false);
        txtRA.setText(null);
        txtNome.setText(null);
        lblFoto.setIcon(new ImageIcon(Carometro.class.getResource("/img/camera.png")));
        txtNome.requestFocus();
    }
}
