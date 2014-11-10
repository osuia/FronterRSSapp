package com.bah.fronterrss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity implements AsyncResponse {
	Connect connect;
	String URL;
	Session session;
	List<RSSElement> rssElement = new ArrayList<RSSElement>();
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		session = new Session(getApplicationContext());
		HashMap<String, String> user = session.getUserDetails();
		String URL = "https://fronter.com/" + user.get(session.KEY_INSTALLATION) + "/rss/get_today_rss.php?&elements=4";

		connect = new Connect(this, this);
		connect.execute(URL);
	}

	public void processFinished(String output)
			throws ParserConfigurationException, SAXException, IOException {
		InputStream in = new ByteArrayInputStream(output.getBytes("iso-8859-1"));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = dbFactory.newDocumentBuilder();
		Document document = parser.parse(in);
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName().equals("channel")) {
				for (int j = 0; j < node.getChildNodes().getLength(); j++) {
					Node temp = node.getChildNodes().item(j);
					// System.out.println(temp.getNodeName());
					RSSElement m = new RSSElement();
					if (temp.getNodeName().equals("item")) {
						for (int k = 0; k < temp.getChildNodes().getLength(); k++) {
							Node temp2 = temp.getChildNodes().item(k);
							String text = getText(temp2);
							if (temp2.getNodeName().equals("title")) {

								m.title = text;
							}
							if (temp2.getNodeName().equals("pubDate")) {
								m.date = text;
							}
							if (temp2.getNodeName().equals("fronterXinfo:roomname")) {
								m.roomName = text;
								
							}
						}
						rssElement.add(m);
					}
				}

			}

		}

		populateList();

	}

	public String getText(Node n) {
		try {
			String content = n.getLastChild().getTextContent().trim();
			return content;
		} catch (Exception e) {
			return "N/A";
		}
	}

	private void populateList() {
		MyAdapter adapter = new MyAdapter(this, generateData());
		ListView listview = (ListView) findViewById(R.id.list);

		listview.setAdapter(adapter);

	}

	private ArrayList<Item> generateData() {
		ArrayList<Item> items = new ArrayList<Item>();
		for (RSSElement item : rssElement) {
			items.add(new Item(item.title, item.date, item.roomName));

		}
		// items.add(new Item("DATE", "TITLE"));
		return items;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.logout) {
			session = new Session(getApplicationContext());
			session.logoutUser();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
