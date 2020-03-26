package cn.marssky.dtfweb.service.impl;

import cn.marssky.common.api.ResultCode;
import cn.marssky.dtfweb.dao.ThreequestionDao;
import cn.marssky.dtfweb.entities.Question;
import cn.marssky.dtfweb.entities.QuestionResult;
import cn.marssky.dtfweb.service.ThreequestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThreequestionServiceImpl implements ThreequestionService {
    @Resource
    private ThreequestionDao threequestionDao;
    @Autowired
    private QuestionResult questionResult;


    @Override
    public QuestionResult countByavatarType(String avatarType) {
       int num =  threequestionDao.countByavatarType(avatarType);
       if(num!=0){
           questionResult.setCode(ResultCode.SUCCESS.getCode());
           questionResult.setMessage(ResultCode.SUCCESS.getMessage());
           questionResult.setData(num);
       }else{
           questionResult.setCode(ResultCode.NOT_FOUND.getCode());
           questionResult.setMessage(ResultCode.NOT_FOUND.getMessage());
           questionResult.setData(num);
       }
        return questionResult;
    }

    @Override
    public QuestionResult findByavatarType(String avatarType) {
        List<Question> questions= threequestionDao.findByavatarType(avatarType);
        if(questions.size()>0){
            questionResult.setCode(ResultCode.SUCCESS.getCode());
            questionResult.setMessage(ResultCode.SUCCESS.getMessage());
            questionResult.setData(questions);
        }else{
            questionResult.setCode(ResultCode.NOT_FOUND.getCode());
            questionResult.setMessage(ResultCode.NOT_FOUND.getMessage());
            questionResult.setData(questions);
        }

        return questionResult;
    }

    @Override
    public QuestionResult findById(Long id) {
        Question question = threequestionDao.findById(id);
        if(question!=null){
            questionResult.setCode(ResultCode.SUCCESS.getCode());
            questionResult.setMessage(ResultCode.SUCCESS.getMessage());
            questionResult.setData(question);
        }else{
            questionResult.setCode(ResultCode.NOT_FOUND.getCode());
            questionResult.setMessage(ResultCode.NOT_FOUND.getMessage());
            questionResult.setData(question);
        }
        return questionResult;
    }

    @Override
    public QuestionResult GroupByAvatarType() {
      List<String> types =   threequestionDao.GroupByAvatarType();
        if(types.size()>0){
            questionResult.setCode(ResultCode.SUCCESS.getCode());
            questionResult.setMessage(ResultCode.SUCCESS.getMessage());
            questionResult.setData(types);
        }else{
            questionResult.setCode(ResultCode.NOT_FOUND.getCode());
            questionResult.setMessage(ResultCode.NOT_FOUND.getMessage());
            questionResult.setData(types);
        }
        return questionResult;
    }
}
