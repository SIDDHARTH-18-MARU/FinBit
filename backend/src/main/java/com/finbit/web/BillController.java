package com.finbit.web;

import com.finbit.domain.Bill;
import com.finbit.domain.BillItem;
import com.finbit.domain.Friend;
import com.finbit.repo.BillRepository;
import com.finbit.repo.FriendRepository;
import com.finbit.web.dto.BillRequest;
import com.finbit.web.dto.BillItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillRepository bills;
    private final FriendRepository friends;

    public BillController(BillRepository bills, FriendRepository friends) {
        this.bills = bills;
        this.friends = friends;
    }

    @GetMapping
    public List<Bill> list() {
        return bills.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BillRequest req) {
        Bill bill = new Bill();
        bill.setRestaurant(req.restaurant());
        bill.setTotal(req.total());
        bill.setDate(req.date() != null ? LocalDate.parse(req.date()) : LocalDate.now());

        // Attach friends
        List<Friend> billFriends = new ArrayList<>();
        if (req.friendIds() != null) {
          for (Long id : req.friendIds()) {
              friends.findById(id).ifPresent(billFriends::add);
          }
        }
        bill.setFriends(billFriends);

        // Attach items
        List<BillItem> items = new ArrayList<>();
        if (req.items() != null) {
            for (BillItemRequest ir : req.items()) {
                BillItem item = new BillItem();
                item.setName(ir.name());
                item.setPrice(ir.price());
                item.setQuantity(ir.quantity());
                item.setBill(bill);
                items.add(item);
            }
        }
        bill.setItems(items);

        Bill saved = bills.save(bill);
        return ResponseEntity.ok(saved);
    }
}
