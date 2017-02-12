package net.MyCloud.cloud.dao;

import net.MyCloud.cloud.model.Data;
import net.MyCloud.cloud.model.User;

import java.util.List;

public interface DataDao {
    void add(Data data);
    void delete(long[] ids);
    Data findByDataid(long id);
    List<Data> userDataList(Long user_id, String type);
    List<Data> searchData(String pattern);
}
