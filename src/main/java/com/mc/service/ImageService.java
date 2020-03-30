package com.mc.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mc.service.exception.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Tipo de imagem não suportado");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Falha ao ler arquivo.");
		}

	}

	private BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo.");
		}

	}
	
	public BufferedImage cropSquare(BufferedImage image) {
		int min = (image.getHeight() <= image.getWidth()) ? image.getHeight() : image.getWidth();
		return Scalr.crop(image,
					(image.getWidth()/2) - (min/2),
					(image.getHeight()/2) - (min/2),
					min,
					min);
	}
	
	public BufferedImage resize(BufferedImage image, int size) {
		return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
	}

}
