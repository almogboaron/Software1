package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet){
		Collections.sort((List<Spaceship>)fleet,new Comparator<Spaceship>(){@Override
															public int compare(Spaceship o1, Spaceship o2) {
																int c1=Integer.compare(o2.getFirePower(),o1.getFirePower());
																if(c1!=0) {return c1;}
																c1 = Integer.compare(o2.getCommissionYear(),o1.getCommissionYear());
																if(c1!=0) {return c1;}
																return o2.getName().compareTo(o1.getName());
																}
																																		});
		return fleet.stream().map(x->x.toString()).collect(Collectors.toList());
	}
		

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(Spaceship ship: fleet) {
			String shipClass = ship.getClass().getSimpleName();
			map.computeIfAbsent(shipClass, key -> 0);
			map.compute(shipClass, (key,val)->val+1);
			}
		return map;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		return fleet.stream().map(ship -> ship.getAnnualMaintenanceCost()).reduce(0, Integer::sum);
	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 * @throws ClassNotFoundException 
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet){
		Set<String> weaponsSet = new HashSet<String>();
		for(Spaceship ship : fleet) {
			try {
			if(myAbstractFightingShip.class.isInstance(ship)) {//Got Weapons
				for(Weapon wep :((myAbstractFightingShip) ship).getWeapon())
					weaponsSet.add(wep.getName());
		}else {continue;}
			}
			catch(Exception a) {
			}
		}
			
		return weaponsSet;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		return fleet.stream().map(x->x.getCrewMembers().size()).reduce(0, Integer::sum);
	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		int counter = 0;
		float sumOfAges=0;
		for(Spaceship ship:fleet) {
			for(CrewMember mem:ship.getCrewMembers()) {
				if(Officer.class.isInstance(mem)) {
					sumOfAges+=mem.getAge();
					counter=counter+1;
				}
			}
		}
		if(counter!=0) {
			return sumOfAges/counter;
		}
		return 0f;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> highRankedOfficers = new HashMap<Officer,Spaceship>();
		for(Spaceship ship:fleet) {
			List<Officer> lst = new ArrayList<Officer>(); ;
			for( CrewMember mem: ship.getCrewMembers()) {
				if(Officer.class.isInstance(mem)) {
					lst.add((Officer)mem);
				}else {continue;}
			}
			if(lst.size()!=0) {
			lst.sort((x,y)->y.getRank().compareTo(x.getRank()));
			highRankedOfficers.put(lst.get(0), ship);
			}
			else {continue;}
		}
		return highRankedOfficers;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> mapR = new HashMap<OfficerRank, Integer>();
		for(Spaceship ship: fleet) {
			for(CrewMember member: ship.getCrewMembers()) {
				if(Officer.class.isInstance(member)) {
					OfficerRank r =((Officer)member).getRank();
					if(mapR.containsKey(r)){
						mapR.put(r,mapR.get(r)+1);
					}else {
						mapR.put(r, 1);
					}
				}
			}
		}
		List<Map.Entry<OfficerRank, Integer>> lst = new ArrayList<Map.Entry<OfficerRank, Integer>>(mapR.entrySet());
		lst.sort((x,y)->{if(x.getValue().compareTo(y.getValue())==0) {
							return x.getKey().compareTo(y.getKey());
						}else {
							return x.getValue().compareTo(y.getValue());}});
		return lst;
	}


}
