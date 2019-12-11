package ua.com.nc.nctrainingproject.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomMultipartFile implements MultipartFile {
	private final byte[] fileContent;
	private String fileName;
	private String contentType;
	private File file;

	public CustomMultipartFile(byte[] fileContent, String fileName) {
		this.fileContent = fileContent;
		this.fileName = fileName;
		this.file = new File(fileName);
	}


	public CustomMultipartFile(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	@Override
	public String getName() {
		return fileName;
	}

	@Override
	public String getOriginalFilename() {
		return null;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return new byte[0];
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public void transferTo(File file) throws IOException, IllegalStateException {

	}
}
