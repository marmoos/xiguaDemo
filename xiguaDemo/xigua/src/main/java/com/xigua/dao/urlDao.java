package com.xigua.dao;

import com.xigua.vo.urlVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface urlDao {
    public void insertUrl(urlVo urlVo);
    public void update(urlVo urlVo);
    public urlVo queryByshortUrl(String shortUrl);
    public urlVo queryBylongUrl(String shortUrl);
    public List<urlVo> queryAll();
}
