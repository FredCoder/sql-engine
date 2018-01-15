/**
 * 
 */
package com.k.parsing.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.k.engine.EngineContext;
import com.k.engine.SQLContext;
import com.k.parsing.result.AnalyResults;
import com.k.parsing.utils.XmlDom4jUitl;

/**
 * TODO
 * 
 * @author Kevin.luo
 * @date 2018年1月11日 上午10:53:26
 * 
 */
public class SQLXmlEngine {
	private Logger logger = Logger.getLogger(SQLXmlEngine.class);
	private String xmlPath = "querys.xml";
	private XmlDom4jUitl dom;
	private String basePath;

	public SQLXmlEngine() {
		this.basePath = System.getProperty("user.dir") + "/src/main/resources" + File.separator;

	};

	public SQLXmlEngine(String basePath, String xmlPath) throws Exception {
		this.xmlPath = xmlPath;
		this.basePath = System.getProperty("user.dir") + File.separator + basePath + File.separator;
		init();
	}

	/**
	 * 将所有SQL配置文件载入缓存
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void init() throws Exception {
		logger.info("SQL解析引擎初始化...");
		List<Element> list = null;
		String[] path = xmlPath.split(",");
		if (path == null) {
			logger.error("SQLxml解析引擎没有找到SQL配置文件的加载路径!");

			throw new Exception("SQLxml解析引擎没有找到SQL配置文件的加载路径!");
		}
		dom = new XmlDom4jUitl();

		for (String string : path) {
			dom.load(basePath + string);
			list = dom.getRootElement().elements();
			for (Element element : list) {
				String key = element.attributeValue("key");
				String type = element.getName();
				String tag = element.asXML();
				JSONObject json = new JSONObject();
				json.put("key", key);
				json.put("type", type);
				json.put("tag", SqlXmlUtil.fromXmlToTag(tag));
				EngineContext.put(key, json);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		SQLXmlEngine engine = new SQLXmlEngine();
		engine.init();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("djyxqq", "2009-11-1");
		map.put("djyxqz", "2009-11-11");

		System.out.println( EngineContext.get("getTest").toJSONString());

		AnalyResults resl = SQLContext.getContext("getCorporateBusinessLicence", map);

		System.out.println(resl.getSql());

	}

}
