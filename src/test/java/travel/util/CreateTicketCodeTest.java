package travel.util;

import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CreateTicketCodeTest {
	
	@Test
	@Ignore
	public void qrCodeTest() {
		
		try {
			String ticketNo = TicketUtil.getStringTicket("161122120448428996", "01045045362");
			BufferedImage image = TicketUtil.createQrCodeImage(ticketNo);
			
			ImageIO.write(image, "jpg", new File("/Users/we/Desktop/" + ticketNo + ".jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Ignore
	public void barCodeTest() {
		
		try {
			String ticketNo = TicketUtil.getNumberTicket("161122120448428996", "01045045362");
			BufferedImage image = TicketUtil.createBarCodeImage(ticketNo);
			
			ImageIO.write(image, "jpg", new File("/Users/we/Desktop/" + ticketNo + ".jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
