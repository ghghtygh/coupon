package com.gpg.couponcore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "coupons")
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CouponType couponType;

    private Integer totalQuantity;

    @Column(nullable = false)
    private int issuedQuantity;

    @Column(nullable = false)
    private int discountAmount;

    @Column(nullable = false)
    private int minAvailableAmount;

    @Column(nullable = false)
    private LocalDateTime issueStartDate;

    @Column(nullable = false)
    private LocalDateTime issueEndDate;

    public boolean availableIssueQuantity() {
        if (totalQuantity == null) {
            return true;
        }

        return totalQuantity > issuedQuantity;
    }

    public boolean availableIssueDate() {
        LocalDateTime now = LocalDateTime.now();
        return issueStartDate.isBefore(now) && issueEndDate.isAfter(now);
    }

    public void issue() {
        if(availableIssueQuantity()) {
            throw new RuntimeException("수량 초과");
        }

        if(availableIssueDate()) {
            throw new RuntimeException("기간 초과");
        }

        this.issuedQuantity += 1;
    }

}
