package it.uninsubria.tesiandreaselva;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.sax.Element;
import android.util.Log;
import android.widget.Toast;

@TargetApi(9)
public class MyParser {
	static void vDebug(String debugString) { 
		Log.v("DomParsing", debugString + "\n");
	}

	static void eDebug(String debugString) {
		Log.e("DomParsing", debugString + "\n");
	}

	private ArrayList<MyNote> parsedData = new ArrayList<MyNote>();

	public ArrayList<MyNote> getParsedData() {

		return parsedData;

	}

	@TargetApi(9)
	public static String parseXml(String xmlUrl, String tit) {

		Document doc;

		try {
			if (Build.VERSION.SDK_INT > 8) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new URL(xmlUrl).openStream());
			org.w3c.dom.Element root = doc.getDocumentElement();
			NodeList notes = root.getElementsByTagName("articolo");
			for (int i = 0; i < notes.getLength(); i++) {
				Node c = notes.item(i);

				if (c.getNodeType() == Node.ELEMENT_NODE) {
					MyNote newNote = new MyNote();
					org.w3c.dom.Element note = (org.w3c.dom.Element) c;
					String titolo = note.getAttribute("titolo");
					String sottotitolo = note.getAttribute("sottotitolo");
					String immagine = note.getAttribute("immagine");
					if (titolo.equals(tit)) {
						NodeList noteDetails = c.getChildNodes();
						for (int j = 0; j < noteDetails.getLength(); j++) {
							Node c1 = noteDetails.item(j);
							if (c1.getNodeType() == Node.ELEMENT_NODE) {
								org.w3c.dom.Element detail = (org.w3c.dom.Element) c1;
								String nodeName = detail.getNodeName();
								if (nodeName.equals("descrizione")) {
									String nodeValue = detail.getFirstChild()
											.getNodeValue();
									return nodeValue;
								}
							}
						}
					}
				}
			}
			return "errore";
		} catch (SAXException e) {
			return "errore";
		} catch (IOException e) {
			return "errore";
		} catch (ParserConfigurationException e) {
			return "errore";
		} catch (FactoryConfigurationError e) {
			return "errore";
		}
	}

	@TargetApi(9)
	public void parseXml(String xmlUrl) {

		Document doc;
		try {
			if (Build.VERSION.SDK_INT > 8) {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new URL(xmlUrl).openStream());

			org.w3c.dom.Element root = doc.getDocumentElement();
			NodeList notes = root.getElementsByTagName("articolo");
			for (int i = 0; i < notes.getLength(); i++) {
				Node c = notes.item(i);
				if (c.getNodeType() == Node.ELEMENT_NODE) {
					MyNote newNote = new MyNote(); // costruiamo un oggetto

					org.w3c.dom.Element note = (org.w3c.dom.Element) c;

					String titolo = note.getAttribute("titolo");
					String sottotitolo = note.getAttribute("sottotitolo");
					String immagine = note.getAttribute("immagine");
					if (!immagine.contains(".jpg")
							&& !immagine.contains(".png"))
						immagine = "http://app.volleybusto.com/news/image.jpg";
					newNote.setTitolo(titolo);
					newNote.setSottotitolo(sottotitolo);
					newNote.setImmagine(immagine);

					NodeList noteDetails = c.getChildNodes();
					for (int j = 0; j < noteDetails.getLength(); j++) {
						Node c1 = noteDetails.item(j);
						if (c1.getNodeType() == Node.ELEMENT_NODE) {
							org.w3c.dom.Element detail = (org.w3c.dom.Element) c1;
							String nodeName = detail.getNodeName();
							if (nodeName.equals("descrizione")) {
								String nodeValue = detail.getFirstChild()
										.getNodeValue();
								newNote.setDescrizione(nodeValue);
							}
						}
					}
					parsedData.add(newNote);
				}
			}
		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (ParserConfigurationException e) {
		} catch (FactoryConfigurationError e) {
		}
	}
}
