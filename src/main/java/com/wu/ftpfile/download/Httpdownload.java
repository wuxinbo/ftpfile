package com.wu.ftpfile.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.wu.ftpfile.utils.Fileutil;

import android.os.Handler;
import android.os.Message;

public class Httpdownload {

	public static HttpURLConnection getURlconnection(String urladreess)
			throws MalformedURLException, IOException {
		HttpURLConnection conn;
		URL url1 = new URL(urladreess);
		conn = (HttpURLConnection) url1.openConnection(); // �õ����ӡ�
		return conn;
	}

	public static int downfile(String adress,String filename){
		InputStream fis=null;
		FileOutputStream fos =null;
		
		try {
			URL url =new URL(adress);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			fis = conn.getInputStream();
			System.out.println("�����ѽ�����");
			int filesize =conn.getContentLength();
			System.out.println(filesize);
			int i=0;
			int readsize=0;
			byte [] data =new byte [2048];
			while ((i=fis.read(data))!=-1) {
//				Fileutil.WriteFiletoSdcard(filename, fis);
				readsize=readsize+i;
				System.out.println(readsize);
			}
			System.out.println("д����ϣ�");
			return (readsize/filesize)*100;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO�쳣��");
		}
		return -1;
		
	}
	class Downloadhandler extends Handler {

		@Override
		public void handleMessage(Message msg) {

			
		}
		 
	 }
}
 