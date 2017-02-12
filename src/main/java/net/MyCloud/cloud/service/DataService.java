package net.MyCloud.cloud.service;

import net.MyCloud.cloud.dao.DataDao;
import net.MyCloud.cloud.dao.UserDao;
import net.MyCloud.cloud.model.Data;
import net.MyCloud.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataService {

    @Autowired
    DataDao dataDao;

    @Autowired
    UserDao userDao;

    @Transactional
    public void addData(Data data) {
        dataDao.add(data);
    }

    @Transactional(readOnly=true)
    public List<Data> userDataList(Long user_id, String type) {
        return dataDao.userDataList(user_id, type);
    }

    @Transactional
    public void deleteData(long[] ids) {
        dataDao.delete(ids);
    }

    @Transactional(readOnly=true)
    public Data findByDataid(long id) {
        return dataDao.findByDataid(id);
    }

    @Transactional(readOnly=true)
    public List<Data> searchData(String pattern) {
      return dataDao.searchData(pattern);
    }
}
