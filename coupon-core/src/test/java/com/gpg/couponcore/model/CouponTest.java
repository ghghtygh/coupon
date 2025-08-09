package com.gpg.couponcore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("발급 수량이 남아 있다면 true를 반환한다.")
    void availableIssueQuantityTest() {
        Coupon coupon = Coupon.builder()
                .totalQuantity(3)
                .issuedQuantity(1)
                .build();

        boolean result = coupon.availableIssueQuantity();

        assertTrue(result);
    }

    @Test
    @DisplayName("발급 수량이 남아 있지 않다면 false를 반환한다.")
    void disableIssueQuantityTest() {
        Coupon coupon = Coupon.builder()
                .totalQuantity(3)
                .issuedQuantity(3)
                .build();

        boolean result = coupon.availableIssueQuantity();

        assertFalse(result);
    }

    @Test
    @DisplayName("발급 수량이 없다면 true를 반환한다.")
    void enableIssueQuantityTest() {
        Coupon coupon = Coupon.builder()
                .issuedQuantity(3)
                .build();

        boolean result = coupon.availableIssueQuantity();

        assertTrue(result);
    }

    @Test
    @DisplayName("발급 기간 내인 경우 true를 반환한다.")
    void availableIssueDate_success() {
        Coupon coupon = Coupon.builder()
                .issueStartDate(LocalDateTime.now().minusDays(1))
                .issueEndDate(LocalDateTime.now().plusDays(1))
                .build();

        boolean result = coupon.availableIssueDate();

        assertTrue(result);
    }

    @Test
    @DisplayName("발급 기간이 아닌 경우 false를 반환한다.")
    void availableIssueDate_fail() {
        Coupon coupon = Coupon.builder()
                .issueStartDate(LocalDateTime.now().minusDays(3))
                .issueEndDate(LocalDateTime.now().minusDays(2))
                .build();

        boolean result = coupon.availableIssueDate();

        assertFalse(result);
    }
}