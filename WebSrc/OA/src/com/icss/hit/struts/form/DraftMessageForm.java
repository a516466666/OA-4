/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-07-2009
 * 
 * XDoclet definition:
 * @struts.form name="draftMessageForm"
 */
public class DraftMessageForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** type property */
	private String type;

	/** userId property */
	private String mesId;

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
		return null;
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
	 * Returns the type.
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * Set the type.
	 * @param type The type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * Returns the userId.
	 * @return String
	 */
	public String getMesId() {
		return mesId;
	}

	/** 
	 * Set the userId.
	 * @param userId The userId to set
	 */
	public void setMesId(String mesId) {
		this.mesId = mesId;
	}
}