package br.org.sesisp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.org.sesisp.controller.ConexaoJO;
import br.org.sesisp.model.AlunoJO;

public class CrudDAO_JO {

    // CRUD C-Create R-Read D-Delete

    // CREATE (Inserir dados)
    public void create(AlunoJO aluno) {
        String sql = "INSERT INTO alunos(nome, idade) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement p = null;
        try {
            conn = ConexaoJO.CriandoConexao();
            p = conn.prepareStatement(sql);
            p.setString(1, aluno.getNome());
            p.setInt(2, aluno.getIdade());
            p.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir dados! \nERRO: " + e);
        } finally {
            try {
                if (p != null)
                    p.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }// fim CREATE
    // *****************************************************************

    // método U - Update
    public void update(AlunoJO aluno) {
        String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE ra = ?";
        Connection conn = null;
        PreparedStatement p = null;
        try {
            conn = ConexaoJO.CriandoConexao();
            p = conn.prepareStatement(sql);
            p.setString(1, aluno.getNome());
            p.setInt(2, aluno.getIdade());
            p.setInt(3, aluno.getRa());
            p.execute();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao atualizar dados! \nERRO: " + e);
        } finally {
            try {
                if (p != null)
                    p.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }// fim UPDATE
    // *****************************************************************

    // método R - Read
    public List<AlunoJO> read() {
        String sql = "SELECT * FROM alunos";
        List<AlunoJO> alunos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet resultado = null;

        try {
            conn = ConexaoJO.CriandoConexao();
            p = conn.prepareStatement(sql);
            resultado = p.executeQuery();
            while (resultado.next()) {
                AlunoJO ver_aluno = new AlunoJO();
                ver_aluno.setRa(resultado.getInt("ra"));
                ver_aluno.setNome(resultado.getString("nome"));
                ver_aluno.setIdade(resultado.getInt("idade"));
                alunos.add(ver_aluno);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao editar dados! \nERRO: " + e);
        } finally {
            try {
                if (p != null)
                    p.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return alunos;
    }// fim READ
    // *****************************************************************

    // método D - Delete
    public boolean delete(int ra) {
        String sql = "DELETE FROM alunos WHERE ra = ?";
        Connection conn = null;
        PreparedStatement p = null;
        try {
            conn = ConexaoJO.CriandoConexao();
            p = conn.prepareStatement(sql);
            p.setInt(1, ra);
            p.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao excluir dados! \nERRO: " + e);
        } finally {
            try {
                if (p != null)
                    p.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }// fim DELETE
}
