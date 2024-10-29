package xxkq.oracle.apps.xbol.v2.employee.webui;

import java.io.Serializable;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.jbo.domain.Number;
import xxkq.oracle.apps.xbol.v2.employee.server.EntitlementChangeReqLineVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RenewalLineVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.ReplacementLineVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RequisitionAMImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RequisitionHeaderVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RequisitionHeaderVORowImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RequisitionLineTableVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RequisitionLineTableVORowImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.RequisitionLineVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.TrialVOImpl;
import xxkq.oracle.apps.xbol.v2.employee.server.TrialVORowImpl;

public class RequisitionCO extends OAControllerImpl {
  public static final String RCS_ID = "$Header$";
  
  public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
    super.processRequest(pageContext, webBean);
    pageContext.writeDiagnostics(this, getClass().getName() + " ====================== START ==================== : ", 2);
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    RequisitionHeaderVOImpl headervo = am.getRequisitionHeaderVO1();
    headervo.setMaxFetchSize(0);
    headervo.insertRow(headervo.createRow());
    pageContext.writeDiagnostics(this, getClass().getName() + ".pageContext.getUserId   MR : =====  " + pageContext.getUserId(), 2);
    pageContext.writeDiagnostics(this, getClass().getName() + ".pageContext.getEmployeeId   MR : =====  " + pageContext.getEmployeeId(), 2);
    String unameId = (new Number(pageContext.getEmployeeId())).toString();
    String reqStatus = (String)pageContext.getTransactionValue("RequisitionSubmittedStatus");
    pageContext.writeDiagnostics(this, getClass().getName() + ".unameId   MR : =====  " + unameId, 2);
    Serializable[] params = { unameId };
    am.invokeMethod("requisitionStatus", params);
    am.invokeMethod("requisitiondate");
    am.addTrialVO();
    System.out.println("Perparer stype : " + webBean.findChildRecursive("Preparer"));
    pageContext.writeDiagnostics(this, "Perparer stype : " + getClass().getName() + " : Key -- > : " + String.valueOf(webBean.findChildRecursive("Preparer")), 2);
    OAMessageStyledTextBean preparerBean = (OAMessageStyledTextBean)webBean.findChildRecursive("Preparer");
    preparerBean.setText(pageContext, pageContext.getUserName());
    pageContext.writeDiagnostics(this, "getUserId " + getClass().getName() + " : Key -- > : " + String.valueOf(pageContext.getUserId()), 2);
    System.out.println("getUserId---------" + pageContext.getUserId());
    RequisitionLineTableVOImpl vo = am.getRequisitionLineTableVO1();
    vo.insertRow(vo.createRow());
    RequisitionLineTableVORowImpl row = (RequisitionLineTableVORowImpl)vo.getCurrentRow();
    row.setInitial(Boolean.FALSE);
    row.setRenewal(Boolean.FALSE);
    row.setReplacement(Boolean.FALSE);
    row.setSaveButton(Boolean.TRUE);
    row.setSubmitButton(Boolean.TRUE);
    row.setCancelButton(Boolean.TRUE);
    row.setReasonCodeHide(Boolean.FALSE);
    row.setECR(Boolean.FALSE);
    OAMessageStyledTextBean ReqNumberBean = (OAMessageStyledTextBean)webBean.findChildRecursive("RequisitionNumber");
    OAMessageStyledTextBean ReqStatusBean = (OAMessageStyledTextBean)webBean.findChildRecursive("RequisitionStatus");
    OASubmitButtonBean initApprBtnBean = (OASubmitButtonBean)webBean.findChildRecursive("ReqApprovalBtn");
    String ReqStatusStr = (String)ReqStatusBean.getValue(pageContext);
    String reqNumberStr = (String)ReqNumberBean.getValue(pageContext);
    if (pageContext.isLoggingEnabled(4)) {
      pageContext.writeDiagnostics(this, "[RequisitionCO]processRequest() : ReqStatusStr : " + ReqStatusStr, 4);
      pageContext.writeDiagnostics(this, "[RequisitionCO]processRequest() : reqNumberStr : " + reqNumberStr, 4);
    } 
    if (ReqStatusStr.equals("Submitted") && ReqNumberBean != null) {
      initApprBtnBean.setRendered(true);
      initApprBtnBean.setDisabled(false);
    } else {
      initApprBtnBean.setRendered(true);
      initApprBtnBean.setDisabled(true);
    } 
  }
  
  private void $init$() {
    this.strReplacementSaved = "";
  }
  
  public void setReplacementSaved(String ReplacementSaved) {
    this.strReplacementSaved = ReplacementSaved;
  }
  
  public String getReplacementSaved() {
    return this.strReplacementSaved;
  }
  
  public void initializeReplacementSaved() {
    this.strReplacementSaved = "";
  }
  
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
    super.processFormRequest(pageContext, webBean);
    OASubmitButtonBean initApprBtnBean = (OASubmitButtonBean)webBean.findChildRecursive("ReqApprovalBtn");
    if (pageContext.getParameter("event").equals("reasoncodeEvent")) {
      RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
      EntitlementChangeReqLineVOImpl lvo = am.getEntitlementChangeReqLineVO1();
      lvo.clearCache();
      RequisitionHeaderVOImpl headervo = am.getRequisitionHeaderVO1();
      RequisitionHeaderVORowImpl hrow = (RequisitionHeaderVORowImpl)headervo.getCurrentRow();
      String reason = hrow.getReasonCode();
      OAFormValueBean bean = (OAFormValueBean)webBean.findChildRecursive("UniformSetIdFormField");
      String uniformsetid = bean.getText(pageContext);
      OAFormValueBean bean1 = (OAFormValueBean)webBean.findChildRecursive("PersonIdFormValue");
      String personid = bean1.getText(pageContext);
      System.out.println(getClass().getName() + " : MR : ReaconcodeEvent.personId        = > : " + bean1.getText(pageContext));
      OAFormValueBean bean2 = (OAFormValueBean)webBean.findChildRecursive("BusinessGroupIdFormValue");
      String bgid = bean2.getText(pageContext);
      Serializable[] params = { uniformsetid, reason, personid, bgid };
      am.invokeMethod("initForSourceMaternity", params);
    } 
    if (pageContext.getParameter("event").equals("requisitionTypeEvent")) {
      System.out.println(getClass().getName() + " : MR : =========================================START ===================================       = > : ");
      System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.START        = > : ");
      RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
      RequisitionLineTableVOImpl vo = am.getRequisitionLineTableVO1();
      System.out.println(getClass().getName() + " : MR : =======================================================================       = > : ");
      System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.RequisitionLineVO        = > : " + vo.getQuery());
      RequisitionLineTableVORowImpl row = (RequisitionLineTableVORowImpl)vo.getCurrentRow();
      RequisitionHeaderVOImpl headervo = am.getRequisitionHeaderVO1();
      System.out.println(getClass().getName() + " : MR : =======================================================================       = > : ");
      System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.RequisitionHeaderVO      = > : " + headervo.getQuery());
      RequisitionHeaderVORowImpl hrow = (RequisitionHeaderVORowImpl)headervo.getCurrentRow();
      TrialVOImpl trvo = am.getTrialVO1();
      System.out.println(getClass().getName() + " : MR : =======================================================================       = > : ");
      System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.TrialVO                  = > : " + trvo.getQuery());
      TrialVORowImpl trow = (TrialVORowImpl)trvo.getCurrentRow();
      RequisitionLineVOImpl lvo = am.getRequisitionLineVO1();
      System.out.println(getClass().getName() + " : MR : =======================================================================       = > : ");
      System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.RequisitionLineVO        = > : " + lvo.getQuery());
      lvo.clearCache();
      if (hrow != null) {
        System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.In Process.getRequisitionType        = > : " + hrow.getRequisitionType());
        if (hrow.getRequisitionType().startsWith("IR")) {
          OAFormValueBean bean = (OAFormValueBean)webBean.findChildRecursive("UniformSetIdFormField");
          Serializable[] params = { bean.getText(pageContext) };
          am.initializeRequisitionType();
          am.setRequisitionType("IR");
          lvo.clearCache();
          am.invokeMethod("validationRequisitionType");
          am.invokeMethod("initForInitialReqLineVO", params);
          String str1 = am.getRequisitionHeaderVO1().getCurrentRow().getAttribute("RequisitionType").toString();
          row.setApproverHide(Boolean.FALSE);
          row.setInitial(Boolean.TRUE);
          row.setRenewal(Boolean.FALSE);
          row.setReplacement(Boolean.FALSE);
          row.setReasonCodeHide(Boolean.FALSE);
          row.setECR(Boolean.FALSE);
          String strEmployeeNumber = pageContext.getParameter("EmployeeNumber");
          System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.IR.getEmployeeNumber        = > : " + strEmployeeNumber);
          am.setEmployeeNumber(strEmployeeNumber);
          initApprBtnBean.setRendered(true);
          initApprBtnBean.setDisabled(true);
          if (hrow.getRequisitionStatus().startsWith("In process")) {
            row.setCancelButton(Boolean.TRUE);
            row.setSaveButton(Boolean.FALSE);
            row.setClearButton(Boolean.FALSE);
            row.setSubmitButton(Boolean.FALSE);
            System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.InProcess.getRequisitionStatus        = > : " + hrow.getRequisitionStatus());
          } else if (hrow.getRequisitionStatus().startsWith("Submitted")) {
            row.setApproverHide(Boolean.FALSE);
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.FALSE);
            initApprBtnBean.setRendered(true);
            initApprBtnBean.setDisabled(false);
          } else if (hrow.getRequisitionStatus().startsWith("Cancelled")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.TRUE);
            initApprBtnBean.setDisabled(true);
          } else if (hrow.getRequisitionStatus().startsWith("Pending for Approval")) {
            row.setClearButton(Boolean.FALSE);
            initApprBtnBean.setDisabled(true);
            row.setApproverHide(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Approved")) {
            row.setClearButton(Boolean.FALSE);
            initApprBtnBean.setDisabled(true);
            row.setApproverHide(Boolean.FALSE);
          } 
        } else if (hrow.getRequisitionType().startsWith("ECR")) {
          initApprBtnBean.setDisabled(true);
          row.setInitial(Boolean.FALSE);
          row.setRenewal(Boolean.FALSE);
          row.setReplacement(Boolean.FALSE);
          row.setApproverHide(Boolean.TRUE);
          row.setECR(Boolean.TRUE);
          String strEmployeeNumber = pageContext.getParameter("EmployeeNumber");
          am.setEmployeeNumber(strEmployeeNumber);
          am.invokeMethod("initalRequisitionOnceCreated");
          boolean bSex = false;
          bSex = am.validateEmployeeSex(strEmployeeNumber);
          System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.validateEmpSex        = > : " + bSex);
          pageContext.writeDiagnostics(this, getClass().getName() + "Inside Requisition Controller : -->  " + String.valueOf(bSex), 2);
          System.out.println("\n Inside Requisition Controller : " + bSex);
          OAFormValueBean bean = (OAFormValueBean)webBean.findChildRecursive("UniformSetIdFormField");
          String uniformsetid = bean.getText(pageContext);
          System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.uniformsetidformValueBean        = > : " + uniformsetid);
          OAFormValueBean bean1 = (OAFormValueBean)webBean.findChildRecursive("PersonIdFormValue");
          String personid = bean1.getText(pageContext);
          System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.personIdFormValueBean        = > : " + personid);
          OAFormValueBean bean2 = (OAFormValueBean)webBean.findChildRecursive("BusinessGroupIdFormValue");
          String bgid = bean2.getText(pageContext);
          System.out.println(getClass().getName() + " : MR : requisitionTypeEvent.businessGroupIdFormValue        = > : " + bgid);
          Serializable[] params = { uniformsetid, personid, bgid };
          am.initializeRequisitionType();
          am.setRequisitionType("ECR");
          EntitlementChangeReqLineVOImpl ecrvo = am.getEntitlementChangeReqLineVO1();
          ecrvo.clearCache();
          if (bSex) {
            row.setReasonCodeHide(Boolean.TRUE);
            am.invokeMethod("maternityCheck");
          } else {
            System.out.println("-------checking----Bsex--------ECR-------::::::::1111111");
            pageContext.writeDiagnostics(this, getClass().getName() + "-------checking----Bsex--------ECR-------::::::::1111111 ", 2);
            row.setReasonCodeHide(Boolean.FALSE);
            am.invokeMethod("initForReqLineVO", params);
            System.out.println("-------checking----Bsex--------ECR-------::::::::222222");
            pageContext.writeDiagnostics(this, getClass().getName() + "-------checking----Bsex--------ECR-------::::::::22222 ", 2);
          } 
          if (hrow.getRequisitionStatus().startsWith("In process")) {
            pageContext.writeDiagnostics(this, getClass().getName() + "RequisitionStatus.Inprocess --> : " + hrow.getRequisitionStatus(), 2);
            row.setCancelButton(Boolean.TRUE);
            row.setSaveButton(Boolean.FALSE);
            row.setClearButton(Boolean.FALSE);
            row.setSubmitButton(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Pending for Approval")) {
            row.setClearButton(Boolean.FALSE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.TRUE);
          } else if (hrow.getRequisitionStatus().startsWith("Approved")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Rejected")) {
            row.setClearButton(Boolean.FALSE);
            row.setSaveButton(Boolean.FALSE);
            row.setSubmitButton(Boolean.FALSE);
            row.setCancelButton(Boolean.TRUE);
          } else if (hrow.getRequisitionStatus().startsWith("Cancelled")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.TRUE);
          } 
        } else if (hrow.getRequisitionType().startsWith("RR")) {
          initApprBtnBean.setDisabled(true);
          row.setInitial(Boolean.FALSE);
          row.setRenewal(Boolean.TRUE);
          row.setReplacement(Boolean.FALSE);
          row.setApproverHide(Boolean.FALSE);
          row.setReasonCodeHide(Boolean.FALSE);
          row.setECR(Boolean.FALSE);
          am.invokeMethod("initalRequisitionOnceCreated");
          String strEmployeeNumber = pageContext.getParameter("EmployeeNumber");
          am.setEmployeeNumber(strEmployeeNumber);
          OAFormValueBean bean = (OAFormValueBean)webBean.findChildRecursive("UniformSetIdFormField");
          Serializable[] params = { bean.getText(pageContext) };
          pageContext.writeDiagnostics(this, getClass().getName() + "UniformSetIdFormField --> : " + String.valueOf(bean), 2);
          System.out.println("\n-----------RR------UniformSetIdFormField:::::::" + bean);
          am.initializeRequisitionType();
          RenewalLineVOImpl rvo = am.getRenewalLineVO1();
          rvo.clearCache();
          am.invokeMethod("initForRenewalReqLineVO", params);
          am.invokeMethod("maternityCheck");
          if (hrow.getRequisitionStatus().startsWith("In process")) {
            row.setCancelButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setClearButton(Boolean.FALSE);
            row.setSubmitButton(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Submitted")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Cancelled")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.TRUE);
          } 
        } else if (hrow.getRequisitionType().startsWith("RPR")) {
          initApprBtnBean.setDisabled(true);
          row.setInitial(Boolean.FALSE);
          row.setRenewal(Boolean.FALSE);
          row.setReplacement(Boolean.TRUE);
          row.setApproverHide(Boolean.TRUE);
          row.setReasonCodeHide(Boolean.FALSE);
          row.setECR(Boolean.FALSE);
          am.invokeMethod("initalRequisitionOnceCreated");
          String strEmployeeNumber = pageContext.getParameter("EmployeeNumber");
          pageContext.writeDiagnostics(this, getClass().getName() + "getEmployeeNumber --> : " + pageContext.getParameter("EmployeeNumber"), 2);
          am.setEmployeeNumber(strEmployeeNumber);
          OAFormValueBean bean = (OAFormValueBean)webBean.findChildRecursive("UniformSetIdFormField");
          String uniformsetid = bean.getText(pageContext);
          OAFormValueBean bean1 = (OAFormValueBean)webBean.findChildRecursive("PersonIdFormValue");
          String personid = bean1.getText(pageContext);
          OAFormValueBean bean2 = (OAFormValueBean)webBean.findChildRecursive("BusinessGroupIdFormValue");
          String bgid = bean2.getText(pageContext);
          Serializable[] params = { uniformsetid, personid, bgid };
          am.initializeRequisitionType();
          ReplacementLineVOImpl replacementLinevo = am.getReplacementLineVO1();
          replacementLinevo.clearCache();
          am.invokeMethod("initForReplacementLineVO", params);
          if (hrow.getRequisitionStatus().startsWith("In process")) {
            row.setCancelButton(Boolean.TRUE);
            row.setSaveButton(Boolean.FALSE);
            row.setClearButton(Boolean.FALSE);
            row.setSubmitButton(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Pending for Approval")) {
            row.setClearButton(Boolean.FALSE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.TRUE);
          } else if (hrow.getRequisitionStatus().startsWith("Approved")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.FALSE);
          } else if (hrow.getRequisitionStatus().startsWith("Rejected")) {
            row.setClearButton(Boolean.FALSE);
            row.setSaveButton(Boolean.FALSE);
            row.setSubmitButton(Boolean.FALSE);
            row.setCancelButton(Boolean.TRUE);
          } else if (hrow.getRequisitionStatus().startsWith("Cancelled")) {
            row.setClearButton(Boolean.TRUE);
            row.setSaveButton(Boolean.TRUE);
            row.setSubmitButton(Boolean.TRUE);
            row.setCancelButton(Boolean.TRUE);
          } 
        } 
      } 
    } 
    if (pageContext.getParameter("event").equals("ReqApprEvent")) {
      RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
      RequisitionHeaderVOImpl headervo = am.getRequisitionHeaderVO1();
      RequisitionHeaderVORowImpl hrow = (RequisitionHeaderVORowImpl)headervo.getCurrentRow();
      pageContext.writeDiagnostics(this, "[RequisitionCO]processRequest() : ReqStatus : " + hrow.getRequisitionStatus(), 1);
      pageContext.writeDiagnostics(this, "[RequisitionCO]processRequest() : ReqTye : " + hrow.getRequisitionType(), 1);
      if (hrow.getRequisitionType().startsWith("IR") && hrow.getRequisitionStatus().startsWith("Submitted")) {
        am.invokeMethod("workFlowForInitialRequisition");
        throw new OAException("Initial Requisition has been sent for Approval", (byte)2);
      } 
    } 
    if (pageContext.getParameter("Submit") != null) {
      RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
      RequisitionHeaderVOImpl headervo = am.getRequisitionHeaderVO1();
      RequisitionHeaderVORowImpl hrow = (RequisitionHeaderVORowImpl)headervo.getCurrentRow();
      if (hrow.getRequisitionType().startsWith("IR")) {
        am.invokeMethod("validationRequisitionType");
        validatelineItemsForInitialRequisition(pageContext, webBean);
        am.invokeMethod("requisitionNumber123");
        am.invokeMethod("onhandQuantityIR");
        am.invokeMethod("submit");
        RequisitionLineTableVOImpl rlinetable = am.getRequisitionLineTableVO1();
        RequisitionLineTableVORowImpl rlinetablerow = (RequisitionLineTableVORowImpl)rlinetable.getCurrentRow();
        rlinetablerow.setClearButton(Boolean.TRUE);
        rlinetablerow.setSubmitButton(Boolean.TRUE);
        initApprBtnBean.setRendered(true);
        initApprBtnBean.setDisabled(false);
        pageContext.putTransactionValue("RequisitionSubmittedStatus", "Submitted");
        throw new OAException("Initial Requisition has been Created", (byte)2);
      } 
      if (hrow.getRequisitionType().startsWith("RR")) {
        validatelineItemsForRenewalRequisition(pageContext, webBean);
        am.invokeMethod("submitRenewalCheck");
        am.invokeMethod("requisitionNumber123");
        am.invokeMethod("onhandQuantityForReplacement");
        am.invokeMethod("submitRenewalRequisition");
        RequisitionLineTableVOImpl rlinetable = am.getRequisitionLineTableVO1();
        RequisitionLineTableVORowImpl rlinetablerow = (RequisitionLineTableVORowImpl)rlinetable.first();
        rlinetablerow.setClearButton(Boolean.TRUE);
        rlinetablerow.setSubmitButton(Boolean.TRUE);
        throw new OAException("Renewal Requisition has been Created", (byte)2);
      } 
      if (hrow.getRequisitionType().startsWith("RPR")) {
        validateAllLines(pageContext, webBean);
        validateItemCode(pageContext, webBean);
        if (hrow.getRequisitionNumber() == null)
          am.invokeMethod("requisitionNumber123"); 
        String strReplacementDone = getReplacementSaved();
        boolean bReplacementSaved = (strReplacementDone != null && strReplacementDone.equalsIgnoreCase("ReplacementSaved"));
        if (!bReplacementSaved)
          am.invokeMethod("submitReplacementRequisition"); 
        am.invokeMethod("onhandQuantityForReplacement");
        am.invokeMethod("workflowforreplacement");
        RequisitionLineTableVOImpl rlinetable = am.getRequisitionLineTableVO1();
        RequisitionLineTableVORowImpl rlinetablerow = (RequisitionLineTableVORowImpl)rlinetable.first();
        if (rlinetablerow != null) {
          rlinetablerow.setClearButton(Boolean.TRUE);
          rlinetablerow.setSubmitButton(Boolean.TRUE);
          rlinetablerow.setSaveButton(Boolean.TRUE);
        } 
        throw new OAException("Work Flow Notification Has been sent for Approval", (byte)2);
      } 
      if (hrow.getRequisitionType().startsWith("ECR")) {
        initApprBtnBean.setDisabled(true);
        validateAllLinesEcr(pageContext, webBean);
        validatelineItemsForEntitlementChangeRequisition(pageContext, webBean);
        if (hrow.getRequisitionNumber() == null)
          am.invokeMethod("requisitionNumber123"); 
        am.invokeMethod("submitEntitlementChangeRequisition");
        am.invokeMethod("onhandQuantity");
        am.invokeMethod("workFlowForEntitlementRequisition");
        RequisitionLineTableVOImpl rlinetable = am.getRequisitionLineTableVO1();
        RequisitionLineTableVORowImpl rlinetablerow = (RequisitionLineTableVORowImpl)rlinetable.first();
        rlinetablerow.setClearButton(Boolean.TRUE);
        rlinetablerow.setSubmitButton(Boolean.TRUE);
        rlinetablerow.setSaveButton(Boolean.TRUE);
        throw new OAException("Work Flow Notification Has been sent for Approval", (byte)2);
      } 
    } 
    if (pageContext.getParameter("Save") != null) {
      RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
      RequisitionHeaderVOImpl headervo = am.getRequisitionHeaderVO1();
      RequisitionHeaderVORowImpl hrow = (RequisitionHeaderVORowImpl)headervo.getCurrentRow();
      if (hrow.getRequisitionType().startsWith("ECR")) {
        validatelineItemsForInitialRequisition(pageContext, webBean);
        am.invokeMethod("requisitionNumber123");
        am.invokeMethod("submit");
        RequisitionLineTableVOImpl rlinetable = am.getRequisitionLineTableVO1();
        RequisitionLineTableVORowImpl rlinetablerow = (RequisitionLineTableVORowImpl)rlinetable.first();
        rlinetablerow.setSaveButton(Boolean.TRUE);
        throw new OAException("Entitlement Change Requisition has been Created", (byte)2);
      } 
      if (hrow.getRequisitionType().startsWith("RPR")) {
        initApprBtnBean.setDisabled(true);
        validateAllLines(pageContext, webBean);
        validateItemCode(pageContext, webBean);
        System.out.println("\n  Inside RPR ::::::::  ");
        am.invokeMethod("requisitionNumber123");
        am.invokeMethod("submitReplacementRequisition");
        RequisitionLineTableVOImpl rlinetable = am.getRequisitionLineTableVO1();
        RequisitionLineTableVORowImpl rlinetablerow = (RequisitionLineTableVORowImpl)rlinetable.first();
        rlinetablerow.setSaveButton(Boolean.TRUE);
        initializeReplacementSaved();
        setReplacementSaved("ReplacementSaved");
        throw new OAException("Replacement Requisition has been Created", (byte)2);
      } 
    } 
    if (pageContext.getParameter("Cancel") != null) {
      RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
      am.invokeMethod("cancelButton");
    } 
  }
  
  public void validateAllLines(OAPageContext pageContext, OAWebBean webBean) {
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    boolean bAllLines = am.validateAllLines();
    if (!bAllLines)
      throw new OAException("Please select minimum one line item for requisition"); 
  }
  
  public void validateAllLinesEcr(OAPageContext pageContext, OAWebBean webBean) {
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    boolean bAllLines = am.validateAllLinesEcr();
    if (!bAllLines)
      throw new OAException("Please select minimum one line item for requisition"); 
  }
  
  public void validateItemCode(OAPageContext pageContext, OAWebBean webBean) {
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    boolean itemCode = am.validateItemCode();
    String strMessage = am.getMessage();
    if (itemCode)
      throw new OAException(strMessage); 
  }
  
  public void validatelineItemsForInitialRequisition(OAPageContext pageContext, OAWebBean webBean) {
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    boolean bLineItem = am.validatelineItemsForInitialRequisition();
    String strMessage = am.getMessage();
    if (bLineItem)
      throw new OAException(strMessage); 
  }
  
  public void validatelineItemsForRenewalRequisition(OAPageContext pageContext, OAWebBean webBean) {
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    boolean bLineItem = am.validatelineItemsForRenewalRequisition();
    String strMessage = am.getMessage();
    if (bLineItem)
      throw new OAException(strMessage); 
  }
  
  public void validatelineItemsForEntitlementChangeRequisition(OAPageContext pageContext, OAWebBean webBean) {
    RequisitionAMImpl am = (RequisitionAMImpl)pageContext.getApplicationModule(webBean);
    boolean bLineItem = am.validatelineItemsForEntitlementChangeRequisition();
    String strMessage = am.getMessage();
    if (bLineItem)
      throw new OAException(strMessage); 
  }
  
  public RequisitionCO() {
    $init$();
  }
  
  public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header$", "%packagename%");
  
  public String strReplacementSaved;
}


/* Location:              D:\Oracle\JDevR12.2.14\jdevhome\jdev\myprojects\!\xxkq\oracle\apps\xbol\v2\employee\webui\RequisitionCO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */