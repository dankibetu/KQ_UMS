package xxkq.oracle.apps.xbol.v2.employee.server;

import com.sun.java.util.collections.ArrayList;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.SQLException;
//import java.util.Date;
import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

public class RequisitionAMImpl extends OAApplicationModuleImpl {
    String requisitionType;

    public int VOIndex;

    String strMessage;

    private void $init$() {
        this.requisitionType = "";
    }

    public RequisitionHeaderVOImpl getRequisitionHeaderVO1() {
        return (RequisitionHeaderVOImpl)findViewObject("RequisitionHeaderVO1");
    }

    public RequisitionLineVOImpl getRequisitionLineVO1() {
        return (RequisitionLineVOImpl)findViewObject("RequisitionLineVO1");
    }

    public void requisitionStatus(String unameId) {
        writeDiagnostics(this, 
                         getClass().getName() + ".requisitionStatus.START      MR : ==== : ", 
                         2);
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        Serializable[] params = { unameId };
        System.out.println("unameId  " + unameId);
        writeDiagnostics(this, 
                         getClass().getName() + ".requisitionStatus.unameId      MR : ==== : " + 
                         String.valueOf(unameId), 2);
        vo.invokeMethod("requisitionstatus", params);
    }

    public void requisitiondate() {
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        writeDiagnostics(this, 
                         getClass().getName() + ".requisitiondate.requisitiondate      MR : ==== : ", 
                         2);
        vo.invokeMethod("requisitiondate");
    }

    public void initalRequisitionOnceCreated() {
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        writeDiagnostics(this, 
                         getClass().getName() + ".initalRequisitionOnceCreated.call.initialRequisitionFirst      MR : ==== : ", 
                         2);
        vo.invokeMethod("initialRequisitionFirst");
    }

    public void initQueryForRequisitionLineVO(String empId) {
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        writeDiagnostics(this, 
                         getClass().getName() + ".initQueryForRequisitionLineVO.empId      MR : ==== : " + 
                         String.valueOf(empId), 2);
        Serializable[] params = { empId };
        vo.invokeMethod("initQueryForRequisitionLineVO", params);
    }

    public void initForReqLineVO(String uniformsetid, String personid, 
                                 String bgid) {
        writeDiagnostics(this, 
                         getClass().getName() + ".initForReqLineVO.START         MR : ==== : ", 
                         2);
        writeDiagnostics(this, 
                         getClass().getName() + ".initForReqLineVO.personId      MR : ==== : " + 
                         String.valueOf(personid), 2);
        writeDiagnostics(this, 
                         getClass().getName() + ".initForReqLineVO.bgid          MR : ==== : " + 
                         String.valueOf(bgid), 2);
        writeDiagnostics(this, 
                         getClass().getName() + ".initForReqLineVO.uniformsetid  MR : ==== : " + 
                         String.valueOf(uniformsetid), 2);
        System.out.println("i am in initForReqLineVO--------ECR::::::AM-------:::::::!!!!!!!! ");
        SourceRequisitionLineVOImpl svo = getSourceRequisitionLineVO1();
        svo.setWhereClause("uniformsetid = :1 and personid = :2 and bgid = :3");
        System.out.println("i am in initForReqLineVO--------ECR::::::AM-------:::::::@@@@ ");
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, uniformsetid);
        svo.setWhereClauseParam(1, personid);
        svo.setWhereClauseParam(2, bgid);
        System.out.println("i am in initForReqLineVO--------ECR::::::AM-------:::::::########## ");
        svo.executeQuery();
        writeDiagnostics(this, 
                         getClass().getName() + ".svo.getQuery().VALUE         MR : ==== : " + 
                         svo.getQuery(), 2);
        System.out.println("i am in initForReqLineVO--------ECR::AM-------::::::::$$$$$$$$ ");
        EntitlementChangeReqLineVOImpl vo = getEntitlementChangeReqLineVO1();
        vo.setMaxFetchSize(0);
        for (int i = svo.getRowCount(); svo.hasNext(); i--) {
            SourceRequisitionLineVORowImpl svoRow = 
                (SourceRequisitionLineVORowImpl)svo.next();
            writeDiagnostics(this, 
                             getClass().getName() + ".initForReqLineVO.insert and create         MR : ==== : ", 
                             2);
            System.out.println("i am calling for insert & create : " + svoRow);
            EntitlementChangeReqLineVORowImpl row = 
                (EntitlementChangeReqLineVORowImpl)vo.createRow();
            row.setItemCategory(svoRow.getItemCategory());
            writeDiagnostics(this, 
                             getClass().getName() + ".svoRow.getItemCategory().VALUE         MR : ==== : " + 
                             svoRow.getItemCategory(), 2);
            row.setEntitledQty(svoRow.getEntitledQty());
            String strType = getRequisitionType();
            writeDiagnostics(this, 
                             getClass().getName() + ".svoRow.getRequisitionType().VALUE         MR : ==== : " + 
                             strType, 2);
            if ((((strType != null) ? 1 : 0) & 
                 ((!strType.equals("IR") && !strType.equals("ECR")) ? 0 : 
                  1)) != 0) {
                row.setRequestedQty(svoRow.getEntitledQty());
                writeDiagnostics(this, 
                                 getClass().getName() + ".svoRow.setRequestedQty().StrType.In.IR.ECR         MR : ==== : " + 
                                 strType, 2);
            }
            row.setRenewelQty(svoRow.getRenewelQty());
            row.setEligibleRenewalQty(svoRow.getEligibleRenewalQty());
            row.setLifeExpectancy(svoRow.getLifeExpectancy());
            row.setLineNumber(new Number(i));
            row.setUniformSetLineId(svoRow.getUniformSetLineId());
            row.setCategoryId(svoRow.getCategoryId());
            row.setOrganizationId(svoRow.getOrganizationId());
            vo.insertRow((Row)row);
        }
        writeDiagnostics(this, 
                         getClass().getName() + ".initForReqLineVO.END        MR : ==== : ", 
                         2);
    }

    public void initForSourceMaternity(String uniformsetid, String reasoncode, 
                                       String personid, String bgid) {
        System.out.println("---------i am in initForReqLineVO--------check------Maternity-----::AM::::::::1111111 ");
        SourceMaternityVOImpl svo = getSourceMaternityVO1();
        svo.setWhereClause("uniformsetid = :1 and reasoncode = :2 and personID = :3 and bgid = :4");
        System.out.println("---------i am in initForReqLineVO--------check------Maternity-----::AM::::::::M22222 ");
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, uniformsetid);
        svo.setWhereClauseParam(1, reasoncode);
        svo.setWhereClauseParam(2, personid);
        svo.setWhereClauseParam(3, bgid);
        System.out.println("---------i am in initForReqLineVO--------check------Maternity-----::AM::::::::M333333 ");
        svo.executeQuery();
        System.out.println("---------i am in initForReqLineVO--------check------Maternity-----::AM:::::::M444444 ");
        EntitlementChangeReqLineVOImpl vo = getEntitlementChangeReqLineVO1();
        if (vo.getFetchedRowCount() == 0) {
            System.out.println("------ i am inside of getFetchedRowCount 0 row count : " + 
                               vo.getFetchedRowCount());
            vo.setMaxFetchSize(0);
        }
        System.out.println("------ i am outside of getFetchedRowCount row count : " + 
                           vo.getFetchedRowCount());
        for (int i = svo.getRowCount(); svo.hasNext(); i--) {
            SourceMaternityVORowImpl svoRow = 
                (SourceMaternityVORowImpl)svo.next();
            System.out.println("i am calling for insert & create : " + svoRow);
            EntitlementChangeReqLineVORowImpl row = 
                (EntitlementChangeReqLineVORowImpl)vo.createRow();
            row.setItemCategory(svoRow.getItemCategory());
            row.setEntitledQty(svoRow.getEntitledQty());
            String strType = getRequisitionType();
            if ((((strType != null) ? 1 : 0) & 
                 ((!strType.equals("IR") && !strType.equals("ECR")) ? 0 : 
                  1)) != 0)
                row.setRequestedQty(svoRow.getEntitledQty());
            row.setRenewelQty(svoRow.getRenewelQty());
            row.setLifeExpectancy(svoRow.getLifeExpectancy());
            row.setLineNumber(new Number(i));
            row.setUniformSetLineId(svoRow.getUniformSetLineId());
            row.setCategoryId(svoRow.getCategoryId());
            row.setOrganizationId(svoRow.getOrganizationId());
            vo.insertRow((Row)row);
        }
    }

    public void requisitionNumber123() {
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        vo.invokeMethod("requisitionNumber");
    }

    public ButtonPVOImpl getButtonPVO1() {
        return (ButtonPVOImpl)findViewObject("ButtonPVO1");
    }

    public RequisitionUSNVOImpl getRequisitionUSNVO1() {
        return (RequisitionUSNVOImpl)findViewObject("RequisitionUSNVO1");
    }

    public void setVOIndex(int setVOIndex) {
        this.VOIndex = setVOIndex;
    }

    public int getVOIndex() {
        return this.VOIndex;
    }

    public void submitReplacementRequisition() {
        System.out.println(getName().getClass() + 
                           " =================================== START : < submitReplacementRequisition > ========================================== ");
        ReplacementLineVOImpl replacementLinevo = getReplacementLineVO1();
        replacementLinevo.reset();
        RequisitionHeaderVOImpl HeaderVO = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl Hreaderrow = 
            (RequisitionHeaderVORowImpl)HeaderVO.getCurrentRow();
        Number UniformSetId = Hreaderrow.getUniformSetId();
        Number numHeaderId = Hreaderrow.getRequisitionHeaderId();
        System.out.println(getName().getClass() + 
                           " submitReplacementRequisition.numHeaderId                       => : " + 
                           String.valueOf(numHeaderId));
        int nRowCount = replacementLinevo.getRowCount();
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        int i = 0;
        setVOIndex(0);
        for (i = 0; i < nRowCount; i++) {
            ReplacementLineVORowImpl replacementLineVoRow = 
                (ReplacementLineVORowImpl)replacementLinevo.getRowAtRangeIndex(i);
            String strtempForSelection = 
                replacementLineVoRow.gettempForSelection();
            System.out.println(getName().getClass() + 
                               " submitReplacementRequisition.strtempForSelection              => : " + 
                               String.valueOf(strtempForSelection));
            if (strtempForSelection != null && 
                (strtempForSelection == null || !strtempForSelection.equals("N"))) {
                int nHeaderId = 0;
                try {
                    nHeaderId = numHeaderId.intValue();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(getName().getClass() + 
                                   " submitReplacementRequisition.numHeaderId              => : " + 
                                   String.valueOf(numHeaderId));
                System.out.println(getName().getClass() + 
                                   " submitReplacementRequisition.create.nHeaderId         => : " + 
                                   String.valueOf(nHeaderId));
                RequisitionLineVORowImpl row = 
                    (RequisitionLineVORowImpl)vo.createRow();
                row.setItemCategory(replacementLineVoRow.getItemCategory());
                row.setEntitledQty(replacementLineVoRow.getEntitledQty());
                row.setRenewelQty(replacementLineVoRow.getRenewelQty());
                row.setEligibleRenewalQty(replacementLineVoRow.getEligibleRenewalQty());
                row.setLifeExpectancy(replacementLineVoRow.getLifeExpectancy());
                row.setInventoryItemId(replacementLineVoRow.getInventoryItemId());
                row.setDamageQty(replacementLineVoRow.getDamageQty());
                row.setChargableQty(replacementLineVoRow.getChargableQty());
                row.setReturnedQty(replacementLineVoRow.getDamageQty());
                row.setReasonCode(replacementLineVoRow.getReasonCode());
                row.setNeedByDate(replacementLineVoRow.getNeedByDate());
                row.setRemarks(replacementLineVoRow.getRemarks());
                System.out.println(getName().getClass() + 
                                   " submitReplacementRequisition.create.getReplacementItemId              => : " + 
                                   replacementLineVoRow.getReplacementItemId());
                System.out.println(getName().getClass() + 
                                   " submitReplacementRequisition.create.getReplacementItemForItem         => : " + 
                                   replacementLineVoRow.getReplacementItemForItem());
                row.setReplacementItemId(replacementLineVoRow.getReplacementItemId());
                row.setRequestedQty(replacementLineVoRow.getDamageQty());
                int p = i + 1;
                row.setLineNumber(new Number(p));
                row.setUniformSetLineId(replacementLineVoRow.getUniformSetLineId());
                row.setCategoryId(replacementLineVoRow.getCategoryId());
                row.setOrganizationId(replacementLineVoRow.getOrganizationId());
                row.setRequisitionHeaderId(numHeaderId);
                row.setUniformsetid(UniformSetId);
                vo.insertRow((Row)row);
            }
        }
        setVOIndex(i);
        System.out.println(getName().getClass() + 
                           " submitReplacementRequisition.vo.getRowCount                  => : " + 
                           String.valueOf(vo.getRowCount()));
        System.out.println(getName().getClass() + 
                           " submitReplacementRequisition.vo.getFetchedRowCount()         => : " + 
                           String.valueOf(vo.getFetchedRowCount()));
        System.out.println(getName().getClass() + 
                           " submitReplacementRequisition.vo.getFetchSize                 => : " + 
                           String.valueOf(vo.getFetchSize()));
        getTransaction().commit();
        System.out.println(getName().getClass() + 
                           " ===================================: END : < submitReplacementRequisition > ========================================== ");
    }

    public void submit() {
        System.out.println(getName().getClass() + 
                           " =============================: START : SubmitRequistion ====================: ");
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        vo.invokeMethod("setRemainingFeilds");
        for (RequisitionLineVORowImpl r = (RequisitionLineVORowImpl)vo.first(); 
             r != null; r = (RequisitionLineVORowImpl)vo.next()) {
            if (!r.getSelectedFlag().equals("Y")) {
                continue;
            }
            System.out.println(getName().getClass() + 
                               " SubmitRequistion.getLineNumber            = > : " + 
                               r.getLineNumber());
            System.out.println(getName().getClass() + 
                               " SubmitRequistion.getRequisitionHeaderId   = > : " + 
                               r.getRequisitionHeaderId());
        }
        getTransaction().commit();
        System.out.println(getName().getClass() + 
                           " =============================: END : SubmitRequistion ====================: ");
        System.out.println("\n Data has been inserted");
    }

    public void submitEntitlementChangeRequisition() {
        EntitlementChangeReqLineVOImpl entitlementLinevo = 
            getEntitlementChangeReqLineVO1();
        entitlementLinevo.reset();
        RequisitionHeaderVOImpl HeaderVO = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl Hreaderrow = 
            (RequisitionHeaderVORowImpl)HeaderVO.getCurrentRow();
        Number UniformSetId = Hreaderrow.getUniformSetId();
        Number numHeaderId = Hreaderrow.getRequisitionHeaderId();
        System.out.println("Inside submitEntitlementChangeRequisition calling for insert & numHeaderId : " + 
                           numHeaderId);
        int nRowCount = entitlementLinevo.getRowCount();
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        int i = 0;
        setVOIndex(0);
        for (i = 0; i < nRowCount; i++) {
            EntitlementChangeReqLineVORowImpl entitlementLineRow = 
                (EntitlementChangeReqLineVORowImpl)entitlementLinevo.getRowAtRangeIndex(i);
            String strtempForSelection = 
                entitlementLineRow.getECRTempForSelection();
            System.out.println(" strtempForSelection " + strtempForSelection);
            if (strtempForSelection != null && 
                (strtempForSelection == null || !strtempForSelection.equals("N"))) {
                int nHeaderId = 0;
                try {
                    nHeaderId = numHeaderId.intValue();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println("Inside submitEntitlementChangeRequisition calling for insert & numHeaderId : " + 
                                   numHeaderId);
                System.out.println("Inside submitEntitlementChangeRequisition calling for insert & create : " + 
                                   nHeaderId);
                RequisitionLineVORowImpl row = 
                    (RequisitionLineVORowImpl)vo.createRow();
                row.setItemCategory(entitlementLineRow.getItemCategory());
                row.setEntitledQty(entitlementLineRow.getEntitledQty());
                row.setInventoryItemId(entitlementLineRow.getInventoryItemId());
                row.setNeedByDate(entitlementLineRow.getNeedByDate());
                row.setRemarks(entitlementLineRow.getRemarks());
                row.setRequestedQty(entitlementLineRow.getEntitledQty());
                int p = i + 1;
                row.setLineNumber(new Number(p));
                row.setUniformSetLineId(entitlementLineRow.getUniformSetLineId());
                row.setCategoryId(entitlementLineRow.getCategoryId());
                row.setOrganizationId(entitlementLineRow.getOrganizationId());
                row.setRequisitionHeaderId(numHeaderId);
                row.setUniformsetid(UniformSetId);
                System.out.println("Outside submitEntitlementChangeRequisition calling for insert & numHeaderId : " + 
                                   numHeaderId);
                vo.insertRow((Row)row);
            }
        }
        setVOIndex(i);
        System.out.println("Inside submitEntitlementChangeRequisition calling for insert &  vo.getRowCount(): " + 
                           vo.getRowCount());
        System.out.println("Inside submitEntitlementChangeRequisition calling for insert &  vo.getFetchedRowCount(): " + 
                           vo.getFetchedRowCount());
        System.out.println("Inside submitEntitlementChangeRequisition calling for insert &  vo.getFetchSize(): " + 
                           vo.getFetchSize());
        getTransaction().commit();
    }

    public void validationRequisitionType() {
        RequisitionHeaderVOImpl voh = getRequisitionHeaderVO1();
        voh.invokeMethod("requistionType");
    }

    public void onhandQuantity() {
        System.out.println(getName().getClass() + 
                           "============================= START : onhandQuantity : ===================================");
        OADBTransaction trxn = (OADBTransaction)getDBTransaction();
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row1 = 
            (RequisitionHeaderVORowImpl)vo.getCurrentRow();
        EntitlementChangeReqLineVOImpl linevo = 
            (EntitlementChangeReqLineVOImpl)findViewObject("EntitlementChangeReqLineVO1");
        linevo.reset();
        System.out.println(getName().getClass() + 
                           ".onhandQuantity.totalrows : " + 
                           String.valueOf(linevo.getRowCount()));
        for (int i = 0; i < linevo.getRowCount(); i++) {
            EntitlementChangeReqLineVORowImpl linerow = 
                (EntitlementChangeReqLineVORowImpl)linevo.getRowAtRangeIndex(i);
            if (linerow != null) {
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.totalrows           = > : " + 
                                   String.valueOf(linerow));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getUniformSetLineId = > : " + 
                                   String.valueOf(linerow.getUniformSetLineId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getCategoryId :     = > : " + 
                                   String.valueOf(linerow.getCategoryId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getInventoryItemId  = > : " + 
                                   String.valueOf(linerow.getInventoryItemId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getOrganizationId   = > : " + 
                                   String.valueOf(linerow.getOrganizationId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getBusinessGroupId  = > : " + 
                                   String.valueOf(row1.getBusinessGroupId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getPersonId         = > : " + 
                                   String.valueOf(row1.getPersonId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getUniformSetId     = > : " + 
                                   String.valueOf(row1.getUniformSetId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.getPersonId         = > : " + 
                                   String.valueOf(row1.getPersonId()));
                Number businessGroup = row1.getBusinessGroupId();
                Number personId = row1.getPersonId();
                Number uniformSetId = row1.getUniformSetId();
                Number uniformSetLineId = linerow.getUniformSetLineId();
                Number categoryId = linerow.getCategoryId();
                Number itemId = linerow.getInventoryItemId();
                Number organisationId = linerow.getOrganizationId();
                Number lineNumber = linerow.getLineNumber();
                String requisitionType = "'" + row1.getRequisitionType() + "'";
                System.out.println(getName().getClass() + 
                                   ".onhandQuantity.requisitionType         = > : " + 
                                   String.valueOf(requisitionType));
                CallableStatement csonhandqty = 
                    trxn.createCallableStatement("BEGIN XX_UMS_EMP_ONHAND_QTY_V2_PKG.INSERT_DATA (" + 
                                                 personId + "," + 
                                                 businessGroup + "," + 
                                                 uniformSetId + "," + 
                                                 uniformSetLineId + "," + 
                                                 categoryId + "," + itemId + 
                                                 "," + organisationId + "," + 
                                                 requisitionType + 
                                                 ",:1); END;", 1);
                try {
                    ((OracleCallableStatement)csonhandqty).registerOutParameter(1, 
                                                                                12, 
                                                                                0, 
                                                                                2000);
                    csonhandqty.execute();
                    String status = csonhandqty.getString(1);
                    System.out.println("onhandQuantity.to see what is written in procedure status:::::" + 
                                       status);
                    csonhandqty.close();
                    if (status != null)
                        throw new OAException(status, (byte)0);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (Exception sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        System.out.println(getName().getClass() + 
                           "============================= END : onhandQuantity : ===================================");
    }

    public RequisitionAMImpl() {
        $init$();
        this.VOIndex = 0;
        this.strMessage = "";
    }

    public void onhandQuantityIR() {
        System.out.println(getName().getClass() + 
                           "============================= START : onhandQuantityIR : ===================================");
        OADBTransaction trxn = (OADBTransaction)getDBTransaction();
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row1 = 
            (RequisitionHeaderVORowImpl)vo.getCurrentRow();
        RequisitionLineVOImpl linevo = 
            (RequisitionLineVOImpl)findViewObject("RequisitionLineVO1");
        linevo.reset();

        // DKIBETU | 13-OCT-2024 | Added logic for selected rows only  

        linevo.setRangeSize(-1);
        Row[] rows = linevo.getFilteredRows("SelectedFlag", "Y");
        System.out.println(getName().getClass() + 
                           ".onhandQuantityIR.totalrows         = > : " + 
                           String.valueOf(rows.length));
        for (int i = 0; i < rows.length; i++) {
            RequisitionLineVORowImpl linerow = 
                (RequisitionLineVORowImpl)rows[i];
            if (linerow != null) {
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.linerow()               = > : " + 
                                   String.valueOf(linerow));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getUniformSetLineId()   = > : " + 
                                   String.valueOf(linerow.getUniformSetLineId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getCategoryId()   = > : " + 
                                   String.valueOf(linerow.getCategoryId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getInventoryItemId()   = > : " + 
                                   String.valueOf(linerow.getInventoryItemId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getOrganizationId()   = > : " + 
                                   String.valueOf(linerow.getOrganizationId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getBusinessGroupId()   = > : " + 
                                   String.valueOf(row1.getBusinessGroupId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getPersonId()   = > : " + 
                                   String.valueOf(row1.getPersonId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityIR.getUniformSetId()   = > : " + 
                                   String.valueOf(row1.getUniformSetId()));
                Number businessGroup = row1.getBusinessGroupId();
                Number personId = row1.getPersonId();
                Number uniformSetId = row1.getUniformSetId();
                Number uniformSetLineId = linerow.getUniformSetLineId();
                Number categoryId = linerow.getCategoryId();
                Number itemId = linerow.getInventoryItemId();
                Number organisationId = linerow.getOrganizationId();
                String requisitionType = "'" + row1.getRequisitionType() + "'";
                CallableStatement csonhandqty = 
                    trxn.createCallableStatement("BEGIN XX_UMS_EMP_ONHAND_QTY_V2_PKG.INSERT_DATA (" + 
                                                 personId + "," + 
                                                 businessGroup + "," + 
                                                 uniformSetId + "," + 
                                                 uniformSetLineId + "," + 
                                                 categoryId + "," + itemId + 
                                                 "," + organisationId + "," + 
                                                 requisitionType + 
                                                 ",:1); END;", 1);
                try {
                    ((OracleCallableStatement)csonhandqty).registerOutParameter(1, 
                                                                                12, 
                                                                                0, 
                                                                                2000);
                    csonhandqty.execute();
                    String status = csonhandqty.getString(1);
                    System.out.println(getName().getClass() + 
                                       ".onhandQuantityIR.to see what is written in procedure status   = > : " + 
                                       String.valueOf(status));
                    csonhandqty.close();
                    if (status != null)
                        throw new OAException(status, (byte)0);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (Exception sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        System.out.println(getName().getClass() + 
                           "============================= END : onhandQuantityIR : ===================================");
    }

    public void onhandQuantityForReplacement() {
        System.out.println(getName().getClass() + 
                           "============================= START : onhandQuantityForReplacement : ===================================");
        OADBTransaction trxn = (OADBTransaction)getDBTransaction();
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row1 = 
            (RequisitionHeaderVORowImpl)vo.getCurrentRow();
        RequisitionLineVOImpl linevo = 
            (RequisitionLineVOImpl)findViewObject("RequisitionLineVO1");
        linevo.reset();
        System.out.println(getName().getClass() + 
                           ".onhandQuantityForReplacement.TotalRows           = > : " + 
                           linevo.getRowCount());
        for (int i = 0; i < getVOIndex(); i++) {
            RequisitionLineVORowImpl linerow = 
                (RequisitionLineVORowImpl)linevo.getRowAtRangeIndex(i);
            if (linerow != null) {
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.linerow()               = > : " + 
                                   String.valueOf(linerow));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getUniformSetLineId()   = > : " + 
                                   String.valueOf(linerow.getUniformSetLineId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getCategoryId()         = > : " + 
                                   String.valueOf(linerow.getCategoryId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getInventoryItemId()    = > : " + 
                                   String.valueOf(linerow.getInventoryItemId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getOrganizationId()     = > : " + 
                                   String.valueOf(linerow.getOrganizationId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getBusinessGroupId()    = > : " + 
                                   String.valueOf(row1.getBusinessGroupId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getPersonId()           = > : " + 
                                   String.valueOf(row1.getPersonId()));
                System.out.println(getName().getClass() + 
                                   ".onhandQuantityForReplacement.getUniformSetId()       = > : " + 
                                   String.valueOf(row1.getUniformSetId()));
                Number businessGroup = row1.getBusinessGroupId();
                Number personId = row1.getPersonId();
                Number uniformSetId = row1.getUniformSetId();
                Number uniformSetLineId = linerow.getUniformSetLineId();
                Number categoryId = linerow.getCategoryId();
                Number itemId = linerow.getInventoryItemId();
                Number organisationId = linerow.getOrganizationId();
                Number returnitemid = linerow.getReplacementItemId();
                String requisitionType = "'" + row1.getRequisitionType() + "'";
                CallableStatement csonhandqty = 
                    trxn.createCallableStatement("BEGIN XX_UMS_EMP_ONHAND_QTY_V2_PKG.INSERT_DATA (" + 
                                                 personId + "," + 
                                                 businessGroup + "," + 
                                                 uniformSetId + "," + 
                                                 uniformSetLineId + "," + 
                                                 categoryId + "," + 
                                                 returnitemid + "," + 
                                                 organisationId + "," + 
                                                 requisitionType + 
                                                 ",:1); END;", 1);
                try {
                    ((OracleCallableStatement)csonhandqty).registerOutParameter(1, 
                                                                                12, 
                                                                                0, 
                                                                                2000);
                    csonhandqty.execute();
                    String status = csonhandqty.getString(1);
                    System.out.println("onhandQuantityForReplacement.to see what is written in procedure status:::::   =>  : " + 
                                       status);
                    csonhandqty.close();
                    if (status != null)
                        throw new OAException(status, (byte)0);
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                } catch (Exception sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        System.out.println(getName().getClass() + 
                           "============================= END : onhandQuantityForReplacement : ===================================");
    }

    public void procedureOnHandQty(ReplacementLineVORowImpl linerow1) {
        OADBTransaction trxn = (OADBTransaction)getDBTransaction();
        RequisitionHeaderVOImpl vo1 = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row12 = 
            (RequisitionHeaderVORowImpl)vo1.getCurrentRow();
        RequisitionLineVOImpl linevo1 = 
            (RequisitionLineVOImpl)findViewObject("RequisitionLineVO1");
        Number businessGroup = row12.getBusinessGroupId();
        Number personId = row12.getPersonId();
        Number uniformSetId = row12.getUniformSetId();
        Number uniformSetLineId = linerow1.getUniformSetLineId();
        Number categoryId = linerow1.getCategoryId();
        Number itemid = linerow1.getInventoryItemId();
        System.out.println("business group----: " + businessGroup);
        System.out.println("personId----: " + personId);
        System.out.println("uniformSetId----: " + uniformSetId);
        System.out.println("uniformSetLineIdp----: " + uniformSetLineId);
        System.out.println("categoryId----: " + categoryId);
        OracleCallableStatement csOnhandQuantity = 
            (OracleCallableStatement)trxn.createCallableStatement("BEGIN XX_UMS_ONHAND_QTY_V2_PROC (" + 
                                                                  personId + 
                                                                  "," + 
                                                                  businessGroup + 
                                                                  "," + 
                                                                  uniformSetId + 
                                                                  "," + 
                                                                  uniformSetLineId + 
                                                                  "," + 
                                                                  categoryId + 
                                                                  "," + 
                                                                  itemid + 
                                                                  ",:1,:2); END;", 
                                                                  1);
        try {
            csOnhandQuantity.registerOutParameter(1, 4, 0);
            csOnhandQuantity.registerOutParameter(2, 12, 0, 2000);
            csOnhandQuantity.execute();
            int onHandQty = csOnhandQuantity.getInt(1);
            System.out.println("onhandqty---- " + onHandQty);
            String Status = csOnhandQuantity.getString(2);
            System.out.println("status---" + Status);
            linerow1.setSumXueoOnhandQty(new Number(onHandQty));
            csOnhandQuantity.close();
            if (Status != null)
                throw new OAException(Status, (byte)0);
        } catch (SQLException sqle) {
            throw new OAException(sqle.getMessage());
        }
    }

    public void setMessage(String message) {
        this.strMessage = message;
    }

    public SourceRequisitionLineVOImpl getSourceRequisitionLineVO1() {
        return (SourceRequisitionLineVOImpl)findViewObject("SourceRequisitionLineVO1");
    }

    public void workflowforreplacement() {
        RequisitionHeaderVOImpl hvo = getRequisitionHeaderVO1();
        hvo.invokeMethod("submitworkflowReplacement");
    }

    public void workFlowForEntitlementRequisition() {
        RequisitionHeaderVOImpl hvo = getRequisitionHeaderVO1();
        hvo.invokeMethod("submitWorkFlowForEntitleChange");
    }

    public void workFlowForInitialRequisition() {
        RequisitionHeaderVOImpl hvo = getRequisitionHeaderVO1();
        hvo.invokeMethod("submitWorkFlowForInitRequisition");
    }

    public InitialRequisitionTypeVVOImpl getInitialRequisitionTypeVVO1() {
        return (InitialRequisitionTypeVVOImpl)findViewObject("InitialRequisitionTypeVVO1");
    }

    public void setEmployeeNumber(String strEmployeeNumber) {
        System.out.println(getClass().getName() + 
                           "========================= START : setEmployeeNumber ======================== ");
        System.out.println(getClass().getName() + 
                           " .setEmployeeNumber.strEmployeeNumber = . : " + 
                           strEmployeeNumber);
        writeDiagnostics(this, 
                         getClass().getName() + " .setEmployeeNumber.strEmployeeNumber        MR : ==== : " + 
                         strEmployeeNumber, 2);
        TrialVOImpl svo = getTrialVO1();
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, strEmployeeNumber);
        svo.executeQuery();
        RequisitionHeaderVOImpl requistionheader = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row = 
            (RequisitionHeaderVORowImpl)requistionheader.getCurrentRow();
        for (; svo.hasNext(); ) {
            TrialVORowImpl voRow = (TrialVORowImpl)svo.next();
            row.setPersonId(voRow.getPersonId());
            row.setUniformSetName(voRow.getUniformSetName());
            row.setApprover(voRow.getApproverName());
            System.out.println(getClass().getName() + 
                               ".setEmployeeNumber.PersonId       = > : " + 
                               voRow.getPersonId());
            System.out.println(getClass().getName() + 
                               ".setEmployeeNumber.UnifornSetName = > : " + 
                               voRow.getUniformSetName());
            System.out.println(getClass().getName() + 
                               ".setEmployeeNumber.Approver =     = > : " + 
                               voRow.getApproverName());
            writeDiagnostics(this, 
                             getClass().getName() + ".setEmployeeNumber.PersonId          MR : ==== : " + 
                             voRow.getPersonId(), 2);
            writeDiagnostics(this, 
                             getClass().getName() + ".setEmployeeNumber.uniformSetname    MR : ==== : " + 
                             voRow.getUniformSetName(), 2);
            writeDiagnostics(this, 
                             getClass().getName() + ".setEmployeeNumber.Approver          MR : ==== : " + 
                             voRow.getApproverName(), 2);
        }
        System.out.println(getClass().getName() + 
                           "========================= END : setEmployeeNumber ======================== ");
        writeDiagnostics(this, 
                         getClass().getName() + ".setEmployeeNumber.END        MR : ==== : ", 
                         2);
    }

    public TrialVOImpl getTrialVO1() {
        return (TrialVOImpl)findViewObject("TrialVO1");
    }

    public void addTrialVO() {
        System.out.println(getClass().getName() + 
                           "========================= START : addTrialVO ======================== ");
        writeDiagnostics(this, 
                         getClass().getName() + ".addTrialVO.START                 MR : ==== : ", 
                         2);
        TrialVOImpl trialVOImpl = getTrialVO1();
        if (trialVOImpl.isPreparedForExecution())
            trialVOImpl.executeQuery();
        Row row = trialVOImpl.createRow();
        trialVOImpl.insertRow(row);
        writeDiagnostics(this, 
                         getClass().getName() + ".addTrialVO.TrialVo.getQuery        MR : ==== : " + 
                         trialVOImpl.getQuery(), 2);
        writeDiagnostics(this, 
                         getClass().getName() + ".addTrialVO.END                     MR : ==== : ", 
                         2);
        System.out.println(getClass().getName() + 
                           "========================= END : addTrialVO ======================== ");
    }

    public RequisitionLineTableVOImpl getRequisitionLineTableVO1() {
        return (RequisitionLineTableVOImpl)findViewObject("RequisitionLineTableVO1");
    }

    public void initForReplacementLineVO(String uniformsetid, String personid, 
                                         String bgid) {
        System.out.println(getClass().getName() + 
                           "========================= START : <initForReplacementLineVO> ================================= ");
        SourceReplacementRequisitionVOImpl svo = 
            getSourceReplacementRequisitionVO1();
        svo.setWhereClause("Uniformsetid = :1 and Personid = :2 and BusinessgroupId = :3");
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, uniformsetid);
        svo.setWhereClauseParam(1, personid);
        svo.setWhereClauseParam(2, bgid);
        svo.executeQuery();
        ReplacementLineVOImpl vo = getReplacementLineVO1();
        vo.setMaxFetchSize(0);
        for (int i = svo.getRowCount(); svo.hasNext(); i--) {
            SourceReplacementRequisitionVORowImpl svoRow = 
                (SourceReplacementRequisitionVORowImpl)svo.next();
            System.out.println(getClass().getName() + 
                               "initForReplacementLineVO.calling.insert_or_create = > :  " + 
                               String.valueOf(svoRow));
            ReplacementLineVORowImpl row = 
                (ReplacementLineVORowImpl)vo.createRow();
            row.setItemCategory(svoRow.getItemCategory());
            row.setEntitledQty(svoRow.getEntitledQty());
            row.setInventoryItemId(svoRow.getItemid());
            row.setItemcode(svoRow.getItemcode());
            row.setItemdescription(svoRow.getItemdescription());
            row.setUom(svoRow.getUom());
            row.setRenewelQty(svoRow.getRenewelQty());
            row.setLifeExpectancy(svoRow.getLifeExpectancy());
            row.setLineNumber(new Number(i));
            row.setUniformSetLineId(svoRow.getUniformSetLineId());
            row.setCategoryId(svoRow.getCategoryId());
            row.setOrganizationId(svoRow.getOrganizationId());
            row.setUniformlimitedquantity(svoRow.getUniformlimitedqty());
            procedureOnHandQty(row);
            vo.insertRow((Row)row);
        }
    }

    public String getMessage() {
        return this.strMessage;
    }

    public void initializeRequisitionType() {
        this.requisitionType = "";
    }

    public void setRequisitionType(String strRequisitionType) {
        this.requisitionType = strRequisitionType;
    }

    public String getRequisitionType() {
        return this.requisitionType;
    }

    public ReplacementLineVOImpl getReplacementLineVO1() {
        return (ReplacementLineVOImpl)findViewObject("ReplacementLineVO1");
    }

    public boolean validateAllLines() {
        boolean bAllLines = false;
        Row[] row = getReplacementLineVO1().getAllRowsInRange();
        System.out.println(getClass().getName() + 
                           "=============================== START :getReplacementLineVO1.validateAllLines ======================== ");
        System.out.println(getClass().getName() + 
                           " ReplacementLineVO1.RowLength                                     = > : " + 
                           String.valueOf(row.length));
        int count = 0;
        while (count < row.length) {
            ReplacementLineVORowImpl replacementLineVORowImpl = 
                (ReplacementLineVORowImpl)row[count];
            System.out.println(getClass().getName() + 
                               " validateAllLines.replacementlineVO.replacementLineVORowImpl             = > : " + 
                               String.valueOf(row.length));
            System.out.println(getClass().getName() + 
                               " validateAllLines.replacementlineVO.gettempForItemNo                      = > :  " + 
                               replacementLineVORowImpl.gettempForItemNo());
            System.out.println(getClass().getName() + 
                               " validateAllLines.replacementlineVO.gettempForSelection                   = > :  " + 
                               replacementLineVORowImpl.gettempForSelection());
            String strtempForSelection = 
                replacementLineVORowImpl.gettempForSelection();
            System.out.println(getClass().getName() + 
                               " validateAllLines.replacementlineVO.strtempForSelection                   = > :  " + 
                               String.valueOf(strtempForSelection));
            if (strtempForSelection == null || 
                (strtempForSelection != null && strtempForSelection.equals("N"))) {
                count++;
                continue;
            }
            if (strtempForSelection.equalsIgnoreCase("Y")) {
                bAllLines = true;
                break;
            }
            bAllLines = true;
        }
        System.out.println(getClass().getName() + 
                           "=============================== : END : getReplacementLineVO1.validateAllLines ======================== ");
        return bAllLines;
    }

    public boolean validateAllLinesEcr() {
        boolean bAllLines = false;
        Row[] row = getEntitlementChangeReqLineVO1().getAllRowsInRange();
        System.out.println(getName().getClass() + 
                           " ====================================== : START : entitlementrow ====================================== ");
        System.out.println(getName().getClass() + 
                           ".validateAllLinesEcr.validateAllLinesEcr          => : " + 
                           String.valueOf(row.length));
        for (int count = 0; count < row.length; ) {
            EntitlementChangeReqLineVORowImpl entitlementrow = 
                (EntitlementChangeReqLineVORowImpl)row[count];
            System.out.println(getName().getClass() + 
                               ".validateAllLinesEcr.entitlementrow            => : " + 
                               String.valueOf(entitlementrow));
            String strtempForSelection = 
                entitlementrow.getECRTempForSelection();
            System.out.println(" strtempForSelection " + strtempForSelection);
            if (strtempForSelection == null || 
                (strtempForSelection != null && strtempForSelection.equals("N"))) {
                count++;
                continue;
            }
            bAllLines = true;
        }
        System.out.println(getName().getClass() + 
                           " ================================== : END : entitlementrow================================== ");
        return bAllLines;
    }

    public boolean validateItemCode() {
        System.out.println(getName().getClass() + 
                           " ================================== : START : validateItemCode ============================= ");
        boolean bLineItem = false;
        Row[] row = getReplacementLineVO1().getAllRowsInRange();
        System.out.println(getName().getClass() + 
                           ".validateItemCode                             => : " + 
                           String.valueOf(row.length));
        for (int count = 0; count < row.length; count++) {
            ReplacementLineVORowImpl replacementLineVORowImpl = 
                (ReplacementLineVORowImpl)row[count];
            System.out.println(getName().getClass() + 
                               ".validateItemCode.replacementLineVORowImpl  = > : " + 
                               String.valueOf(replacementLineVORowImpl));
            System.out.println(getName().getClass() + 
                               ".validateItemCode.gettempForItemNo          = > : " + 
                               String.valueOf(replacementLineVORowImpl.gettempForItemNo()));
            String strtempForSelection = 
                replacementLineVORowImpl.gettempForSelection();
            System.out.println(getName().getClass() + 
                               ".validateItemCode.strtempForSelection       = > : " + 
                               String.valueOf(strtempForSelection));
            boolean bLineItems = 
                !(strtempForSelection != null && (strtempForSelection == 
                                                  null || 
                                                  !strtempForSelection.equals("N")));
            if (!bLineItems) {
                Number itemcode = 
                    replacementLineVORowImpl.getInventoryItemId();
                Number numReturnItemId = 
                    replacementLineVORowImpl.getReplacementItemId();
                Number damageQty = replacementLineVORowImpl.getDamageQty();
                Number chargableQty = 
                    replacementLineVORowImpl.getChargableQty();
                Number returnedQty = replacementLineVORowImpl.getReturnedQty();
                String reasonCode = replacementLineVORowImpl.getReasonCode();
                String remarks = replacementLineVORowImpl.getRemarks();
                Number qtywithemp = 
                    replacementLineVORowImpl.getSumXueoOnhandQty();
                Date needByDate = replacementLineVORowImpl.getNeedByDate();
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.itemcode              = > : " + 
                                   String.valueOf(itemcode));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.numReturnItemId       = > : " + 
                                   String.valueOf(numReturnItemId));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.damageQty             = > : " + 
                                   String.valueOf(damageQty));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.chargableQty          = > : " + 
                                   String.valueOf(chargableQty));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.returnedQty           = > : " + 
                                   String.valueOf(returnedQty));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.reasonCode            = > : " + 
                                   String.valueOf(reasonCode));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.remarks               = > : " + 
                                   String.valueOf(remarks));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.qtywithemp            = > : " + 
                                   String.valueOf(qtywithemp));
                System.out.println(getName().getClass() + 
                                   ".validateItemCode.needByDate            = > : " + 
                                   String.valueOf(needByDate));
                if (damageQty == null) {
                    this.strMessage = 
                            "Please enter Replacement Quantity for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.gettempForItemNo    = > : " + 
                                       replacementLineVORowImpl.gettempForItemNo());
                    bLineItem = true;
                    break;
                }
                if (damageQty.intValue() > qtywithemp.intValue()) {
                    this.strMessage = 
                            "Replacement Quantity should not be greater than Quantity with Employee for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.damageQty.GR.qtywithemp    = > : " + 
                                       qtywithemp);
                    bLineItem = true;
                    break;
                }
                if (damageQty.intValue() <= 0) {
                    this.strMessage = 
                            "Please enter positive value as  Replacement Quantity for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.damageQty.LS.0    = > : " + 
                                       damageQty);
                    bLineItem = true;
                    break;
                }
                if (chargableQty == null) {
                    this.strMessage = 
                            "Please enter Chargeable Quantity for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.chargableQty.NULL    = > : " + 
                                       chargableQty);
                    bLineItem = true;
                    break;
                }
                if (chargableQty.intValue() < 0) {
                    this.strMessage = 
                            "Please enter positive value as  chargeable Quantity for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.chargableQty.LS.0    = > : " + 
                                       chargableQty);
                    bLineItem = true;
                    break;
                }
                if (chargableQty.intValue() > damageQty.intValue()) {
                    this.strMessage = 
                            "Chargeable Quantity should not be greater than the Replacement Quantity for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.chargableQty.GR.damageQty    = > : " + 
                                       chargableQty);
                    bLineItem = true;
                    break;
                }
                if (reasonCode == null) {
                    this.strMessage = 
                            "Please enter Reason Code for the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.reasonCode.NULL    = > : " + 
                                       reasonCode);
                    bLineItem = true;
                    break;
                }
                if (numReturnItemId == null) {
                    this.strMessage = 
                            "Please select item from the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.numReturnItemId.NULL    = > : " + 
                                       numReturnItemId);
                    bLineItem = true;
                    break;
                }
                if (needByDate != null) {
                    //          Date dateNeedByDate = needByDate.dateValue();
                    boolean bValidDate = validateNeedByDate(needByDate);
                    if (bValidDate) {
                        this.strMessage = 
                                "Need by date should not be less than system date at the line level" + 
                                (count + 1);
                        System.out.println(getName().getClass() + 
                                           ".validateItemCode.dateNeedByDate.NOTNULL    = > : " + 
                                           needByDate.stringValue());
                        bLineItem = true;
                        break;
                    }
                }
                if (remarks == null) {
                    this.strMessage = 
                            "Please Note down the remarks at the line level " + 
                            (count + 1);
                    System.out.println(getName().getClass() + 
                                       ".validateItemCode.remarks.NOTNULL    = > : " + 
                                       remarks);
                    bLineItem = true;
                    break;
                }
            }
        }
        System.out.println(getName().getClass() + 
                           " ================================== END validateItemCode =============================");
        return bLineItem;
    }

    public boolean validateEmployeeSex(String strEmployeeNumber) {
        writeDiagnostics(this, 
                         getClass().getName() + ".validateEmployeeSex.START        MR : ==== : ", 
                         2);
        MaternityVOImpl svo = getMaternityVO1();
        String strSex = "";
        boolean bSex = false;
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, strEmployeeNumber);
        svo.executeQuery();
        MaternityVOImpl requistionheader = getMaternityVO1();
        MaternityVORowImpl row = 
            (MaternityVORowImpl)requistionheader.getCurrentRow();
        for (; svo.hasNext(); ) {
            MaternityVORowImpl voRow = (MaternityVORowImpl)svo.next();
            strSex = voRow.getSex();
            writeDiagnostics(this, 
                             getClass().getName() + ".validateEmployeeSex.strSex        MR : ==== : " + 
                             strSex, 2);
        }
        if (strSex != null && strSex.equals("F"))
            bSex = true;
        writeDiagnostics(this, 
                         getClass().getName() + ".validateEmployeeSex.END        MR : ==== : ", 
                         2);
        return bSex;
    }

    public MaternityVOImpl getMaternityVO1() {
        return (MaternityVOImpl)findViewObject("MaternityVO1");
    }

    public void submitRenewalRequisition() {
        boolean bReturn = false;
        RenewalLineVOImpl renewalLineVO = getRenewalLineVO1();
        renewalLineVO.reset();
        RequisitionHeaderVOImpl HeaderVO = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl Hreaderrow = 
            (RequisitionHeaderVORowImpl)HeaderVO.first();
        Number UniformSetId = Hreaderrow.getUniformSetId();
        Number numHeaderId = Hreaderrow.getRequisitionHeaderId();
        System.out.println("\n Inside submitReplacementRequisition calling for insert & numHeaderId : HeaderVO rowcount ::: " + 
                           numHeaderId + HeaderVO.getRowCount());
        System.out.println("\n Inside RowCount : " + 
                           renewalLineVO.getRowCount());
        int nRowCount = renewalLineVO.getRowCount();
        setVOIndex(0);
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        int i = 0;
        for (i = 0; i < nRowCount; i++) {
            RenewalLineVORowImpl renewalLineVORow = 
                (RenewalLineVORowImpl)renewalLineVO.getRowAtRangeIndex(i);
            if (renewalLineVORow != null) {
                String strrenewaltempForSelection = 
                    renewalLineVORow.getRenewalTempForSelection();
                System.out.println(" strrenewaltempForSelection " + 
                                   strrenewaltempForSelection);
                if (strrenewaltempForSelection != null && 
                    (strrenewaltempForSelection == null || 
                     !strrenewaltempForSelection.equals("N"))) {
                    boolean bRenewalCheck = renewalCheck(renewalLineVORow);
                    if (!bRenewalCheck) {
                        bReturn = true;
                        throw new OAException(" Your maximum limited quantity is reached at line item : " + 
                                              (i + 1));
                    }
                    int nHeaderId = 0;
                    System.out.println("Inside submitRenewalRequisition calling for insert & numHeaderId : " + 
                                       numHeaderId);
                    System.out.println("Inside submitRenewalRequisition calling for insert & create : " + 
                                       nHeaderId);
                    RequisitionLineVORowImpl row = 
                        (RequisitionLineVORowImpl)vo.createRow();
                    row.setItemCategory(renewalLineVORow.getItemCategory());
                    row.setRenewelQty(renewalLineVORow.getRenewelQty());
                    row.setEligibleRenewalQty(renewalLineVORow.getEligibleRenewalQty());
                    row.setLifeExpectancy(renewalLineVORow.getLifeExpectancy());
                    row.setInventoryItemId(renewalLineVORow.getInventoryItemId());
                    row.setNeedByDate(renewalLineVORow.getNeedByDate());
                    row.setRemarks(renewalLineVORow.getRemarks());
                    row.setRequestedQty(renewalLineVORow.getEligibleRenewalQty());
                    int p = i + 1;
                    row.setLineNumber(new Number(p));
                    row.setUniformSetLineId(renewalLineVORow.getUniformSetLineId());
                    row.setCategoryId(renewalLineVORow.getCategoryId());
                    row.setOrganizationId(renewalLineVORow.getOrganizationId());
                    row.setRequisitionHeaderId(numHeaderId);
                    row.setUniformsetid(UniformSetId);
                    System.out.println("Outside submitRenewalRequisition calling for insert & numHeaderId : " + 
                                       numHeaderId);
                    vo.insertRow((Row)row);
                }
            }
        }
        setVOIndex(i);
        System.out.println("Inside submitReplacementRequisition calling for insert &  vo.getRowCount(): " + 
                           vo.getRowCount());
        System.out.println("Inside submitReplacementRequisition calling for insert &  vo.getFetchedRowCount(): " + 
                           vo.getFetchedRowCount());
        System.out.println("Inside submitReplacementRequisition calling for insert &  vo.getFetchSize(): " + 
                           vo.getFetchSize());
        setRequisitionStatus();
        getTransaction().commit();
    }

    public RenewalLineVOImpl getRenewalLineVO1() {
        return (RenewalLineVOImpl)findViewObject("RenewalLineVO1");
    }

    public SourceRequisitionInitialRequisitionVOImpl getSourceRequisitionInitialRequisitionVO1() {
        return (SourceRequisitionInitialRequisitionVOImpl)findViewObject("SourceRequisitionInitialRequisitionVO1");
    }

    public void initForInitialReqLineVO(String uniformSetId) {
        System.out.println(getName().getClass() + 
                           " ================================== START : < initForInitialReqLineVO > =============================");
        writeDiagnostics(this, 
                         getClass().getName() + ".initForInitialReqLineVO.START               MR : ==== : ", 
                         2);
        writeDiagnostics(this, 
                         getClass().getName() + ".initForInitialReqLineVO.uniformSetId        MR : ==== : " + 
                         uniformSetId, 2);
        SourceRequisitionInitialRequisitionVOImpl svo = 
            getSourceRequisitionInitialRequisitionVO1();
        svo.setWhereClause("Uniformsetid = :1");
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, uniformSetId);
        svo.executeQuery();
        System.out.println("\n This is  uniformsetid :" + uniformSetId);
        writeDiagnostics(this, 
                         getClass().getName() + ".initForInitialReqLineVO.getRowCount        MR : ==== : " + 
                         svo.getRowCount(), 2);
        System.out.println("\n getRowCount :" + svo.getRowCount());
        System.out.println("\n getQuery :" + svo.getQuery());
        writeDiagnostics(this, 
                         getClass().getName() + ".initForInitialReqLineVO.getRowCount        MR : ==== : " + 
                         svo.getQuery(), 2);
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        vo.setMaxFetchSize(0);
        for (int i = svo.getRowCount(); svo.hasNext(); i--) {
            SourceRequisitionInitialRequisitionVORowImpl svoRow = 
                (SourceRequisitionInitialRequisitionVORowImpl)svo.next();
            writeDiagnostics(this, 
                             getClass().getName() + ".initForInitialReqLineVO.insert and create        MR : ==== : " + 
                             String.valueOf(svoRow), 2);
            System.out.println("i am calling for insert & create : " + svoRow);
            RequisitionLineVORowImpl row = 
                (RequisitionLineVORowImpl)vo.createRow();
            row.setItemCategory(svoRow.getItemCategory());
            row.setEntitledQty(svoRow.getEntitledQty());
            String strType = getRequisitionType();
            writeDiagnostics(this, 
                             getClass().getName() + ".initForInitialReqLineVO.strType        MR : ==== : " + 
                             strType, 2);
            if ((((strType != null) ? 1 : 0) & 
                 ((!strType.equals("IR") && !strType.equals("ECR")) ? 0 : 
                  1)) != 0)
                row.setRequestedQty(svoRow.getEntitledQty());
            row.setRenewelQty(svoRow.getRenewelQty());
            row.setEligibleRenewalQty(svoRow.getEligibleRenewalQty());
            row.setLifeExpectancy(svoRow.getLifeExpectancy());
            row.setLineNumber(new Number(i));
            row.setUniformSetLineId(svoRow.getUniformSetLineId());
            row.setCategoryId(svoRow.getCategoryId());
            row.setOrganizationId(svoRow.getOrganizationId());
            vo.insertRow((Row)row);
        }
        System.out.println(getName().getClass() + 
                           " ================================== END : < initForInitialReqLineVO > =============================");
        writeDiagnostics(this, 
                         getClass().getName() + ".initForInitialReqLineVO.END        MR : ==== : ", 
                         2);
    }

    public void initForRenewalReqLineVO(String uniformSetId) {
        System.out.println("i am in initForReqLineVO::AM ");
        System.out.println(getName().getClass() + 
                           " ================================== START : < initForRenewalReqLineVO > =============================");
        RequisitionHeaderVOImpl HeaderVO = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl Hreaderrow = 
            (RequisitionHeaderVORowImpl)HeaderVO.getCurrentRow();
        Number UniformSetId = Hreaderrow.getUniformSetId();
        Number numPersonId = Hreaderrow.getPersonId();
        String strRequisitionType = Hreaderrow.getRequisitionType();
        Number numBusinessGroupId = Hreaderrow.getBusinessGroupId();
        System.out.println(" initForRenewalReqLineVO.AM.UniformSetId getRequisitionHeaderId     => : " + 
                           Hreaderrow.getRequisitionHeaderId());
        System.out.println(" initForRenewalReqLineVOM.AM.UniformSetId getRequisitionHeaderId    => :" + 
                           Hreaderrow.getRequisitionHeaderId());
        System.out.println(" initForRenewalReqLineVOM.AM.UniformSetId                           => : " + 
                           UniformSetId.intValue());
        System.out.println(" initForRenewalReqLineVOM.AM.numPersonId                            => : " + 
                           numPersonId.intValue());
        System.out.println(" initForRenewalReqLineVOM.AM.strRequisitionType                     => : " + 
                           strRequisitionType);
        System.out.println(" initForRenewalReqLineVOM.AM.numBusinessGroupId                   => : " + 
                           numBusinessGroupId.intValue());
        SourceRenewelRequisitionVOImpl svo = getSourceRenewelRequisitionVO1();
        svo.setWhereClause("Uniformsetid = :1");
        svo.setWhereClauseParams(null);
        svo.setWhereClauseParam(0, uniformSetId);
        svo.executeQuery();
        System.out.println("initForRenewalReqLineVOM.AM.niformsetid            => : " + 
                           uniformSetId);
        System.out.println("initForRenewalReqLineVOM.AM.getRowCount            => : " + 
                           svo.getRowCount());
        System.out.println("initForRenewalReqLineVOM.AM.getQuery               => : " + 
                           svo.getQuery());
        RenewalLineVOImpl vo = getRenewalLineVO1();
        vo.setMaxFetchSize(0);
        int i = svo.getRowCount();
        int iFiterCount = 0;
        while (svo.hasNext()) {
            SourceRenewelRequisitionVORowImpl svoRow = 
                (SourceRenewelRequisitionVORowImpl)svo.next();
            System.out.println("initForRenewalReqLineVOM.AM.insert_create               => : " + 
                               svoRow);
            boolean bRenevable = false;
            bRenevable = filterRenewalLines(svoRow);
            System.out.println("initForRenewalReqLineVOM.AM.renewable               => : " + 
                               bRenevable);
            if (!bRenevable) {
                RenewalLineVORowImpl row = 
                    (RenewalLineVORowImpl)vo.createRow();
                row.setItemCategory(svoRow.getItemCategory());
                row.setEntitledQty(svoRow.getEntitledQty());
                String strType = getRequisitionType();
                row.setRenewelQty(svoRow.getRenewelQty());
                row.setEligibleRenewalQty(svoRow.getEligibleRenewalQty());
                row.setLifeExpectancy(svoRow.getLifeExpectancy());
                row.setLineNumber(new Number(i));
                row.setUniformSetLineId(svoRow.getUniformSetLineId());
                row.setCategoryId(svoRow.getCategoryId());
                row.setOrganizationId(svoRow.getOrganizationId());
                vo.insertRow((Row)row);
                i--;
                iFiterCount++;
            }
        }
        String strRenewalMessage = 
            "No line items are eligible for Renewal requisition.The reasons are either of the following1.No Transaction Exists for Current Line 2.Error while retrieving life expectancy for uniform set line id 3.Not in Eligible Period ";
        if (iFiterCount == 0)
            throw new OAException(strRenewalMessage, (byte)0);
        System.out.println(getName().getClass() + 
                           " ================================== END : < initForRenewalReqLineVO > =============================");
    }

    public void setRequisitionStatus() {
        RequisitionHeaderVOImpl HeaderVO = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row = 
            (RequisitionHeaderVORowImpl)HeaderVO.getCurrentRow();
        writeDiagnostics(this, 
                         getClass().getName() + ".setRequisitionStatus.START        MR : ==== : ", 
                         2);
        writeDiagnostics(this, 
                         getClass().getName() + ".setRequisitionStatus.RequisitionType        MR : ==== : " + 
                         row.getRequisitionType(), 2);
        if (row.getRequisitionType().startsWith("IR") || 
            row.getRequisitionType().startsWith("RR")) {
            writeDiagnostics(this, 
                             getClass().getName() + ".setRequisitionStatus.RequisitionType.IR.RR        MR : ==== : " + 
                             row.getRequisitionType(), 2);
            row.setRequisitionStatus("Submitted");
        } else if (row.getRequisitionType().startsWith("ECR") || 
                   row.getRequisitionType().startsWith("RPR")) {
            writeDiagnostics(this, 
                             getClass().getName() + ".setRequisitionStatus.RequisitionType.ECR.RPR.       MR : ==== : " + 
                             row.getRequisitionType(), 2);
            row.setRequisitionStatus("In process");
            writeDiagnostics(this, 
                             getClass().getName() + ".setRequisitionStatus.getRequisitionStatus       MR : ==== : " + 
                             row.getRequisitionStatus(), 2);
        }
        writeDiagnostics(this, 
                         getClass().getName() + ".setRequisitionStatus.END       MR : ==== : ", 
                         2);
    }

    public boolean renewalCheck(RenewalLineVORowImpl rLineRow) {
        boolean brenewalCheck = false;
        System.out.println(getName().getClass() + 
                           " ================================== START : < renewalCheck > =============================");
        OADBTransaction trxn = (OADBTransaction)getDBTransaction();
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row = 
            (RequisitionHeaderVORowImpl)vo.getCurrentRow();
        Number personId = row.getPersonId();
        Number businessGroupId = row.getBusinessGroupId();
        Number uniformSetId = row.getUniformSetId();
        Number uniformSetLineId = rLineRow.getUniformSetLineId();
        Number categoryId = rLineRow.getCategoryId();
        Number requestedQuantity = rLineRow.getRenewelQty();
        System.out.println(getName().getClass() + 
                           ".renewalCheck.personId                => :" + 
                           String.valueOf(personId.intValue()));
        System.out.println(getName().getClass() + 
                           ".renewalCheck.businessGroupId         => :" + 
                           String.valueOf(businessGroupId.intValue()));
        System.out.println(getName().getClass() + 
                           ".renewalCheck.uniformSetId            => :" + 
                           String.valueOf(uniformSetId.intValue()));
        System.out.println(getName().getClass() + 
                           ".renewalCheck.uniformSetLineId        => :" + 
                           String.valueOf(uniformSetLineId.intValue()));
        System.out.println(getName().getClass() + 
                           ".renewalCheck.categoryId              => :" + 
                           String.valueOf(categoryId.intValue()));
        if (requestedQuantity != null)
            System.out.println(getName().getClass() + 
                               ".renewalCheck.requestedQuantity              => :" + 
                               String.valueOf(requestedQuantity.intValue()));
        CallableStatement csRenewalCheck = 
            trxn.createCallableStatement("BEGIN XX_UMS_RENEWAL_CHECK_V2_PROC(" + 
                                         personId + "," + businessGroupId + 
                                         "," + uniformSetId + "," + 
                                         uniformSetLineId + "," + categoryId + 
                                         "," + requestedQuantity + 
                                         ",:1,:2); END;", 1);
        try {
            ((OracleCallableStatement)csRenewalCheck).registerOutParameter(1, 
                                                                           12, 
                                                                           0, 
                                                                           2000);
            ((OracleCallableStatement)csRenewalCheck).registerOutParameter(2, 
                                                                           12, 
                                                                           0, 
                                                                           2000);
            csRenewalCheck.execute();
            String errorMsg = csRenewalCheck.getString(1);
            String status = csRenewalCheck.getString(2);
            System.out.println(getName().getClass() + 
                               ".renewalCheck.status              => :" + 
                               String.valueOf(status));
            if (errorMsg != null)
                throw new OAException(errorMsg);
            csRenewalCheck.close();
            if (status.equalsIgnoreCase("Yes"))
                brenewalCheck = true;
        } catch (SQLException sqle) {
            throw new OAException(sqle.getMessage());
        }
        return brenewalCheck;
    }

    public boolean validateNeedByDate(Date strDate) {
        boolean bValidDate = false;
        java.util.Date needByDate = strDate.dateValue();
        long nstartDate = needByDate.getTime();
        OADBTransaction txn = (OADBTransaction)getTransaction();
        java.util.Date strSystemDate = txn.getCurrentDBDate().dateValue();
        long nSystemDate = strSystemDate.getTime();
        if (nstartDate < nSystemDate)
            bValidDate = true;
        return bValidDate;
    }

    public SourceRenewelRequisitionVOImpl getSourceRenewelRequisitionVO1() {
        return (SourceRenewelRequisitionVOImpl)findViewObject("SourceRenewelRequisitionVO1");
    }

    //  public boolean validatelineItemsForInitialRequisition() {
    //    writeDiagnostics(this, getClass().getName() + ".validatelineItemsForInitialRequisition.START       MR : ==== : ", 2);
    //    boolean bLineItem = false;
    //    
    //    Row[] row = getRequisitionLineVO1().getAllRowsInRange();
    //    writeDiagnostics(this, getClass().getName() + ".validatelineItemsForInitialRequisition.validateAllLines.getRowLength      MR : ==== : " + String.valueOf(row.length), 2);
    //    System.out.println(" Inside length validateAllLines " + row.length);
    //    for (int count = 0; count < row.length; count++) {
    //      RequisitionLineVORowImpl requisitionLineVORowImpl = (RequisitionLineVORowImpl)row[count];
    //      Date needByDate = requisitionLineVORowImpl.getNeedByDate();
    //      Number itemcode = requisitionLineVORowImpl.getInventoryItemId();
    //      if (itemcode == null) {
    //        bLineItem = true;
    //        this.strMessage = "Please enter the Itemcode in the line level " + (count + 1);
    //        break;
    //      } 
    //      if (needByDate != null) {
    ////        Date dateNeedByDate = needByDate.dateValue();
    //        boolean bValidDate = validateNeedByDate(needByDate);
    //        if (bValidDate) {
    //          this.strMessage = "Need by date should not be less than system date at the line level" + (count + 1);
    //          bLineItem = true;
    //          break;
    //        } 
    //      } 
    //    } 
    //    writeDiagnostics(this, getClass().getName() + ".validatelineItemsForInitialRequisition.END      MR : ==== : ", 2);
    //    return bLineItem;
    //  }

    public boolean validatelineItemsForInitialRequisition() {
        writeDiagnostics(this, 
                         getClass().getName() + ".validatelineItemsForInitialRequisition.START       MR : ==== : ", 
                         2);
        boolean bLineItem = false;
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        vo.setRangeSize(-1);
        Row[] row = vo.getFilteredRows("SelectedFlag", "Y");
        writeDiagnostics(this, 
                         getClass().getName() + ".validatelineItemsForInitialRequisition.validateAllLines.getRowLength      MR : ==== : " + 
                         String.valueOf(row.length), 2);
        System.out.println(" Inside length validateAllLines " + row.length);
        if (row.length <= 0) {
            throw new OAException("XXKQ", "XXKQ_UMS_NO_ITEMS_SELECTED");
        }
        ArrayList errorDetail = new ArrayList();
        for (int count = 0; count < row.length; count++) {
            RequisitionLineVORowImpl requisitionLineVORowImpl = 
                (RequisitionLineVORowImpl)row[count];
            oracle.jbo.domain.Date needByDate = 
                requisitionLineVORowImpl.getNeedByDate();
            Number lineNumber = requisitionLineVORowImpl.getLineNumber();
            MessageToken[] messageToken = 
            { new MessageToken("LINE_NUMBER", lineNumber.stringValue()) };
            Number itemcode = requisitionLineVORowImpl.getInventoryItemId();

            if (itemcode == null) {
                bLineItem = true;
                errorDetail.add(new OAException("XXKQ", 
                                                "XXKQ_UMS_ITEM_CODE_REQUIRED", 
                                                messageToken));

            }
            if (needByDate == null) {
                bLineItem = true;
                errorDetail.add(new OAException("XXKQ", 
                                                "XXKQ_UMS_NEED_BY_DATE_REQUIRED", 
                                                messageToken));

            }
            if (needByDate != null) {
                boolean bValidDate = validateNeedByDate(needByDate);
                if (bValidDate) {

                    bLineItem = true;
                    errorDetail.add(new OAException("XXKQ", 
                                                    "XXKQ_UMS_NEED_BY_DATE_ERROR", 
                                                    messageToken));

                }
            }
        }
        writeDiagnostics(this, 
                         getClass().getName() + ".validatelineItemsForInitialRequisition.END      MR : ==== : ", 
                         2);
        if (errorDetail.size() > 0) {
            OAException.raiseBundledOAException(errorDetail);

        }

        return bLineItem;
    }


    public boolean validatelineItemsForRenewalRequisition() {
        boolean bLineItem = false;
        Row[] row = getRenewalLineVO1().getAllRowsInRange();
        System.out.println(" Inside length validateAllLines " + row.length);
        for (int count = 0; count < row.length; count++) {
            RenewalLineVORowImpl renewalLineVORowImpl = 
                (RenewalLineVORowImpl)row[count];
            String strrenewaltempForSelection = 
                renewalLineVORowImpl.getRenewalTempForSelection();
            Number itemcode = renewalLineVORowImpl.getInventoryItemId();
            boolean bLineItems = 
                !(strrenewaltempForSelection != null && (strrenewaltempForSelection == 
                                                         null || 
                                                         !strrenewaltempForSelection.equals("N")));
            if (!bLineItems) {
                Date needByDate = renewalLineVORowImpl.getNeedByDate();
                if (itemcode == null) {
                    bLineItem = true;
                    this.strMessage = 
                            "Please enter the Itemcode in the line level " + 
                            (count + 1);
                    break;
                }
                if (needByDate != null) {
                    //          Date dateNeedByDate = needByDate.dateValue();
                    boolean bValidDate = validateNeedByDate(needByDate);
                    if (bValidDate) {
                        this.strMessage = 
                                "Need by date should not be less than system date at the line level" + 
                                (count + 1);
                        bLineItem = true;
                        break;
                    }
                }
            }
        }
        return bLineItem;
    }

    public boolean validatelineItemsForEntitlementChangeRequisition() {
        boolean bLineItem = false;
        Row[] row = getEntitlementChangeReqLineVO1().getAllRowsInRange();
        System.out.println(" Inside length validateAllLines " + row.length);
        for (int count = 0; count < row.length; count++) {
            EntitlementChangeReqLineVORowImpl entitlementrowline = 
                (EntitlementChangeReqLineVORowImpl)row[count];
            String strrenewaltempForSelection = 
                entitlementrowline.getECRTempForSelection();
            Number itemcode = entitlementrowline.getInventoryItemId();
            boolean bLineItems = 
                !(strrenewaltempForSelection != null && (strrenewaltempForSelection == 
                                                         null || 
                                                         !strrenewaltempForSelection.equals("N")));
            if (!bLineItems) {
                Date needByDate = entitlementrowline.getNeedByDate();
                if (itemcode == null) {
                    bLineItem = true;
                    this.strMessage = 
                            "Please enter the Itemcode in the line level " + 
                            (count + 1);
                    break;
                }
                if (needByDate != null) {
                    //          Date dateNeedByDate = needByDate.dateValue();
                    boolean bValidDate = validateNeedByDate(needByDate);
                    if (bValidDate) {
                        this.strMessage = 
                                "Need by date should not be less than system date at the line level" + 
                                (count + 1);
                        bLineItem = true;
                        break;
                    }
                }
            }
        }
        return bLineItem;
    }

    public void cancelButton() {
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row1 = 
            (RequisitionHeaderVORowImpl)vo.getCurrentRow();
        row1.setRequisitionStatus("Cancelled");
    }

    public void maternityCheck() {
        RequisitionHeaderVOImpl vo = getRequisitionHeaderVO1();
        vo.invokeMethod("maternityCheck");
    }

    public boolean filterRenewalLines(SourceRenewelRequisitionVORowImpl svoRow) {
        String status;
        boolean bFilter = false;
        System.out.println(" \n Inside  filterRenewalLines ::::  ");
        RequisitionHeaderVOImpl requisitionHeaderVOImpl = 
            getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl requisitionHeaderVORowImpl = 
            (RequisitionHeaderVORowImpl)requisitionHeaderVOImpl.getCurrentRow();
        Number numPersonID = requisitionHeaderVORowImpl.getPersonId();
        int nPersonID = numPersonID.intValue();
        Number numBusinessGroupId = 
            requisitionHeaderVORowImpl.getBusinessGroupId();
        int nBusinessGroupId = numPersonID.intValue();
        String strRequisitionType = 
            requisitionHeaderVORowImpl.getRequisitionType();
        System.out.println(" \n Inside  filterRenewalLines :::: numPersonID ::0  " + 
                           numPersonID);
        System.out.println(" \n Inside  filterRenewalLines :::: numBusinessGroupId ::0  " + 
                           numBusinessGroupId);
        System.out.println(" \n Inside  filterRenewalLines :::: strRequisitionType ::0  " + 
                           strRequisitionType);
        Number numUniformSetLineId = svoRow.getUniformSetLineId();
        int nUniformSetLineId = numUniformSetLineId.intValue();
        System.out.println(" \n Inside  filterRenewalLines :::: numUniformSetLineId ::0  " + 
                           numUniformSetLineId);
        Number numUniformSetId = svoRow.getUniformsetid();
        OADBTransaction trxn = (OADBTransaction)getDBTransaction();
        CallableStatement csrenewalrequisitionfilterlines = 
            trxn.createCallableStatement("BEGIN XX_UMS_REQ_CHECK_V2_PKG.Validate_Renewal_Req_Line_Proc(" + 
                                         numPersonID + "," + 
                                         numBusinessGroupId + "," + 
                                         numUniformSetLineId + ",:1,:2); END;", 
                                         1);
        try {
            ((OracleCallableStatement)csrenewalrequisitionfilterlines).registerOutParameter(1, 
                                                                                            12, 
                                                                                            0, 
                                                                                            2000);
            ((OracleCallableStatement)csrenewalrequisitionfilterlines).registerOutParameter(2, 
                                                                                            12, 
                                                                                            0, 
                                                                                            2000);
            csrenewalrequisitionfilterlines.execute();
            status = csrenewalrequisitionfilterlines.getString(1);
            String error = csrenewalrequisitionfilterlines.getString(2);
            System.out.println("\n status : " + status);
            System.out.println("\n error : " + error);
            csrenewalrequisitionfilterlines.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new OAException(sqle.getMessage());
        }
        if (status.equals("E"))
            bFilter = true;
        return bFilter;
    }

    public void submitRenewalCheck() {
        boolean bReturn = false;
        RenewalLineVOImpl renewalLineVO = getRenewalLineVO1();
        renewalLineVO.reset();
        RequisitionHeaderVOImpl HeaderVO = getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl Hreaderrow = 
            (RequisitionHeaderVORowImpl)HeaderVO.first();
        Number UniformSetId = Hreaderrow.getUniformSetId();
        Number numHeaderId = Hreaderrow.getRequisitionHeaderId();
        System.out.println("\n Inside submitReplacementRequisition calling for insert & numHeaderId : HeaderVO rowcount ::: " + 
                           numHeaderId + HeaderVO.getRowCount());
        System.out.println("\n Inside RowCount : " + 
                           renewalLineVO.getRowCount());
        int nRowCount = renewalLineVO.getRowCount();
        setVOIndex(0);
        RequisitionLineVOImpl vo = getRequisitionLineVO1();
        int i = 0;
        for (i = 0; i < nRowCount; i++) {
            RenewalLineVORowImpl renewalLineVORow = 
                (RenewalLineVORowImpl)renewalLineVO.getRowAtRangeIndex(i);
            if (renewalLineVORow != null) {
                String strrenewaltempForSelection = 
                    renewalLineVORow.getRenewalTempForSelection();
                System.out.println(" strrenewaltempForSelection " + 
                                   strrenewaltempForSelection);
                if (strrenewaltempForSelection != null && 
                    (strrenewaltempForSelection == null || 
                     !strrenewaltempForSelection.equals("N"))) {
                    boolean bRenewalCheck = renewalCheck(renewalLineVORow);
                    if (!bRenewalCheck) {
                        bReturn = true;
                        String ExceptionStr = 
                            "Your maximun limited quantity is reached at line item : " + 
                            (i + 1);
                        throw new OAException(ExceptionStr, (byte)0);
                    }
                }
            }
        }
    }

    public SourceReplacementRequisitionVOImpl getSourceReplacementRequisitionVO1() {
        return (SourceReplacementRequisitionVOImpl)findViewObject("SourceReplacementRequisitionVO1");
    }

    public SourceMaternityVOImpl getSourceMaternityVO1() {
        return (SourceMaternityVOImpl)findViewObject("SourceMaternityVO1");
    }

    public EntitlementChangeReqLineVOImpl getEntitlementChangeReqLineVO1() {
        return (EntitlementChangeReqLineVOImpl)findViewObject("EntitlementChangeReqLineVO1");
    }

    public static void main(String[] args) {
        launchTester("xxkq.oracle.apps.xbol.v2.employee.server", 
                     "RequisitionAMLocal");
    }
}
