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
		// �����������
		resp.setContentType("text/html;charset=utf-8");
		// ���һ�仰
		resp.getWriter().write("���ǵ�һ��servlet���򣬵�ǰʱ��Ϊ��" + new Date());
	}
}
