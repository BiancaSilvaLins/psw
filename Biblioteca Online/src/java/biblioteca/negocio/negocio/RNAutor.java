package biblioteca.negocio.negocio;

import java.util.ArrayList;
import biblioteca.negocio.dao.AutorDAO;
import biblioteca.negocio.dao.AutorDAOImpl;
import biblioteca.negocio.basica.Autor;


public class RNAutor {

    private final AutorDAO dao;

    public RNAutor() {
        dao = new AutorDAOImpl();
    }

    public ArrayList preencheTabela() throws ListaException {
        try {
            return dao.listarTabelaAutor();
        } catch (DAOException e) {
            throw new ListaException("Erro de SQL");
        } catch (ConexaoException e) {
            throw new ListaException("Erro de BD");
        }
    }

    /**
     * Verifica se todos os campos importantes foram preenchidos
     *
     * @param a Objeto contendo todos os valores informados
     * @throws CampoVazioException Caso algum campo esteja vazio
     */
    public void verificaPreenchimentoAutor(Autor a) throws CampoVazioException {
        //verifica preenchimento
        if (a.getNome() == null || a.getNome().trim().isEmpty()) {
            throw new CampoVazioException("Nome vazio");
        }
        if (a.getEmail() == null || a.getEmail().isEmpty()) {
            throw new CampoVazioException("Email inválido");
        }
    }

    public void validaValoresAutor(Autor a) throws DadoInvalidoException {
        //valida valores (regras)
        if (a.getNome().length() < 3 || a.getNome().contains("  ")) {
            throw new DadoInvalidoException("erro nome");
        }
        if (a.getNome().indexOf(" ") == 0 || a.getNome().lastIndexOf(" ") == a.getNome().length()) {
            throw new DadoInvalidoException("erro nome");
        }
        if (a.getEmail().length() < 3 || a.getEmail().contains("  ")) {
            throw new DadoInvalidoException("erro email");
        }
        if (a.getEmail().indexOf(" ") == 0 || a.getEmail().lastIndexOf(" ") == a.getEmail().length()) {
            throw new DadoInvalidoException("erro email");
        }
    }

    public void verificaDuplicacaoAutor(Autor a) throws DadoDuplicadoException, VerificaDuplicacaoException {
        //checa duplicacao no bd
        try {
            Autor x = dao.consultar(a.getNome());
            if (x != null) {
                throw new DadoDuplicadoException("Já existe esse autor!!!");
            }
        } catch (DAOException e) {
            throw new VerificaDuplicacaoException("Erro de SQL");
        } catch (ConexaoException e) {
            throw new VerificaDuplicacaoException("Erro de BD");
        }
    }
    
    public void verificaDuplicacaoAutor2(Autor a) throws DadoDuplicadoException, VerificaDuplicacaoException {
        //checa duplicacao no bd
        try {
            Autor x = dao.consultar(a.getNome());
            if (x != null && !x.getNome().equals(a.getNome())) {
                throw new DadoDuplicadoException("Já existe esse autor!!!");
            }
        } catch (DAOException e) {
            throw new VerificaDuplicacaoException("Erro de SQL");
        } catch (ConexaoException e) {
            throw new VerificaDuplicacaoException("Erro de BD");
        }
    }

    /**
     *
     * @param a
     * @throws GravaException
     */
    public void gravaAutor(Autor a) throws GravaException {
        try {
            dao.incluir(a);
        } catch (DAOException e) {
            throw new GravaException("Erro de SQL");
        } catch (ConexaoException e) {
            throw new GravaException("Erro de BD");
        }
    }

    public void excluirAutor(Integer id) throws ExcluirException {
        try {
            dao.excluirAutor(id);
        } catch (DAOException e) {
            throw new ExcluirException("Erro de SQL ou Autor está vinculado à um livro.");
        } catch (ConexaoException e) {
            throw new ExcluirException("Erro na conexão com o banco de dados.");
        }
    }

    public ArrayList pesquisaTabelaAutor(String nome) throws PesquisaException {
        try {
            return dao.pesquisaTabelaAutor(nome);
        } catch (ConexaoException ex) {
            throw new PesquisaException("erro do kapeta666");
        } catch (DAOException ex) {
            throw new PesquisaException("erro do kapeta666!");
        }

    }

    public void editaAutor(Autor a) throws EditaException {
        try {

            dao.alterarAutor(a);

        } catch (DAOException e) {

            throw new EditaException("Erro de SQL edita");
        } catch (ConexaoException e) {
            throw new EditaException("Erro de BD");
        }
    }

}
