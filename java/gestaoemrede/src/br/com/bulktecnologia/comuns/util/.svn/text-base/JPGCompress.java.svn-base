package br.com.bulktecnologia.comuns.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang.StringUtils;

public class JPGCompress {

	/**
	 * Comprime um arquivo JPG e grava
	 *  
	 * @param file
	 * @param float escala de precisao de compactacao
	 * @param outputFileName - diretorio/arquivo de saida com extensao onde sera serializado o novo arquivo ja compactado.
	 * 
	 * @throws IOException
	 */
	public static void compressJPGFile(File file,float precision, String outputFileName) throws IOException {
			
		if (StringUtils.isBlank(outputFileName)){
			throw new IOException("falha: outputFileName nulo ou vazio, informacao de saida obrigatoria!");
		}
		
	       BufferedImage input = ImageIO.read(file);
	       // Get Writer and set compression
	       Iterator iter =   ImageIO.getImageWritersByFormatName("JPG");
	       
	       if (iter.hasNext()) {
	         ImageWriter writer = (ImageWriter)iter.next();
	         ImageWriteParam iwp =  writer.getDefaultWriteParam();
	         iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	         
	           iwp.setCompressionQuality(precision);
	           
	           String newName = outputFileName;
	           if (!newName.endsWith(".jpg")) {
	             newName += ".jpg";
	           }          
	           
	           File outFile = new File(newName);
	           
	           FileImageOutputStream output = new FileImageOutputStream(outFile);
	           
	           writer.setOutput(output);
	           
	           IIOImage image =  new IIOImage(input, null, null);
	           
	           writer.write(null, image, iwp);
	         
	       }
	     }
	
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}	 
	
	
	public static void main(String[] args) throws IOException {
		compressJPGFile(new File("c:\\temp\\manuela.jpg"), 0.3f, "c:\\uploads\\compressed2.jpg");
		BufferedImage b = null;
	}
	
	   
	
}
