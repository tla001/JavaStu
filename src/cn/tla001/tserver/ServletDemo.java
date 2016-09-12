package cn.tla001.tserver;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 解决中文乱码
		resp.setContentType("text/html;charset=utf-8");
		// 输出一句话
		resp.getWriter().write("这是第一个servlet程序，当前时间为：" + new Date());
	}
}
