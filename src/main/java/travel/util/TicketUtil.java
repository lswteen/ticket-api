package travel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @brief 티켓발행 관련 유틸
 * @date 생성 : 2016. 11. 21.
 * @date 최종수정 : 2016. 11. 21.
 */
public class TicketUtil {
	
	private static final String TICKET_PREFIX = "WT";
	private static final int QR_SIZE = 180;
	private static final int BAR_WIDTH = 220;
	private static final int BAR_HEIGHT = 40;
	private static final int BAR_MARGIN = 15;
	private static final int BAR_TEXT_HEIGHT = 30;

	/**
	 * 문자형 티켓번호 생성 - 텍스트, QR
	 * 규칙: 영문대문자(2) + 주문번호 뒷 6자리 + 주문자 휴대폰번호 뒷 4자리 + nanoTime 뒷 4자리
	 * @param orderCode 주문번호
	 * @param mobile 주문자 휴대폰번호
	 * @return String
	 */
	public static String getStringTicket(String orderCode, String mobile) {
		
		String nanoTime = String.valueOf(System.nanoTime());
		return TICKET_PREFIX
				+ orderCode.substring(orderCode.length() - 6) 
				+ mobile.substring(mobile.length() - 4)
				+ nanoTime.substring(nanoTime.length() - 4);
	}
	
	/**
	 * 숫자형 티켓번호 생성 - BAR
	 * 규칙: 주문번호 뒷 6자리 + 주문자 휴대폰번호 뒷 4자리 + nanoTime 뒷 6자리
	 * @param orderCode 주문번호
	 * @param mobile 주문자 휴대폰번호
	 * @return String
	 */
	public static String getNumberTicket(String orderCode, String mobile) {
		
		String nanoTime = String.valueOf(System.nanoTime());
		
		return orderCode.substring(orderCode.length() - 6) 
				+ mobile.substring(mobile.length() - 4)
				+ nanoTime.substring(nanoTime.length() - 6);
		
	}
	
	/**
	 * 인증번호 생성
	 * 규칙: 100000 ~ 999999 난수 6자리
	 * @return String
	 */
	public static String authenticationCode() {
		
		Random random = new Random();
		
		return String.valueOf(random.nextInt(899999) + 100000);
		
	}
	
	/**
	 * BAR코드 이미지 생성
	 * @param ticketCode 티켓번호
	 * @return BufferedImage
	 */
	public static BufferedImage createBarCodeImage(String ticketCode) throws WriterException {
		
		// code writer
		Code128Writer codeWriter = new Code128Writer();
		BitMatrix byteMatrix = codeWriter.encode(ticketCode, BarcodeFormat.CODE_128, BAR_WIDTH, BAR_HEIGHT, null);
		
		// bufferimage
		BufferedImage image = new BufferedImage(BAR_WIDTH, (BAR_MARGIN + BAR_HEIGHT + BAR_TEXT_HEIGHT), BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		// image 객체에 코드 그리기
		Graphics2D graphics2d = (Graphics2D) image.getGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, BAR_WIDTH, (BAR_MARGIN + BAR_HEIGHT + BAR_TEXT_HEIGHT));
		graphics2d.setColor(Color.BLACK);
		for (int i = 0; i < BAR_WIDTH; i++) {
			for (int j = 0; j < BAR_HEIGHT; j++) {
				if (byteMatrix.get(i, j)) {
					graphics2d.fillRect(i, (j + BAR_MARGIN), 1, 1);
				}
			}
		}
		
		// 티켓번호 이미지에 추가 - 중앙정렬
		Font font = new Font("Courier", Font.BOLD, 12);
		graphics2d.setFont(font);
		FontMetrics metrics = graphics2d.getFontMetrics(font);
		ticketCode = ticketCode.substring(0, 4) + "-" + ticketCode.substring(4, 8) + "-" 
					+ ticketCode.substring(8, 12) + "-" + ticketCode.substring(12, 16);
		int fontWidth = metrics.stringWidth(ticketCode);
		int fontHeight = metrics.getHeight() - metrics.getDescent() + 10;
		int boxMid = (int) Math.round(BAR_WIDTH / 2);
		int textMid = (int) Math.round(fontWidth / 2);
		int posX = boxMid - textMid;
		graphics2d.drawString(ticketCode, posX, (BAR_MARGIN + BAR_HEIGHT + fontHeight));
		
		return image;
				
	}
	
	/**
	 * QR코드 이미지 생성
	 * @param ticketCode 티켓번호
	 * @return BufferedImage
	 */
	public static BufferedImage createQrCodeImage(String ticketCode) throws WriterException {
		
		// code writer
		QRCodeWriter codeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = codeWriter.encode(ticketCode, BarcodeFormat.QR_CODE, QR_SIZE, QR_SIZE, null);
		
		// bufferimage
		BufferedImage image = new BufferedImage(QR_SIZE, QR_SIZE, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		// image 객체에 코드 그리기
		Graphics2D graphics2d = (Graphics2D) image.getGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, QR_SIZE, QR_SIZE);
		graphics2d.setColor(Color.BLACK);
		for (int i = 0; i < QR_SIZE; i++) {
			for (int j = 0; j < QR_SIZE; j++) {
				if (byteMatrix.get(i, j)) {
					graphics2d.fillRect(i, j, 1, 1);
				}
			}
		}
		
		return image;
		
	}

	/**
	 * Log적재용 Request Json 파싱
	 * Request Payload JSON
	 * @param obj
	 */
	public static void requestToJson(Object obj,HttpServletRequest request){
		String reqJson = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			reqJson = mapper.writeValueAsString(obj);
			request.setAttribute("requestJson", reqJson);
		} catch (JsonProcessingException e) {
			request.setAttribute("requestJson", reqJson);
		}
	}
	
}
