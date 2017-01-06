# crawerdemo
爬虫采用crawler4j开源工具包
自定义的爬虫继承工具包中的WebCrawler的类 并重写其中的shouldVisit（哪些网页需要爬取）和 visit（爬取内容的获取）方法
	通过观察编程类的信息的url中都带有编程以及type=T

在visit方法中 得到需要爬取网页的地址，然后调用jsoup 工具类 对html网页进行解析 主要是取得图书列表信息.将信息转化为自定义的图书信息类的集合


得到图书集合后调用自己excel转化工具类，将图书信息写入excel
说明：导入项目需要注意下包的结构
