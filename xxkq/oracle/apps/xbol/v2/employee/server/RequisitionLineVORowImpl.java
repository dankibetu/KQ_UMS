/*     */ package xxkq.oracle.apps.xbol.v2.employee.server;
/*     */ 
/*     */ import oracle.apps.fnd.framework.server.OAViewRowImpl;
/*     */ import oracle.jbo.domain.Date;
/*     */ import oracle.jbo.domain.Number;
/*     */ import oracle.jbo.server.AttributeDefImpl;
/*     */ import xxkq.oracle.apps.xbol.v2.employee.schema.server.RequisitionLineEOImpl;
/*     */ 
/*     */ public class RequisitionLineVORowImpl extends OAViewRowImpl {
/*     */
    public static final int REQUISITIONLINEID = 0;
    public static final int REQUISITIONHEADERID = 1;
    public static final int UNIFORMSETLINEID = 2;
    public static final int LINENUMBER = 3;
    public static final int INVENTORYITEMID = 4;
    public static final int NEEDBYDATE = 5;
    public static final int REMARKS = 6;
    public static final int ITEMCATEGORY = 7;
    public static final int ENTITLEDQTY = 8;
    public static final int CATEGORYID = 9;
    public static final int UNIFORMSETID = 10;
    public static final int RENEWELQTY = 11;
    public static final int LIFEEXPECTANCY = 12;
    public static final int ELIGIBLERENEWALQTY = 13;
    public static final int REQUESTEDQTY = 14;
    public static final int DAMAGEQTY = 15;
    public static final int CHARGABLEQTY = 16;
    public static final int RETURNEDQTY = 17;
    public static final int REASONCODE = 18;
    public static final int RETURNITEMID = 19;
    public static final int ORGANIZATIONID = 20;
    public static final int ONHANDQTY = 21;
    public static final int ITEMDESCRIPTION = 22;
    public static final int UOM = 23;
    public static final int REPLACEMENTITEMID = 24;
    public static final int TEMPFORITEMNO = 25;
    public static final int REPLACEMENTITEMFORITEM = 26;
    public static final int TEMPFORSELECTION = 27;
    public static final int T1 = 28;
    public static final int T2 = 29;
    public static final int RENEWALTEMPFORSELECTION = 30;
    public static final int ECRTEMPFORSELECTION = 31;
    public static final int SELECTEDFLAG = 32;

    /**This is the default constructor (do not remove)
     */
    public RequisitionLineVORowImpl() {
    }
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */
    /*     */   
/*     */   public RequisitionLineEOImpl getRequisitionLineEO() {
/*  52 */     return (RequisitionLineEOImpl)getEntity(0);
/*     */   }
/*     */   
/*     */   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
/*  59 */
        /* 126 */
        /*     */switch (index) {
        case REQUISITIONLINEID:
            return getRequisitionLineId();
        case REQUISITIONHEADERID:
            return getRequisitionHeaderId();
        case UNIFORMSETLINEID:
            return getUniformSetLineId();
        case LINENUMBER:
            return getLineNumber();
        case INVENTORYITEMID:
            return getInventoryItemId();
        case NEEDBYDATE:
            return getNeedByDate();
        case REMARKS:
            return getRemarks();
        case ITEMCATEGORY:
            return getItemCategory();
        case ENTITLEDQTY:
            return getEntitledQty();
        case CATEGORYID:
            return getCategoryId();
        case UNIFORMSETID:
            return getUniformsetid();
        case RENEWELQTY:
            return getRenewelQty();
        case LIFEEXPECTANCY:
            return getLifeExpectancy();
        case ELIGIBLERENEWALQTY:
            return getEligibleRenewalQty();
        case REQUESTEDQTY:
            return getRequestedQty();
        case DAMAGEQTY:
            return getDamageQty();
        case CHARGABLEQTY:
            return getChargableQty();
        case RETURNEDQTY:
            return getReturnedQty();
        case REASONCODE:
            return getReasonCode();
        case RETURNITEMID:
            return getReturnItemId();
        case ORGANIZATIONID:
            return getOrganizationId();
        case ONHANDQTY:
            return getOnHandQty();
        case ITEMDESCRIPTION:
            return getItemdescription();
        case UOM:
            return getUom();
        case REPLACEMENTITEMID:
            return getReplacementItemId();
        case TEMPFORITEMNO:
            return gettempForItemNo();
        case REPLACEMENTITEMFORITEM:
            return getReplacementItemForItem();
        case TEMPFORSELECTION:
            return gettempForSelection();
        case T1:
            return gett1();
        case T2:
            return gett2();
        case RENEWALTEMPFORSELECTION:
            return getRenewalTempForSelection();
        case ECRTEMPFORSELECTION:
            return getECRTempForSelection();
        case SELECTEDFLAG:
            return getSelectedFlag();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }
/*     */   
/*     */   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
/* 133 */
        /* 232 */
        /*     */switch (index) {
        case REQUISITIONLINEID:
            setRequisitionLineId((Number)value);
            return;
        case REQUISITIONHEADERID:
            setRequisitionHeaderId((Number)value);
            return;
        case UNIFORMSETLINEID:
            setUniformSetLineId((Number)value);
            return;
        case LINENUMBER:
            setLineNumber((Number)value);
            return;
        case INVENTORYITEMID:
            setInventoryItemId((Number)value);
            return;
        case NEEDBYDATE:
            setNeedByDate((Date)value);
            return;
        case REMARKS:
            setRemarks((String)value);
            return;
        case ITEMCATEGORY:
            setItemCategory((String)value);
            return;
        case ENTITLEDQTY:
            setEntitledQty((Number)value);
            return;
        case CATEGORYID:
            setCategoryId((Number)value);
            return;
        case UNIFORMSETID:
            setUniformsetid((Number)value);
            return;
        case RENEWELQTY:
            setRenewelQty((Number)value);
            return;
        case LIFEEXPECTANCY:
            setLifeExpectancy((Number)value);
            return;
        case ELIGIBLERENEWALQTY:
            setEligibleRenewalQty((Number)value);
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
        case RETURNEDQTY:
            setReturnedQty((Number)value);
            return;
        case REASONCODE:
            setReasonCode((String)value);
            return;
        case RETURNITEMID:
            setReturnItemId((Number)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case ONHANDQTY:
            setOnHandQty((Number)value);
            return;
        case ITEMDESCRIPTION:
            setItemdescription((String)value);
            return;
        case UOM:
            setUom((String)value);
            return;
        case REPLACEMENTITEMID:
            setReplacementItemId((Number)value);
            return;
        case TEMPFORITEMNO:
            settempForItemNo((String)value);
            return;
        case REPLACEMENTITEMFORITEM:
            setReplacementItemForItem((String)value);
            return;
        case TEMPFORSELECTION:
            settempForSelection((String)value);
            return;
        case T1:
            sett1((String)value);
            return;
        case T2:
            sett2((String)value);
            return;
        case RENEWALTEMPFORSELECTION:
            setRenewalTempForSelection((String)value);
            return;
        case ECRTEMPFORSELECTION:
            setECRTempForSelection((String)value);
            return;
        case SELECTEDFLAG:
            setSelectedFlag((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
/*     */   
/*     */   public Number getRequisitionLineId() {
/* 239 */     return (Number)getAttributeInternal(0);
/*     */   }
/*     */   
/*     */   public void setRequisitionLineId(Number value) {
/* 244 */     setAttributeInternal(0, value);
/*     */   }
/*     */   
/*     */   public Number getRequisitionHeaderId() {
/* 249 */     return (Number)getAttributeInternal(1);
/*     */   }
/*     */   
/*     */   public void setRequisitionHeaderId(Number value) {
/* 254 */     setAttributeInternal(1, value);
/*     */   }
/*     */   
/*     */   public Number getUniformSetLineId() {
/* 259 */     return (Number)getAttributeInternal(2);
/*     */   }
/*     */   
/*     */   public void setUniformSetLineId(Number value) {
/* 264 */     setAttributeInternal(2, value);
/*     */   }
/*     */   
/*     */   public Number getLineNumber() {
/* 269 */     return (Number)getAttributeInternal(3);
/*     */   }
/*     */   
/*     */   public void setLineNumber(Number value) {
/* 274 */     setAttributeInternal(3, value);
/*     */   }
/*     */   
/*     */   public Number getInventoryItemId() {
/* 279 */     return (Number)getAttributeInternal(4);
/*     */   }
/*     */   
/*     */   public void setInventoryItemId(Number value) {
/* 284 */     setAttributeInternal(4, value);
/*     */   }
/*     */   
/*     */   public Date getNeedByDate() {
/* 289 */     return (Date)getAttributeInternal(5);
/*     */   }
/*     */   
/*     */   public void setNeedByDate(Date value) {
/* 294 */     setAttributeInternal(5, value);
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 299 */     return (String)getAttributeInternal(6);
/*     */   }
/*     */   
/*     */   public void setRemarks(String value) {
/* 304 */     setAttributeInternal(6, value);
/*     */   }
/*     */   
/*     */   public String getItemCategory() {
/* 309 */     return (String)getAttributeInternal(7);
/*     */   }
/*     */   
/*     */   public void setItemCategory(String value) {
/* 314 */     setAttributeInternal(7, value);
/*     */   }
/*     */   
/*     */   public Number getEntitledQty() {
/* 319 */     return (Number)getAttributeInternal(8);
/*     */   }
/*     */   
/*     */   public void setEntitledQty(Number value) {
/* 324 */     setAttributeInternal(8, value);
/*     */   }
/*     */   
/*     */   public Number getCategoryId() {
/* 329 */     return (Number)getAttributeInternal(9);
/*     */   }
/*     */   
/*     */   public void setCategoryId(Number value) {
/* 334 */     setAttributeInternal(9, value);
/*     */   }
/*     */   
/*     */   public Number getUniformsetid() {
/* 339 */     return (Number)getAttributeInternal(10);
/*     */   }
/*     */   
/*     */   public void setUniformsetid(Number value) {
/* 344 */     setAttributeInternal(10, value);
/*     */   }
/*     */   
/*     */   public Number getRenewelQty() {
/* 349 */     return (Number)getAttributeInternal(11);
/*     */   }
/*     */   
/*     */   public void setRenewelQty(Number value) {
/* 354 */     setAttributeInternal(11, value);
/*     */   }
/*     */   
/*     */   public Number getLifeExpectancy() {
/* 359 */     return (Number)getAttributeInternal(12);
/*     */   }
/*     */   
/*     */   public void setLifeExpectancy(Number value) {
/* 364 */     setAttributeInternal(12, value);
/*     */   }
/*     */   
/*     */   public Number getEligibleRenewalQty() {
/* 369 */     return (Number)getAttributeInternal(13);
/*     */   }
/*     */   
/*     */   public void setEligibleRenewalQty(Number value) {
/* 374 */     setAttributeInternal(13, value);
/*     */   }
/*     */   
/*     */   public Number getRequestedQty() {
/* 379 */     return (Number)getAttributeInternal(14);
/*     */   }
/*     */   
/*     */   public void setRequestedQty(Number value) {
/* 384 */     setAttributeInternal(14, value);
/*     */   }
/*     */   
/*     */   public Number getDamageQty() {
/* 389 */     return (Number)getAttributeInternal(15);
/*     */   }
/*     */   
/*     */   public void setDamageQty(Number value) {
/* 394 */     setAttributeInternal(15, value);
/*     */   }
/*     */   
/*     */   public Number getChargableQty() {
/* 399 */     return (Number)getAttributeInternal(16);
/*     */   }
/*     */   
/*     */   public void setChargableQty(Number value) {
/* 404 */     setAttributeInternal(16, value);
/*     */   }
/*     */   
/*     */   public Number getReturnedQty() {
/* 409 */     return (Number)getAttributeInternal(17);
/*     */   }
/*     */   
/*     */   public void setReturnedQty(Number value) {
/* 414 */     setAttributeInternal(17, value);
/*     */   }
/*     */   
/*     */   public String getReasonCode() {
/* 419 */     return (String)getAttributeInternal(18);
/*     */   }
/*     */   
/*     */   public void setReasonCode(String value) {
/* 424 */     setAttributeInternal(18, value);
/*     */   }
/*     */   
/*     */   public Number getReplacementItemId() {
/* 429 */     return (Number)getAttributeInternal(19);
/*     */   }
/*     */   
/*     */   public void setReplacementItemId(Number value) {
/* 434 */     setAttributeInternal(19, value);
/*     */   }
/*     */   
/*     */   public Number getOrganizationId() {
/* 439 */     return (Number)getAttributeInternal(20);
/*     */   }
/*     */   
/*     */   public void setOrganizationId(Number value) {
/* 444 */     setAttributeInternal(20, value);
/*     */   }
/*     */   
/*     */   public String getItemdescription() {
/* 450 */     return (String)getAttributeInternal(22);
/*     */   }
/*     */   
/*     */   public void setItemdescription(String value) {
/* 455 */     setAttributeInternal(22, value);
/*     */   }
/*     */   
/*     */   public String getUom() {
/* 460 */     return (String)getAttributeInternal(23);
/*     */   }
/*     */   
/*     */   public void setUom(String value) {
/* 465 */     setAttributeInternal(23, value);
/*     */   }
/*     */   
/*     */   public String gettempForItemNo() {
/* 470 */     return (String)getAttributeInternal(24);
/*     */   }
/*     */   
/*     */   public void settempForItemNo(String value) {
/* 475 */     setAttributeInternal(24, value);
/*     */   }
/*     */   
/*     */   public String getReplacementItemForItem() {
/* 480 */     return (String)getAttributeInternal(25);
/*     */   }
/*     */   
/*     */   public void setReplacementItemForItem(String value) {
/* 485 */     setAttributeInternal(25, value);
/*     */   }
/*     */   
/*     */   public String gettempForSelection() {
/* 490 */     return (String)getAttributeInternal(26);
/*     */   }
/*     */   
/*     */   public void settempForSelection(String value) {
/* 495 */     setAttributeInternal(26, value);
/*     */   }
/*     */   
/*     */   public String gett1() {
/* 500 */     return (String)getAttributeInternal(27);
/*     */   }
/*     */   
/*     */   public void sett1(String value) {
/* 505 */     setAttributeInternal(27, value);
/*     */   }
/*     */   
/*     */   public String gett2() {
/* 510 */     return (String)getAttributeInternal(28);
/*     */   }
/*     */   
/*     */   public void sett2(String value) {
/* 515 */     setAttributeInternal(28, value);
/*     */   }
/*     */   
/*     */   public String getRenewalTempForSelection() {
/* 520 */     return (String)getAttributeInternal(29);
/*     */   }
/*     */   
/*     */   public void setRenewalTempForSelection(String value) {
/* 525 */     setAttributeInternal(29, value);
/*     */   }
/*     */   
/*     */   public String getECRTempForSelection() {
/* 530 */     return (String)getAttributeInternal(30);
/*     */   }
/*     */   
/*     */   public void setECRTempForSelection(String value) {
/* 535 */     setAttributeInternal(30, value);
/*     */   }
/*     */   
/*     */   public Number getReturnItemId() {
/* 543 */     return (Number)getAttributeInternal(19);
/*     */   }
/*     */   
/*     */   public void setReturnItemId(Number value) {
/* 550 */     setAttributeInternal(19, value);
/*     */   }
/*     */   
/*     */   public Number getOnHandQty() {
/* 557 */     return (Number)getAttributeInternal(21);
/*     */   }
/*     */   
/*     */   public void setOnHandQty(Number value) {
/* 564 */     setAttributeInternal(21, value);
/*     */   }
/*     */

    /**Gets the attribute value for the calculated attribute SelectedFlag
     */
    public String getSelectedFlag() {
        return (String) getAttributeInternal(SELECTEDFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SelectedFlag
     */
    public void setSelectedFlag(String value) {
        setAttributeInternal(SELECTEDFLAG, value);
    }
}


/* Location:              D:\Oracle\JDevR12.2.14\jdevhome\jdev\myprojects\!\xxkq\oracle\apps\xbol\v2\employee\server\RequisitionLineVORowImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */