package travel.util;

import org.junit.Ignore;
import org.junit.Test;

public class DateCommonUtilTest {
	
	@Test
	@Ignore
	public void compareTodayTest() {
		
		Long diffDays = DateUtils.compareToday("20170407");
		
		System.out.println(diffDays);
		
	}

	@Test
	@Ignore
	public void compareDayTest() {

		Long diffDays = DateUtils.compareDay("20170407", "20170410");
		
		System.out.println(diffDays);
		
	}

}
