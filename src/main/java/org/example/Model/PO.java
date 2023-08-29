package org.example.Model;

public class PO extends PR{
    int poId; boolean approvalStatus;

    public PO(){
        super();
    }
    public PO(int suppId, int itemId, int quantityRequired) {
        super(suppId, itemId, quantityRequired);
        this.poId = (int)(Math.random() * 1000000);
    }

    public int getPoId() {
        return poId;
    }

    public boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Override
    public String toString() {
        return "PO{" +
                "poId=" + poId +
                ", approvalStatus=" + approvalStatus +
                '}';
    }
}
