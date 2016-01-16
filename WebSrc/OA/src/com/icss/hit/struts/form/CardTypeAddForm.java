/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2009
 * 
 * XDoclet definition:
 * @struts.form name="cardTypeAddForm"
 */
public class CardTypeAddForm extends ActionForm {
	/*
	 * Generated fields
	 */
	
	/** newCardType property */
	private String newCardType;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		ActionErrors errors = new ActionErrors();
		if(newCardType == null || newCardType.equals("")){//对 空值拿到后台做验证就太伤了 我靠 有道理这样判断空值
			errors.add("newCardTypeNull", new ActionMessage("NewCardType.cardTypeNameNull"));
		}
		else if( newCardType.length() > 20 ){//终于成功 太奸了
			errors.add("newCardTypeTooLong", new ActionMessage("NewCardType.cardTypeNameTooLong"));
		}
		
		return errors;
		
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the newCardType.
	 * @return String
	 */
	public String getNewCardType() {
		return newCardType;
	}

	/** 
	 * Set the newCardType.
	 * @param newCardType The newCardType to set
	 */
	public void setNewCardType(String newCardType) {
		this.newCardType = newCardType;
	}

	
}