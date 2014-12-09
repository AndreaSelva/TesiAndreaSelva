package it.uninsubria.tesiandreaselva;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyNote {

	public static final String TITOLO = "TITOLO";
	public static final String SOTTOTITOLO = "SOTTOTITOLO";
	public static final String IMMAGINE = "IMMAGINE";
	public static final String DESCRIZIONE = "DESCRIZIONE";

	private String titolo;
	private String sottotitolo;
	private String immagine;
	private String descrizione;
	private static MyParser pars = new MyParser();
	protected static List<Map<String, String>> ret = new ArrayList<Map<String, String>>();

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getSottotitolo() {
		return sottotitolo;
	}

	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public static List<Map<String, String>> getData() {

		pars.parseXml("http://volleybusto.com/newsfeed.php");
		ArrayList<MyNote> a = pars.getParsedData();
		Object[] db = a.toArray();
		MyNote news;

		for (int i = 0; i < db.length; i++) {
			news = (MyNote) db[i];
			ret.add(news.getMap());
		}

		return ret;
	}

	protected Map<String, String> getMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(MyNote.TITOLO, titolo);
		map.put(MyNote.SOTTOTITOLO, sottotitolo);
		map.put(MyNote.DESCRIZIONE, descrizione);
		map.put(MyNote.IMMAGINE, immagine);
		return map;
	}

	protected static Bitmap LoadImage(String URL, BitmapFactory.Options options) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = OpenHttpConnection(URL);
			if (in != null) {
				bitmap = BitmapFactory.decodeStream(in, null, options);
				in.close();
			}
		} catch (IOException e1) {
			return bitmap;
		}
		return bitmap;
	}

	private static InputStream OpenHttpConnection(String strURL)
			throws IOException {
		InputStream inputStream = null;
		URL url = new URL(strURL);
		URLConnection conn = url.openConnection();

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpConn.getInputStream();
			}
		} catch (Exception ex) {
		}
		return inputStream;
	}

	@Override
	public String toString() {
		return "\n titolo= " + titolo + "\n" + "descrizione= " + descrizione;
	}
}
