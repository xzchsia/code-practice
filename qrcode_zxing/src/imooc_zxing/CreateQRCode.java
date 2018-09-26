package imooc_zxing;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

// ���ɶ�ά��
public class CreateQRCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int width = 300;
		int height = 300;
		String format = "png";
		String content = "Hello,World";
		
		// �����ά��Ĳ���
		HashMap hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);
		
		// ���ɶ�ά��
		try {
			BitMatrix bm = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			Path file = new File("E:/code/img.png").toPath();
			MatrixToImageWriter.writeToPath(bm, format, file);
			System.out.println("��ά�����ɳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
