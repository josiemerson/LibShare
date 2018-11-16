package br.com.libshare.utils.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.com.libshare.utils.StringUtils;

public class ImageUtils {
	public static final String PATH_IMG_DEFAULT = "../libshare-web/app/img/";

	public static File convertBase64ToFile(DataImage dataImage) throws Exception {
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(dataImage.base64Image);

		ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
		BufferedImage img = ImageIO.read(bis);
		bis.close();

		String pathSave = "";
		if (dataImage.codBook != null) {
			pathSave = PATH_IMG_DEFAULT + "users/ID_" + dataImage.codUsu + "/books/";
		} else if (dataImage.codBook != null) {
			pathSave = PATH_IMG_DEFAULT + "users/ID_" + dataImage.codUsu + "/";			
		}

		File file = null;
		if(StringUtils.isEmpty(pathSave)) {
			throw new Exception("Caminho passado é nulo.");
		} else {
			pathSave += dataImage.nameFile;

			file = new File(pathSave);
			if (file.exists()) {
				file.delete();
			}

			ImageIO.write(img, "png", file);				
		}

		return file;
	}
}