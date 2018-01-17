package com.k.parsing.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;

import com.k.parsing.tag.CommTag;
import com.k.parsing.tag.DeleteTag;
import com.k.parsing.tag.InsertTag;
import com.k.parsing.tag.SelectTag;
import com.k.parsing.tag.UpdateTag;
import com.k.parsing.template.SqlConFigXml;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 
 *
 */
public class SqlXmlUtil {
	private static XStream xstream = null;
	// 生成或者读取xml的文件路径
	private static String xmlPath = "resources/query.xml";

	static {
		xmlPath = System.getProperty("user.dir") + "/src/main/" + xmlPath;
		if (xstream == null) {
			xstream = new XStream(new XppDriver() {
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						// 对所有xml节点的转换都增加CDATA标记
						boolean cdata = true;

						public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
							super.startNode(name, clazz);
						}

						protected void writeText(QuickWriter writer, String text) {
							if (cdata) {
								writer.write("<![CDATA[");
								writer.write(text);
								writer.write("]]>");
							} else {
								writer.write(text);
							}
						}
					};
				}
			});

			// 注册启用注解的实体
			xstream.processAnnotations(new Class[] { SqlConFigXml.class, DeleteTag.class, UpdateTag.class,
					SelectTag.class, InsertTag.class });
			xstream.autodetectAnnotations(true);
			xstream.setMode(XStream.NO_REFERENCES);// 取消引用

		}
	}

	/**
	 * 生成sql配置文件xml模板字符串
	 * 
	 * @param xml
	 * @return
	 */
	public static String sqlXMLTemplate(SqlConFigXml xml) {
		return xstream.toXML(xml);
	}

	/**
	 * 将标签序列化生成 对应格式的xml字符串
	 * 
	 * @param tag
	 * @return
	 */
	public static String tagToXml(CommTag tag) {

		return xstream.toXML(tag);
	}

	/**
	 * 根据标签xml字符串 反序列化生成对应的标签对象
	 * 
	 * @param xml
	 * @return
	 */
	public static CommTag fromXmlToTag(String xml) {

		return (CommTag) xstream.fromXML(xml);
	}

	/**
	 * 生成sql配置文件
	 * 
	 * @param xml
	 */
	public static void CreateSQLXmlFile(SqlConFigXml xml) {
		String xmlstr = xstream.toXML(xml);
		// 生成xml;
		BufferedOutputStream Buff = null;
		FileOutputStream fs = null;

		try {
			System.out.println("生成SQL配置文件为：" + xmlPath);
			fs = new FileOutputStream(xmlPath);

			Buff = new BufferedOutputStream(fs);
			Buff.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes("utf-8"));
			Buff.write(xmlstr.getBytes("utf-8"));
			Buff.flush();
			Buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Close(Buff, fs);
		}
	}

	/**
	 * 根据sql配置文件反序列 生成sql配置文件模板实体（sqlConFigXml）
	 * 
	 * @return
	 */
	public static SqlConFigXml fromXMLToSQLTemplate(String xmlPath) {
		return (SqlConFigXml) xstream.fromXML(new File(xmlPath));
	}

	public static void Close(BufferedOutputStream Buff, FileOutputStream fs) {
		try {
			if (Buff != null) {
				Buff.close();
			}
			if (fs != null) {
				fs.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
