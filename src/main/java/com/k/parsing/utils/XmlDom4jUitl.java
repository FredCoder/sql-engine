package com.k.parsing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 *
 */
public class XmlDom4jUitl {

	private final static Logger loggin = Logger.getLogger(XmlDom4jUitl.class);

	private Document document = null;
	// 文档根节点
	private Element rootElement;

	public XmlDom4jUitl() {
	};

	public XmlDom4jUitl(String root) {
		document = DocumentHelper.createDocument();
		setRootElement(document.addElement(root));
	}

	public void parseFileXML(String configFile) throws DocumentException {
		InputStream is = this.getClass().getResourceAsStream(configFile);
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(is);
	}

	/**
	 * 
	 * @param nodePath
	 * @return
	 */
	public List<?> selectNodes(String nodePath) {
		return document.selectNodes(nodePath);
	}

	/**
	 * 
	 * @param nodePath
	 * @return
	 */
	public Element selectSingeNode(String nodePath) {
		return (Element) document.selectSingleNode(nodePath);
	}

	/**
	 * 
	 * @param elemenName
	 * @param textVlaue
	 * @param currElement
	 * @return
	 */
	public Element appendChildNode(String elemenName, String textVlaue, Element currElement) {
		Element child = currElement.addElement(elemenName);
		child.setText(textVlaue);
		return child;
	}

	/**
	 * 
	 * @param elemenName
	 * @param textVlaue
	 * @param nodePath
	 * @return
	 */
	public Element appendChildNode(String elemenName, String textVlaue, String nodePath) {
		Element child = this.selectSingeNode(nodePath).addElement(elemenName);
		child.setText(textVlaue);
		return null;
	}

	/**
	 * 
	 * @param xml
	 * @throws DocumentException
	 */
	public void parseText(String xml) throws DocumentException {
		setDocument(DocumentHelper.parseText(xml));
	}

	/**
	 * 
	 * @param element
	 * @param attrName
	 * @return
	 */
	public String getElementAttrValue(Element element, String attrName) {
		return element.attributeValue(attrName);
	}

	/**
	 * 
	 * @param element
	 * @param attrName
	 * @param value
	 */
	public void setElemenAttrValue(Element element, String attrName, String value) {
		element.attribute(attrName).setValue(value);
	}

	/**
	 * 
	 * @return
	 */
	public String getXml() {
		return document.asXML();
	}

	/**
	 * 
	 * @param ElementName
	 * @return
	 */
	public String getRootElementXml(String ElementName) {
		return this.getRootElement().element(ElementName).asXML();
	}

	/**
	 * 
	 * @param nodePath
	 * @return
	 */
	public String getAnyElementXml(String nodePath) {
		return this.selectSingeNode(nodePath).asXML();
	}

	/**
	 * 
	 * @param nodePath
	 */
	public void removeNode(String nodePath) {
		this.selectSingeNode(nodePath).getParent().remove(this.selectSingeNode(nodePath));
	}

	/**
	 * 
	 * @param element
	 * @param attributeName
	 */
	public void removeAttribute(Element element, String attributeName) {
		element.remove(element.attribute(attributeName));
	}

	/**
	 * 
	 * @param element
	 * @param elementName
	 * @return
	 */
	public List<?> getAllElemenByName(Element element, String elementName) {
		return element.elements(elementName);
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	public List<?> getAllElemenByName(Element element) {
		return element.elements();
	}

	/**
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public void load(String filename) throws IOException {

		FileInputStream in = null;
		SAXReader saxReader = null;
		Reader read = null;
		try {
			saxReader = new SAXReader();
			in = new FileInputStream(new File(filename));
			read = new InputStreamReader(in, "utf-8");
			document = saxReader.read(read);
		} catch (Exception ex) {
			loggin.error("加载[" + filename + "]xml文件出现异常，请检查！", ex);
		} finally {
			in.close();
			read.close();
		}
	}

	public void load(File filename) {
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(filename);
		} catch (Exception ex) {
			loggin.error("加载[" + filename.getName() + "]xml文件出现异常，请检查！", ex);
		}
	}

	/**
	 * 
	 * @param in
	 * @throws IOException
	 */
	public void load(InputStream in) throws IOException {
		SAXReader saxReader = null;
		Reader read = null;
		try {
			saxReader = new SAXReader();
			read = new InputStreamReader(in, "utf-8");
			document = saxReader.read(read);
		} catch (Exception ex) {
			loggin.error("加载 xml文件出现异常，请检查！", ex);
		} finally {
			in.close();
			read.close();
		}
	}

	/**
	 * 
	 * @param in
	 * @param charsetName
	 * @throws IOException
	 */
	public void load(InputStream in, String charsetName) throws IOException {
		SAXReader saxReader = null;
		Reader read = null;
		try {
			saxReader = new SAXReader();
			read = new InputStreamReader(in, charsetName);
			document = saxReader.read(read);
		} catch (Exception ex) {
			loggin.error("加载 xml文件出现异常，请检查！", ex);
		} finally {
			in.close();
			read.close();
		}
	}

	/**
	 * 
	 * @param filePath
	 * @param encoding
	 * @throws IOException
	 */
	public void docTOwriter(String filePath, String encoding) throws IOException {
		OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
		if (encoding == null) {
			xmlFormat.setEncoding("UTF-8"); // 指定XML编码
		}

		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileWriter(filePath), xmlFormat);

			writer.write(document);
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	public void test() throws IOException {
		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("EsbData");
		root.addAttribute("name", "44");
		Element item = root.addElement("item");
		item.addAttribute("sql", "dd");
		item.addElement("ss");
		root.attribute("name").setValue("55");
		root.selectSingleNode("/EsbData/item/ss").setText("dd");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(System.out, format);

		writer.write(document);
	}

	public static void main(String[] args) throws IOException, DocumentException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + "<ESB>" + "<Config>"
				+ "<EsbId>System_Login</EsbId>" + "</Config>" + "<EsbData>" + "<item name=\"userName\">manager</item>"
				+ "<item name=\"passWord\">1</item>"
				+ "<item name=\"SESSION\">&lt;?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?&gt;&lt;SESSION/&gt;</item>"
				+ "<item name=\"URL\">&lt;?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?&gt;&lt;URL&gt;&lt;__FORMDATA&gt;&amp;lt;data&amp;gt;&amp;lt;var&amp;gt;&amp;lt;txtUserName update=\"false\"&amp;gt;manager&amp;lt;/txtUserName&amp;gt;&amp;lt;txtPassword update=\"false\"&amp;gt;1&amp;lt;/txtPassword&amp;gt;&amp;lt;/var&amp;gt;&amp;lt;dataSet/&amp;gt;&amp;lt;/data&amp;gt;&lt;/__FORMDATA&gt;&lt;__EVENTTARGET&gt;userLogin&lt;/__EVENTTARGET&gt;&lt;PAGEINDEX&gt;840bf45b-1707-4c20-ae9d-470907563640&lt;/PAGEINDEX&gt;&lt;TXTUSERNAME&gt;manager&lt;/TXTUSERNAME&gt;&lt;TXTPASSWORD&gt;1&lt;/TXTPASSWORD&gt;&lt;/URL&gt;</item><item name=\"SESSION\">&lt;?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?&gt;&lt;SESSION/&gt;</item><item name=\"URL\">&lt;?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?&gt;&lt;URL&gt;&lt;__FORMDATA&gt;&amp;lt;data&amp;gt;&amp;lt;var&amp;gt;&amp;lt;txtUserName update=\"false\"&amp;gt;manager&amp;lt;/txtUserName&amp;gt;&amp;lt;txtPassword update=\"false\"&amp;gt;1&amp;lt;/txtPassword&amp;gt;&amp;lt;/var&amp;gt;&amp;lt;dataSet/&amp;gt;&amp;lt;/data&amp;gt;&lt;/__FORMDATA&gt;&lt;__EVENTTARGET&gt;userLogin&lt;/__EVENTTARGET&gt;&lt;PAGEINDEX&gt;840bf45b-1707-4c20-ae9d-470907563640&lt;/PAGEINDEX&gt;&lt;TXTUSERNAME&gt;manager&lt;/TXTUSERNAME&gt;&lt;TXTPASSWORD&gt;1&lt;/TXTPASSWORD&gt;&lt;/URL&gt;</item>"
				+ "</EsbData>" + "</ESB>";
		XmlDom4jUitl esbDoc = new XmlDom4jUitl();
		esbDoc.parseText(xml);
		String d = esbDoc.selectSingeNode("ESB/EsbData/item").getText();

		List<Element> tt = (List<Element>) esbDoc.selectNodes("ESB/EsbData/item");
		System.out.println(">>>" + d);

		for (int i = 0; i < tt.size(); i++) {
			System.out.println(tt.get(i).attributeValue("name"));
		}
	}

	public Document getDocument() {
		if (document == null) {
			document = DocumentHelper.createDocument();
		}
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Element getRootElement() {
		rootElement = document.getRootElement();
		return rootElement;
	}

	public void setRootElement(Element rootElement) {
		this.rootElement = rootElement;
	}
}
