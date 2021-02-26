package com.xyc.service.imp;

import com.xyc.dto.SlidShowDTO;
import com.xyc.mapper.SlidShowMapper;
import com.xyc.pojo.SlidShow;
import com.xyc.service.SlidShowService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class SlidShowServiceImp implements SlidShowService {

    @Autowired
    private SlidShowMapper slidShowMapper;

    @Transactional
    @Override
    public int add(SlidShowDTO showDTO) {

        SlidShow slidShow = new SlidShow();
        slidShow.setCgoodsId(showDTO.getCgoodsId());
        slidShow.setPic(PhotoUtils.BASE_PREFIX+PhotoUtils.SLIDSHOW_PREFIX
                +showDTO.getCgoodsId()+PhotoUtils.SUFFIX);

//        上传商品照片到服务器
        try {
            FTPConstants fc = new FTPConstants();
            fc.setFilename(PhotoUtils.SLIDSHOW_PREFIX+showDTO.getCgoodsId()+PhotoUtils.SUFFIX);
            File file = PhotoUtils.MultipartFileToFile(showDTO.getPic());
            fc.setInput(new FileInputStream(file));
            PhotoUtils.uploadFile(fc);
            System.out.println("轮播图照片已上传");
            //删除本地临时文件 C:\UserData\AppData\Local\Temp目录下
            PhotoUtils.deleteTempFile(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return slidShowMapper.add(slidShow);
    }

    @Transactional
    @Override
    public int delete(Integer cgoodsId) {
        //同时删除服务器上对应的轮播图
        try {
            FTPConstants fc = new FTPConstants();
            //删除原来的照片
            fc.setFilename(PhotoUtils.SLIDSHOW_PREFIX+cgoodsId+PhotoUtils.SUFFIX);
            PhotoUtils.deleteFile(fc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return slidShowMapper.delete(cgoodsId);
    }

    @Override
    public List<SlidShow> get() {
        List<SlidShow> list = slidShowMapper.get();
        return list;
    }
}
