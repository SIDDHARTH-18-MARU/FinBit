package com.finbit.web.dto;

import java.util.List;

public record BillRequest(
        String date,            // ISO-8601 (yyyy-MM-dd)
        String restaurant,
        double total,
        List<Long> friendIds,
        List<BillItemRequest> items
) {}
