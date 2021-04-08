package com.xyc.service;

import com.xyc.pojo.OrderRecord;

import java.util.List;

public interface OrderRecordService {

    public List<OrderRecord> getRecordsBySellModel(String sellModel);

}
