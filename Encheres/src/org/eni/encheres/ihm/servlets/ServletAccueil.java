package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		updateCategories();
		updateArticles();
	}

	private void updateCategories() {
		Map<Integer,Categorie> categories = new HashMap<>();	
		CategorieManager manager = CategorieManager.getInstance();
		try {
			categories = manager.getCategories();
		} catch (BLLException blle) {
			blle.printStackTrace();//TODO voir si possible de faire mieux en gestion de
		}
		getServletContext().setAttribute("listeCategories", categories);
	}

	private void updateArticles() {
		Map<Integer,ArticleVendu> articles = new HashMap<>();	
		ArticleManager manager = ArticleManager.getInstance();
		
		try {
			articles = manager.getMapArticles();
		}catch (BLLException blle) {
			blle.printStackTrace();//TODO voir si possible de faire mieux en gestion de
		}
		getServletContext().setAttribute("mapArticles", articles);
	}
}
