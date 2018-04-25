package biblioteca.negocio.dao;

import java.util.ArrayList;
import biblioteca.negocio.basica.Autor;


public interface AutorDAO {
    
    
    
    
    /**
     * Insere um registro no BD
     * @param a Objeto contendo todos os dados
     * @throws ConexaoException
     * @throws DAOException 
     */
    public void incluir(Autor a)throws ConexaoException,DAOException;
    
    /**
     * Apaga um registro da tabela produto
     * @param id Identificacao (PK) do registro
     * @throws ConexaoException
     * @throws DAOException 
     */
    public void excluirAutor(Integer id)throws ConexaoException,DAOException;
    
    /**
     * Atualiza TODOS os campos de um registro
     * @param a Obejto contendo todos os campos
     * @throws ConexaoException
     * @throws DAOException 
     */
    public void alterarAutor(Autor a)throws ConexaoException,DAOException;
    
    /**
     * Lista todos os registros da tabela produto
     * @return um ArrayList com todos os dados
     * @throws ConexaoException
     * @throws DAOException 
     */
    public ArrayList<Autor> listar()throws ConexaoException,DAOException;
    
    /**
     * Retorna o objeto com os dados da tabela
     * @param id Chave primaria
     * @return
     * @throws ConexaoException
     * @throws DAOException 
     */
    public Autor get(Integer id)throws ConexaoException,DAOException;
    
    /**
     * Faz uma busca pelo valor dogitado no campo NOME
     * @param nome Texto contendo o nome a ser procurado
     * @return
     * @throws ConexaoException
     * @throws DAOException 
     */
    public Autor consultar(String nome)throws ConexaoException,DAOException;
    
    public ArrayList pesquisaTabelaAutor(String nome) throws ConexaoException, DAOException;
    
    public ArrayList listarTabelaAutor() throws ConexaoException, DAOException;
}
