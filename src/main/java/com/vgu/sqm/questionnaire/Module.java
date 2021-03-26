package com.vgu.sqm.questionnaire;

import java.io.IOException;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/module")
public class Module extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String ModuleID;
    private String ModuleName;

    public Module(String ModuleID, String ModuleName) {
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
    }

    private JSONObject exportJsonData(){
        JSONObject module = new JSONObject();
        module.put("ID", this.ModuleID);
        module.put("name", this.ModuleName);
        return module;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
		response.getWriter().print(exportJsonData());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
