package Hibernate;

import java.util.List;

import entity.Venue;

import DAO.VenueService;

public class HibernateVenueTest {

	public static void main(String[] args) {
		VenueService venueService = new VenueService();

//		Create ok
		
		Venue v1 = new Venue();
		v1.setId(50);	v1.setLatitude(261);	v1.setLongitude(154);	v1.setCity("asdfasdf"); v1.setRegion("aaasssdddfff");
		Venue v2 = new Venue();
		v2.setId(51);	v1.setLatitude(162);	v2.setLongitude(451);	v2.setCity("qwerqwer"); v2.setRegion("qqwweerr");
		Venue v3 = new Venue();
		v3.setId(52);	v3.setLatitude(621);	v3.setLongitude(541);	v3.setCity("zxcvzxvc"); v3.setRegion("zzxxcc");
		System.out.println("*** Persist - start ***");
		venueService.persist(v1);
		venueService.persist(v2);
		venueService.persist(v3);
		List<Venue> venues1 = venueService.findAll();
		System.out.println("Venues Persisted are : " + venues1.size());
		for (Venue a : venues1) {
			System.out.println("-" + a.toString());
		}
		System.out.println("*** Persist - end ***");
		
//		Update ok		
		
		System.out.println("*** Update - start ***");
		Venue v4 = new Venue();
		v4.setId(51);	v4.setLatitude(0);	v4.setLongitude(0);	v4.setCity("resettedCity"); v4.setRegion("restettedRegion");
		venueService.update(v4);
		System.out.println("Venue Updated is =>" +venueService.findById(v4.getId()));
		System.out.println("*** Update - end ***");

		
//		Find ok
		
		System.out.println("*** Find - start ***");
		Venue v5 = new Venue();
		v5.setId(52);
		int id1 = v5.getId();
		Venue another = venueService.findById(id1);
		System.out.println("Venue found with id " + id1 + " is =>" + another.toString());
		System.out.println("*** Find - end ***");


//		Delete ok

		System.out.println("*** Delete - start ***");
		Venue v6 = new Venue();
		v6.setId(51);
		int id3 = v6.getId();
		venueService.delete(id3);
		System.out.println("Deleted Venue with id " + id3 + ".");
		System.out.println("Now all Venues are " + venueService.findAll().size() + ".");
		System.out.println("*** Delete - end ***");
		
//		FindAll ok		
		
		System.out.println("*** FindAll - start ***");
		List<Venue> Venues2 = venueService.findAll();
		System.out.println("Venues found are :");
		for (Venue v : Venues2) {
			System.out.println("-" + v.toString());
		}
		System.out.println("*** FindAll - end ***");

// 		DeleteAll ok
		
		System.out.println("*** DeleteAll - start ***");
		venueService.deleteAll();
		System.out.println("Venues found are now " + venueService.findAll().size());
		System.out.println("*** DeleteAll - end ***");
		
		System.exit(0);
	}
}
