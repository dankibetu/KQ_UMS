package xxkq.oracle.apps.xbol.v2.employee.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

public class RequisitionLineVOImpl extends OAViewObjectImpl {


    /**This is the default constructor (do not remove)
     */
    public RequisitionLineVOImpl() {
    }

    public void initQueryForRequisitionLineVO(String empId) {
    }


    public void setRemainingFeilds() {
        System.out.println(getName().getClass() + 
                           " =================== initQueryForRequisitionLineVO: START : setRemainingFeilds : ==========================");
        RequisitionHeaderVOImpl HeaderVO = 
            ((RequisitionAMImpl)getApplicationModule()).getRequisitionHeaderVO1();
        RequisitionHeaderVORowImpl row = 
            (RequisitionHeaderVORowImpl)HeaderVO.getCurrentRow();
        writeDiagnostics(this, 
                         getClass().getName() + ".setRemainingFeilds.Start     --> : ", 
                         2);
        if (row.getRequisitionType().startsWith("IR") || 
            row.getRequisitionType().startsWith("RR")) {
            row.setRequisitionStatus("Submitted");

        } else if (row.getRequisitionType().startsWith("ECR") || 
                   row.getRequisitionType().startsWith("RPR")) {
            row.setRequisitionStatus("In process");
            writeDiagnostics(this, 
                             getClass().getName() + ".setRequisitionStatus.set     --> : " + 
                             row.getRequisitionStatus(), 2);

        }
        Number UniformSetId = row.getUniformSetId();
        writeDiagnostics(this, 
                         getClass().getName() + ".UniformSetId --> : " + UniformSetId, 
                         2);
        Row[] rows = 
            getFilteredRows("SelectedFlag", "Y");
        //            RequisitionLineVORowImpl LineVORow = (RequisitionLineVORowImpl)first();
        for (int i = 0; i < rows.length; i++) {
            RequisitionLineVORowImpl LineVORow = (RequisitionLineVORowImpl)rows[i];
            LineVORow.setUniformsetid(UniformSetId);
            Number LineNum = LineVORow.getLineNumber();
            LineVORow.setLineNumber(LineNum);
            System.out.println(getName().getClass() + 
                               " initQueryForRequisitionLineVO.setRemainingFeilds.UniformSetId  = > : " + 
                               UniformSetId);
            System.out.println(getName().getClass() + 
                               " initQueryForRequisitionLineVO.setRemainingFeilds.LineNumber    = > : " + 
                               LineNum);
            System.out.println(getName().getClass() + 
                               " initQueryForRequisitionLineVO.setRemainingFeilds.ReqHeaderId   = > : " + 
                               row.getRequisitionHeaderId());
            writeDiagnostics(this, 
                             getClass().getName() + ".initQueryForRequisitionLineVO.getLineVORowUniformsetid      --> : " + 
                             String.valueOf(LineVORow.getUniformsetid()), 2);
            writeDiagnostics(this, 
                             getClass().getName() + ".initQueryForRequisitionLineVO.setReqHeaderId                --> : " + 
                             String.valueOf(row.getRequisitionHeaderId()), 2);
            LineVORow.setRequisitionHeaderId(row.getRequisitionHeaderId());
            System.out.println(getName().getClass() + 
                               " initQueryForRequisitionLineVO.setRemainingFeilds.ReqLineHeaderId   = > : " + 
                               LineVORow.getRequisitionHeaderId());
            writeDiagnostics(this, 
                             getClass().getName() + ".initQueryForRequisitionLineVO.getReqLineHeaderId                 --> : " + 
                             String.valueOf(row.getRequisitionHeaderId()), 2);

        }
        writeDiagnostics(this, 
                         getClass().getName() + ".setRemainingFeilds.End   --> : ", 
                         2);
        System.out.println(getName().getClass() + 
                           " =================== initQueryForRequisitionLineVO: END : setRemainingFeilds : ==========================");

    }

}


