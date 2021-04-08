package com.xyc.service.imp;

import com.xyc.mapper.OrderRecordMapper;
import com.xyc.pojo.OrderRecord;
import com.xyc.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRecordServiceImp implements OrderRecordService {

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Override
    public List<OrderRecord> getRecordsBySellModel(String sellModel) {
        if (sellModel.equals("以租代售")){
            return orderRecordMapper.queryBySellModel(1);
        }else if (sellModel.equals("先租后买")){
            return orderRecordMapper.queryBySellModel(2);
        }else {
            return orderRecordMapper.queryBySellModel(4);
        }
    }
}
