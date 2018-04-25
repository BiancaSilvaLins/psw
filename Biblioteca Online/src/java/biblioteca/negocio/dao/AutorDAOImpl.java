
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import biblioteca.util.GerenciadorConexaoImpl;

public class AutorDAOImpl implements AutorDAO {

    GerenciadorConexao g;

    public AutorDAOImpl() {
        g = GerenciadorConexaoImpl.getInstancia();
    }

    @Override
    public void incluir(Autor a) throws ConexaoException, DAOException {
        Connection au = g.conectar();
        String sql = "INSERT INTO AUTOR (NOME_AUTOR,EMAIL_AUTOR) VALUES (?,?)";
        try {
            PreparedStatement pstm = au.prepareStatement(sql);
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getEmail());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(au);
        }
    }

    @Override
    public void excluirAutor(Integer id) throws ConexaoException, DAOException {
        Connection a = g.conectar();
        String sql = "DELETE FROM AUTOR WHERE ID_AUTOR = ?";
        try {
            PreparedStatement pstm = a.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(a);
        }
    }

    @Override
    public void alterarAutor(Autor a) throws ConexaoException, DAOException {
        Connection au = g.conectar();
        String sql = "UPDATE AUTOR SET NOME_AUTOR = ?,EMAIL_AUTOR = ? WHERE ID_AUTOR = ?";

        try {

            PreparedStatement pstm = au.prepareStatement(sql);
            pstm.setString(1, a.getNome());
            pstm.setString(2, a.getEmail());
            pstm.setInt(3, a.getId());
            pstm.executeUpdate();

        } catch (SQLException e) {

            throw new DAOException(e);

        } finally {
            g.desconectar(au);
        }
    }

    @Override
    public ArrayList<Autor> listar() throws ConexaoException, DAOException {
        Connection co = g.conectar();
        ArrayList<Autor> lista = new ArrayList();

        String sql = "SELECT ID_AUTOR,NOME_AUTOR,EMAIL_AUTOR FROM AUTOR";

        try {
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Autor a = new Autor();
                a.setId(rs.getInt("ID_AUTOR"));
                a.setNome(rs.getString("NOME_AUTOR"));
                a.setEmail(rs.getString("EMAIL_AUTOR"));
                lista.add(a);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(co);
        }
    }

    @Override
    public Autor get(Integer id) throws ConexaoException, DAOException {
        Connection co = g.conectar();
        Autor a = null;

        String sql = "SELECT ID_AUTOR,NOME_AUTOR,EMAIL_AUTOR FROM AUTOR WHERE ID_AUTOR =?";

        try {
            PreparedStatement pstm = co.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                a = new Autor();
                a.setId(rs.getInt("ID_AUTOR"));
                a.setNome(rs.getString("NOME_AUTOR"));
                a.setEmail(rs.getString("EMAIL_AUTOR"));
            }
            return a;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(co);
        }
    }

    @Override
    public Autor consultar(String nome) throws ConexaoException, DAOException {
        Connection co = g.conectar();
        Autor a = null;

        String sql;
        sql = "SELECT ID_AUTOR,NOME_AUTOR,EMAIL_AUTOR FROM AUTOR WHERE NOME_AUTOR LIKE ?";

        try {
            PreparedStatement pstm = co.prepareStatement(sql);
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                a = new Autor();
                a.setId(rs.getInt("ID_AUTOR"));
                a.setNome(rs.getString("NOME_AUTOR"));
                a.setEmail(rs.getString("EMAIL_AUTOR"));
            }
            return a;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(co);
        }
    }

    @Override
    public ArrayList pesquisaTabelaAutor(String nome) throws ConexaoException, DAOException {
        Connection co = g.conectar();
        ArrayList lista = new ArrayList();

        String sql = "SELECT ID_AUTOR,NOME_AUTOR,EMAIL_AUTOR FROM AUTOR WHERE NOME_AUTOR LIKE '%" + nome + "%'";

        try {
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"), rs.getString("EMAIL_AUTOR")});
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(co);
        }
    }

    @Override
    public ArrayList listarTabelaAutor() throws ConexaoException, DAOException {
        Connection co = g.conectar();
        ArrayList lista = new ArrayList();

        String sql = "SELECT ID_AUTOR,NOME_AUTOR,EMAIL_AUTOR FROM AUTOR";

        try {
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt("ID_AUTOR"), rs.getString("NOME_AUTOR"), rs.getString("EMAIL_AUTOR")});
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            g.desconectar(co);
        }
    }
}
