package com.finbit.web.dto;

public record BillItemRequest(
        String name,
        double price,
        int quantity
) {}
