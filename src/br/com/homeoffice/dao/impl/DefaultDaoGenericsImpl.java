package br.com.homeoffice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.homeoffice.core.Login;
import br.com.homeoffice.dao.CadGenericoDaoService;
import br.com.homeoffice.util.JPAUtil;

@Repository()
public class DefaultDaoGenericsImpl<T> implements CadGenericoDaoService<T>

{

	private static final Logger LOG = Logger.getLogger(DefaultDaoGenericsImpl.class);
	private Class<T> classe;

	public DefaultDaoGenericsImpl(Class<T> classe) {
		this.classe = classe;
	}

	public DefaultDaoGenericsImpl() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void removerObjeto(T t) {
		new JPAUtil();
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(t));
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void atualizarObjeto(T t) {
		new JPAUtil();
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<T> listarObjetos(Class<T> classes) {
		new JPAUtil();
		entityManager = JPAUtil.getEntityManager();
		CriteriaQuery<T> query = (CriteriaQuery<T>) entityManager.getCriteriaBuilder().createQuery(classes);
		query.select(query.from(classes));
		List<T> lista = entityManager.createQuery(query).getResultList();
		entityManager.close();
		return lista;
	}

	@Override
	public T buscarObjetoPorId(Integer id) {
		new JPAUtil();
		entityManager = JPAUtil.getEntityManager();
		T instancia = entityManager.find(classe, id);
		entityManager.close();
		return instancia;
	}

	@Override
	public List<Login> buscarPorLogin(Login bean) {
		List<Login> logins = new ArrayList<Login>();
		Connection conn = null;
		PreparedStatement query = null;
		String sql = null;
		ResultSet rs = null;
		Login login = null;

		try {
			conn = ConexaoDefaultDAO.getconxao();
			sql = "SELECT LoginUsuario,senhaUsuario, ativo FROM login WHERE LoginUsuario=?";

			try {
				query = conn.prepareStatement(sql);
				query.setString(1, bean.getLoginUsuario());
				rs = query.executeQuery();
				while (rs.next()) {
					login = new Login();
					login.setLoginUsuario(rs.getString("LoginUsuario"));
					login.setSenhaUsuario(rs.getString("senhaUsuario"));
					login.setAtivo(rs.getBoolean("ativo"));
					logins.add(login);
				}

			} catch (Exception causa) {
				LOG.info(causa.getMessage());

			}

		} catch (Exception causa) {
			LOG.info(causa.getMessage());
		}
		return logins;
	}

	@Override
	public String buscarPorLoginString(String userName) {
		// List<Login> logins = new ArrayList<Login>();
		Connection conn = null;
		PreparedStatement query = null;
		String sql = null;
		ResultSet rs = null;
		Login login = null;

		try {
			conn = ConexaoDefaultDAO.getconxao();
			sql = "SELECT LoginUsuario FROM login WHERE LoginUsuario=?";

			try {
				query = conn.prepareStatement(sql);
				query.setString(1, userName.getClass().getName());
				rs = query.executeQuery();
				while (rs.next()) {
					login = new Login();
					login.setLoginUsuario(rs.getString("LoginUsuario"));
					// login.setSenhaUsuario(rs.getString("senhaUsuario"));
					// login.setAtivo(rs.getBoolean("ativo"));
					// logins.add(login);
				}

			} catch (Exception causa) {
				causa.printStackTrace();

			}

		} catch (Exception causa) {
			causa.printStackTrace();
		}

		return userName;

	}

	@Override
	public void adicionarObjeto(T t) {
		new JPAUtil();
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getClasse() {
		return classe;
	}

}
