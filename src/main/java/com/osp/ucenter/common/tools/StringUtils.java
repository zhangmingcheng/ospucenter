package com.osp.ucenter.common.tools;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具
 * 主要对 StringUtils 的一些方法进行重写,达到更方便的使用
 * @author zhangmingcheng
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	 * 一次性判断多个或单个对象为空。
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object...objects){
		Boolean result = false ;
		for (Object object : objects) {
			if(null == object || "".equals(object.toString().trim()) 
					|| "null".equals(object.toString().trim())){
				result = true ; 
				break ; 
			}
		}
		return result ; 
	}
	
	public static String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase();
	}
	/**
	 * 一次性判断多个或单个对象不为空。
	 * @param objects
     * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object...objects){
		return !isBlank(objects);
	}
	public static boolean isBlank(String...objects){
		Object[] object = objects ;
		return isBlank(object);
	}
	public static boolean isNotBlank(String...objects){
		Object[] object = objects ;
		return !isBlank(object);
	}
	public static boolean isBlank(String str){
		Object object = str ;
		return isBlank(object);
	}
	public static boolean isNotBlank(String str){
		Object object = str ;
		return !isBlank(object);
	}
	/**
	 * 判断一个字符串在数组中存在几个
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr,String[] strings){
		
		if(null == baseStr || baseStr.length() == 0 || null == strings)
			return 0;
		
		int i = 0;
		for (String string : strings) {
			boolean result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i ;
	}

	public static String trimToEmpty(Object str){
	  return (isBlank(str) ? "" : str.toString().trim());
	}
	
	/**
	 * 将 Strig  进行 BASE64 编码
	 * @param str [要编码的字符串]
	 * @param bf  [true|false,true:去掉结尾补充的'=',false:不做处理]
	 * @return
	 */
    public static String getBASE64(String str,boolean...bf) { 
       if (StringUtils.isBlank(str)) return null; 
       String base64 = new sun.misc.BASE64Encoder().encode(str.getBytes()) ;
       //去掉 '='
       if(isBlank(bf) && bf[0]){
    	   base64 = base64.replaceAll("=", "");
       }
       return base64;
    }

 
    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object,? extends Object> map){
    	String result = "" ;
    	if(map == null || map.size() ==0){
    		return result ;
    	}
    	Set<? extends Object> keys = map.keySet();
    	for (Object key : keys ) {
    		result += ((String)key + "=" + (String)map.get(key) + "&");
		}
    	
    	return isBlank(result) ? result : result.substring(0,result.length() - 1);
    }
    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     * @param args
     * @return
     */
    public static Map<String, ? extends Object> getToMap(String args){
    	if(isBlank(args)){
    		return null ;
    	}
    	args = args.trim();
    	//如果是?开头,把?去掉
    	if(args.startsWith("?")){
    		args = args.substring(1,args.length());
    	}
    	String[] argsArray = args.split("&");
    	
    	Map<String,Object> result = new HashMap<String,Object>();
    	for (String ag : argsArray) {
			if(!isBlank(ag) && ag.indexOf("=")>0){
				
				String[] keyValue = ag.split("=");
				//如果value或者key值里包含 "="号,以第一个"="号为主 ,如  name=0=3  转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.
					
				String key = keyValue[0];
				String value = "" ;
				for (int i = 1; i < keyValue.length; i++) {
					value += keyValue[i]  + "=";
				}
				value = value.length() > 0 ? value.substring(0,value.length()-1) : value ;
				result.put(key,value);
				
			}
		}
    	
    	return result ;
    }
    
    /**
	 * 转换成Unicode
	 * @param str
	 * @return
	 */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
        	int v = str.charAt(i);
        	if(v >=19968 && v <= 171941){
	            as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
	            s1 = s1 + "\\u" + as[i];
        	}else{
        		 s1 = s1 + str.charAt(i);
        	}
        }
        return s1;
     }
    /**
     * 合并数据
     * @param v
     * @return
     */
    public static String merge(Object...v){
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < v.length; i++) {
    		sb.append(v[i]);
		}
    	return sb.toString() ; 
    }
    /**
     * 字符串转urlcode
     * @param value
     * @return
     */
    public static String strToUrlcode(String value){
    	try {
			value = java.net.URLEncoder.encode(value,"utf-8");
			return value ;
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(StringUtils.class, "字符串转换为URLCode失败,value:" + value,e);
			e.printStackTrace();
			return null;
		}    
    }
    /**
     * urlcode转字符串
     * @param value
     * @return
     */
    public static String urlcodeToStr(String value){
    	try {
			value = java.net.URLDecoder.decode(value,"utf-8");
			return value ;
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(StringUtils.class, "URLCode转换为字符串失败;value:" + value,e);
			e.printStackTrace();
			return null;
		}  
    }
    /**
     * 判断字符串是否包含汉字
     * @param txt
     * @return
     */
    public static Boolean containsCN(String txt){
    	if(isBlank(txt)){
    		return false;
    	}
    	for (int i = 0; i < txt.length(); i++) { 

    		String bb = txt.substring(i, i + 1); 

    		boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
    		if(cc)
    		return cc ;
    	}
		return false;
    }
    /**
     * 去掉HTML代码
     * @param news
     * @return
     */
    public static String removeHtml(String news) {
      String s = news.replaceAll("amp;", "").replaceAll("<","<").replaceAll(">", ">");
      
      Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
      Matcher matcher = pattern.matcher(s);
      String str = matcher.replaceAll("");
      
      Pattern pattern2 = Pattern.compile("(<[^>]+>)",Pattern.DOTALL);
      Matcher matcher2 = pattern2.matcher(str);
      String strhttp = matcher2.replaceAll(" ");
      
      
      String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
         + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?"
         + "("
         + "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
         + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
         + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
         + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
         + "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*"
         + ")"
         + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
         + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
      Pattern p1 = Pattern.compile(regEx,Pattern.DOTALL);
      Matcher matchhttp = p1.matcher(strhttp);
      String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");
      
      
      Pattern patterncomma = Pattern.compile("(&[^;]+;)",Pattern.DOTALL);
      Matcher matchercomma = patterncomma.matcher(strnew);
      String strout = matchercomma.replaceAll(" ");
      String answer = strout.replaceAll("[\\pP‘’“”]", " ")
        .replaceAll("\r", " ").replaceAll("\n", " ")
        .replaceAll("\\s", " ").replaceAll("　", "");

      
      return answer;
    }
    /**
	 * 把数组的空数据去掉
	 * @param array
	 * @return
	 */
	public static List<String> array2Empty(String[] array){
		List<String> list = new ArrayList<String>();
		for (String string : array) {
			if(StringUtils.isNotBlank(string)){
				list.add(string);
			}
		}
		return list;
	}
	/**
	 * 把数组转换成set
	 * @param array
	 * @return
	 */
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if(null != id){
				set.add(id);
			}
		}
		return set;
	}
	/**
	 * serializable toString
	 * @param serializable
	 * @return
	 */
	public static String toString(Serializable serializable) {
		if(null == serializable){
			return null;
		}
		try {
			return (String)serializable;
		} catch (Exception e) {
			return serializable.toString();
		}
	}
	public static boolean notEmpty(Object o) {
		return o != null && !o.toString().trim().equals("");
	}

	public static boolean empty(Object o) {
		return o == null || o.toString().trim().equals("");
	}

	public static   String filterEmoji(String str) {  
        
        if(str.trim().isEmpty()){  
            return str;  
        }  
        String pattern="[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";  
        String reStr="";  
        Pattern emoji=Pattern.compile(pattern);  
        Matcher  emojiMatcher=emoji.matcher(str);  
        str=emojiMatcher.replaceAll(reStr);  
        return str;  
    } 

	/***************************************************************************
	 * 使用split连接count个c字符,返回连接后的字符串
	 * 
	 * @param c
	 *            字符
	 * @param split
	 *            分隔符
	 * @param count
	 *            个数
	 * @return 返回字符串
	 * @author jiangfl
	 */
	public static String getJoinSplitChar(String c, String split, Integer count) {
		String[] chars = new String[count];
		for (int i = 0; i < count; i++) {
			chars[i] = c;
		}
		return join(chars, split);
	}

	/***
	 * 把字符串str根据split字符分割，然后返回对应的cls类型数据
	 * 
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @param cls
	 *            被分割后的值的类型
	 * @return
	 * @author jiangfl
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object[] split(String str, String split, Class cls) {
		if (str != null) {
			try {
				Object[] splits = str.split(split);
				// 如果是字符串
				if (cls != String.class) {
					Object result[] = new Object[splits.length];
					Constructor constructor;
					constructor = cls.getConstructor(String.class);
					for (int i = 0; i < splits.length; i++) {
						result[i] = constructor.newInstance(splits[i]);
					}
					return result;
				} else {
					return splits;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/***
	 * 将数组 array 使用 split连接 成 字符串
	 * 
	 * @param array
	 * @param split
	 * @return
	 * @author jiangfl
	 */
	public static String join(Object[] array, String split) {
		StringBuffer sb = new StringBuffer();
		for (Object o : array) {
			sb.append(o).append(split);
		}
		if (sb.length() > 1) {
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

	/***
	 * 将数组 array 的内容添加到集合list中去
	 * 
	 * @param list
	 * @param array
	 * @author jiangfl
	 */
	public static void ListAddArray(List<Object> list, Object array[]) {
		for (Object obj : array) {
			list.add(obj);
		}
	}

	/**
	 * 传入 val值 这个值于list对象中的model(或者Map)实体的field属性相比较 看是否已经存在
	 * 
	 * @param list
	 *            list集合保存的是model实体(或者Map)
	 * @param field
	 *            model实体的某字段
	 * @param val
	 *            值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean exists(List list, String field, Object val) {

		Method m = null;
		Object result = null;
		Map map = null;
		for (Object o : list) {
			try {
				if (o instanceof Map) {
					map = (Map) o;
					result = map.get(field);
				} else {
					if (!field.startsWith("get")) {
						field = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
					}
					m = o.getClass().getMethod(field);
					result = m.invoke(o);
				}
				if (result == null && val == null) {
					return true;
				}
				if (result == null || val == null) {
					continue;
				}
				if (val.toString().equals(result.toString())) {
					return true;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String getSql(String[] sUUid) {
		StringBuilder sb = new StringBuilder();
		for (String s : sUUid) {
			sb.append(",\"").append(s).append("\"");
		}
		return sb.substring(1);
	}

	/**
	 *  取字符串的前toCount个字符 　　
	 *  @param str 被处理字符串 　 　
	 *  @param toCount 截取长度
	 * 　 @param more 后缀字符串 ... 省略　 　　 * @return String 　　
	 */
	public static String subString(String str, int toCount) throws Exception {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = str.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes("GBK");
			reInt += b.length;
			reStr += tempChar[kk];
		}
		// if (toCount == reInt || (toCount == reInt - 1))
		// reStr += more;
		return reStr;
	}

	public static String subString(String str, int toCount, boolean isMore) throws Exception {
		if (isMore) {
			return subString(str, toCount) + "...";
		} else {
			return subString(str, toCount);
		}

	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}
	
	public static void main(String[] args) {
		// System.out.println(unicode2String("\\u61\\u61\\u61\\ud83d\\ude00"));
	}
	
	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {
	 
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	 
	        // 追加成string
	        string.append((char) data);
	    }
	 
	    return string.toString();
	}
   
}
