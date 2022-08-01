package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.bilgeadam.model.Race.HOBBIT;

import com.bilgeadam.model.Movie;
import com.bilgeadam.model.Race;
import com.bilgeadam.model.Ring;
import com.bilgeadam.model.TolkienCharacter;
import com.bilgeadam.service.DataService;

class DataServiceTest {

	DataService dataService;
	
	@BeforeEach
	void setup() {
		dataService = new DataService();
	}
	
	@Test
	void ensureThatInitializationOfTolkienCharacterWorks() {
		
		
		TolkienCharacter frodo = new TolkienCharacter("Frodo",33,HOBBIT);
		assertEquals(33, frodo.getAge(),"Frodo should be 33");
		assertEquals("Frodo", frodo.getName(),"Frodo's character has wrong name");
		assertEquals(Race.HOBBIT, frodo.getRace());
		assertNotEquals("Frodon", frodo.getName(),"Frodo's character has wrong name");
	}
	
	@Test
	void ensureFellowshipCharacterAccessByNameReturnsNullForUnknownCharacter() {
		
		TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Lars"); //lars isimli bir karakter olmadığı için null dönmeli
		assertNull(fellowshipCharacter);
	}
	
	@Test
	void ensureFellowshipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
		
		TolkienCharacter fellowshipCharacter = dataService.getFellowshipCharacter("Frodo"); //frodo isimli bir karakter olduğu için null dönmemeli
		assertNotNull(fellowshipCharacter, "Frodo not found");
		
	}
	@Test
	void checkInheritance() {
		
		TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
		assertFalse(Movie.class.isAssignableFrom(tolkienCharacter.getClass()));  
		assertTrue(TolkienCharacter.class.isAssignableFrom(tolkienCharacter.getClass()));
	}    
	
	@Test
	void ensureThatOneRingBearerIsPartOfTheFellowship() {
		
		List<TolkienCharacter> fellowship = dataService.getFellowship();
		
		Map< Ring,TolkienCharacter>ringBearers = dataService.getRingBearers();
		
		assertTrue(ringBearers.values().stream().anyMatch(ringBearer -> fellowship.contains(ringBearer)));
	}
	
	@RepeatedTest(1000)
	@DisplayName("Ensure that we can call getFollowship multiple times")
	void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
		
		
		assertNotNull(dataService.getFellowship());
	}
	
	@Test
	void ensureOrdering() {
		
		List<TolkienCharacter>fellowship = dataService.getFellowship();
		assertEquals(dataService.getFellowshipCharacter("Frodo"), fellowship.get(0));
		assertEquals(dataService.getFellowshipCharacter("Sam"), fellowship.get(1));
		assertEquals(dataService.getFellowshipCharacter("Merry"), fellowship.get(2));
		assertEquals(dataService.getFellowshipCharacter("Pippin"), fellowship.get(3));
		assertEquals(dataService.getFellowshipCharacter("Gandalf"), fellowship.get(4));
		assertEquals(dataService.getFellowshipCharacter("Legolas"), fellowship.get(5));
		assertEquals(dataService.getFellowshipCharacter("Gimli"), fellowship.get(6));
		assertEquals(dataService.getFellowshipCharacter("Aragorn"), fellowship.get(7));
		assertEquals(dataService.getFellowshipCharacter("Boromir"), fellowship.get(8));
		
	}
	
	@Test
	void ensureAge() {
		
		List<TolkienCharacter> fellowship = dataService.getFellowship();
		
		assertTrue(fellowship.stream()
				.filter(fellow -> fellow.getRace().equals(HOBBIT) || fellow.getRace().equals(Race.MAN))
				.allMatch(fellow -> fellow.getAge()<100));
		
		assertTrue(fellowship.stream()
				.filter(fellow -> fellow.getRace().equals(Race.DWARF) || fellow.getRace()
						.equals(Race.ELF) || fellow.getRace().equals(Race.MAIA)).allMatch(fellow -> fellow.getAge()>100));
	}
	
	@Test
	void ensureThatFellowsStayASmallGroup() {
		
		List<TolkienCharacter>fellowship = dataService.getFellowship();
		
		assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20)); //fellowship listesinin sınırını 20 yapıyor
	}
}
