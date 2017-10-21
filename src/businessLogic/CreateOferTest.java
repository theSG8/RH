package businessLogic;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import domain.RuralHouse;
import exceptions.BadDates;
import exceptions.OverlappingOfferExists;


class CreateOferTest {

	@Test
	void test() {
		FacadeImplementationWS bl = new FacadeImplementationWS();
		RuralHouse rh = new RuralHouse();
		Date fd = new Date(2017,3,02);
		Date ld = new Date(2017,3,01);
		float p = 5;
		try {
			bl.createOffer(rh, fd, ld, p);
			fail();
		} catch (BadDates e) {
			assertTrue(true);
		} catch (OverlappingOfferExists e) {
			fail();
		}
	}

}
