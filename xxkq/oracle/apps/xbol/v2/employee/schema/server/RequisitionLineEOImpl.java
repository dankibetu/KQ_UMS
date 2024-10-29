package xxkq.oracle.apps.xbol.v2.employee.schema.server;

import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;
import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;

public class RequisitionLineEOImpl extends OAEntityImpl {
    public static final int REQUISITIONHEADERID = 0;
    public static final int REQUISITIONLINEID = 1;
    public static final int LINENUMBER = 2;
    public static final int UNIFORMSETLINEID = 3;
    public static final int INVENTORYITEMID = 4;
    public static final int ORGANIZATIONID = 5;
    public static final int REQUESTEDQTY = 6;
    public static final int DAMAGEQTY = 7;
    public static final int CHARGABLEQTY = 8;
    public static final int NEEDBYDATE = 9;
    public static final int REASONCODE = 10;
    public static final int REMARKS = 11;
    public static final int STATUS = 12;
    public static final int REPLACEMENTITEMID = 13;
    public static final int REPLACEMENTORGANIZATIONID = 14;
    public static final int TOTALISSUEDQTY = 15;
    public static final int RETURNEDQTY = 16;
    public static final int CREATEDBY = 17;
    public static final int CREATIONDATE = 18;
    public static final int LASTUPDATEDBY = 19;
    public static final int LASTUPDATEDATE = 20;
    public static final int LASTUPDATELOGIN = 21;
    public static final int ATTRIBUTE1 = 22;
    public static final int ATTRIBUTE2 = 23;
    public static final int ATTRIBUTE3 = 24;
    public static final int ATTRIBUTE4 = 25;
    public static final int ATTRIBUTE5 = 26;
    public static final int ATTRIBUTE6 = 27;
    public static final int ATTRIBUTE7 = 28;
    public static final int ATTRIBUTE8 = 29;
    public static final int ATTRIBUTE9 = 30;
    public static final int ATTRIBUTE10 = 31;
    public static final int ATTRIBUTE11 = 32;
    public static final int ATTRIBUTE12 = 33;
    public static final int ATTRIBUTE13 = 34;
    public static final int ATTRIBUTE14 = 35;
    public static final int ATTRIBUTE15 = 36;
    public static final int ATTRIBUTE16 = 37;
    public static final int ATTRIBUTE17 = 38;
    public static final int ATTRIBUTE18 = 39;
    public static final int ATTRIBUTE19 = 40;
    public static final int ATTRIBUTE20 = 41;
    public static final int REPLACEMENTISSUESTUTUS = 42;
    public static final int REPLACEMENTRECEIPTSTATUS = 43;

    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public RequisitionLineEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxkq.oracle.apps.xbol.v2.employee.schema.server.RequisitionLineEO");
        }
        return mDefinitionObject;
    }

    public void create(AttributeList attributeList) {
    super.create(attributeList);
    OADBTransaction transaction = (OADBTransaction)getDBTransaction();
    Number RequisitionLineNo = transaction.getSequenceValue("XX_UMS_REQUISITION_LINE_V2_S");
    setRequisitionLineId(RequisitionLineNo);
  }
  
  public Number getRequisitionHeaderId() {
    return (Number)getAttributeInternal(0);
  }
  
  public void setRequisitionHeaderId(Number value) {
    setAttributeInternal(0, value);
  }
  
  public Number getRequisitionLineId() {
    return (Number)getAttributeInternal(1);
  }
  
  public void setRequisitionLineId(Number value) {
    setAttributeInternal(1, value);
  }
  
  public Number getLineNumber() {
    return (Number)getAttributeInternal(2);
  }
  
  public void setLineNumber(Number value) {
    setAttributeInternal(2, value);
  }
  
  public Number getUniformSetLineId() {
    return (Number)getAttributeInternal(3);
  }
  
  public void setUniformSetLineId(Number value) {
    setAttributeInternal(3, value);
  }
  
  public Number getInventoryItemId() {
    return (Number)getAttributeInternal(4);
  }
  
  public void setInventoryItemId(Number value) {
    setAttributeInternal(4, value);
  }
  
  public Number getOrganizationId() {
    return (Number)getAttributeInternal(5);
  }
  
  public void setOrganizationId(Number value) {
    setAttributeInternal(5, value);
  }
  
  public Number getRequestedQty() {
    return (Number)getAttributeInternal(6);
  }
  
  public void setRequestedQty(Number value) {
    setAttributeInternal(6, value);
  }
  
  public Number getDamageQty() {
    return (Number)getAttributeInternal(7);
  }
  
  public void setDamageQty(Number value) {
    setAttributeInternal(7, value);
  }
  
  public Number getChargableQty() {
    return (Number)getAttributeInternal(8);
  }
  
  public void setChargableQty(Number value) {
    setAttributeInternal(8, value);
  }
  
  public Date getNeedByDate() {
    return (Date)getAttributeInternal(9);
  }
  
  public void setNeedByDate(Date value) {
    setAttributeInternal(9, value);
  }
  
  public String getReasonCode() {
    return (String)getAttributeInternal(10);
  }
  
  public void setReasonCode(String value) {
    setAttributeInternal(10, value);
  }
  
  public String getRemarks() {
    return (String)getAttributeInternal(11);
  }
  
  public void setRemarks(String value) {
    setAttributeInternal(11, value);
  }
  
  public String getStatus() {
    return (String)getAttributeInternal(12);
  }
  
  public void setStatus(String value) {
    setAttributeInternal(12, value);
  }
  
  public Number getReplacementItemId() {
    return (Number)getAttributeInternal(13);
  }
  
  public void setReplacementItemId(Number value) {
    setAttributeInternal(13, value);
  }
  
  public Number getReplacementOrganizationId() {
    return (Number)getAttributeInternal(14);
  }
  
  public void setReplacementOrganizationId(Number value) {
    setAttributeInternal(14, value);
  }
  
  public Number getTotalIssuedQty() {
    return (Number)getAttributeInternal(15);
  }
  
  public void setTotalIssuedQty(Number value) {
    setAttributeInternal(15, value);
  }
  
  public Number getReturnedQty() {
    return (Number)getAttributeInternal(16);
  }
  
  public void setReturnedQty(Number value) {
    setAttributeInternal(16, value);
  }
  
  protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case REQUISITIONHEADERID:
            return getRequisitionHeaderId();
        case REQUISITIONLINEID:
            return getRequisitionLineId();
        case LINENUMBER:
            return getLineNumber();
        case UNIFORMSETLINEID:
            return getUniformSetLineId();
        case INVENTORYITEMID:
            return getInventoryItemId();
        case ORGANIZATIONID:
            return getOrganizationId();
        case REQUESTEDQTY:
            return getRequestedQty();
        case DAMAGEQTY:
            return getDamageQty();
        case CHARGABLEQTY:
            return getChargableQty();
        case NEEDBYDATE:
            return getNeedByDate();
        case REASONCODE:
            return getReasonCode();
        case REMARKS:
            return getRemarks();
        case STATUS:
            return getStatus();
        case REPLACEMENTITEMID:
            return getReplacementItemId();
        case REPLACEMENTORGANIZATIONID:
            return getReplacementOrganizationId();
        case TOTALISSUEDQTY:
            return getTotalIssuedQty();
        case RETURNEDQTY:
            return getReturnedQty();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ATTRIBUTE5:
            return getAttribute5();
        case ATTRIBUTE6:
            return getAttribute6();
        case ATTRIBUTE7:
            return getAttribute7();
        case ATTRIBUTE8:
            return getAttribute8();
        case ATTRIBUTE9:
            return getAttribute9();
        case ATTRIBUTE10:
            return getAttribute10();
        case ATTRIBUTE11:
            return getAttribute11();
        case ATTRIBUTE12:
            return getAttribute12();
        case ATTRIBUTE13:
            return getAttribute13();
        case ATTRIBUTE14:
            return getAttribute14();
        case ATTRIBUTE15:
            return getAttribute15();
        case ATTRIBUTE16:
            return getAttribute16();
        case ATTRIBUTE17:
            return getAttribute17();
        case ATTRIBUTE18:
            return getAttribute18();
        case ATTRIBUTE19:
            return getAttribute19();
        case ATTRIBUTE20:
            return getAttribute20();
        case REPLACEMENTISSUESTUTUS:
            return getReplacementIssueStutus();
        case REPLACEMENTRECEIPTSTATUS:
            return getReplacementReceiptStatus();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }
  
  protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case REQUISITIONHEADERID:
            setRequisitionHeaderId((Number)value);
            return;
        case REQUISITIONLINEID:
            setRequisitionLineId((Number)value);
            return;
        case LINENUMBER:
            setLineNumber((Number)value);
            return;
        case UNIFORMSETLINEID:
            setUniformSetLineId((Number)value);
            return;
        case INVENTORYITEMID:
            setInventoryItemId((Number)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case REQUESTEDQTY:
            setRequestedQty((Number)value);
            return;
        case DAMAGEQTY:
            setDamageQty((Number)value);
            return;
        case CHARGABLEQTY:
            setChargableQty((Number)value);
            return;
        case NEEDBYDATE:
            setNeedByDate((Date)value);
            return;
        case REASONCODE:
            setReasonCode((String)value);
            return;
        case REMARKS:
            setRemarks((String)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case REPLACEMENTITEMID:
            setReplacementItemId((Number)value);
            return;
        case REPLACEMENTORGANIZATIONID:
            setReplacementOrganizationId((Number)value);
            return;
        case TOTALISSUEDQTY:
            setTotalIssuedQty((Number)value);
            return;
        case RETURNEDQTY:
            setReturnedQty((Number)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case ATTRIBUTE1:
            setAttribute1((String)value);
            return;
        case ATTRIBUTE2:
            setAttribute2((String)value);
            return;
        case ATTRIBUTE3:
            setAttribute3((String)value);
            return;
        case ATTRIBUTE4:
            setAttribute4((String)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        case ATTRIBUTE6:
            setAttribute6((String)value);
            return;
        case ATTRIBUTE7:
            setAttribute7((String)value);
            return;
        case ATTRIBUTE8:
            setAttribute8((String)value);
            return;
        case ATTRIBUTE9:
            setAttribute9((String)value);
            return;
        case ATTRIBUTE10:
            setAttribute10((String)value);
            return;
        case ATTRIBUTE11:
            setAttribute11((String)value);
            return;
        case ATTRIBUTE12:
            setAttribute12((String)value);
            return;
        case ATTRIBUTE13:
            setAttribute13((String)value);
            return;
        case ATTRIBUTE14:
            setAttribute14((String)value);
            return;
        case ATTRIBUTE15:
            setAttribute15((String)value);
            return;
        case ATTRIBUTE16:
            setAttribute16((String)value);
            return;
        case ATTRIBUTE17:
            setAttribute17((String)value);
            return;
        case ATTRIBUTE18:
            setAttribute18((String)value);
            return;
        case ATTRIBUTE19:
            setAttribute19((String)value);
            return;
        case ATTRIBUTE20:
            setAttribute20((String)value);
            return;
        case REPLACEMENTISSUESTUTUS:
            setReplacementIssueStutus((String)value);
            return;
        case REPLACEMENTRECEIPTSTATUS:
            setReplacementReceiptStatus((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
  
  public Number getCreatedBy() {
    return (Number)getAttributeInternal(17);
  }
  
  public void setCreatedBy(Number value) {
    setAttributeInternal(17, value);
  }
  
  public Date getCreationDate() {
    return (Date)getAttributeInternal(18);
  }
  
  public void setCreationDate(Date value) {
    setAttributeInternal(18, value);
  }
  
  public Number getLastUpdatedBy() {
    return (Number)getAttributeInternal(19);
  }
  
  public void setLastUpdatedBy(Number value) {
    setAttributeInternal(19, value);
  }
  
  public Date getLastUpdateDate() {
    return (Date)getAttributeInternal(20);
  }
  
  public void setLastUpdateDate(Date value) {
    setAttributeInternal(20, value);
  }
  
  public Number getLastUpdateLogin() {
    return (Number)getAttributeInternal(21);
  }
  
  public void setLastUpdateLogin(Number value) {
    setAttributeInternal(21, value);
  }
  
  public String getAttribute1() {
    return (String)getAttributeInternal(22);
  }
  
  public void setAttribute1(String value) {
    setAttributeInternal(22, value);
  }
  
  public String getAttribute2() {
    return (String)getAttributeInternal(23);
  }
  
  public void setAttribute2(String value) {
    setAttributeInternal(23, value);
  }
  
  public String getAttribute3() {
    return (String)getAttributeInternal(24);
  }
  
  public void setAttribute3(String value) {
    setAttributeInternal(24, value);
  }
  
  public String getAttribute4() {
    return (String)getAttributeInternal(25);
  }
  
  public void setAttribute4(String value) {
    setAttributeInternal(25, value);
  }
  
  public String getAttribute5() {
    return (String)getAttributeInternal(26);
  }
  
  public void setAttribute5(String value) {
    setAttributeInternal(26, value);
  }
  
  public String getAttribute6() {
    return (String)getAttributeInternal(27);
  }
  
  public void setAttribute6(String value) {
    setAttributeInternal(27, value);
  }
  
  public String getAttribute7() {
    return (String)getAttributeInternal(28);
  }
  
  public void setAttribute7(String value) {
    setAttributeInternal(28, value);
  }
  
  public String getAttribute8() {
    return (String)getAttributeInternal(29);
  }
  
  public void setAttribute8(String value) {
    setAttributeInternal(29, value);
  }
  
  public String getAttribute9() {
    return (String)getAttributeInternal(30);
  }
  
  public void setAttribute9(String value) {
    setAttributeInternal(30, value);
  }
  
  public String getAttribute10() {
    return (String)getAttributeInternal(31);
  }
  
  public void setAttribute10(String value) {
    setAttributeInternal(31, value);
  }
  
  public String getAttribute11() {
    return (String)getAttributeInternal(32);
  }
  
  public void setAttribute11(String value) {
    setAttributeInternal(32, value);
  }
  
  public String getAttribute12() {
    return (String)getAttributeInternal(33);
  }
  
  public void setAttribute12(String value) {
    setAttributeInternal(33, value);
  }
  
  public String getAttribute13() {
    return (String)getAttributeInternal(34);
  }
  
  public void setAttribute13(String value) {
    setAttributeInternal(34, value);
  }
  
  public String getAttribute14() {
    return (String)getAttributeInternal(35);
  }
  
  public void setAttribute14(String value) {
    setAttributeInternal(35, value);
  }
  
  public String getAttribute15() {
    return (String)getAttributeInternal(36);
  }
  
  public void setAttribute15(String value) {
    setAttributeInternal(36, value);
  }
  
  public String getAttribute16() {
    return (String)getAttributeInternal(37);
  }
  
  public void setAttribute16(String value) {
    setAttributeInternal(37, value);
  }
  
  public String getAttribute17() {
    return (String)getAttributeInternal(38);
  }
  
  public void setAttribute17(String value) {
    setAttributeInternal(38, value);
  }
  
  public String getAttribute18() {
    return (String)getAttributeInternal(39);
  }
  
  public void setAttribute18(String value) {
    setAttributeInternal(39, value);
  }
  
  public String getAttribute19() {
    return (String)getAttributeInternal(40);
  }
  
  public void setAttribute19(String value) {
    setAttributeInternal(40, value);
  }
  
  public String getAttribute20() {
    return (String)getAttributeInternal(41);
  }
  
  public void setAttribute20(String value) {
    setAttributeInternal(41, value);
  }
  
  public String getReplacementIssueStutus() {
    return (String)getAttributeInternal(42);
  }
  
  public void setReplacementIssueStutus(String value) {
    setAttributeInternal(42, value);
  }
  
  public String getReplacementReceiptStatus() {
    return (String)getAttributeInternal(43);
  }
  
  public void setReplacementReceiptStatus(String value) {
    setAttributeInternal(43, value);
  }

    /**Add Entity validation code in this method.
     */
    protected void validateEntity() {
        super.validateEntity();
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number requisitionLineId) {
        return new Key(new Object[]{requisitionLineId});
    }
}


/* Location:              D:\Oracle\JDevR12.2.14\jdevhome\jdev\myprojects\!\xxkq\oracle\apps\xbol\v2\employee\schema\server\RequisitionLineEOImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */