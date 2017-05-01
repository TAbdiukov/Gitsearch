package main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class GithubCodeDownload {
	URL url;
	
	String savePath = "";
	
	String fileName ;
	
	File file = null;
	
	GithubCodeDownload() {
	}
	public void setURL(String url) throws MalformedURLException {
		this.url = new URL(url);	
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean Download() throws IOException {
		//HttpURLConnection connection = (HttpURLConnection)url.openConnection();		
		boolean success = false;
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.connect();
        
        int fileLength = httpURLConnection.getContentLength();
        System.out.println(fileLength);
        BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
        String path = savePath + File.separator + fileName;
        file = new File(path);
        if(!file.getParentFile().exists()){
        	file.getParentFile().mkdirs();
        }
        if(file.exists()){
        }
        OutputStream outputStream = new FileOutputStream(file);
        int size = 0;
        int len = 0;
        byte[] buf = new byte[1024];
        while((size = bin.read(buf)) != -1){
        	len+=size;
        	outputStream.write(buf, 0, size);
        	System.out.println(len*100/fileLength);
        }
        bin.close();
        outputStream.close();
        success = true;
        return success;
	}
	
	public String getSavePath() {
		return savePath;
	}
}
