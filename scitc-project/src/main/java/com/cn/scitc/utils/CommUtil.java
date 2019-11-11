package com.cn.scitc.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSON;





/**
 * 
 * <p>
 * Title: CommUtil.java
 * </p>
 * 
 * <p>
 * Description:
 * 系统工具类，用来快速处理,系统可以将该工具类添加到ModelAndView中，前台可以使用$!CommUtil.xxx调用该工具类
 * </p>
 */
@SuppressWarnings({ "rawtypes", "unchecked"})
public class CommUtil {
	private static final SimpleDateFormat dateFormat = new

	SimpleDateFormat("yyyy-MM-dd");

	public static String first2low(String str) {
		String s = "";
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	/**
	 * 用来处理一行一条数据
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static List<String> str2list(String s) throws IOException {
		List<String> list = new ArrayList<String>();
		s = CommUtil.null2String(s);
		if (!s.equals("")) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline = "";
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}
	public static char randomChar() {
		char[] chars = new char[] { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i',
				'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S',
				't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };
		int index = (int) (Math.random() * 52) - 1;
		if (index < 0) {
			index = 0;
		}
		return chars[index];
	}

	public static String getTimeInfo(long time) {
		int hour = (int) time / (1000 * 60 * 60);
		long balance = time - hour * 1000 * 60 * 60;
		int minute = (int) balance / (1000 * 60);
		balance = balance - minute * 1000 * 60;
		int seconds = (int) balance / 1000;
		String ret = "";
		if (hour > 0)
			ret += hour + "小时";
		if (minute > 0)
			ret += minute + "分";
		else if (minute <= 0 && seconds > 0)
			ret += "零";
		if (seconds > 0)
			ret += seconds + "秒";
		return ret;
	}
	/**
	 * 获取当前请求完整的URL
	 * 
	 * @param request
	 */
	public static String get_all_url(HttpServletRequest request) {
		String query_url = request.getRequestURI();
		if (request.getQueryString() != null && !request.getQueryString().equals("")) {
			query_url = query_url + "?" + request.getQueryString();
		}
		return query_url;
	}
	/**
	 * 根据种子a随机出一组长度为length不重复的整型数组
	 * 
	 * @param a
	 * @param length
	 * @return
	 */
	public static Set<Integer> randomInt(int a, int length) {
		Set<Integer> list = new TreeSet<Integer>();
		int size = length;
		if (length > a) {
			size = a;
		}
		while (list.size() < size) {
			Random random = new Random();
			int b = random.nextInt(a);
			list.add(b);
		}
		return list;
	}
	/**
	 * 生成16位不重复ID
	* @param @return  参数说明 
	* @throws 
	* @return String    返回类型 
	 */
	public static String get16UUId() {

	    int hashCodeV = UUID.randomUUID().toString().hashCode();
	    if (hashCodeV < 0) {//有可能是负数
	        hashCodeV = -hashCodeV;
	    }
	    // 0 代表前面补充0
	    // 4 代表长度为4
	    // d 代表参数为正数型
	    String orderId=String.format("%016d", hashCodeV);
	    return orderId;
	}
	/**
	 * 将json转为为List<Map>
	 * 
	 * @param json
	 * @return
	 */
	public static List<Map> Json2List(String json) {
		List<Map> list = new ArrayList<>();
		if (json != null && !json.equals("")) {
			list = JSON.parseArray(json, Map.class);
		}
		return list;
	}

	public static Map<String,Object> Json2Map(String json) {
		Map map = new HashMap<>();
		if (json != null && !json.equals("")) {
			map = JSON.parseObject(json,Map.class); 
		}
		return map;
	}
	public static String Map2Json(Map json) {
		String map="";
		if (json != null) {
			map=(String) JSON.toJSON(json);
		}
		return map;
	}
	public static String forjson(Object obj) {
		if(obj!=null && !obj.equals("")) {
			return JSON.toJSONString(obj);
		}
		return "";
	}
	/**
	 * 是否为空
	 * 
	 * @param Object
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		if (obj != null && !obj.toString().equals("")) {
			return true;
		} else
			return false;
	}
	/**
	 * 转换特定格式的String为String[]
	 * 
	 * @param String,String
	 * @return
	 */
	public static String[] splitByChar(String s, String c) {
		String[] list = s.split(c);
		return list;
	}
	/**
	 *初始化int
	 * 
	 * @param Object
	 * @return
	 */
	public static int null2Int(Object s) {
		int v = 0;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception e) {
			}
		return v;
	}
	/**
	 *初始化float
	 * 
	 * @param Object
	 * @return
	 */
	public static float null2Float(Object s) {
		float v = 0.0f;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception e) {
			}
		return v;
	}
	/**
	 *初始化double
	 * 
	 * @param Object
	 * @return
	 */
	public static double null2Double(Object s) {
		double v = 0.0;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception e) {
			}
		return v;
	}
	/**
	 *初始化boolean
	 * 
	 * @param Object
	 * @return
	 */
	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception e) {
			}
		return v;
	}
	/**
	 *初始化String
	 * 
	 * @param Object
	 * @return
	 */
	public static String null2String(Object s) {
		return s == null ? "" : s.toString().trim();
	}
	/**
	 *初始化long
	 * 
	 * @param Object
	 * @return
	 */
	public static Long null2Long(Object s) {
		Long v = -1l;
		if (s != null)
			try {
				v = Long.parseLong(s.toString());
			} catch (Exception e) {
			}
		return v;
	}
	/**
	 *随机一个length长度的字符串
	 * 
	 * @param int
	 * @return
	 */
	public static final String randomString(int length) {
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
				.toCharArray();
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 *随机一个length长度的数字
	 * 
	 * @param int
	 * @return
	 */
	public static final String randomInt(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}
	/**
	 *获取请求地址
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	public static String getURL(HttpServletRequest request) {

		String URL = request.getRequestURL().toString();  
		String url = "http://"+request.getServerName();
		if(URL.startsWith("https:")){
			url = "https://"+request.getServerName();
		}
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
		if (null2Int(request.getServerPort()) != 80 || null2Int(request.getServerPort()) != 443) {
			url = url + ":" + null2Int(request.getServerPort()) + contextPath;
		} else {
			url = url + contextPath;
		}
		return url;
	}
	/**
	 *获取请求地址（不带http头）
	 * 
	 * @param HttpServletRequest
	 * @return
	 */
	public static String getNoHttpURL(HttpServletRequest request) {
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
		String url = request.getServerName();
		if (null2Int(request.getServerPort()) != 80) {
			url = url + ":" + null2Int(request.getServerPort()) + contextPath;
		} else {
			url = url + contextPath;
		}
		return url;
	}

	/**
	 * 计算当前域名，不含www的顶级域名
	 * 
	 * @param request
	 *            输入请求
	 * @return 顶级域名信息
	 */
	public static String generic_domain(HttpServletRequest request) {
		// System.out.println(request.getServerName());
		String system_domain = "localhost";
		String serverName = request.getServerName();
		if (isIp(serverName)) {
			system_domain = serverName;
		} else {
			if (serverName.indexOf(".") == serverName.lastIndexOf(".")) {
				system_domain = serverName;
			} else {
				system_domain = serverName.substring(serverName.indexOf(".") + 1);
			}
		}
		// System.out.println(system_domain);
		return system_domain;
	}
	/**
	 * 判断是否是一个IP
	 * 
	 * @param IP
	 * @return
	 */
	public static boolean isIp(String IP) {//
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}
	/**
	 * 去掉IP字符串前后所有的空格
	 * 
	 * @param IP
	 * @return
	 */
	public static String trimSpaces(String IP) {//
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}
	/**
	 * 清除html标签 
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String removeTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // script
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // style
		String regEx_html = "<[^>]+>"; // HTML tag
		String regEx_space = "\\s+|\t|\r|\n";// other characters

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");
		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(" ");
		return htmlStr;
	}
	public static String first2upper(String str) {
		String s = "";
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}
	public static java.util.Date formatDate(String s) {
		java.util.Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static java.util.Date formatDate(String s, String format) {
		java.util.Date d = null;
		try {
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static String formatTime(String format, Object v) {
		if (v == null)
			return null;
		if (v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static String formatLongDate(Object v) {
		if (v == null || v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String formatShortDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}

	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			java.net.InetAddress addr = null;
			try {
				addr = java.net.InetAddress.getLocalHost();
			} catch (java.net.UnknownHostException e) {
				e.printStackTrace();
			}
			ip = CommUtil.null2String(addr.getHostAddress());// 获得本机IP
		}
		return ip;
	}
	public static boolean isMobil(String content) {
		 String patrn ="^[1][3,4,5,7,8,9][0-9]{9}$";

		 boolean isMatch = Pattern.matches(patrn, content);
		 if (!isMatch) {
		     return false;
		 }
		 return true;
		} 
}
