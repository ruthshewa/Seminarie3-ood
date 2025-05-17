package se.kth.iv1350.seminarie3.model;
import se.kth.iv1350.seminarie3.model.ItemDTO;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {
    private final List<ItemDTO> saleList;
    private final double totalPrice;
    private final double totalVat;
    private final double runningTotal;
    private final LocalDateTime startedAt;

    public SaleDTO(List<ItemDTO> saleList, double totalPrice, double totalVat, double runningTotal, LocalDateTime startedAt) {
        this.saleList = saleList;
        this.totalPrice = totalPrice;
        this.totalVat = totalVat;
        this.runningTotal = runningTotal;
        this.startedAt = startedAt;
    }

    public List<ItemDTO> getSaleList() {
        return saleList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public double getRunningTotal(){
        return runningTotal;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }
}
