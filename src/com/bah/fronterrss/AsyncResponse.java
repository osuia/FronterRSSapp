package com.bah.fronterrss;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface AsyncResponse {
	void processFinished(String output) throws ParserConfigurationException, SAXException, IOException;

}
