package cn.marssky.video.service.impl;

import cn.marssky.video.dto.TwoQuestionBanksDto;
import cn.marssky.video.dao.TwoQuestionBanksDao;
import cn.marssky.video.model.TwoQuestionBanks;
import cn.marssky.video.service.TwoQuestionBanksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 科目二题库(TwoQuestionBanks)表服务实现类
 *
 * @author makejava
 * @since 2020-03-25 16:33:01
 */
@Service
public class TwoQuestionBanksServiceImpl implements TwoQuestionBanksService {
    @Autowired
    private TwoQuestionBanksDao twoQuestionBanksDao;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<TwoQuestionBanksDto> queryAll() {
        List<TwoQuestionBanks> dmos = this.twoQuestionBanksDao.queryAll();
        List<TwoQuestionBanksDto> dtos = new ArrayList<>();
        for (TwoQuestionBanks dmo:dmos
             ) {
            dtos.add(this.convertToDto(dmo));
        }
        return dtos;
    }

    //dto和dmo互相转换
    public TwoQuestionBanks convertToDmo(TwoQuestionBanksDto dto){
        TwoQuestionBanks map = modelMapper.map(dto, TwoQuestionBanks.class);
        return map;
    }
    public TwoQuestionBanksDto convertToDto(TwoQuestionBanks dmo){
        TwoQuestionBanksDto map = modelMapper.map(dmo, TwoQuestionBanksDto.class);
        return map;
    }
}