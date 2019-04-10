package com.xigua.server;

import com.xigua.vo.urlVo;

import java.util.List;

public interface UrlServer {
    public String getRandomStr(String code,int length,String context);
    public urlVo addShortURL(String url,String code,int length,String context);
    public void incTimes(String shortUrl);
    public List<urlVo> queryAll();
    public urlVo queryByshortUrl(String shortUrl);
    public void insertUrl(urlVo urlVo);
    public urlVo queryBylongUrl(String shortUrl);
}
