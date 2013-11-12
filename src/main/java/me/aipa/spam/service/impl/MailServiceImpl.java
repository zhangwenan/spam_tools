package me.aipa.spam.service.impl;

import com.alibaba.fastjson.JSONArray;
import me.aipa.spam.bean.MailContentDO;
import me.aipa.spam.bean.MailSendFromDO;
import me.aipa.spam.bean.MailSendHistoryDO;
import me.aipa.spam.bean.MailSendToDO;
import me.aipa.spam.service.MailService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午7:16
 * To change this template use File | Settings | File Templates.
 */
public class MailServiceImpl  implements MailService{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<MailSendToDO> getMailSendToList(int start,int num) throws Exception {
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("start",start) ;
        map.put("num",num) ;
        return sqlSession.selectList("MailSendToMapper.getMailSendToList",map);
    }
    @Override
    public int getMailSendToCount() throws Exception {
        return sqlSession.selectOne("MailSendToMapper.getMailSendToCount");
    }
    @Override
    public List<MailSendToDO>getMailSendToListRand(int num) throws Exception{
        return sqlSession.selectList("MailSendToMapper.getMailSendToListRand",num);
    }
    @Override
    public int insertMailSendTo(String address) throws Exception{
        if(checkMailSendToAddressRepeat(address)==0){
            return sqlSession.insert("MailSendToMapper.insertMailSendTo",address);
        }else{
            return 0;
        }
    }
    @Override
    public  int checkMailSendToAddressRepeat(String address) throws  Exception{
        return sqlSession.selectOne("MailSendToMapper.checkMailSendToAddressRepeat",address);
    }
    @Override
    public  List<MailSendToDO> getMailSendToByAddress(String address) throws Exception{
        return  sqlSession.selectList("MailSendToMapper.getMailSendToByAddress",address);
    }
    @Override
    public List<MailSendFromDO> getMailSendFromList(int start,int num) throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("start",start) ;
        map.put("num",num) ;
        return sqlSession.selectList("MailSendFromMapper.getMailSendFromList", map);
    }
    @Override
    public int getMailSendFromCount() throws Exception{
        return sqlSession.selectOne("MailSendFromMapper.getMailSendFromCount");
    }
    @Override
    public List<MailSendFromDO> getMailSendFromListRand(int num) throws Exception{
        return sqlSession.selectList("MailSendFromMapper.getMailSendFromListRand",num);
    }
    @Override
    public List<MailSendFromDO> getMailSendFromByAddress(String address) throws Exception{
        return sqlSession.selectList("MailSendFromMapper.getMailSendFromByAddress", address);
    }
    @Override
    public int checkMailSendFromAddressRepeat(String address) throws Exception{
        return sqlSession.selectOne("MailSendFromMapper.checkMailSendFromAddressRepeat", address);
    }
    @Override
    public int insertMailSendFrom(MailSendFromDO mailSendFromDO) throws Exception{
        if(checkMailSendFromAddressRepeat(mailSendFromDO.getAddress())==0){
            return  sqlSession.insert("MailSendFromMapper.insertMailSendFrom",mailSendFromDO);
        }else{
            return 0;
        }
    }
    @Override
    public  int updateMailSendFromById(MailSendFromDO mailSendFromDO) throws Exception{
        if(checkMailSendFromAddressRepeat(mailSendFromDO.getAddress())==1){
             return sqlSession.update("MailSendFromMapper.updateMailSendFrom",mailSendFromDO);
        }else{
            return 0;
        }
    }
    @Override
    public List<MailSendHistoryDO>getMailSendHistoryList(int num) throws  Exception{
        return sqlSession.selectOne("MailSendHistoryMapper.getMailSendHistoryList",num);
    }
    @Override
    public List<MailSendHistoryDO>getMailSendHistoryByState(int state,int num) throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("state",state) ;
        map.put("num",num) ;
        return sqlSession.selectList("MailSendHistoryMapper.getMailSendHistoryByState",map);
    }
     @Override
    public List<MailContentDO>getContentList(int num) throws Exception{
         return sqlSession.selectList("MailContentMapper.getContentList",num);
     }
    @Override
    public int insertMailContent(MailContentDO mailContentDO) throws  Exception{
        return sqlSession.insert("MailContentMapper.insertMailContent",mailContentDO);
    }
    @Override
    public int updateMailContentById(MailContentDO mailContentDO) throws  Exception{
        return sqlSession.update("MailContentMapper.updateMailContentById",mailContentDO);
    }
    @Override
    public int deleteMailContentById(int id) throws  Exception{
        return sqlSession.update("MailContentMapper.deleteMailContentById",id) ;
    }
    @Override
     public int delMailSendFromById(int id) throws  Exception{
        return sqlSession.update("MailSendFromMapper.delMailSendFromById",id);
     }
    @Override
    public int delMailSendToById(int id) throws Exception{
        return sqlSession.update("MailSendToMapper.delMailSendToById",id);
    }
    @Override
    public  int updateMailSendToById(MailSendToDO mailSendToDO) throws Exception{
      return sqlSession.update("MailSendToMapper.updateMailSendToById",mailSendToDO);
    }
}
