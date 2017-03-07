/**
 * 
 */
package com.lc.zy.ball.boss.common;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lc.zy.common.util.FileServerUtils;
import com.lc.zy.common.util.ImageUtils;
import com.lc.zy.common.util.UUID;

/**
 * @author lichunxi
 * 
 */
public class BarcodeFactory {
	// 图片宽度的一般
	private static final int IMAGE_WIDTH = 80;
	private static final int IMAGE_HEIGHT = 80;
	private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
	private static final int FRAME_WIDTH = 2;
	
	private static Logger logger = LoggerFactory.getLogger(BarcodeFactory.class);

	// 二维码写码器
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	public static String encode(String content, int width, int height,String barUri) throws Exception {
		String url = "http://fileserver.qiuyouzone.com/fileserver/get/";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(genBarcode(content, width, height, barUri),"jpg", os);
		String id = thumbnailAndUpload(UUID.get(), os.toByteArray());
		url = url+id;
		return url;
	}
	
	public static String encode(String content,int width,int height) throws Exception{
		String url = "http://fileserver.qiuyouzone.com/fileserver/get/";
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");  
        BitMatrix matrix = null;  
        try {  
            matrix = new MultiFormatWriter().encode(content,  
                    BarcodeFormat.QR_CODE, 300, 300, hints);  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
        ByteArrayOutputStream os = new ByteArrayOutputStream(); 
        MatrixToImageWriter.writeToStream(matrix, "jpg", os);  
        String id = thumbnailAndUpload(UUID.get(), os.toByteArray());
		url = url+id;
		return url;
	}
	
	/**
	 * 压缩并上传
	 * @param buff
	 * @throws FileNotFoundException 
	 */
	private static String thumbnailAndUpload(String fileName ,byte[] buff) throws Exception{
		String id = UUID.get();
		String file = "/tmp/"+id;
		try{
			OutputStream os = new FileOutputStream(file);
			os.write(buff);
			os.flush();
			os.close();
			String f1 = ImageUtils.createThumbnail(file, 150 );
			String f2 = ImageUtils.createThumbnail(file, 750 );
			FileServerUtils.upload(id, fileName, f1  , false, "image");
			FileServerUtils.upload(id+"bigPicture", fileName, f2  , false, "image");
		}catch(FileNotFoundException e){
			FileServerUtils.upload(id, fileName, buff,false,"image");
			FileServerUtils.upload(id+"bigPicture", fileName, buff,false,"image");
		}
		logger.debug("upFile.id={}",id);
		return id;
	}

	private static BufferedImage genBarcode(String content, int width,
			int height, String barUri) throws WriterException,
			IOException {
		// 读取源图像
		BufferedImage scaleImage = scale(barUri, IMAGE_WIDTH,
				IMAGE_HEIGHT, true);
		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}

		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 生成二维码
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
				width, height, hint);

		// 二维矩阵转为一维像素数组
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];

		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				// 读取图片
				if (x > halfW - IMAGE_HALF_WIDTH
						&& x < halfW + IMAGE_HALF_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH
						&& y < halfH + IMAGE_HALF_WIDTH) {
					pixels[y * width + x] = srcPixels[x - halfW
							+ IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
				} 
				// 在图片四周形成边框
				else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
						&& x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
						+ IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								+ IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								- IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								+ IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
					pixels[y * width + x] = 0xfffffff;
				} else {
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
							: 0xfffffff;
				}
			}
		}

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);

		return image;
	}

	/**
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
	 * 
	 * @param srcImageFile
	 *            源文件地址
	 * @param height
	 *            目标高度
	 * @param width
	 *            目标宽度
	 * @param hasFiller
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 * @throws IOException
	 */
	private static BufferedImage scale(String barUri, int height,
			int width, boolean hasFiller) throws IOException {
		double ratio = 0.0; // 缩放比例
		
		HttpGet httpget = new HttpGet(barUri);
		CloseableHttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		InputStream stream = entity.getContent();
		
		BufferedImage srcImage = ImageIO.read(stream);
		Image destImage = srcImage.getScaledInstance(width, height,
				BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue()
						/ srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue()
						/ srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(
					AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0,
						(height - destImage.getHeight(null)) / 2,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			else
				graphic.drawImage(destImage,
						(width - destImage.getWidth(null)) / 2, 0,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	public static void main(String[] args) {
		try {
			String url = BarcodeFactory
					.encode("http://qiuyouzone.com",
							300, 300);
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
