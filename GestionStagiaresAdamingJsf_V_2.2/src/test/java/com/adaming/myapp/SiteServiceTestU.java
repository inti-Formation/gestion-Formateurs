package com.adaming.myapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.site.service.ISiteService;

public class SiteServiceTestU {

	
	private static ISiteService serviceSite;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceSite=(ISiteService)context.getBean("SiteService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	@Ignore
	public void testSite() {
		Adresse adresse = new Adresse("Avenu","paris", "75000","France");
	    Site site = new Site("MontParnasse",adresse);
	    try {
			serviceSite.add(site);
		    assertNotNull(site.getIdSite());
		} catch (VerificationInDataBaseException e) {
			System.out.println(e.getMessage());
		}
	
	}

	@Test
	@Ignore
	public void testGetSiteById() {
		Site s = serviceSite.getOne(1L);
		assertNotNull(s.getIdSite());
	}

	@Test
	@Ignore
	public void testUpdateSite() {
	Site site = serviceSite.getOne(1L);
	site.setNomSite("nom Modifié");
	serviceSite.update(site);
	}

	@Test
	@Ignore
	public void testGetAllSites() {
		List<Site> sites = serviceSite.getAll();
		assertTrue(sites.size()>0);
		
	}
	
	@Test
	@Ignore
	public void testremoveSite() {
		List<Site> start = serviceSite.getAll();
		serviceSite.remove(1L);
		List<Site> end = serviceSite.getAll();
		assertTrue(start.size()-1 == end.size());
	}
	@Test
	@Ignore
	public void getSiteByName(){
	List<Site> s = 	serviceSite.getSiteByName("MontParnasse","Avenu");
	   System.out.println(s);
	}
	@Test
	public void getSallesBySite(){
		
		List<Object[]> results= serviceSite.getSallesBySite(1L);
		for(Object [] e:results){
			Long id = Long.valueOf(String.valueOf(e[0]));
			String numeroSalle = String.valueOf(String.valueOf(e[1]));
			Integer nbPlace = Integer.valueOf(String.valueOf(e[2]));
			System.out.println(id);
			System.out.println(nbPlace);
			System.out.println(numeroSalle);
		}
		   
	}

}
