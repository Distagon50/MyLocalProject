package code;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;


public class AuditLogCount {
	
	@Test
	public void DateCheck () {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		Date d1 = cal.getTime();
		Date d2 = cal.getTime();
		
		d2 = DateUtils.addHours(d2, 1);
		
		d1 = DateUtils.truncate(d1, Calendar.HOUR_OF_DAY);
		d2 = DateUtils.truncate(d2, Calendar.HOUR_OF_DAY);
		
		Map<Date, String> map1 = new HashMap<Date, String>();
		map1.put(d1, "1");
		map1.put(d2, "2");
		map1.put(d1, "3");
		
		assertEquals("3", map1.get(d1));
		assertEquals("2", map1.get(d2));
	}
}
