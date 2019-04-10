package com.xigua.server.impl;

import com.xigua.dao.urlDao;
import com.xigua.server.UrlServer;
import com.xigua.vo.urlVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

@Service
public class UrlServerImpl implements UrlServer {
    @Resource
    private urlDao urlsDao;

    /**
     * 获取随机短网址
     * @param code
     * @param length
     * @return
     */
    public String getRandomStr(String code, int length,String context) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(context.length());
            sb.append(context.charAt(number));
        }
        String rtnURL = null;
        try {
            rtnURL = new String(sb.toString().getBytes(), code);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtnURL;
    }

    /**
     * 加上短网址到数据库 并返回短网址
     * @param url
     * @param code
     * @param length
     * @return
     */
    public urlVo addShortURL(String url, String code, int length,String context) {
        urlVo longUrl = urlsDao.queryBylongUrl(url);
        if (null == longUrl) {
            String shortUrl = getRandomStr(code, length,context);
            longUrl=urlsDao.queryByshortUrl(shortUrl);
            int count=0;
            while (longUrl!=null){
                shortUrl = getRandomStr(code, length,context);
                longUrl=urlsDao.queryByshortUrl(shortUrl);
                count++;
                if(count>100){
                    break;
                }
            }
            longUrl = new urlVo();
            longUrl.setLongUrl(url);
            longUrl.setShortUrl(shortUrl);
            longUrl.setTimes(0);
            urlsDao.insertUrl(longUrl);
            return longUrl;
        } else {
            return longUrl;
        }


    }

    /**
     * 增加访问次数
     * @param shortUrl
     */
    @Override
    public void incTimes(String shortUrl) {
        urlVo url = urlsDao.queryByshortUrl(shortUrl);
        int times = url.getTimes();
        url.setTimes(++times);
        urlsDao.update(url);
    }

    /**
     * 查询所有短网址信息
     * @return
     */
    @Override
    public List<urlVo> queryAll() {
        return urlsDao.queryAll();
    }

    @Override
    public urlVo queryByshortUrl(String shortUrl) {
        return urlsDao.queryByshortUrl(shortUrl);
    }

    @Override
    public void insertUrl(urlVo urlVo) {
        urlsDao.insertUrl(urlVo);
    }

    @Override
    public urlVo queryBylongUrl(String Url) {
        return urlsDao.queryBylongUrl(Url);
    }
}
