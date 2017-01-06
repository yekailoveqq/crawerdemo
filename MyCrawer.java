package org.simple.crawerDemo.crawer;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 
 * @author ge
 * 自定义爬虫
 */
public class MyCrawer extends WebCrawler {

	private static final String B_TYPE = "编程";
	
	private Executor executor=Executors.newFixedThreadPool(3);
	
	
	/**
	 * 判断当前的url是否需要爬取
	 */
	 @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return href.indexOf(B_TYPE)>0;
     }

     /**
      * 处理爬取内容
      */
     @Override
     public void visit(Page page) {
    	 try {
			final List<BookInfo> books = DealStrUtil.getBookInfo(page.getWebURL().toString());
			//多线程处理图书信息记录
			executor.execute(new Runnable() {
				public void run() {
					try {
						ExcelUtil.appendWrite("D:\\soft\\bookinfo.xls",books,BookInfo.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
