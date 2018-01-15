/**
 * 
 */
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
 * TODO
 * 
 * @author Kevin.luo
 * @date 2018年1月11日 下午3:58:41
 * 
 */
public class XmlDom4jUitl {

	private final static Logger log = Logger.getLogger(XmlDom4jUitl.class);

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
	 * 功能：通过xpath查找指定的节点集合 List list=document.selectNodes("/books/book/@show");
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param nodePath
	 * @return
	 *
	 */
	public List<?> selectNodes(String nodePath) {
		return document.selectNodes(nodePath);
	}

	/**
	 * 
	 * 功能：通过xpath精确查找指定的节点 只返回一个节点
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param nodePath
	 * @return
	 *
	 */
	public Element selectSingeNode(String nodePath) {

		return (Element) document.selectSingleNode(nodePath);
	}

	/**
	 * 
	 * 功能：给指定的节点元素添加子节点元素
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param elemenName
	 * @param textVlaue
	 * @param currElement
	 * @return
	 *
	 */
	public Element appendChildNode(String elemenName, String textVlaue, Element currElement) {
		Element child = currElement.addElement(elemenName);
		child.setText(textVlaue);
		return child;

	}

	/**
	 * 
	 * 功能：通过指定的节点路径给该节点添加子节点元素
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param elemenName
	 * @param textVlaue
	 * @param nodePath
	 * @return
	 *
	 */
	public Element appendChildNode(String elemenName, String textVlaue, String nodePath) {
		Element child = this.selectSingeNode(nodePath).addElement(elemenName);
		child.setText(textVlaue);
		return null;

	}

	/**
	 * 
	 * 功能：将xml字符串转换成 Document文档
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param xml
	 * @throws DocumentException
	 *
	 */
	public void parseText(String xml) throws DocumentException {
		setDocument(DocumentHelper.parseText(xml));
	}

	/**
	 * 
	 * 功能：根据节点和指定的属性获取该节点的属性值
	 * 
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param element
	 * @param attrName
	 * @return
	 * 
	 */
	public String getElementAttrValue(Element element, String attrName) {

		return element.attributeValue(attrName);
	}

	/**
	 * 
	 * 功能：给节点属性设置值
	 * 
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param element
	 * @param attrName
	 * @param value
	 * 
	 */
	public void setElemenAttrValue(Element element, String attrName, String value) {
		element.attribute(attrName).setValue(value);

	}

	/**
	 * 
	 * 功能：xml文檔字符串
	 * 
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @return
	 * 
	 */
	public String getXml() {
		return document.asXML();
	}

	/**
	 * 
	 * 功能：返回指定根节点的子节点的xml字符串
	 * 
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param ElementName
	 * @return
	 * 
	 */
	public String getRootElementXml(String ElementName) {

		return this.getRootElement().element(ElementName).asXML();
	}

	/**
	 * 
	 * 功能： 通过节点路径返回该任意节点的xml字符串
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param nodePath
	 * @return
	 *
	 */
	public String getAnyElementXml(String nodePath) {

		return this.selectSingeNode(nodePath).asXML();
	}

	/**
	 * 
	 * 功能：根据指定节点路径移除给节点
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param nodePath
	 *
	 */

	public void removeNode(String nodePath) {
		this.selectSingeNode(nodePath).getParent().remove(this.selectSingeNode(nodePath));
	}

	/**
	 * 
	 * 功能： 根据节点与指定属性名称移除，该节点指定的属性
	 * 
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param element
	 * @param attributeName
	 *
	 */
	public void removeAttribute(Element element, String attributeName) {
		element.remove(element.attribute(attributeName));

	}

	/**
	 * 
	 * 功能：取得某节点下指定名称的所有节点
	 * 
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param element
	 * @param elementName
	 * @return
	 * 
	 */
	public List<?> getAllElemenByName(Element element, String elementName) {
		return element.elements(elementName);

	}

	/**
	 * 
	 * 功能：根据节点获取该节点下所有子节点元素
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param element
	 * @return
	 *
	 */
	public List<?> getAllElemenByName(Element element) {
		return element.elements();

	}

	/**
	 * 
	 * 功能：读取XML文件,获得document对象
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param filename
	 * @throws IOException
	 *
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
			log.error("加载[" + filename + "]xml文件出现异常，请检查！", ex);
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
			log.error("加载[" + filename.getName() + "]xml文件出现异常，请检查！", ex);

		}

	}

	/**
	 * 
	 * load(这里用一句话描述这个方法的作用) 加载一个xml流
	 * 
	 * @param in
	 * @throws IOException
	 *             void
	 * @exception @version
	 *                1.0.0
	 * @date 2015年10月9日
	 *
	 */
	public void load(InputStream in) throws IOException {
		SAXReader saxReader = null;
		Reader read = null;
		try {

			saxReader = new SAXReader();
			read = new InputStreamReader(in, "utf-8");

			document = saxReader.read(read);

		} catch (Exception ex) {
			log.error("加载 xml文件出现异常，请检查！", ex);
		} finally {
			in.close();
			read.close();

		}

	}

	public void load(InputStream in, String charsetName) throws IOException {
		SAXReader saxReader = null;
		Reader read = null;
		try {

			saxReader = new SAXReader();
			read = new InputStreamReader(in, charsetName);

			document = saxReader.read(read);

		} catch (Exception ex) {
			log.error("加载 xml文件出现异常，请检查！", ex);
		} finally {
			in.close();
			read.close();

		}

	}

	/**
	 * 
	 * 功能：生成xml文件
	 *
	 * @创建时间:2014-6-6 @修改时间： @修订版：1.0 @修订说明：
	 * @作者：CHEN_JIAN
	 * @param filePath
	 * @param encoding
	 * @throws IOException
	 *
	 */
	public void docTOwriter(String filePath, String encoding) throws IOException {
		OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
		if (encoding == null) {
			xmlFormat.setEncoding("UTF-8"); // 指定XML编码
		}
		// // 设置换行
		// xmlFormat.setNewlines(true);
		// // 生成缩进
		// xmlFormat.setIndent(true);
		// // 使用4个空格进行缩进, 可以兼容文本编辑器
		// xmlFormat.setIndent(" ");

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

		// System.out.println( document.asXML());
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

	// 递归算法：遍历配置文件，找出所有有效的xpath
//	private static void recursiveElement(Element elements) {
//		if (elements.elements().size() > 0) {
//			for (Iterator i = elements.elementIterator(); i.hasNext();) {
//				Element element = (Element) i.next();
//				String queryName = element.getName();
//				if (StringUtils.isNotEmpty(queryName) && "select".equalsIgnoreCase(queryName)) {
//					System.out.println(element.getName() + ">>> " + element.attributeValue("parameterType") + ">>>"
//							+ element.getText().trim());
//				} else if ("trim".equalsIgnoreCase(queryName)) {
//					System.out.println(element.getName() + ">>> " + element.attributeValue("prefix"));
//				} else {
//					System.out.println(element.getName() + ">>> " + element.attributeValue("test") + ">>>"
//							+ element.getText().trim());
//
//				}
//				recursiveElement(element);
//			}
//		}
//
//	}

}
