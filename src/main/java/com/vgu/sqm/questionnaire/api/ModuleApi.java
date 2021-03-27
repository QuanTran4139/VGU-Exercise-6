package com.vgu.sqm.questionnaire.api;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vgu.sqm.questionnaire.core.Module;

@WebServlet("/api/module")
public class ModuleApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModuleApi() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().append("Testing Module API");
		try {
			new Module("a,","v").getJsonData();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}