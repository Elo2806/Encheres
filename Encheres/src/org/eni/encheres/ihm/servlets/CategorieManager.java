package org.eni.encheres.ihm.servlets;

public class CategorieManager {
	
	private static CategorieManager single;
	
	private CategorieManager() {
		
	}
	
	public static CategorieManager getInstance() {
		if(single == null) {
			single = new CategorieManager();
		}
		return single;
	}
	
	
}
