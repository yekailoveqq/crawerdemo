package org.simple.crawerDemo.crawer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author ge
 * 处理string的工具类
 */
public class DealStrUtil {

	public static List<BookInfo> getBookInfo(String url) throws Exception{
		
		List<BookInfo> bookinfos = new ArrayList<BookInfo>();
		
		//解析当前html页面
		Document doc = Jsoup.connect(url).get();
		//获取所有的书籍信息
		Elements elements = doc.getElementsByAttributeValueStarting("class", "subject-item");
		//解析书籍信息
		for(Element element:elements){
			
			BookInfo book = new BookInfo();
			
			//获取 基本块
			Element info = element.getElementsByClass("info")!=null?element.getElementsByClass("info").get(0):null;
			if(info!=null){
				if(info.getElementsByTag("h2")!=null&&info.getElementsByTag("h2").size()>0){
					//图书名字
					String title = info.getElementsByTag("h2").get(0).text();
					book.setBookName(title);
				}
				if(info.getElementsByClass("pub")!=null&&info.getElementsByClass("pub").size()>0){
					//作者 出版社 时间 价格 字符串
					String pubStr = info.getElementsByClass("pub").get(0).text();
					DealStrUtil.getBaseInfo(pubStr, book);
				}
				if(info.getElementsByClass("star")!=null&&info.getElementsByClass("star").size()>0){
					if(info.getElementsByClass("star").get(0).getElementsByClass("rating_nums")!=null&&info.getElementsByClass("star").get(0).getElementsByClass("rating_nums").size()>0){
						//评分
						String source = info.getElementsByClass("star").get(0).getElementsByClass("rating_nums").get(0).text();
						book.setBookScore(source);
					}
				}

				if(info.getElementsByClass("star")!=null&&info.getElementsByClass("star").size()>0){
					if(info.getElementsByClass("star").get(0).getElementsByClass("pl")!=null&&info.getElementsByClass("star").get(0).getElementsByClass("pl").size()>0){
						//评论
						String comment = info.getElementsByClass("star").get(0).getElementsByClass("pl").get(0).text();
						book.setBookComment(comment);
					}
				}

			}
			if(!DealStrUtil.isNull(book)){
				bookinfos.add(book);
			}
		}
		return bookinfos;
	}
	
	
	/**
	 * 设置基础信息
	 * @param baseInfo
	 * @return
	 */
	public static void getBaseInfo(String baseInfo,BookInfo book){
		//分割基础信息
		String[] baseInfoArry = baseInfo.split("/");
		if(baseInfoArry.length>4){
			//设置作者
			book.setBookAuthor(baseInfoArry[0]+"/"+baseInfoArry[1]);
			//出版社
			book.setBookPublish(baseInfoArry[2]);
			//时间
			book.setBookTime(baseInfoArry[3]);
			//价格
			book.setBookPrice(baseInfoArry[4]);
		}
		else if(baseInfoArry.length==4){
			//设置作者
			book.setBookAuthor(baseInfoArry[0]);
			//出版社
			book.setBookPublish(baseInfoArry[1]);
			//时间
			book.setBookTime(baseInfoArry[2]);
			//价格
			book.setBookPrice(baseInfoArry[3]);
		}
		else if(baseInfoArry.length==3){
			//设置作者
			book.setBookAuthor(baseInfoArry[0]);
			//出版社
			book.setBookPublish(baseInfoArry[1]);
			//时间
			book.setBookTime(baseInfoArry[2]);
		}
		else if(baseInfoArry.length==2){
			//设置作者
			book.setBookAuthor(baseInfoArry[0]);
			//出版社
			book.setBookPublish(baseInfoArry[1]);
		}
		else{
			//设置作者
			book.setBookAuthor(baseInfoArry[0]);
		}
	}
	
	public static boolean isNull(BookInfo book){
		if(StringUtils.isEmpty(book.getBookName())&&StringUtils.isEmpty(book.getBookScore())&&StringUtils.isEmpty(book.getBookComment())&&StringUtils.isEmpty(book.getBookAuthor())&&StringUtils.isEmpty(book.getBookPublish())&&StringUtils.isEmpty(book.getBookPrice())){
			return true;
		}
		return false;
	}
}
