package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.FileUtil;

/**
 * Servlet implementation class uoloadServlet
 */
public class UoloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UoloadServlet() {
		
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 文件上传的路径，这里设置的是服务器项目根路径下的 IMG 目录
		String filePath = request.getSession().getServletContext().getRealPath("/")+ "IMG/";

		// 获取照片上传工具类，并且批量上传照片文件
		List<File> files = FileUtil.getInstance(filePath).upload(request);

		// 将上传成功照片的照片传值到页面
		request.setAttribute("files", files);

		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
}
