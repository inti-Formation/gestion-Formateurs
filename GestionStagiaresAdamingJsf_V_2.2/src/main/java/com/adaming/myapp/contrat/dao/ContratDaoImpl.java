package com.adaming.myapp.contrat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



















import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.adaming.myapp.entities.Contrat;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.persistence.AbstractJpaDao;


public class ContratDaoImpl extends AbstractJpaDao<Contrat> implements IContratDao{

	/*@Override
	public List<Contrat> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Contrat getOne(Long id) {
		// TODO Auto-generated method stub
		return getOneAbstractJpa(id);
	}

	@Override
	public Contrat addContrat(Contrat c, Long idEtudiant) {
		Etudiant e = em.find(Etudiant.class,idEtudiant);
		c.setEtudiant(e);
		em.persist(c);
		return c;
	}

	@Override
	public int nombreContrat() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Long idContrat) {
		Contrat c=getOne(idContrat);
		em.remove(c);
	}
    */
	
	
	
	
	
	/*Contrat dao width jdbcTemplate*/
	/**
	 * @inject dateSource
	 * @inject jdbcTemplate
	 */
	
	@Inject
	private JdbcTemplate jdbcTemplate ;
	@Inject
	private DataSource dataSource;
	
	/**
	 * @param dataSource the dataSource to set
	 */

	@Override
	public List<Contrat> getAll() {
		/*si je veux récupérer que la liste des contrats*/
		/*String sql = "select * from contrat";
		List<Contrat> contrats = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Contrat>(Contrat.class));
		return contrats;*/
		
		final String SQL = "select * from contrat c join etudiant e on c.ID_CONTRAT_ETU=e.idEtudiant";
		List<Contrat> contrats = jdbcTemplate.query(SQL, new RowMapper<Contrat>(){

			@Override
			public Contrat mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contrat c = new Contrat();
				c.setIdContrat(rs.getLong("idContrat"));
				c.setDate(rs.getDate("date"));
				c.setActive(rs.getBoolean("active"));
				/**je veux récupérer seulement le id et aussi le nom de l'etudiant*/
				Etudiant e = new Etudiant();
				e.setIdEtudiant(rs.getLong("idEtudiant"));
				e.setNomEtudiant(rs.getString("nomEtudiant"));
				c.setEtudiant(e);
				return c;
			}
			
			
		});
		return contrats;
		
	}

	@Override
	public Contrat getOne(Long id) {
		final String SQL = "select * from contrat where idContrat=?";
		Contrat c = jdbcTemplate.queryForObject(SQL, new Object[]{id},new BeanPropertyRowMapper<Contrat>(Contrat.class));
		return c;
	}
    
	
	
	@Override
	public Contrat addContrat(Contrat c,Long idEtudiant) {
		final String SQL = "insert into contrat (date,active,ID_CONTRAT_ETU) values (?,?,?)";
		jdbcTemplate.update(SQL, c.getDate(),c.isActive(),idEtudiant);
		return c;
	}

	@Override
	public int nombreContrat() {
		final String SQL = "select count(*) from contrat";
		return jdbcTemplate.queryForInt(SQL);
	}
	
	@Override
	public void remove(Long idContrat) {
		final String SQL = "delete from contrat where idContrat=?";
		jdbcTemplate.update(SQL, idContrat);
	}


	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

	
	

	
}
