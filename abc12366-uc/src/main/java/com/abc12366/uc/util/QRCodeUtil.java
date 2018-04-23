package com.abc12366.uc.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生产工具
 * @author zhushuai 2017-8-5
 *
 */
public class QRCodeUtil {
	
	public static String getCreatQRcodeString(String content,int qrCodeSize,String formatName){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			 Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>(); 
			 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
		     hints.put(EncodeHintType.MARGIN, 0);  
	         BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
	                 BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
	         MatrixToImageWriter.writeToStream(bitMatrix, formatName, baos);
	         
			 BASE64Encoder encoder = new BASE64Encoder();  
		     return encoder.encode(baos.toByteArray());
		} catch (Exception e) {
			return "";
		}
		
	}

}
